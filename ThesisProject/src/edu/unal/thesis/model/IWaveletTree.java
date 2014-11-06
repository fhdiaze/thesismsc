package edu.unal.thesis.model;

/**
 * 
 * @author Fredy Diaz
 *
 */
public interface IWaveletTree<E extends Comparable<? super E>> {

    /**
     * Return the element at the position {@code index} (0-based)
     * 
     * @param index
     *            The position desired
     * @return The element at the position {@code index}
     * @throws IndexOutOfBoundsException
     *             if the {@code index} is out of range (
     *             <tt>index &lt; 0 || index &gt;= length()</tt>)
     */
    public E access(int index);

    /**
     * Returns the number of elements in the sequence of this
     * {@code WaveletTree}. If the sequence of this {@code WaveletTree} contains
     * more than Integer.MAX_VALUE bits, returns Integer.MAX_VALUE.
     * 
     * @return Returns the number of elements in the sequence of this
     *         {@code WaveletTree}.
     */
    public int length();

    /**
     * Return the number of elements {@code e} there are from the position
     * {@code 0} to the position {@code index} (excluded)
     * 
     * @param e
     *            The element is looking for
     * @param index
     *            The position desired (excluded)
     * @return The positions where the element {@code e} appear
     * @throws IndexOutOfBoundsException
     *             if the {@code index} is out of range (
     *             <tt>index &lt; 0 || index &gt;= length()</tt>)
     */
    public int rank(E e, int index);

    /**
     * Return the position (0-based) where nth occurrence (0-based) of the element {@code e} is staying or
     * -1 if there is no such element
     * 
     * @param e
     *            The element looking for
     * @param nth
     *            The number (0-based) of occurrence desired
     * @return The position (0-based) where the element {@code e} appear
     * @throws IndexOutOfBoundsException
     *             if the nth is out of range (<tt>nth &lt; 1</tt>)
     */
    public int select(E e, int nth);
}
