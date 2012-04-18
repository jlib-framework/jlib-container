/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * Copyright (c) 2006-2008 Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.container.binaryrelation;

/**
 * Association in a BinaryRelation.
 * 
 * @param <LeftValue>
 *        type of the objects on the left hand side of the BinaryRelation
 * @param <RightValue>
 *        type of the objects on the right hand side of the BinaryRelation
 * @author Igor Akkerman
 */
public class Association<LeftValue, RightValue> {

    /** LeftValue of this Association */
    private final LeftValue leftValue;

    /** RightValue of this Association */
    private final RightValue rightValue;

    /**
     * Creates a new Association.
     * 
     * @param leftValue
     *        LeftValue of this Association
     * @param rightValue
     *        RightValue of this Association
     */
    public Association(LeftValue leftValue, RightValue rightValue) {
        super();
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    /**
     * Returns the object on the left hand side of this Association.
     * 
     * @return LeftValue of this Association
     */
    public LeftValue left() {
        return leftValue;
    }

    /**
     * Returns the object on the right hand side of this Association.
     * 
     * @return RightValue of this Association
     */
    public RightValue right() {
        return rightValue;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof Association<?, ?>))
            return false;
        Association<?, ?> otherAssociation = (Association<?, ?>) otherObject;
        Object otherLeftValue = otherAssociation.left();
        Object otherRightValue = otherAssociation.right();
        if (leftValue != null && rightValue != null)
            return leftValue.equals(otherLeftValue) && rightValue.equals(otherRightValue);
        if (leftValue != null /* && rightValue == null */)
            return leftValue.equals(otherLeftValue) && otherRightValue == null;
        if (rightValue != null /* && leftValue == null */)
            return rightValue.equals(otherRightValue) && otherLeftValue == null;
        // if (leftValue == rightValue == null)
        return otherLeftValue == null && otherRightValue == null;
    }

    @Override
    public int hashCode() {
        return (leftValue != null ? leftValue.hashCode() : 0) + (rightValue != null ? rightValue.hashCode() : 0);
    }
}