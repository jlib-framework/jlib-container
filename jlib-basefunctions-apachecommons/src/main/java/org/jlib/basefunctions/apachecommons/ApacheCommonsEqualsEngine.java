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

package org.jlib.basefunctions.apachecommons;

import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;

import org.jlib.basefunctions.EqualsEngine;

public class ApacheCommonsEqualsEngine<Obj>
implements EqualsEngine<Obj> {

    private final EqualsBuilder builder;
    private final Object other;

    public ApacheCommonsEqualsEngine(final Object other) {
        this.other = other;
        builder = new EqualsBuilder();
    }

    @Override
    public <Value> ApacheCommonsEqualsEngine<Obj> add(final Value thisValue, final Value otherValue) {
        builder.append(thisValue, otherValue);

        return this;
    }

    @Override
    public EqualsEngine<Obj> addSuper(final Predicate<Object> superEquals) {
        builder.appendSuper(superEquals.test(other));

        return this;
    }

    @Override
    public boolean areEqual() {
        return builder.isEquals();
    }
}
