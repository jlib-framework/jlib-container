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

package org.jlib.object_spi.apachecommons;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringStyle;

import org.jlib.core.language.Valid;

class MapIdentifiedToStringStyleSupplier
implements IdentifiedToStringStyleSupplier {

    private final Map<String, ToStringStyle> toStringStyles;

    MapIdentifiedToStringStyleSupplier(final Map<String, ToStringStyle> toStringStyles) {
        this.toStringStyles = toStringStyles;
    }

    @Override
    public boolean isValidIdentifier(final String identifier) {
        return toStringStyles.containsKey(identifier);
    }

    @Override
    public ToStringStyle getIdentifiedToStringStyle(@Valid final String identifier) {
        return toStringStyles.get(identifier);
    }
}
