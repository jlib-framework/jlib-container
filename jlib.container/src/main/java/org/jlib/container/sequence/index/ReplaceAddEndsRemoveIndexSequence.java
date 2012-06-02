package org.jlib.container.sequence.index;

import org.jlib.container.sequence.HeadRemoveSequence;
import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.TailRemoveSequence;

/**
 * {@link ReplaceAddIndexSequence}, {@link HeadRemoveSequence} and
 * {@link TailRemoveSequence}.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public interface ReplaceAddEndsRemoveIndexSequence<Item>
extends ReplaceAddIndexSequence<Item>, HeadRemoveSequence<Item>, TailRemoveSequence<Item> {
    // unifying interface
}