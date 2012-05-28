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

package org.jlib.container;

import java.util.Collection;
import java.util.Set;

import org.jlib.container.collection.CollectionUtility;
import org.jlib.core.traverser.RemoveTraverser;

/**
 * Utility class providing methods operating on {@link Container Containers}.
 * 
 * @author Igor Akkerman
 */
public final class ContainerUtility {

    /** No visible constructor. */
    private ContainerUtility() {}

    /**
     * Adds all Items provided by the specified {@link Iterable} to the
     * specified {@link AddContainer}.
     * 
     * @param container
     *        {@link AddContainer} to which the Items are added
     * 
     * @param items
     *        {@link Iterable} providing the Items to add
     */
    public static <Item> void addAll(final AddContainer<Item> container, final Iterable<? extends Item> items) {
        for (final Item item : items)
            container.add(item);
    }

    /**
     * Removes all Items provided by the specified {@link Iterable} from the
     * specified {@link AddContainer}.
     * 
     * @param container
     *        {@link AddContainer} containing the Items
     * 
     * @param items
     *        {@link Iterable} providing the Items to remove
     */
    public static <Item> void removeAll(final RemoveContainer<Item> container, final Iterable<? extends Item> items) {
        for (final Item item : items)
            container.remove(item);
    }

    /**
     * Removes all Items from the specified {@link AddContainer} <i>except</i>
     * the Items provided by the specified {@link Iterable}.
     * 
     * @param container
     *        {@link RemoveContainer} containing the Items to remove
     * 
     * @param items
     *        {@link Iterable} providing the Items to retain
     */
    public static <Item> void retainAll(final RemoveContainer<Item> container, final Iterable<? extends Item> items) {
        final Set<Item> retainedItemsSet = CollectionUtility.toSet(items);

        final RemoveTraverser<Item> containerTraverser = container.createTraverser();
        while (containerTraverser.hasNextItem())
            if (!retainedItemsSet.contains(containerTraverser.getNextItem()))
                containerTraverser.remove();
    }

    /**
     * Removes all Items from the specified {@link AddContainer} <i>except</i>
     * for the Items contained by the specified {@link Collection}.
     * 
     * @param container
     *        {@link RemoveContainer} containing the Items to remove
     * 
     * @param items
     *        {@link Collection} containing the Items to retain
     */
    public static <Item> void retainAll(final RemoveContainer<Item> container, final Collection<? extends Item> items) {
        final RemoveTraverser<Item> itemsTraverser = container.createTraverser();
        while (itemsTraverser.hasNextItem())
            if (!items.contains(itemsTraverser.getNextItem()))
                itemsTraverser.remove();
    }

    /**
     * Removes all Items from the specified {@link AddContainer} <i>except</i>
     * for the Items contained by the specified {@link Collection}.
     * 
     * @param container
     *        {@link RemoveContainer} containing the Items to remove
     * 
     * @param items
     *        {@link Collection} containing the Items to retain
     */
    public static <Item, RetainedItem extends Item> void retainAll(final RemoveContainer<Item> container,
                                                                   @SuppressWarnings({ "unchecked", /* "varargs" */}) final RetainedItem... items) {
        // necessary as we need the contains() method fot the items sequence
        retainAll(container, CollectionUtility.toSet(items));
    }
}
