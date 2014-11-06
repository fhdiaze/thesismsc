package edu.unal.thesis.implementations;

import edu.unal.thesis.model.IBitSequence;

/**
 * This class represents the data stored in a node of a Wavelet Tree
 * @author fredy
 *
 */
public class Node {
    private IBitSequence bitSequence;
    private int minElemInd;
    private int maxElemInd;
    
    /**
     * Create a Node with the received data
     * @param bitSequence The {@code BitSequence} to be stored
     * @param minElement The minimum element of the sequence represented by the {@code BitSequence}
     * @param maxElement The maximum element of the sequence represented by the {@code BitSequence}
     */
    public Node(IBitSequence bits, int minElement, int maxElement) {
        this.setBits(bits);
        this.setMinElemInd(minElement);
        this.setMaxElemInd(maxElement);
    }

    /**
     * @return the bitSequence
     */
    public IBitSequence getBits() {
        return bitSequence;
    }

    /**
     * @return the maxElemInd
     */
    public int getMaxElemInd() {
        return maxElemInd;
    }

    /**
     * @return the minElemInd
     */
    public int getMinElemInd() {
        return minElemInd;
    }

    /**
     * @param bitSequence the bitSequence to set
     */
    public void setBits(IBitSequence bits) {
        this.bitSequence = bits;
    }

    /**
     * @param maxElemInd the maxElemInd to set
     */
    public void setMaxElemInd(int maxElemInd) {
        this.maxElemInd = maxElemInd;
    }

    /**
     * @param minElemInd the minElemInd to set
     */
    public void setMinElemInd(int minElemInd) {
        this.minElemInd = minElemInd;
    }
    
    /**
     * Returns the current status of the bitSequence that represent the element {@code index} in
     * {@code this.node}
     * 
     * @param index
     *            The index of the element into the alphabet
     * @return The current status
     */
    public boolean getElementValue(int index) {
        return index > this.getMiddleIndex();
    }
    
    /**
     * Returns the index of the pivot element
     * @return The index of the pivot element
     */
    public int getMiddleIndex() {
        return this.minElemInd + (this.maxElemInd - this.minElemInd) / 2;
    }
    
    /**
     * Checks if a {@code Node} is a leaf (Has only one or two elements)
     * @return true if {@code this} is a leaf, false in other case
     */
    public boolean isLeaf() {
        return this.minElemInd == getMiddleIndex();
    }
}
