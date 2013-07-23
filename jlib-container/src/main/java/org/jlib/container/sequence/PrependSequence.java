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

package org.jlib.container.sequence;

import java.util.Collection;

import org.jlib.container.ReadContainer;

import org.jlib.core.traverser.InvalidTraversableArgumentException;

/**
 * {@link Sequence} to which Items can be prepended.
 *
 * @param <Item>
 *        type of items held in the {@link Sequence}
 *
 * @author Igor Akkerman
 */
public interface PrependSequence<Item>
extends Sequence<Item>{

    /**
     * @throws InvalidTraversableArgumentException
     *         if some property of {@code item} prevents it from being
     *         prepended, for instance, if it is already contained
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    public void prepend(Item item)
    throws InvalidTraversableArgumentException;

    /**
     * @throws InvalidTraversableArgumentException
     *         if some property of an Item in {@code items} prevents it from
     *         being prepended, for instance, if it is already contained
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    public void prepend(ReadContainer<? extends Item> items)
    throws InvalidTraversableArgumentException;

    /**
     * @throws InvalidTraversableArgumentException
     *         if some property of an Item in {@code items} prevents it from
     *         being prepended, for instance, if it is already contained
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    public void prepend(Collection<? extends Item> items)
    throws InvalidTraversableArgumentException;

    /**
     * @throws InvalidTraversableArgumentException
     *         if some property of an Item in {@code items} prevents it from
     *         being prepended, for instance, if it is already contained
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     */
    @SuppressWarnings("unchecked")
    public void prepend(Item... items)
    throws InvalidTraversableArgumentException;
}
