/*
 * jlib - The Free Java Library
 * 
 * http://www.jlib.org
 * 
 * Copyright (c) 2006-2008 Igor Akkerman
 * 
 * jlib is distributed under the
 * 
 * COMMON PUBLIC LICENSE VERSION 1.0
 * 
 * http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.container.sequence.index.array;

import java.util.Arrays;

import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.index.IndexSequenceCreator;
import org.jlib.container.sequence.index.InitializeableIndexSequence;
import org.jlib.container.sequence.index.InvalidSequenceIndexRangeException;

// @formatter:off   
/**
 * <p>
 * Fixed sized array. Replacement for the standard Java arrays with special
 * features.
 * </p>
 * <p>
 * The only syntactical difference to Java arrays lies in the notation for
 * retrieving and providing values and retrieving the array size:
 * </p>
 * 
 * <pre>
 * {@literal
 * // good(?) old Java array                      // cool(!) new jlib ArraySequence class
 * String[] stringArray = new String[10];         ArraySequence<String> stringArray = new ArraySequence<String>(10);
 * stringArray[4] = "good(?) old Java array";     stringArray.set(4, "cool(!) new jlib ArraySequence class");
 * String s = stringArray[4];                     String s = stringArray.get(4);
 * int size = stringArray.length;                 int size = stringArray.size(); }
 * </pre>
 * 
 * <p>
 * Special features:
 * </p>
 * <ul>
 * <li>Minimum and maximum index: <br/>
 * On instantiation, you can specify the first and the maximum index of the
 * ArraySequence. Thus, no offset is necessary for Arrays starting at other indices than
 * 0. The following example illustrates how an ArraySequence is filled with numbers from
 * 1 to 10:
 * 
 * <pre>
 * // good(?) old Java array                      // cool(!) new jlib ArraySequence class
 * Integer[] integerArray = new Integer[10];      ArraySequence&lt;Integer&gt; integerArray = new ArraySequence&lt;Integer&gt;(1, 10);
 * for (int i = 1; i <= 10; i ++)                 for (int i = 1; i <= 10; i ++)
 *     integerArray[i - 1] = i;                       integerArray.set(i, i);
 * </pre>
 * 
 * </li>
 * 
 * <li>Conformance to the Collections framework <br/>
 * The class implements the {@code Collection} interface and thus
 * behaves like all Collections.</li>
 * <br />
 * <li>Full support for generics:<br/>
 * The Java arrays do not support generic classes. For example, you cannot
 * create an array of String sequences:
 * 
 * <pre>
 * {@literal
 * // FORBIDDEN!
 * Sequence<String>[] stringSequenceArray = new Sequence<String>[10];
 *
 * // PERMITTED!
 * ArraySequence<Sequence<String>> stringSequenceArray = new ArraySequence<Sequence<String>>(10);}
 * </pre>
 * 
 * </li>
 * <li>
 * Easy to create:<br />
 * 
 * <pre>
 * {@literal
 * // creating an ArraySequence with three Strings
 * ArraySequence<String> stringArray = new ArraySequence<String>("cool", "ArraySequence", "class!");
 *
 * // creating an ArraySequence with three Strings starting at index 1
 * ArraySequence<String> stringArray = new ArraySequence<String>(1, "jlib", "is", "cool!");}
 * </pre>
 * 
 * To create Arrays of Integers. The Java
 * autoboxing feature forbids the following ArraySequence creation:
 * 
 * <pre>
 * {@literal
 * // FORBIDDEN!
 * ArraySequence<Integer> integerArray = new ArraySequence<Integer>(1, 2, 3, 4, 5, 6);}
 * </pre>
 * 
 * The compiler claims:
 * 
 * <pre>
 * {@literal The constructor ArraySequence<Integer>(Integer[]) is ambiguous}
 * </pre>
 * 
 * </li>
 * </ul>
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 *        
 * @author Igor Akkerman
 */
// @formatter:on

public class ArraySequence<Item>
extends InitializeableIndexSequence<Item>
implements Cloneable {

    /** {@link IndexSequenceCreator} of {@link ArraySequence} insstances */
    private static final IndexSequenceCreator<?, ? extends ArraySequence<?>> CREATOR =
        new IndexSequenceCreator<Object, ArraySequence<Object>>() {

            @Override
            public ArraySequence<Object> createSequence(final int firstIndex, final int lastIndex)
            throws InvalidSequenceIndexRangeException {
                return new ArraySequence<Object>(firstIndex, lastIndex);
            }
        };

    /**
     * Returns the {@link IndexSequenceCreator} of {@link ArraySequence}
     * instances.
     * 
     * @return {@link IndexSequenceCreator} of {@link ArraySequence} instances
     */
    @SuppressWarnings("unchecked")
    public static <Item> IndexSequenceCreator<Item, ? extends ArraySequence<Item>> getCreator() {
        return (IndexSequenceCreator<Item, ArraySequence<Item>>) CREATOR;
    }

    /** array holding the Items of this {@link ArraySequence} */
    private Item[] delegateArray;

    /**
     * Creates a new {@link ArraySequence} with the specified minimum and
     * maximum indices initialized with {@code null} values.
     * 
     * @param firstIndex
     *        integer specifying the minimum index of this {@link ArraySequence}
     * 
     * @param lastIndex
     *        integer specifying the maximum index of this {@link ArraySequence}
     */
    @SuppressWarnings("unchecked")
    protected ArraySequence(final int firstIndex, final int lastIndex) {
        super(firstIndex, lastIndex);

        final int size = getSize();

        delegateArray = (Item[]) new Object[size];
    }

    /**
     * Returns the Item stored at the specified index expecting the index to
     * be valid.
     * 
     * @param index
     *        integer specifying the valid index
     * 
     * @return Item stored at {@code index}
     */
    @Override
    protected Item getStoredItem(final int index) {
        return getDelegateArrayItem(getDelegateArrayIndex(index));
    }

    /**
     * Replaces the Item stored at the specified index in this IndexSequence
     * by the specified Item expecting the index to be valid.
     * 
     * @param index
     *        integer specifying the valid index
     * 
     * @param item
     *        Item to store
     */
    @Override
    protected void replaceStoredItem(final int index, final Item item) {
        replaceDelegateArrayItem(getDelegateArrayIndex(index), item);
    }

    /**
     * Returns the delegate array index in the specified index in this
     * {@link ArraySequence}.
     * 
     * @param index
     *        integer specifying the index of the Item in the
     *        {@link ArraySequence}
     * 
     * @return integer specifying the corresponding index in the delegate array
     */
    protected int getDelegateArrayIndex(final int index) {
        return index - getFirstIndex();
    }

    /**
     * Returns the Item stored in the delegate array at the specified index.
     * Provides a typesafe access to the (non generic) array.
     * 
     * @param arrayIndex
     *        index of the Item in the array
     * 
     * @return Item stored at {@code arrayIndex} in the array
     */
    protected Item getDelegateArrayItem(final int arrayIndex) {
        return delegateArray[arrayIndex];
    }

    /**
     * Replaces the Item stored in the delegate array at the specified index.
     * Provides a typesafe access to the (non generic) array.
     * 
     * @param arrayIndex
     *        integer specifying the index of the Item in the array
     * 
     * @param item
     *        replacing Item
     */
    protected void replaceDelegateArrayItem(final int arrayIndex, final Item item) {
        delegateArray[arrayIndex] = item;
    }

    // @see java.lang.Object#clone()
    @Override
    public ArraySequence<Item> clone() {
        final ArraySequence<Item> cloneSequence = new ArraySequence<Item>(getFirstIndex(), getLastIndex());

        final int delegateArrayLength = delegateArray.length;

        final Object[] cloneSequenceDelegateArray = cloneSequence.delegateArray;

        for (int delegateArrayIndex = 0; delegateArrayIndex < delegateArrayLength; delegateArrayIndex ++)
            cloneSequenceDelegateArray[delegateArrayIndex] = delegateArray[delegateArrayIndex];

        return cloneSequence;
    }

    // @see org.jlib.container.sequence.IndexSequence#equals(java.lang.Object)
    @Override
    public boolean equals(final Object otherObject) {
        if (!(otherObject instanceof ArraySequence<?>))
            return false;

        final ArraySequence<?> otherSequence = (ArraySequence<?>) otherObject;

        return getFirstIndex() == otherSequence.getFirstIndex() && getLastIndex() == otherSequence.getLastIndex() &&
               Arrays.equals(delegateArray, otherSequence.delegateArray);
    }

    // @see org.jlib.container.AbstractContainer#hashCode()
    @Override
    public int hashCode() {
        return 3 * getFirstIndex() + 5 * getLastIndex() + Arrays.hashCode(delegateArray);
    }

    /**
     * Asserts that the delegate array has the specified expected capacity,
     * replacing it by a larger {@code null} padded copy, if necessary.
     * 
     * @param expectedCapacity
     *        integer specifying the expected capacity
     * 
     * @throws IllegalArgumentException
     *         if {@code expectedCapacity < 1}
     */
    protected void assertCapacity(final int expectedCapacity) {
        assertExpectedCapacityValid(expectedCapacity);

        if (delegateArray.length < expectedCapacity)
            delegateArray = Arrays.copyOf(delegateArray, expectedCapacity);
    }

    /**
     * Asserts that the delegate array has the specified expected capacity,
     * replacing it by a larger {@code null} padded copy, if necessary.
     * 
     * @param expectedCapacity
     *        integer specifying the expected capacity
     * 
     * @param insertIndex
     *        integer specifying the insert index
     * 
     * @param holeSize
     *        integer specifying the size of the hole
     * 
     * @throws IllegalArgumentException
     *         if
     *         {@code expectedCapacity < 1 || getSize() + holeSize > expectedCapacity}
     */
    protected void assertCapacityWithHole(final int expectedCapacity, final int insertIndex, final int holeSize) {
        assertExpectedCapacityValid(expectedCapacity);

        if (getSize() + holeSize > expectedCapacity)
            throw new IllegalArgumentException("getSize() + items.length == " + getSize() + " + " + holeSize +
                                               " > " + expectedCapacity + " == expectedCapacity");
        @SuppressWarnings("unchecked")
        final Item[] newDelegateArray = delegateArray.length < expectedCapacity
            ? (Item[]) new Object[expectedCapacity]
            : delegateArray;

        System.arraycopy(delegateArray, 0, newDelegateArray, 0, insertIndex);
        System.arraycopy(delegateArray, insertIndex, newDelegateArray, insertIndex + holeSize, getSize() - insertIndex);
        Arrays.fill(newDelegateArray, getSize() + expectedCapacity, newDelegateArray.length, null);

        delegateArray = newDelegateArray;
    }

    /**
     * Asserts that the specified expected capacity is valid.
     * 
     * @param expectedCapacity
     *        integer specifying the expected capacity
     * 
     * @throws IllegalArgumentException
     *         if {@code expectedCapacity < 1}
     */
    protected void assertExpectedCapacityValid(final int expectedCapacity)
    throws IllegalArgumentException {
        if (expectedCapacity < 1)
            throw new IllegalArgumentException("expectedCapacity == " + expectedCapacity + " < 1");
    }
}
