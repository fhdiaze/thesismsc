package edu.unal.thesis.implementations;

import java.util.BitSet;

import edu.unal.thesis.model.IBitSequence;

public class BitArray implements IBitSequence {
    private final BitSet bits;
    private int length;

    /**
     * Create a {@code BitArray} with length {@code length}
     * 
     * @param length
     *            The initial length of the {@code BitArray}
     */
    public BitArray(int length) {
        bits = new BitSet(length);
        this.length = length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.unal.thesis.implementations.IBitSequence#clear(int)
     */
    @Override
    public void clear(int bitIndex) {
        this.checkIndexBounds(bitIndex);
        this.bits.clear(bitIndex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.unal.thesis.implementations.IBitSequence#get(int)
     */
    @Override
    public boolean get(int bitIndex) {
        this.checkIndexBounds(bitIndex);
        return this.bits.get(bitIndex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.unal.thesis.implementations.IBitSequence#getIndexOf(boolean, int)
     */
    @Override
    public int getIndexOf(boolean status, int nth) {
        int i = 0, indexOfNth = -1;

        if (nth < 1)
            throw new IndexOutOfBoundsException("nth < 1: " + nth);
        
        do {
            indexOfNth++;
            indexOfNth = status ? this.bits.nextSetBit(indexOfNth) : this.bits
                    .nextClearBit(indexOfNth);

        } while (++i < nth && indexOfNth >= 0 && indexOfNth < this.length);

        indexOfNth = indexOfNth >= this.length ? -1 : indexOfNth;
        
        return indexOfNth;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.unal.thesis.implementations.IBitSequence#length()
     */
    @Override
    public int length() {
        return this.length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.unal.thesis.implementations.IBitSequence#rank(boolean, int)
     */
    @Override
    public int rank(boolean status, int index) {
        this.checkIndexBounds(index);
        BitSet bs = this.bits.get(0, index);
        int count = bs.cardinality();

        if (!status) {
            count = index - count;
        }
        
        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.unal.thesis.implementations.IBitSequence#set(int)
     */
    @Override
    public void set(int bitIndex) {
        this.checkIndexBounds(bitIndex);
        this.bits.set(bitIndex);
    }

    /**
     * Check if the index is between the bounds
     * 
     * @param index
     *            The index to check
     * @throws IndexOutOfBoundsException
     *             if the {@code index} is out of range (
     *             <tt>index &lt; 0 || index &gt;= length()</tt>)
     */
    private void checkIndexBounds(int index) {
        if (index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException(this.getOutOfBoundsMsg(index));
    }
    
    /**
     * Constructs an IndexOutOfBoundsException detail message. Of the many
     * possible refactorings of the error handling code, this "outlining"
     * performs best with both server and client VMs.
     * 
     * @param index
     *            Index which is out of bound
     * @return The message
     */
    private String getOutOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.length;
    }
}
