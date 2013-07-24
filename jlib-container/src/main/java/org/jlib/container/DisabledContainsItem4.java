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

import java.util.Collection;

import org.jlib.core.traverser.Traversable;

final class DisabledContainsItem4<Item>

implements ContainsItemsByCollection<Item> {

    /** sole {@link DisabledContainsItem4} instance */
    private static final DisabledContainsItem4<?> INSTANCE = new DisabledContainsItem4<Object>();

    /**
     * Returns the sole {@link DisabledContainsItem4} instance.
     *
     * @param <Item>
     *        type of the Item
     *
     * @return sole {@link DisabledContainsItem4} instance
     */
    @SuppressWarnings("unchecked")
    public static <Item> DisabledContainsItem4<Item> getInstance() {
        return (DisabledContainsItem4<Item>) INSTANCE;
    }

    /**
     * Creates a new {@link DisabledContainsItem4}.
     */
    private DisabledContainsItem4() {
        super();
    }

    @Override
    public boolean containsItem(final Item item)
    throws ForbiddenCastException {
        throw new ForbiddenCastException(this);
    }

    @Override
    public boolean contains(final Traversable<? extends Item> items)
    throws ForbiddenCastException {
        throw new ForbiddenCastException(this);
    }

    @Override
    public boolean contains(final Iterable<? extends Item> items)
    throws ForbiddenCastException {
        throw new ForbiddenCastException(this);
    }

    @Override
    public boolean contains(final Collection<? extends Item> items)
    throws ForbiddenCastException {
        throw new ForbiddenCastException(this);
    }

    @Override
    @SafeVarargs
    public final boolean contains(final Item... items)
    throws ForbiddenCastException {
        throw new ForbiddenCastException(this);
    }
}