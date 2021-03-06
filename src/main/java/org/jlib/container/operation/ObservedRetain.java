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

package org.jlib.container.operation;

import java.util.Collection;

import org.jlib.operator.observer.Observer;
import org.jlib.operator.observer.ObserverException;

/**
 * Ability to retain Items; the retain operations can be attended by {@link Observer}
 * instances.
 *
 * @param <Item>
 *        type of items held in the {@link Object}
 *
 * @author Igor Akkerman
 */
public interface ObservedRetain<Item> {

    /**
     * Removes all Items from this {@link ObservedRemoveMultiple}
     * <em>except</em> the Items contained by the specified {@link Collection}.
     *
     * @param items
     *        {@link Collection} containing the Items to remove
     *
     * @param observers
     *        comma separated sequence of {@link Observer} instances
     *        attending the removal
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of one
     *         Item in {@code items}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws ObserverException
     *         if an error occurs during the {@link Observer} operation
     */
    @SuppressWarnings("unchecked")
    void retain(Iterable<Item> items, Observer<Item>... observers)
        throws InvalidContainerArgumentException, InvalidContainerStateException, ObserverException;
}
