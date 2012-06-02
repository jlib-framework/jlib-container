package org.jlib.container.binaryrelation;

/**
 * {@link IllegalBinaryRelationArgumentException} thrown when an
 * {@link Association} cannot be added to a {@link BinaryRelation}.
 * 
 * @author Igor Akkerman
 */
public abstract class IllegalAssociationException
extends IllegalBinaryRelationArgumentException {

    /** serialVersionUID */
    private static final long serialVersionUID = 6498242933289941100L;

    /** LeftValue of the illegal {@link Association} */
    private final Object leftValue;

    /** RightValue of the illegal {@link Association} */
    private final Object rightValue;

    /**
     * Creates a new {@link IllegalAssociationException}.
     * 
     * @param binaryRelation
     *        referenced {@link BinaryRelation}
     * 
     * @param leftValue
     *        LeftValue of the illegal {@link Association}
     * 
     * @param rightValue
     *        RightValue of the illegal {@link Association}
     * 
     * @param messagePattern
     *        {@link String} specifying the pattern of the error message
     * 
     * @param messageParameters
     *        sequence of {@link Object} parameters of the error message
     */
    public IllegalAssociationException(final BinaryRelation<?, ?> binaryRelation, final Object leftValue,
                                       final Object rightValue, final String messagePattern,
                                       final Object... messageParameters) {
        this(binaryRelation, leftValue, rightValue, messagePattern, (Throwable) null, messageParameters);
    }

    /**
     * Creates a new {@link IllegalAssociationException}.
     * 
     * @param binaryRelation
     *        referenced {@link BinaryRelation}
     * 
     * @param leftValue
     *        LeftValue of the illegal {@link Association}
     * 
     * @param rightValue
     *        RightValue of the illegal {@link Association}
     * 
     * @param messagePattern
     *        {@link String} specifying the pattern of the error message
     * 
     * @param cause
     *        {@link Throwable} that caused this
     *        {@link IllegalBinaryRelationArgumentException}
     * 
     * @param messageParameters
     *        sequence of {@link Object} parameters of the error message
     */
    public IllegalAssociationException(final BinaryRelation<?, ?> binaryRelation, final Object leftValue,
                                       final Object rightValue, final String messagePattern, final Throwable cause,
                                       final Object... messageParameters) {
        super(binaryRelation, messagePattern, cause, messageParameters, leftValue, rightValue);

        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    /**
     * Returns the LeftValue of the illegal {@link Association}.
     * 
     * @return LeftValue of the {@link Association}
     */
    public Object getLeftValue() {
        return leftValue;
    }

    /**
     * Returns the rightValue of this {@link IllegalAssociationException}.
     * 
     * @return RightValue of the {@link Association}
     */
    public Object getRightValue() {
        return rightValue;
    }
}