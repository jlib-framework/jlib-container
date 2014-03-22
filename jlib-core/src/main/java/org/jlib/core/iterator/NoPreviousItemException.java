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

package org.jlib.core.iterator;

import org.jlib.core.language.ExceptionMessage;

/**
 * {@link InvalidIteratorStateException} thrown when there is no next Item to
 * return by a {@link Iterator}.
 *
 * @author Igor Akkerman
 */
public class NoPreviousItemException
extends InvalidIteratorStateException {

    /** serialVersionUID */
    private static final long serialVersionUID = 1747026481047589428L;

    /**
     * Creates a new {@link NoPreviousItemException}.
     *
     * @param iterable
     *        traversed {@link Iterable}
     */
    public NoPreviousItemException(final Iterable<?> iterable) {
        super(iterable);
    }

    /**
     * Creates a new {@link NoPreviousItemException} with the specified cause.
     *
     * @param iterable
     *        traversed {@link Iterable}
     *
     * @param cause
     *        {@link Exception} that caused this {@link NoPreviousItemException}
     */
    public NoPreviousItemException(final Iterable<?> iterable, final Exception cause) {
        super(iterable, cause);
    }

    /**
     * Creates a new {@link NoPreviousItemException} with the specified cause.
     *
     * @param iterable
     *        traversed {@link Iterable}
     *
     * @param messageTemplate
     *        {@link String} specifying the error message template
     *
     * @param messageArguments
     *        comma separated sequence of {@link Object} instances specifying
     *        the message arguments
     */
    public NoPreviousItemException(final Iterable<?> iterable, final ExceptionMessage message) {
        super(iterable, message);
    }

    /**
     * Creates a new {@link NoPreviousItemException} with the specified cause.
     *
     * @param iterable
     *        traversed {@link Iterable}
     *
     * @param messageTemplate
     *        {@link String} specifying the error message template
     *
     * @param cause
     *        {@link Exception} that caused this {@link NoPreviousItemException}
     *
     * @param messageArguments
     *        comma separated sequence of {@link Object} instances specifying
     *        the message arguments
     */
    public NoPreviousItemException(final Iterable<?> iterable, final ExceptionMessage message,
                                   final Exception cause) {
        super(iterable, message, cause);
    }
}