/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * File:    Wrapper.java
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

package org.jlib.core.system;

/**
 * <p>
 * Wrapper of any kind of Objects allowing a quasi call-by-reference semantic.
 * </p>
 * <p>
 * For example, a method could be used to convert a specified wrapped String to lower case:
 * </p>
 * 
 * <pre>
 * {@literal
 * private static void toLowerCase(Wrapper<String> wrappedString)
 * throws NullPointerException {
 *     wrappedString.set(wrappedString.get().toLowerCase());
 * }}
 * </pre>
 * 
 * <p>
 * The modified String can then be unwrapped:
 * </p>
 * 
 * <pre>
 * {@literal
 * String big = "HelLO!";
 * Wrapper<String> wrapped = wrap(big);  // Wrapper.wrap by static import
 * toLowerCase(wrapped);
 * String small = wrapped.get();
 * System.out.println(big + " >= " + wrapped + " = " + small);}
 * </pre>
 * 
 * This code prints
 * 
 * <pre>
 * {@literal
 * HelLO! >= hello! = hello!}
 * </pre>
 * 
 * <p>
 * Note that the {@code toString()} method of this class returns the {@code toString()} result from
 * the wrapped Object.
 * </p>
 * 
 * @param <Obj>
 *        type of the Object to wrap
 * @author Igor Akkerman
 */
public class Wrapper<Obj> {

    /**
     * Creates a new Wrapper for the specified Object.
     * 
     * @param <Obj>
     *        type of the Object to wrap
     * @param wrappedObject
     *        Object to wrap
     * @return Wrapper wrapping {@code wrappedObject}
     */
    public static <Obj> Wrapper<Obj> wrap(Obj wrappedObject) {
        return new Wrapper<Obj>(wrappedObject);
    }

    /** Object wrapped by this Wrapper */
    private Obj wrappedObject;

    /** no default constructor */
    private Wrapper() {}

    /**
     * Creates a new Wrapper of the specified Type.
     * 
     * @param wrappedObject
     *        Object wrapped by this Wrapper
     */
    private Wrapper(Obj wrappedObject) {
        super();
        this.wrappedObject = wrappedObject;
    }

    /**
     * Returns the Object wrapped by this Wrapper.
     * 
     * @return wrapped Object
     */
    public final Obj get() {
        return wrappedObject;
    }

    /**
     * Registers the Object wrapped by this Wrapper.
     * 
     * @param wrappedObject
     *        wrapped Object
     */
    public final void set(Obj wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    /**
     * Returns whether the Object wrapped by the specified Wrapper is equal to the Object wrapped by
     * this wrapper.
     * 
     * @param obj
     *        other Wrapper
     * @return {@code true} if the object wrapped by the specified Wrapper is equal to the Object
     *         wrapped by this wrapper or if both wrapped Objects are {@code null}; {@code false}
     *         otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Wrapper))
            return false;

        Object otherWrappedObject = ((Wrapper) obj).get();

        return wrappedObject != null ? wrappedObject.equals(otherWrappedObject) : otherWrappedObject == null;
    }

    /**
     * Returns the String representation of the wrapped Object.
     * 
     * @return String representation of the wrapped Object
     */
    @Override
    public String toString() {
        return wrappedObject.toString();
    }
}
