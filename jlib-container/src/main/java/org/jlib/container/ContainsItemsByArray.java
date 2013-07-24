/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2013 Igor Akkerman
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

package org.jlib.container;

import org.jlib.core.traverser.InvalidTraversableArgumentException;
import org.jlib.core.traverser.InvalidTraversableStateException;

public interface ContainsItemsByArray<Item>
extends ItemOperationStrategy<Item> {

    /**
     * Verifies whether this {@link ReadContainer} containsItem all of the specified
     * Items.
     *
     * @param items
     *        comma separated sequence of Items to verify
     *
     * @return {@code true} if this {@link ReadContainer} containsItem all of the
     *         {@code objects}; {@code false} otherwise
     *
     * @throws InvalidTraversableArgumentException
     *         if the operation cannot be completed due to some property of one
     *         item in {@code items}
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    @SuppressWarnings("unchecked")
    boolean contains(Item... items)
    throws InvalidTraversableArgumentException, InvalidTraversableStateException;
}