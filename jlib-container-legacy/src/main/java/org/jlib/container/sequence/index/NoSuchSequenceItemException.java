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

package org.jlib.container.operation.sequence.index;

import org.jlib.container.operation.InvalidContainerArgumentException;
import org.jlib.container.operation.sequence.Sequence;

import static org.jlib.core.language.ExceptionMessageUtility.message;

/**
 * {@link InvalidContainerArgumentException} thrown when a requested Item is not found.
 *
 * @author Igor Akkerman
 */
public class NoSuchSequenceItemException
extends InvalidContainerArgumentException {

    /** serialVersionUID */
    private static final long serialVersionUID = - 8162511917404174346L;

    /**
     * Creates a new {@link NoSuchSequenceItemException}.
     *
     * @param <Item>
     *        type of the item
     *
     * @param sequence
     *        searched {@link Sequence}
     *
     * @param item
     *        Item that could not be found
     */
    public <Item> NoSuchSequenceItemException(@SuppressWarnings("TypeMayBeWeakened") final Sequence<Item> sequence,
                                              final Item item) {
        super(sequence, message(item));
    }
}