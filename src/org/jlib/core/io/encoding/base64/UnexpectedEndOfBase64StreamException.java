/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    UnexpectedEndOfBase64StreamException.java
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


/**
 * Exception thrown if at least one character was expected in a base64 encoded stream.
 * 
 * @author Igor Akkerman
 */
public class UnexpectedEndOfBase64StreamException
extends InvalidBase64StreamException {

    /**
     * Creates a new UnexpectedEndOfBase64StreamException.
     */
    protected UnexpectedEndOfBase64StreamException() {
        super();
    }
}
