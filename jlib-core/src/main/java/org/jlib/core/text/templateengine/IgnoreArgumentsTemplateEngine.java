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

package org.jlib.core.text.templateengine;

public final class IgnoreArgumentsTemplateEngine
extends AbstractTemplateEngine<Object> {

    /** sole {@link IgnoreArgumentsTemplateEngine} instance */
    private static final IgnoreArgumentsTemplateEngine INSTANCE = new IgnoreArgumentsTemplateEngine();

    /**
     * Returns the sole {@link IgnoreArgumentsTemplateEngine} instance.
     *
     * @return sole {@link IgnoreArgumentsTemplateEngine} instance
     */
    public static IgnoreArgumentsTemplateEngine getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new {@link IgnoreArgumentsTemplateEngine}.
     */
    private IgnoreArgumentsTemplateEngine() {
        super();
    }

    @Override
    public String applyArguments(final CharSequence template, final Object... arguments) {
        return template.toString();
    }
}
