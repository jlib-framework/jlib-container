/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    PropertyNotSetException.java
 * Project: jlib.core
 * 
 * Copyright (c) 2006 by Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.eclipse.org/legal/cpl-v10.html
 */

package org.jlib.core.system;

/**
 * Exception thrown when trying to retreive the value of a SystemUtility property that is not set.
 * 
 * @author Igor Akkerman
 */
public class PropertyNotSetException
extends RuntimeException {

    /** name of the property that is not set */
    private String propertyName;

    /** no default constructor */
    private PropertyNotSetException() {}

    /**
     * Creates a new PropertyNotSetException.
     * 
     * @param propertyName
     *        String specifying the name of the property that is not set
     */
    public PropertyNotSetException(String propertyName) {
        super();
        this.propertyName = propertyName;
    }

    /**
     * Returns the name of the property that is not set.
     * 
     * @return String specifying the name of the property that is not set
     */
    public String getPropertyName() {
        return propertyName;
    }

    // @see java.lang.Throwable#toString()
    @Override
    public String toString() {
        return getClass().getName() + "[" + propertyName + "]";
    }
}
