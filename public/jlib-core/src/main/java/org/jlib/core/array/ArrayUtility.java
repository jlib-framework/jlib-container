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

package org.jlib.core.array;

import java.util.ArrayList;
import java.util.List;

import org.jlib.core.iterator.BidiIterable;
import org.jlib.core.iterator.BidiIterator;

/**
 * Utility for arrays.
 *
 * @author Igor Akkerman
 */
public final class ArrayUtility {

    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * Crates an array of Items in a typesafe manner.
     *
     * @param <Item>
     *        type of the items held in the array
     *
     * @param length
     *        integer specifying the array length
     *
     * @return newly created array
     *
     * @throws NegativeArraySizeException
     *         if {@code length < 0}
     */
    @SuppressWarnings("unchecked")
    public static <Item> Item[] array(final int length)
    throws NegativeArraySizeException {
        return (Item[]) new Object[length];
    }

    /**
     * Returns a new {@link BidiIterable} adapter for the specified Items.
     *
     * @param <Item>
     *        type of the items held in the array
     *
     * @param items
     *        comma separated sequence of Items to traverse
     *
     * @return {@link BidiIterable} adapter for {@code items}
     */
    @SafeVarargs
    public static <Item> BidiIterable<Item> iterable(final Item... items) {
        return new ArrayIterable<>(items);
    }

    /**
     * Returns a new {@link BidiIterator} over the specified Items.
     *
     * @param <Item>
     *        type of the items held in the array
     *
     * @param items
     *        comma separated sequence of Items to traverse
     *
     * @return {@link BidiIterable} adapter for {@code items}
     */
    @SafeVarargs
    public static <Item> BidiIterator<Item> iterator(final Item... items) {
        return new ArrayIterator<>(items);
    }

    /**
     * Returns the total number of non array items held in the specified array,
     * recursively descending in every array item.
     *
     * @param items
     *        comma separated sequence of {@link Object} items
     *
     * @return integer specifying the total number of itemsnew
     */
    public static int getFlattenedItemsCount(final Object... items) {
        int itemsCount = 0;

        for (final Object item : items)
            itemsCount += item.getClass().isArray() ?
                          getFlattenedItemsCount((Object[]) item) :
                          1;

        return itemsCount;
    }

    /**
     * Recursively appends all Items specified as a comma separated list to the
     * specified {@link List}.
     *
     * @param allItems
     *        {@link List} to which the items are added
     *
     * @param items
     *        comma separated liet of items
     */
    public static void flatten(final List<Object> allItems, final Object... items) {
        for (final Object item : items)
            if (item.getClass().isArray())
                flatten(allItems, (Object[]) item);
            else
                allItems.add(item);
    }

    /**
     * Returns an array of all Items specified as a comma separated list to the
     * specified {@link List}, recursively collected from contained arrays.
     *
     * @param <Item>
     *        type of the specified items
     *
     * @param items
     *        comma separated liet of items
     *
     * @return array of all collected Items
     */
    @SuppressWarnings("unchecked")
    public static <Item> Item[] flatten(final Item... items) {
        final List<Object> allItems = new ArrayList<>(getFlattenedItemsCount(items));
        flatten(allItems, items);
        return (Item[]) allItems.toArray();
    }

    /**
     * Compares the specified {@link Object}s for mutual equality. Two {@link Object}s {@code object1}, {@code object2}
     * are considered equal if {@code object1.equals(object2)}. Returns {@code true} for an empty sequence of
     * {@link Object}s.
     *
     * @param objects
     *        comma separated sequence of {@link Object}s to compare
     *
     * @return {@code true} if all specified {@link Object}s are equal or if the specified sequence of {@link Object}s
     *         is empty; {@code false} otherwise
     */
    public static boolean allEqual(final Object... objects) {
        if (objects.length == 0)
            return true;

        final Object firstObject = objects[0];

        for (int index = 1; index < objects.length; index++)
            if (! firstObject.equals(objects[index]))
                return false;

        return true;
    }

    private ArrayUtility() {}
}
