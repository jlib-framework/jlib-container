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

import org.jlib.iterable.BidiIterable;
import org.jlib.iterable.InvalidIterableStateException;
import org.jlib.iterable.RemoveIterable;
import org.jlib.iterable.SingletonIterable;
import org.jlib.operator.observer.Observer;

/**
 * {@link Iterator} utility.
 *
 * @author Igor Akkerman
 */
public final class Iterables {

    /**
     * Removes all Items of the specified {@link RemoveIterable}.
     *
     * @param iterable
     *        {@link RemoveIterable} providing the Items
     *
     * @param <Item>
     *        type of the items of {@code iterable}
     *
     * @param observers
     *        comma separated sequence of {@link Observer} instances
     *        attending the removal
     *
     * @throws InvalidIterableStateException
     *         if an error occurs during one of the remove operations
     */
    @SafeVarargs
    public static <Item> void removeAll(final ObservedRemoveIterable<Item> iterable,
                                        final Observer<Item>... observers)
        throws InvalidIterableStateException {
        for (final ObservedRemoveIterator<Item> iterator = iterable.iterator(); iterator.hasNext(); ) {
            iterator.next();
            iterator.remove(observers);
        }
    }

    public static <Item> BidiIterable<Item> singletonIterable(final Item item) {
        return new SingletonIterable<>(item);
    }
}
