/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2018 Igor Akkerman
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

package org.jlib.container.iterator;

import java.util.Iterator;

import org.jlib.iterable.InvalidIteratorStateException;

/**
 * {@link InvalidIteratorStateException} thrown when there is Item to remove by
 * a {@link Iterator}.
 *
 * @author Igor Akkerman
 */
public class NoItemToReplaceException
    extends InvalidIteratorStateException {

    private static final long serialVersionUID = - 1299720624484946758L;

    /**
     * Creates a new {@link NoItemToReplaceException}.
     *
     * @param iterable
     *        traversed {@link Iterable}
     */
    public NoItemToReplaceException(final Iterable<?> iterable) {
        super(iterable);
    }

    /**
     * Creates a new {@link NoItemToReplaceException} with the specified cause.
     *
     * @param iterable
     *        traversed {@link Iterable}
     *
     * @param cause
     *        {@link Exception} that caused this
     *        {@link NoItemToReplaceException}
     */
    public NoItemToReplaceException(final Iterable<?> iterable, final Exception cause) {
        super(iterable, cause);
    }
}
