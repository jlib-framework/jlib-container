/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2018 Igor Akkerman
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

package org.jlib.container.iterator;

import java.util.Iterator;

import org.jlib.iterable.InvalidIteratorStateException;

/**
 * {@link Iterator} over replaceable Items.
 *
 * @param <Item>
 *        type of the traversed items
 *
 * @author Igor Akkerman
 */
public interface ReplaceIterator<Item>
    extends Iterator<Item> {

    /**
     * Replaces the last traversed Item with the specified Item.
     *
     * @param newItem
     *        Item by which the former Item is replaced
     *
     * @throws InvalidIteratorStateException
     *         if no Item has been traversed by this {@link ReplaceIterator}
     */
    void replace(Item newItem)
        throws InvalidIteratorStateException;
}
