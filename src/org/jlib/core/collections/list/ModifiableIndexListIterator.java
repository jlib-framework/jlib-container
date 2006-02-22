/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * File:    ModifiableIndexListIterator.java
 * Project: jlib.core
 *
 * Copyright (c) 2006 Igor Akkerman
 *
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.collections.list;

/**
 * <p>
 * ListIterator over a ModifiableIndexList.
 * </p>
 * <p>
 * This interface simply unites the capabilities of the interfaces EditableIndexListIterator and
 * ModifiableListIterator.
 * </p>
 *
 * @param <Element>
 *        type of the elements held in the List
 * @author Igor Akkerman
 */
public interface ModifiableIndexListIterator<Element>
extends EditableIndexListIterator<Element>, ModifiableListIterator<Element> {
    // intentionally left blank
}
