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

package org.jlib.container.collection;

import java.util.Collection;
import java.util.Iterator;

import org.jlib.core.language.ItemOperationStrategy;
import org.jlib.core.traverser.TraversableTraverser;
import org.jlib.core.traverser.Traverser;

import org.jlib.container.InvalidContainerArgumentException;
import org.jlib.container.InvalidContainerStateException;

/**
 * Adapter allowing a {@link Collection} to be used as a {@link TraversableContainer}. A
 * {@link CollectionOperation} is backed by a {@link Collection} specified at
 * initialization.
 *
 * @param <Item>
 *        type of items held in the {@link TraversableContainer}
 * @author Igor Akkerman
 */
public class CollectionOperation<Item>
implements ItemOperationStrategy<Item>,
           org.jlib.core.traverser.Traversable<Item>,
           Traversable<Item> {

    private static final long serialVersionUID = 4025909176358714675L;

    /** adapted and backed {@link Collection} */
    private final Collection<Item> delegateCollection;

    /**
     * Creates a new {@link ContainerCollection} backed by the specified
     * {@link Collection}.
     *
     * @param delegateCollection
     *        {@link Collection} backing this {@link CollectionOperation}
     */
    public CollectionOperation(final Collection<Item> delegateCollection) {
        this.delegateCollection = delegateCollection;
    }

    // implemented for efficiency
    @Override
    public int getItemsCount() {
        return delegateCollection.size();
    }

    @Override
    public boolean isEmpty()
    throws InvalidContainerStateException {
        return false;
    }

    @Override
    public boolean hasMatchingProperties(final TraversableContainer<Item> otherContainer) {
        return false;
    }

    @Override
    public Traverser<Item> createTraverser() {
        return new TraversableTraverser<>(this);
    }

    // implemented for efficiency
    @Override
    public Iterator<Item> iterator() {
        return delegateCollection.iterator();
    }

    // implemented for efficiency
    public boolean contains(final Item item) {
        return delegateCollection.contains(item);
    }

    public boolean contains(final TraversableContainer<? extends Item> items)
    throws InvalidContainerArgumentException, InvalidContainerStateException {
        return false;
    }

    // implemented for efficiency
    public boolean contains(final Collection<? extends Item> collection) {
        return delegateCollection.containsAll(collection);
    }

    public boolean contains(final Item... items)
    throws InvalidContainerArgumentException, InvalidContainerStateException {
        return false;
    }

    @Override
    public boolean containsEqualItems(final TraversableContainer<Item> otherContainer) {
        return false;
    }

    // implemented for efficiency
    @SuppressWarnings("unchecked")
    public Item[] toArray() {
        return (Item[]) delegateCollection.toArray();
    }

    /**
     * Returns the {@link Collection} adapted and backed by this
     * {@link CollectionOperation}.
     *
     * @return delegate {@link Collection} of this {@link CollectionOperation}
     */
    protected Collection<Item> getDelegateCollection() {
        return delegateCollection;
    }
}
