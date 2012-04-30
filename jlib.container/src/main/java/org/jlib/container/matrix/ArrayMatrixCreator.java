package org.jlib.container.matrix;

/**
 * {@link IndexMatrixCreator} of {@link ArrayMatrix} instances.
 * 
 * @param <Entry>
 *        type of the entries of the {@link Matrix}
 * 
 * @author Igor Akkerman
 */
public class ArrayMatrixCreator<Entry>
extends IndexMatrixCreator<ArrayMatrix<Entry>, Entry> {

    /** sole {@link ArrayMatrixCreator} instance */
    private static final ArrayMatrixCreator<?> INSTANCE = new ArrayMatrixCreator<>();

    /**
     * Returns the sole {@link ArrayMatrixCreator} instance.
     * 
     * @return sole {@link ArrayMatrixCreator} instance
     */
    @SuppressWarnings("unchecked")
    public static <Entry> ArrayMatrixCreator<Entry> getInstance() {
        return (ArrayMatrixCreator<Entry>) INSTANCE;
    }

    /**
     * Creates a new {@link ArrayMatrixCreator} instance.
     */
    private ArrayMatrixCreator() {
        super();
    }

    /**
     * Creates a new {@link ArrayMatrix} with the specified minimum and maximum
     * column and row indices.
     * 
     * @param firstColumnIndex
     *        first column index
     * 
     * @param lastColumnIndex
     *        last column index
     * 
     * @param firstRowIndex
     *        first row index
     * 
     * @param lastRowIndex
     *        last row index
     * 
     * @throws IllegalArgumentException
     *         if lastColumnIndex < firstColumnIndex || lastRowIndex <
     *         firstRowIndex}
     */
    @Override
    public ArrayMatrix<Entry> createMatrix(final int firstColumnIndex, final int firstRowIndex,
                                           final int lastColumnIndex, final int lastRowIndex) {
        return new ArrayMatrix<>(firstColumnIndex, firstRowIndex, lastColumnIndex, lastRowIndex);
    }
}
