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

package org.jlib.container.storage.array;

import org.jlib.container.storage.InvalidStorageCapacityException;
import org.jlib.container.storage.InvalidStorageIndexException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ArrayStorageTest {

    @Test
    public void zeroCapacityStorageShouldReturnCorrectCapacity() {
        final ArrayStorage<Integer> storage = new ArrayStorage<>(0);

        assertThat(storage.capacity()).isEqualTo(0);
    }

    @Test
    public void positiveCapacityStorageShouldReturnCorrectCapacity() {
        final ArrayStorage<Integer> storage = new ArrayStorage<>(5);

        assertThat(storage.capacity()).isEqualTo(5);
    }

    @Test
    public void negativeCapacityStorageReadShouldThrowException() {
        Throwable thrown = catchThrowable(() ->
                new ArrayStorage<>(-1)
        );

        assertThat(thrown)
                .isInstanceOf(InvalidStorageCapacityException.class);
    }

    @Test
    public void zeroCapacityStorageReadShouldThrowException() {
        Throwable thrown = catchThrowable(() ->
                new ArrayStorage<Integer>(0).get(0)
        );

        assertThat(thrown)
                .isInstanceOf(InvalidStorageIndexException.class);
    }

    @Test
    public void readNegativeIndexShouldThrowException() {
        Throwable thrown = catchThrowable(() ->
                new ArrayStorage<Integer>(5).get(-1)
        );

        assertThat(thrown)
                .isInstanceOf(InvalidStorageIndexException.class);
    }

    @Test
    public void getPreviouslySetItemShouldReturnCorrectItem() {
        final ArrayStorage<String> storage = new ArrayStorage<>(5);
        storage.set(3, "Test");

        assertThat(storage.get(3)).isEqualTo("Test");
    }

    @Test
    public void getReplacedItemShouldReturnCorrectItem() {
        final ArrayStorage<String> storage = new ArrayStorage<>(5);
        storage.set(3, "Test");
        storage.set(3, "new");

        assertThat(storage.get(3)).isEqualTo("new");
    }

    @SuppressWarnings("EmptyMethod")
    @Test
    public void testShiftItems() {
        // TODO: implement
    }

    @SuppressWarnings("EmptyMethod")
    @Test
    public void testCopyItems() {
        // TODO: implement
    }

    @SuppressWarnings("EmptyMethod")
    @Test
    public void testGetCapacity() {
        // TODO: implement
    }

    @SuppressWarnings("EmptyMethod")
    @Test
    public void testEnsureCapacityAndShiftItems() {
        // TODO: implement
    }
}
