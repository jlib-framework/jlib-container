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

package org.jlib.container.traverser;

/**
 * {@link InvalidTraverserStateException} thrown when the traversed
 * {@link Traversible} claims a state error.
 *
 * @author Igor Akkerman
 */
public class InvalidTraversibleStateException
extends InvalidTraverserStateException {

    /** serialVersionUID */
    private static final long serialVersionUID = 1706750148627927636L;

    /**
     * Creates a new {@link InvalidTraversibleStateException}.
     *
     * @param traversible
     *        traversed {@link Traversible}
     *
     * @param cause
     *        {@link Throwable} that caused this
     *        {@link InvalidTraversibleStateException}
     */
    public InvalidTraversibleStateException(final Traversible<?> traversible, final Throwable cause) {
        super(traversible, cause);
    }
}