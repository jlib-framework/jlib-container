/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2015 Igor Akkerman
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

package org.jlib.container.capacity;

import org.jlib.container.storage.InvalidIndexException;
import org.jlib.container.storage.LinearIndexStorage;

/**
 * <p>
 * Strategy of capacity provision in a {@link LinearIndexStorage}.
 * The following terms are used:
 * </p>
 * <dl>
 * <dt>head capacity</dt> <dd>capacity in front of the first item</dd>
 * <dt>split capacity</dt><dd>capacity provided between two sections when splitting one big section into two</dd>
 * <dt>tail capacity</dt> <dd>capacity behind the last item</dd>
 * </dl>
 *
 * @author Igor Akkerman
 */
public interface CapacityStrategy {

    /**
     * Initializes the referenced {@link LinearIndexStorage} with a sufficient capacity to fit items in the specified
     * range. Registers the item indices.
     *
     * @throws InvalidIndexException
     *         if {@code firstitemIndex < 0 ||
     *                   lastitemIndex < firstitemIndex ||
     *                   lastitemIndex > storage.getCapacity() - 1}
     */
    void initialize()
    throws InvalidIndexException;

    /**
     * Ensures that the referenced {@link LinearIndexStorage} fits the specified number of items at the head of the
     * {@link LinearIndexStorage}. The indices of the stored items are incremented, if necessary.
     *
     * @param headCapacity
     *        integer specifying the head capacity
     *
     * @throws InvalidPartialCapacityException
     *         if {@code headCapacity < 0}
     */
    void ensureHeadCapacity(int headCapacity)
    throws InvalidPartialCapacityException;

    /**
     * Ensures that the referenced {@link LinearIndexStorage} fits the specified number of items betweeen the existing
     * stored items. The indices of the items stored after the specified split index are incremented.
     *
     * @param splitIndex
     *        integer specifying the split index
     *
     * @param splitCapacity
     *        integer specifying the split capacity
     *
     * @throws InvalidPartialCapacityException
     *         if {@code splitCapacity < 0}
     *
     * @throws InvalidIndexException
     *         if {@code middleIndex < linearIndexStorage.getFirstitemIndex() ||
     *                   middleIndex > linearIndexStorage.getMaximum()}
     */
    void ensureSplitCapacity(int splitIndex, int splitCapacity)
    throws InvalidPartialCapacityException, InvalidIndexException;

    /**
     * Ensures that the referenced {@link LinearIndexStorage} fits the specified number of items behind the existing
     * stored items. The indices of all stored items are left unchanged.
     *
     * @param tailCapacity
     *        integer specifying the tail capacity
     *
     * @throws InvalidPartialCapacityException
     *         if {@code tailCapacity < 0}
     */
    void ensureTailCapacity(int tailCapacity)
    throws InvalidPartialCapacityException;
}
