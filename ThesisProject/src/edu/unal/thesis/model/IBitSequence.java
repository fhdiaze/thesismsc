package edu.unal.thesis.model;

/**
 * 
 * @author fredy
 *
 */
public interface IBitSequence {

    /**
     * Sets the bit specified by the index to {@code false}.
     *
     * @param bitIndex
     *            the index (0-based) of the bit to be cleared
     * @throws IndexOutOfBoundsException
     *             if the {@code bitIndex} is out of range (
     *             <tt>bitIndex &lt; 0 || bitIndex &gt;= length()</tt>)
     */
    public void clear(int bitIndex);

    /**
     * Returns the value of the bit with the specified index (0-based). The
     * value is {@code true} if the bit with the index {@code bitIndex} is
     * currently set in this {@code BitSet}; otherwise, the result is
     * {@code false}.
     *
     * @param bitIndex
     *            The bit index (0-based)
     * @return the value of the bit with the specified index
     * @throws IndexOutOfBoundsException
     *             if the {@code bitIndex} is out of range (
     *             <tt>bitIndex &lt; 0 || bitIndex &gt;= length()</tt>)
     */
    public boolean get(int bitIndex);

    /**
     * Returns the index (0-based) of the {@code nth} occurrence of
     * {@code status} in this {@code BitSequence}
     * 
     * @param status
     *            The status of the bit be looking for
     * @param nth
     *            The number of occurrence desired
     * @return the index (0-based) of the next bit with status {@code status},
     *         or {@code -1} if there is no such bit
     * @throws IndexOutOfBoundsException
     *             if the nth is out of range (<tt>nth &lt; 1</tt>)
     */
    public int getIndexOf(boolean status, int nth);

    /**
     * Returns the number of bits in this {@code BitSequence}. If this
     * {@code BitSequence} contains more than Integer.MAX_VALUE bits, returns
     * Integer.MAX_VALUE.
     * 
     * @return Returns the number of bits in this {@code BitSequence}.
     */
    public int length();

    /**
     * Returns the number of bits set to {@code status} in this
     * {@code BitSequence} from the position {@code 0} to the position
     * {@code index} (excluded).
     * 
     * @param status
     *            The status of elements are looking for
     * @param index
     *            The position (0-based) desired (excluded)
     * @return The number of bits with status {@code status}
     * @throws IndexOutOfBoundsException
     *             if the {@code bitIndex} is out of range (
     *             <tt>index &lt; 0 || index &gt;= length()</tt>)
     */
    public int rank(boolean status, int index);

    /**
     * Sets the bit at the specified index (0-based) to {@code true}.
     * 
     * @param bitIndex
     *            a bit index (0-based)
     * @throws IndexOutOfBoundsException
     *             if the {@code bitIndex} is out of range (
     *             <tt>bitIndex &lt; 0 || bitIndex &gt;= length()</tt>)
     */
    public void set(int bitIndex);
}
