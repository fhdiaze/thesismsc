package edu.unal.thesis.implementations;

import java.util.ArrayList;
import java.util.List;

import edu.unal.thesis.model.IBitSequence;
import edu.unal.thesis.model.WaveletTree;

/**
 * @author fredy diaz
 *
 */
public class LinkedWaveletTree<E extends Comparable<? super E>> extends WaveletTree<E> {
    private BinaryTree root;
    
    /**
     * Create a LinkedWaveletTree with the specified sequence
     * @param sequence The sequence to be stored in the Wavelet Tree
     */
    public LinkedWaveletTree(List<E> sequence) {
        super(sequence);        
        this.root = new BinaryTree(sequence, 0, super.getAlphabet().size() - 1);
    }

    /*
     * (non-Javadoc)
     * @see edu.unal.thesis.implementations.IWaveletTree#access(int)
     */
    @Override
    public E access(int bitIndex) {
        return root.access(bitIndex);
    }

    /* (non-Javadoc)
     * @see edu.unal.thesis.implementations.IWaveletTree#length()
     */
    @Override
    public int length() {
        return this.root.getNode().getBits().length();
    }
    
    /*
     * (non-Javadoc)
     * @see edu.unal.thesis.implementations.IWaveletTree#rank(java.lang.Comparable, int)
     */
    @Override
    public int rank(E e, int index) {
        return root.rank(e, index);
    }

    /*
     * (non-Javadoc)
     * @see edu.unal.thesis.implementations.IWaveletTree#select(java.lang.Comparable, int)
     */
    @Override
    public int select(E e, int nth) {
        return root.select(e, nth);
    }

    /**
     * Check whether a element is in the alphabet or not
     * @param e The element to check
     * @return true if the element is in the alphabet, false in other case
     */
    private boolean exists(E e) {
        return super.getAlphabet().contains(e);
    }
    
    /**
     * This class implements a linked Binary Tree
     * @author fredy
     *
     */
    private class BinaryTree {
        private Node node;
        private BinaryTree leftChild;
        private BinaryTree rightChild;
        
        /**
         * Create a BinaryTree with the specified sequence of symbols
         * @param sequence The sequence to store in the Wavelet Tree
         * @param minElemInd The minimum element of the alphabet in the sequence
         * @param maxElemInd The maximum element of the alphabet in the sequence
         */
        private BinaryTree(List<E> sequence, int minElemInd, int maxElemInd) {
            IBitSequence bs = new BitArray(sequence.size());
            List<E> leftSequence = new ArrayList<E>(), rightSequence = new ArrayList<E>();
            int i = 0;
            this.node = new Node(bs, minElemInd, maxElemInd);
            
            for (E e : sequence) {
                if (this.node.getElementValue(getAlphabet().indexOf(e))) {
                    bs.set(i);
                    rightSequence.add(e);
                } else {
                    bs.clear(i);
                    leftSequence.add(e);
                }
                
                i++;
            }
                        
            if (!this.node.isLeaf()) {
                this.leftChild = new BinaryTree(leftSequence, minElemInd, node.getMiddleIndex());
                this.rightChild = new BinaryTree(rightSequence, node.getMiddleIndex() + 1, maxElemInd);
            }
        }
        
        /**
         * @return the node
         */
        private Node getNode() {
            return node;
        }
        
        /**
         * Return the left child of this if {@code status} is {@code true} or the
         * right in the other case
         * 
         * @param status
         *            The status of the child
         * @return The correct child
         */
        private BinaryTree getChild(boolean status) {
            return status ? this.rightChild : this.leftChild;
        }
        
        /*
         * (non-Javadoc)
         * @see edu.unal.thesis.implementations.IWaveletTree#access(int)
         */
        private E access(int bitIndex) {
            E result;
            boolean status = this.node.getBits().get(bitIndex);
            int index;

            if (this.node.isLeaf()) {
                index = status ? this.node.getMaxElemInd() : this.node.getMinElemInd();
                result = getAlphabet().get(index);
            } else {
                result = this.getChild(status).access(this.node.getBits().rank(status, bitIndex + 1) - 1);
            }

            return result;
        }
        
        /*
         * (non-Javadoc)
         * @see edu.unal.thesis.implementations.IWaveletTree#rank(java.lang.Comparable, int)
         */
        private int rank(E e, int index) {
            int result = 0;
            
            if (exists(e)) {
                boolean status = this.node.getElementValue(getAlphabet().indexOf(e));
                IBitSequence bits = this.node.getBits();

                if (this.node.isLeaf()) {
                    result = bits.rank(status, index);
                } else {
                    result = this.getChild(status).rank(e, bits.rank(status, index));
                }
            }
            
            return result;
        }
        
        /*
         * (non-Javadoc)
         * @see edu.unal.thesis.implementations.IWaveletTree#select(java.lang.Comparable, int) {@inheritDoc}
         */
        private int select(E e, int nth) {
            int position = -1;
            
            if (exists(e)) {
                boolean status = this.node.getElementValue(getAlphabet().indexOf(e));
                IBitSequence bits = this.node.getBits();
        
                if (this.node.isLeaf()) {
                    position = bits.select(status, nth);
                } else {
                    position = bits.select(status, this.getChild(status).select(e, nth) + 1);
                }
            }

            return position;
        }
    }
}
