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

package org.jlib.container.sequence.index;

import org.jlib.core.observer.ValueObserver;

import org.jlib.container.sequence.IllegalSequenceArgumentException;
import org.jlib.container.sequence.IllegalSequenceStateException;
import org.jlib.container.sequence.InsertSequence;
import org.jlib.container.sequence.ObservedInsertSequence;

/**
 * {@link IndexSequence} and {@link InsertSequence}.
 * 
 * @param <Item>
 *        type of items held in the {@link ObservedInsertIndexSequence}
 * 
 * @author Igor Akkerman
 */
public interface ObservedInsertIndexSequence<Item>
extends ObservedInsertSequence<Item>, InsertIndexSequence<Item> {

    /**
     * Inserts the Item at the specified index in this
     * {@link ObservedInsertIndexSequence} by the specified Items.
     * 
     * @param index
     *        integer specifying the index
     * 
     * @param item
     *        item to store
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the insertment
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if {@code index < getFirstIndex() || index > getLastIndex()}
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of {@code item} prevents the operation from
     *         being performed
     * 
     * @throws IllegalSequenceStateException
     *         if an error occurs performing the operation
     */
    @SuppressWarnings("unchecked")
    public void insert(final int index, final Item item, final ValueObserver<Item>... observers)
    throws SequenceIndexOutOfBoundsException, IllegalSequenceArgumentException, IllegalSequenceStateException;

    /**
     * @return {@link ObservedRemoveIndexSequenceTraverser} traversing the Items
     */
    @Override
    public ObservedInsertIndexSequenceTraverser<Item> createTraverser()
    throws SequenceIndexOutOfBoundsException;

    /**
     * @return {@link ObservedRemoveIndexSequenceTraverser} traversing the Items
     */
    @Override
    public ObservedInsertIndexSequenceTraverser<Item> createTraverser(final int startIndex)
    throws SequenceIndexOutOfBoundsException;
}
