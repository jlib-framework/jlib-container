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

package org.jlib.container.binaryrelation;

import java.util.Set;

import org.jlib.container.Container;
import org.jlib.core.traverser.Traverser;

/**
 * Binary association between various Objects. Each Object on either of the two
 * sides may be associated with a Set of Objects on the other side.
 *
 * @param <LeftValue>
 *        type of the values on the left hand side of the BinaryRelation
 *
 * @param <RightValue>
 *        type of the values on the right hand side of the BinaryRelation
 *
 * @author Igor Akkerman
 */
public interface BinaryRelation<LeftValue, RightValue>
extends Container<Pair<LeftValue, RightValue>> {

    /**
     * Returns the Set of RightValues associated with the specified LeftValue.
     *
     * @param leftValue
     *        LeftValue associated with the Set of RightValues to return
     *
     * @return {@link Set} of RightValues associated with {@code leftValue};
     *         empty {@link Set} if no RightValue is associated with
     *         {@code leftValue}
     */
    public Set<RightValue> getRightSet(LeftValue leftValue);

    /**
     * Returns the Set of LeftValues associated with the specified RightValue.
     *
     * @param rightValue
     *        RightValue associated with the Set of LeftValues to return
     *
     * @return {@link Set} of RightValues associated with {@code leftValue}
     *         empty {@link Set} if no RightValue is associated with
     *         {@code leftValue}
     */
    public Set<LeftValue> getLeftSet(RightValue rightValue);

    /**
     * Verifies whether the specified LeftValue is associated with the specified
     * RightValue.
     *
     * @param leftValue
     *        LeftValue of the potential association
     *
     * @param rightValue
     *        RightValue of the potential association
     *
     * @return {@code true} if {@code leftValue} is associated with
     *         {@code rightValue}
     */
    public boolean contains(LeftValue leftValue, RightValue rightValue);

    /**
     * Verifies whether the specified LeftValue is associated with some
     * RightValue by this BinaryRelation.
     *
     * @param leftValue
     *        the LeftValue
     *
     * @return {@code true} if {@code leftValue} is associated with some
     *         RightValue; {@code false} otherwise
     */
    public boolean hasLeft(LeftValue leftValue);

    /**
     * Verifies whether the specified RightValue is associated with some
     * LeftValue by this BinaryRelation.
     *
     * @param rightValue
     *        the RightValue
     *
     * @return {@code true} if {@code rightValue} is associated with some
     *         LeftValue; {@code false} otherwise
     */
    public boolean hasRight(RightValue rightValue);

    /**
     * Returns a {@link Set} containing the LeftValues of this
     * {@link BinaryRelation}. The {@link Set} is updated when this
     * {@link BinaryRelation} is modified. Note that, in that case, the values
     * returned by the {@link Set}'s {@link Traverser} may be inconsistent. The
     * {@link Set} is immutable, that is, calling one of its modifying
     * operations results in an {@link UnsupportedOperationException}.
     *
     * @return {@link Set} containing the LeftValues
     */
    public Set<LeftValue> getLeftValues();

    /**
     * Returns a {@link Set} containing the RightValues of this
     * {@link BinaryRelation}. The {@link Set} is updated when this
     * {@link BinaryRelation} is modified. Note that, in that case, the values
     * returned by the {@link Set}'s {@link Traverser} may be inconsistent. The
     * {@link Set} is immutable, that is, calling one of its modifying
     * operations results in an {@link UnsupportedOperationException}.
     *
     * @return {@link Set} containing the RightValues
     */
    public Set<RightValue> getRightValues();
}
