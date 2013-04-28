package org.jlib.container.sequence.index;

import org.jlib.container.sequence.AbstractSequenceTraverser;
import org.jlib.container.sequence.NoNextSequenceItemException;
import org.jlib.container.sequence.NoPreviousSequenceItemException;
import org.jlib.container.sequence.Sequence;
import org.jlib.core.ValueNotAccessibleException;
import org.jlib.core.valueholder.InitializedModifiableValueHolder;
import org.jlib.core.valueholder.ModifiableValueHolder;
import org.jlib.core.valueholder.UninitializedValueHolder;

/**
 * Default implementation of an {@link IndexSequenceTraverser}.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @param <Sequenze>
 *        type of the traversed {@link IndexSequence}
 * 
 * @author Igor Akkerman
 */
public class DefaultIndexSequenceTraverser<Item, Sequenze extends IndexSequence<Item>>
extends AbstractSequenceTraverser<Item, Sequenze>
implements IndexSequenceTraverser<Item> {

    /** index of the potential next traversed Item */
    private int potentialNextItemIndex;

    /** {@link ModifiableValueHolder} for the index of the last accessed Item */
    private ModifiableValueHolder<Integer> lastAccessedItemIndexHolder;

    /**
     * Creates a new {@link DefaultIndexSequenceTraverser} over the Items of the
     * specified {@link IndexSequence} beginning at its first index.
     * 
     * @param sequence
     *        {@link IndexSequence} to traverse
     */
    public DefaultIndexSequenceTraverser(final Sequenze sequence) {
        super(sequence);

        potentialNextItemIndex = sequence.getFirstIndex();
    }

    /**
     * Creates a new {@link DefaultIndexSequenceTraverser} over the Items of the
     * specified {@link IndexSequence} beginning the traversal at the specified
     * index.
     * 
     * @param sequence
     *        {@link IndexSequence} to traverse
     * 
     * @param initialNextItemIndex
     *        integer specifying the index of the initial next Item
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if
     *         {@code initialNextItemIndex < sequence.getFirstIndex() || initialNextItemIndex > sequence.getLastIndex()}
     */
    public DefaultIndexSequenceTraverser(final Sequenze sequence, final int initialNextItemIndex) {
        super(sequence);

        IndexSequenceUtility.assertIndexValid(sequence, initialNextItemIndex);

        potentialNextItemIndex = initialNextItemIndex;

        unsetLastAccessedItem();
    }

    /**
     * Unregisters the last accessed Item.
     */
    protected void unsetLastAccessedItem() {
        lastAccessedItemIndexHolder = new UninitializedValueHolder<Integer>() {

            @Override
            public void setValue(final Integer index) {
                lastAccessedItemIndexHolder = new InitializedModifiableValueHolder<Integer>(index);
            }
        };
    }

    @Override
    public boolean isPreviousItemAccessible() {
        return potentialNextItemIndex - 1 >= getSequence().getFirstIndex();
    }

    @Override
    public boolean isNextItemAccessible() {
        return potentialNextItemIndex <= getSequence().getLastIndex();
    }

    @Override
    public final Item getPreviousItem()
    throws NoPreviousSequenceItemException {
        try {
            final Item sequenceItem = getSequenceItem(potentialNextItemIndex - 1);

            potentialNextItemIndex --;  // allow decrementation only if no exception is thrown

            return sequenceItem;
        }
        catch (final SequenceIndexOutOfBoundsException exception) {
            throw new NoPreviousSequenceItemException(getSequence(), exception);
        }
    }

    @Override
    public Item getNextItem()
    throws NoNextSequenceItemException {
        try {
            final Item sequenceItem = getSequenceItem(potentialNextItemIndex);

            potentialNextItemIndex ++; // allow incrementation only if no exception is thrown

            return sequenceItem;
        }
        catch (final SequenceIndexOutOfBoundsException exception) {
            throw new NoNextSequenceItemException(getSequence(), exception);
        }
    }

    /**
     * Returns the Item stored at the specified index in the {@link Sequence}
     * 
     * @param itemIndex
     *        integer specifying the index of the Item
     * 
     * @return Item stored at {@code itemIndex}
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if {@code itemIndex} is out of the index bounds
     */
    private Item getSequenceItem(final int itemIndex)
    throws SequenceIndexOutOfBoundsException {
        final Item item = getSequence().get(itemIndex);

        setLastAccessedItemIndex(itemIndex);

        return item;
    }

    @Override
    public int getPreviousItemIndex()
    throws NoPreviousSequenceItemException {

        final int previousItemIndex = potentialNextItemIndex - 1;

        if (previousItemIndex < getSequence().getFirstIndex())
            throw new NoPreviousSequenceItemException(getSequence());

        return previousItemIndex;
    }

    @Override
    public int getNextItemIndex() {
        if (!isNextItemAccessible())
            throw new NoPreviousSequenceItemException(getSequence());

        return potentialNextItemIndex;
    }

    /**
     * Returns the index of the potential next Item, even if the index is higher
     * than {@link IndexSequence#getLastIndex()}.
     * 
     * @return integer specifying the index of the potential next Item
     */
    protected int getPotentialNextItemIndex() {
        return potentialNextItemIndex;
    }

    /**
     * Returns the index of the last Item accessed by this
     * {@link DefaultIndexSequenceTraverser}.
     * 
     * @return integer specifying the index of the last accessed Item
     * 
     * @throws ValueNotAccessibleException
     *         if no Item has been accessed
     */
    protected int getLastAccessedItemIndex()
    throws ValueNotAccessibleException {
        return lastAccessedItemIndexHolder.getValue();
    }

    /**
     * Registers the index of the last Item accessed by this
     * {@link DefaultIndexSequenceTraverser}.
     * 
     * @param lastAccessedItemIndex
     *        integer specifying the index
     */
    protected void setLastAccessedItemIndex(final int lastAccessedItemIndex) {
        lastAccessedItemIndexHolder.setValue(lastAccessedItemIndex);
    }
}