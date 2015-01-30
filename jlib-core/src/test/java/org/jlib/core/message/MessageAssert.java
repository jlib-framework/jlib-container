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

package org.jlib.core.message;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;

public class MessageAssert extends AbstractAssert<MessageAssert, Message> {

    public static MessageAssert assertThat(final Message actual) {
        return new MessageAssert(actual);
    }

    public MessageAssert(final Message actual) {
        super(actual, MessageAssert.class);
    }

    public MessageAssert isEqualTo(final String expected) {
        isNotNull();
        Objects.instance().assertEqual(info, actual.toString(), expected);
        return this;
    }

    @Override
    public MessageAssert isEqualTo(final Object expected) {
        return super.isEqualTo(expected);
    }
}
