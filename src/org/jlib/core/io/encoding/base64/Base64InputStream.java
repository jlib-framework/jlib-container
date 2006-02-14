/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    Base64InputStream.java
 * Project: jlib.core
 * 
 * Copyright (c) 2006 Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.io.encoding.base64;

import static org.jlib.core.io.encoding.base64.Base64Utility.mapBase64Character;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * FilterInputStream performing a base64 decoding of a source InputStream.
 *
 * @author Igor Akkerman
 */

public class Base64InputStream
extends FilterInputStream {

    /** padding byte */
    private static final byte PAD = -1;
    
    /** input buffer */
    private int[] inputBuffer = new int[4];

    /** output buffer */
    private int[] outputBuffer = new int[3];

    /** size of the output buffer */
    private int outputBufferSize = 0;

    /** current position in the output buffer */
    private int outputBufferPosition = 0;

    /** no default constructor */
    private Base64InputStream() {
        super(null);
    }

    /**
     * Creates a new filter input stream decoding the specified base64 input
     * stream.
     *
     * @param sourceInputStream
     *        input stream to be decoded
     */
    public Base64InputStream(InputStream sourceInputStream) {
        super(sourceInputStream);
    }

    // documented in super class
    public int read()
    throws IOException {
        do {
            try {
                if (outputBufferPosition == outputBufferSize) {
                    readBase64Block();
                    decodeBase64Block();
                    outputBufferPosition = 0;
                }

                return outputBuffer[outputBufferPosition++];
            }
            catch (EndOfBase64StreamException eob64se) {
                return -1;
            }
            catch (IllegalBase64BlockPadException ib64bpe) {
                // illegal block is ignored
            }
        }
        while (true);
    }

    // documented in super class
    public int read(byte[] buffer, int offset, int length)
    throws IOException {
        for (int i = 0; i < length; i++) {
            buffer[i] = (byte) read();
            if (buffer[i] == -1) {
                return i != 0 ? i : -1;
            }
        }
        return length;
    }

    /**
     * Reads a block of four base64 characters writing their values into the
     * input buffer.
     *
     * @throws EndOfBase64StreamException
     *         if the end of the base64 stream was reached before the next block;
     *         this exception does not signal an error
     * @throws UnexpectedEndOfBase64StreamException
     *         if the base64 block was not completed at the end of the base64
     *         stream
     * @throws IOException
     *         if an I/O error occurs
     */
    private void readBase64Block()
    throws EndOfBase64StreamException, UnexpectedEndOfBase64StreamException, IOException {
        int readChar;
        int charValue;
        boolean illegalCharRead;

        for (int i = 0; i <= 3; i++) {
            do {
                try {
                    readChar = in.read();

                    // end of stream handling
                    if (readChar == -1) {
                        if (i == 0) {
                            throw new EndOfBase64StreamException();
                        }
                        else {
                            throw new UnexpectedEndOfBase64StreamException();
                        }
                    }

                    charValue = mapBase64Character(readChar);
                    inputBuffer[i] = charValue;
                    illegalCharRead = false;
                }
                catch (IllegalBase64CharacterException ib64ce) {
                    illegalCharRead = true;
                }
            }
            while (illegalCharRead);
        }
    }

    /**
     * Decodes the currently read base64 block into the output buffer.
     *
     * @throws IllegalBase64BlockPadException
     *         if the read base64 block contains illegal padding characters
     */
    private void decodeBase64Block()
    throws IllegalBase64BlockPadException {

        // get number of padding characters and clear them in the input buffer
        int numPads = getNumPaddingCharacters();
        clearPaddingCharacters();

        outputBuffer[0] = inputBuffer[0] << 2 | (inputBuffer[1] & 0x30) >> 4;
        outputBuffer[1] = (inputBuffer[1] & 0x0F) << 4 | (inputBuffer[2] & 0x3C) >> 2;
        outputBuffer[2] = (inputBuffer[2] & 0x03) << 6 | inputBuffer[3];

        outputBufferSize = 3 - numPads;
    }

    /**
     * Returns the number of padding characters in the currently read base64 block.
     *
     * @return integer specifying the number of padding characters
     *
     * @throws IllegalBase64BlockPadException
     *         if the read base64 block contains illegal padding characters
     */
    private int getNumPaddingCharacters()
    throws IllegalBase64BlockPadException {
        boolean[] pad = new boolean[4];
        for (int i = 0; i <= 3; i ++)
            pad[i] = (inputBuffer[i] == PAD);
        
        if (!pad[0] && !pad[1] && !pad[2] && !pad[3])
            return 0;
        else if (!pad[0] && !pad[1] && !pad[2] && pad[3] && (inputBuffer[2] & 0x03) == 0)
            return 1;
        else if (!pad[0] && !pad[1] && pad[2] && pad[3] && (inputBuffer[1] & 0x0F) == 0)
            return 2;
        else
            throw new IllegalBase64BlockPadException(inputBuffer);
    }

    /**
     * Clears all padding characters in the input buffer. Their positions are
     * set to 0.
     */
    private void clearPaddingCharacters() {
        if (inputBuffer[2] == PAD) {
            inputBuffer[2] = 0;
        }
        if (inputBuffer[3] == PAD) {
            inputBuffer[3] = 0;
        }
    }

    // documented in super class
    public int available() {
        return 4 - outputBufferPosition;
    }
}
