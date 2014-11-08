package edu.unal.thesis.implementations;

import java.util.ArrayList;
import java.util.List;

import edu.unal.thesis.model.IBitSequence;
import edu.unal.thesis.model.WaveletTree;

/**
 * @author fredy
 *
 */
public class ArrayWaveletTree<E extends Comparable<? super E>> extends WaveletTree<E> {
    private Node[] nodes;
    
    /**
     * Create a LinkedWaveletTree with the specified sequence
     * @param sequence The sequence to be stored in the Wavelet Tree
     */
    public ArrayWaveletTree(List<E> sequence) {
        super(sequence);
        int alphabetSize = super.getAlphabet().size();
        long logarithm =  Math.round(Math.ceil(Math.log(alphabetSize)/Math.log(2)));
        this.nodes = new Node[(int) Math.round(Math.pow(2, logarithm))];
        this.nodes[0] = this.construct(sequence, 0, 0, super.getAlphabet().size() - 1);
    }   
    
    
    /**
     * 
     * @param sequence
     * @param index
     * @param minElemInd
     * @param maxElemInd
     * @return
     */
    private Node construct(List<E> sequence, int index, int minElemInd, int maxElemInd) {
        IBitSequence bs = new BitArray(sequence.size());
        List<E> leftSequence = new ArrayList<E>(), rightSequence = new ArrayList<E>();
        int i = 0;
        Node parent = new Node(bs, minElemInd, maxElemInd);
        
        for (E e : sequence) {
            if (parent.getElementValue(getAlphabet().indexOf(e))) {
                bs.set(i);
                rightSequence.add(e);
            } else {
                bs.clear(i);
                leftSequence.add(e);
            }
            
            i++;
        }
                    
        if (!parent.isLeaf()) {
            this.nodes[index * 2 + 1] = construct(leftSequence, index * 2 + 1, minElemInd, parent.getMiddleIndex());
            this.nodes[index * 2 + 2] = construct(rightSequence, index * 2 + 2, parent.getMiddleIndex() + 1, maxElemInd);
        }
        
        return parent;
    }

    /* (non-Javadoc)
     * @see edu.unal.thesis.model.IWaveletTree#access(int)
     */
    @Override
    public E access(int index) {
        return this.access(0, index);
    }

    @Override
    public int length() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int rank(E e, int index) {
        return this.rank(0, e, index);
    }

    @Override
    public int select(E e, int nth) {
        return this.select(0, e, nth);
    }
    
    /**
     * 
     * @param node
     * @param index
     * @return
     */
    private E access(int node, int index) {
        E result;
        boolean status = this.nodes[node].getBits().get(index);
        int leafIndex;

        if (this.nodes[node].isLeaf()) {
            leafIndex = status ? this.nodes[node].getMaxElemInd() : this.nodes[node].getMinElemInd();
            result = getAlphabet().get(leafIndex);
        } else {
            result = access(this.getChild(node, status), this.nodes[node].getBits().rank(status, index + 1) - 1);
        }

        return result;
    }
    
    /**
     * 
     * @param node
     * @param e
     * @param index
     * @return
     */
    private int rank(int node, E e, int index) {
        int result = 0;
        
        if (exists(e)) {
            boolean status = this.nodes[node].getElementValue(getAlphabet().indexOf(e));
            IBitSequence bits = this.nodes[node].getBits();

            if (this.nodes[node].isLeaf()) {
                result = bits.rank(status, index);
            } else {
                result = this.rank(this.getChild(node, status), e, bits.rank(status, index));
            }
        }
        
        return result;
    }
    
    /**
     * 
     * @param node
     * @param e
     * @param nth
     * @return
     */
    private int select(int node, E e, int nth) {
        int position = -1;
        
        if (exists(e)) {
            boolean status = this.nodes[node].getElementValue(getAlphabet().indexOf(e));
            IBitSequence bits = this.nodes[node].getBits();
    
            if (this.nodes[node].isLeaf()) {
                position = bits.select(status, nth);
            } else {
                int posAux = this.select(this.getChild(node,status), e, nth);
                position = posAux >= 0? bits.select(status, posAux) : posAux;
            }
        }

        return position;
    }
    
    /**
     * Return the index of the left child to the node with index {@code node} if
     * {@code status} is {@code true} or the right in the other case
     * 
     * @param node
     *            The index of the parent node
     * @param status
     *            The status of the child
     * @return The index of the correct child
     */
    private int getChild(int node, boolean status) {
        return status ? node * 2 + 2 : node * 2 + 1;
    }
    
    /**
     * Check whether a element is in the alphabet or not
     * @param e The element to check
     * @return true if the element is in the alphabet, false in other case
     */
    private boolean exists(E e) {
        return super.getAlphabet().contains(e);
    }
}
