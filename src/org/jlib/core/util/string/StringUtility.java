/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    StringUtility.java
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

package org.jlib.core.util.string;

/**
 * Utility class providing static methods for String operations and manipulations.
 * 
 * @author Igor Akkerman
 */
public class StringUtility {

    /** String padding options */
    public enum PaddingType {
        /** front padding */
        front,

        /** back padding */
        back,

        /**
         * equal front and back padding. If the number of characters to pad is odd, the front will
         * be padded with one character more than the back
         */
        frontback,

        /**
         * equal front and back padding. If the number of characters to pad is odd, the back will be
         * padded with one character more than the front
         */
        backfront
    }

    /** no default constructor */
    private StringUtility() {};

    /**
     * Pads the specified String with the blank character at the back to the specified length.
     * 
     * @param string
     *        String to pad
     * @param length
     *        integer specifying the desired length of the String
     * @return padded String. If {@code string.length() >= length} then {@code string} is returned.
     */
    public static String pad(String string, int length) {
        return pad(string, length, ' ', PaddingType.back);
    }

    /**
     * Pads the specified String with the blank character using the specified PaddingType to the
     * specified length.
     * 
     * @param string
     *        String to pad
     * @param length
     *        integer specifying the desired length of the String
     * @param paddingType
     *        PaddingType specifying how the string should be padded
     * @return padded String. If {@code string.length() >= length} then {@code string} is returned.
     */
    public static String pad(String string, int length, PaddingType paddingType) {
        return pad(string, length, ' ', paddingType);
    }

    /**
     * Pads the specified String with the specified character using the specified PaddingType to the
     * specified length.
     * 
     * @param string
     *        String to pad
     * @param length
     *        integer specifying the desired length of the String
     * @param character
     *        character used for padding
     * @param paddingType
     *        PaddingType specifying how the string should be padded
     * @return padded String. If {@code string.length() >= length} then {@code string} is returned.
     */
    public static String pad(String string, int length, char character, PaddingType paddingType) {
        int halfLength;

        int currentLength = string.length();

        if (currentLength >= length)
            return string;

        StringBuffer stringBuffer = new StringBuffer(length);

        switch (paddingType) {
            case front:
                for (; currentLength < length; currentLength ++)
                    stringBuffer.append(character);
                stringBuffer.append(string);
                break;
            case back:
                stringBuffer.append(string);
                for (; currentLength < length; currentLength ++)
                    stringBuffer.append(character);
                break;
            case frontback:
                halfLength = (int) (currentLength + length + 1) / 2;
                for (; currentLength < halfLength; currentLength ++)
                    stringBuffer.append(character);
                stringBuffer.append(string);
                for (; currentLength < length; currentLength ++)
                    stringBuffer.append(character);
                break;
            case backfront:
                halfLength = (int) (currentLength + length) / 2;
                for (; currentLength < halfLength; currentLength ++)
                    stringBuffer.append(character);
                stringBuffer.append(string);
                for (; currentLength < length; currentLength ++)
                    stringBuffer.append(character);
        }
        return stringBuffer.toString();
    }

}
