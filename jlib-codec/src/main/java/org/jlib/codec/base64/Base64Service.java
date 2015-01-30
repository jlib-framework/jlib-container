/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2015 Igor Akkerman
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jlib.codec.base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.jlib.core.io.StreamException;

import static java.lang.System.arraycopy;
import org.jlib.codec.CodecService;

public class Base64Service
implements CodecService {

    /**
     * Encodes the specified byte array using base64 encoding.
     *
     * @param inputBytes
     *        array of bytes containing the bytes to encode
     *
     * @return String containing the base64 encoded output of {@code inputBytes}
     *
     * @throws StreamException
     *         if an i/o exception occurs
     */
    @Override
    public String encode(final byte[] inputBytes)
    throws StreamException {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final OutputStream base64OutputStream = new Base64OutputStream(outputStream);

            base64OutputStream.write(inputBytes);
            base64OutputStream.flush();
            base64OutputStream.close();

            return outputStream.toString();
        }
        catch (final IOException exception) {
            throw new StreamException(exception);
        }
    }

    /**
     * Decodes the specified String using base64 decoding.
     *
     * @param encodedString
     *        base64 encodedString String
     *
     * @return array of decoded bytes from {@code encodedString}
     *
     * @throws StreamException
     *         if an i/o exception occurs
     */
    @Override
    public byte[] decode(final String encodedString)
    throws StreamException {
        try {
            final InputStream inputStream = new ByteArrayInputStream(encodedString.getBytes());
            final InputStream base64InputStream = new Base64InputStream(inputStream);

            final byte[] readBytes = new byte[1024];

            int length = base64InputStream.read(readBytes);

            if (length == - 1)
                length = 0;

            base64InputStream.close();

            final byte[] outputBytes = new byte[length];
            arraycopy(readBytes, 0, outputBytes, 0, length);

            return outputBytes;
        }
        catch (final IOException exception) {
            throw new StreamException(exception);
        }
    }
}
