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

package org.jlib.core.collection;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * <p>
 * Proxy {@link Map} caching the last {@link Value} looked up using {@link #containsKey(Object)} and returning it by a
 * subsequent call to {@link #get(Object)} for the same {@link Key}. Note that the {@link Key} is <em>only</em> tested
 * for <em>identity</em>, <em>not</em> for <em>equality</em>. The other methods are delegated to the {@link Map}
 * specified to the constructor.
 * </p>
 * <p>
 * Note that if the requested {@link Key} is mapped to another {@link Value} in the delegate {@link Map} between the
 * calls to {@link #containsKey(Object)} and {@link Map#get(Object)}, the <em>former, now wrong</em>, {@link Value} will
 * be returned by the latter call.
 * </p>
 * <p>
 * As in all <em>jlib</em> classes, neither {@code null} {@link Key}s nor {@code null} {@link Value}s are permitted and
 * cause undefined behaviour, such as {@link RuntimeException}s or invalid results. Hence, a {@link ContainsKeyCacheMap}
 * may not be used on delegate {@link Map}s containing {@code null} {@link Key}s or {@link Value}s.
 * </p>
 * <p>
 * The key idea behind this proxy is to be able to use the following idiom without worrying about performance issues due
 * to multiple lookups:
 * </p>
 * <pre>
 * if (map.containsKey(key)) {
 *     value = map.get(key);
 *     // commands with value
 * }
 * else {
 *     // commands with no value
 * }</pre>
 * <p>
 * Instead, many developers use the following technique which enforces comparing the result with {@code null}. This is a
 * discouraged code style and less readable:
 * </p>
 * <pre>
 * // dicouraged by clean coders
 * value = map.get(key);
 * if (value != null) {
 *     value = map.get(key);
 *     // commands with value
 * }
 * else {
 *     // commands with no value
 * }</pre>
 *
 * @param <Key>
 *        type of the keys
 *
 * @param <Value>
 *        type of the values
 *
 * @author Igor Akkerman
 */
public final class ContainsKeyCacheMap<Key, Value>
extends DelegateMap<Key, Value> {

    /**
     * <p>
     * Proxies a newly created {@link HashMap} using a {@link ContainsKeyCacheMap}.
     * </p>
     * <p>
     * The delegate {@link HashMap} is created using {@link HashMap#HashMap()}.
     * </p>
     *
     * @param <Key>
     *        type of the keys
     *
     * @param <Value>
     *        type of the values
     *
     * @return {@link ContainsKeyCacheMap} proxying the new {@link HashMap}
     */
    public static <Key, Value> Map<Key, Value> createHashMap() {
        return new ContainsKeyCacheMap<>(new HashMap<Key, Value>());
    }

    /**
     * <p>
     * Proxies a newly created {@link HashMap} using a {@link ContainsKeyCacheMap}.
     * </p>
     * <p>
     * The delegate {@link HashMap} is created using {@link HashMap#HashMap(int)}.
     * </p>
     *
     * @param <Key>
     *        type of the keys
     *
     * @param <Value>
     *        type of the values
     *
     * @param initialCapacity
     *        integer specifying the initial capacity of the delegate {@link HashMap}
     *
     * @return {@link ContainsKeyCacheMap} proxying the new {@link HashMap}
     */
    public static <Key, Value> Map<Key, Value> createHashMap(final int initialCapacity) {
        return new ContainsKeyCacheMap<>(new HashMap<Key, Value>(initialCapacity));
    }

    /**
     * <p>
     * Proxies a newly created {@link HashMap} using a {@link ContainsKeyCacheMap}.
     * </p>
     * <p>
     * The delegate {@link HashMap} is created using {@link HashMap#HashMap(int, float)}.
     * </p>
     *
     * @param <Key>
     *        type of the keys
     *
     * @param <Value>
     *        type of the values
     *
     * @param initialCapacity
     *        integer specifying the initial capacity of the delegate {@link HashMap}
     *
     * @param loadFactor
     *        integer specifying the load factro of the delegate {@link HashMap}
     *
     * @return {@link ContainsKeyCacheMap} proxying the new {@link HashMap}
     */
    public static <Key, Value> Map<Key, Value> createHashMap(final int initialCapacity, final int loadFactor) {
        return new ContainsKeyCacheMap<>(new HashMap<Key, Value>(initialCapacity, loadFactor));
    }

    /**
     * <p>
     * Proxies a newly created {@link HashMap} using a {@link ContainsKeyCacheMap}.
     * </p>
     * <p>
     * The delegate {@link HashMap} is created using {@link HashMap#HashMap(Map)}.
     * </p>
     *
     * @param <Key>
     *        type of the keys
     *
     * @param <Value>
     *        type of the values
     *
     * @param sourceMap
     *        {@link Map} containing the {@link Map.Entry}s copied to the delegate {@link Map}
     *
     * @return {@link ContainsKeyCacheMap} proxying the new {@link HashMap}
     */
    public static <Key, Value> Map<Key, Value> createHashMap(final Map<Key, Value> sourceMap) {
        return new ContainsKeyCacheMap<>(new HashMap<>(sourceMap));
    }

    /** last looked up key */
    private Object lastLookedUpKey;

    /** last looked up value for {@link #lastLookedUpKey} */
    private Value lastLookedUpValue;

    /**
     * Creates a new {@link ContainsKeyCacheMap}.
     *
     * @param delegateMap
     *        delegate {@link Map} to which all calls are delegated
     */
    public ContainsKeyCacheMap(final Map<Key, Value> delegateMap) {
        super(delegateMap);
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean containsKey(final Object key) {
        final Value value = super.get(key);

        lastLookedUpKey = key;
        lastLookedUpValue = value;

        return lastLookedUpValue != null;
    }

    @Override
    @SuppressWarnings({ "ReturnOfNull", "ObjectEquality" })
    @Nullable
    public Value get(final Object key) {
        if (lastLookedUpKey == key)
            return lastLookedUpValue;

        clearLastLookedUpItems();
        return super.get(key);
    }

    @SuppressWarnings("ObjectEquality")
    @Override
    public Value put(final Key key, final Value value) {
        if (lastLookedUpKey == key)
            clearLastLookedUpItems();

        return super.put(key, value);
    }

    @SuppressWarnings("ObjectEquality")
    @Override
    public Value remove(final Object key) {
        if (lastLookedUpKey == key)
            clearLastLookedUpItems();

        return super.remove(key);
    }

    @Override
    public void clear() {
        clearLastLookedUpItems();

        super.clear();
    }

    /**
     * Clears the last looked up contained Key and Value.
     */
    @SuppressWarnings("AssignmentToNull")
    private void clearLastLookedUpItems() {
        lastLookedUpKey = null;
        lastLookedUpValue = null;
    }

    @Override
    @SuppressWarnings({ "NullableProblems", "SuspiciousMethodCalls" })
    public void putAll(final Map<? extends Key, ? extends Value> map) {
        if (lastLookedUpKey != null && map.containsKey(lastLookedUpKey))
            clearLastLookedUpItems();

        super.putAll(map);
    }
}
