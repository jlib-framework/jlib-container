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

package org.jlib.container.operation;

/**
 * {@link InvalidContainerStateException} thrown when trying to retain the sole
 * Item of a {@link Object} that may not be empty.
 *
 * @author Igor Akkerman
 */
public class SoleItemNotRemoveableException
    extends InvalidContainerStateException {

    private static final long serialVersionUID = - 7467942886021869121L;

    public SoleItemNotRemoveableException(final Object itemOperation) {
        super(itemOperation);
    }
}
