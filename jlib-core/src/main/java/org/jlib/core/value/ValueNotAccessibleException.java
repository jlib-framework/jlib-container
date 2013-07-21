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

package org.jlib.core.value;

import static org.jlib.core.language.ExceptionMessageUtility.message;

import org.jlib.core.language.ApplicationException;
import org.jlib.core.language.ParametrizedMessage;

/**
 * {@link ApplicationException} thrown when a requested Value is not accessible.
 *
 * @author Igor Akkerman
 */
public abstract class ValueNotAccessibleException
extends ApplicationException {

    /** serialVersionUID */
    private static final long serialVersionUID = - 813625306823615853L;

    private final String valueName;

    protected ValueNotAccessibleException() {
        super();
    }

    /**
     * Creates a new {@link ValueNotAccessibleException}.
     *
     * @param valueName
     *        {@link CharSequence} specifying a descriptive name of the Value
     */
    public ValueNotAccessibleException(final CharSequence valueName) {
        super(message(valueName));

        this.valueName = valueName.toString();
    }

    public String getValueName() {
        return valueName;
    }
}
