package org.jlib.container.sequence.index.array;

import org.jlib.container.Container;
import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.index.DefaultInsertReplaceIndexSequenceTraverser;
import org.jlib.container.sequence.index.InsertIndexSequence;
import org.jlib.container.sequence.index.InsertReplaceIndexSequenceTraverser;
import org.jlib.container.sequence.index.SequenceIndexOutOfBoundsException;

import static org.jlib.container.sequence.SequenceUtility.singleton;

/**
 * {@link AddReplaceArraySequence} into which Items can be inserted.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public class InsertAddReplaceArraySequence<Item>
extends AddReplaceArraySequence<Item>
implements InsertIndexSequence<Item> {

    /**
     * Creates a new {@link InsertAddReplaceArraySequence} with the specified
     * first and last indices.
     * 
     * @param firstIndex
     *        integer specifying the first index
     * 
     * @param lastIndex
     *        integer specifying the last index
     * 
     * @throws IllegalArgumentException
     *         if {@code lastIndex > firstIndex}
     */
    protected InsertAddReplaceArraySequence(final int firstIndex, final int lastIndex) {
        super(firstIndex, lastIndex);
    }

    @Override
    public void insert(final int index, final Item item) {
        insert(index, singleton(item));
    }

    @Override
    public void insert(final int index, final Container<? extends Item> items) {
        final int insertedItemsCount = items.getSize();

        final int newSize = getSize() + insertedItemsCount;
        final int delegateArrayInsertIndex = getDelegateArrayIndex(index);

        assertCapacityWithHole(newSize, delegateArrayInsertIndex, insertedItemsCount);

        int delegateArrayIndex = delegateArrayInsertIndex;
        for (final Item item : items)
            replaceDelegateArrayItem(delegateArrayIndex ++, item);

        setLastIndex(getLastIndex() + insertedItemsCount);
    }

    @Override
    public InsertReplaceIndexSequenceTraverser<Item> createTraverser() {
        return createTraverser(getFirstIndex());
    }

    @Override
    public InsertReplaceIndexSequenceTraverser<Item> createTraverser(final int startIndex)
    throws SequenceIndexOutOfBoundsException {
        return new DefaultInsertReplaceIndexSequenceTraverser<Item>(this, startIndex);
    }
}
