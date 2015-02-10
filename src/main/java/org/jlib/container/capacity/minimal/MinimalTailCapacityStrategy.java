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

package org.jlib.container.capacity.minimal;

import org.jlib.container.storage.IndexRange;
import org.jlib.container.storage.LinearIndexStorage;
import org.jlib.container.capacity.AbstractHeadOrTailCapacityStrategy;
import org.jlib.container.capacity.CapacityStrategy;
import org.jlib.container.capacity.HeadOrTailCapacityStrategy;
import org.jlib.container.storage.IndexRangeOperationDescriptor;

/**
 * <p>
 * {@link HeadOrTailCapacityStrategy} providing just as much tail capacity as needed.
 * </p>
 * <p>
 * This {@link CapacityStrategy} analyzes the current head capacity to verify for the requested capacity.
 * If the requested head capacity is above the available head capacity,
 * the {@link LinearIndexStorage} is requested to re-allocate a capacity higher by the difference between requested and
 * available head capacity. The {@link Item}s are shifted "right" to have exactly the requested head capacity.
 * The {@link LinearIndexStorage} is always requested to provide an additional capacity even if its tail capacity would
 * be sufficient.
 * </p>
 *
 * @param <Item>
 *        type of the items held in the {@link LinearIndexStorage}
 *
 * @author Igor Akkerman
 */
public class MinimalTailCapacityStrategy<Item>
extends AbstractHeadOrTailCapacityStrategy<Item> {

    public MinimalTailCapacityStrategy(final LinearIndexStorage<Item> storage,
                                       final IndexRange contentIndexRange) {
        super(storage, contentIndexRange);
    }

    @Override
    protected void safeEnsureCapacity(final int tailCapacity) {
        final int missingTailCapacity = tailCapacity - getTailCapacity();

        if (missingTailCapacity <= 0)
            return;

        final IndexRangeOperationDescriptor keepAllItems = /*
         */ getDescriptorCopyAllItemsToIndex(getContentIndexRange().getMinimum());

        getStorage().addCapacityAndShiftItems(missingTailCapacity, keepAllItems);
    }
}