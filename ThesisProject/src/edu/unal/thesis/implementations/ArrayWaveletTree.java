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
    
    public ArrayWaveletTree(List<E> sequence) {
        super(sequence);
        int alphabetSize = super.getAlphabet().size();
        long logarithm =  Math.round(Math.ceil(Math.log(alphabetSize)/Math.log(2)));
        this.nodes = new Node[(int) Math.round(Math.pow(2, logarithm))];
        this.construct(sequence, 0, 0, super.getAlphabet().size() - 1);
    }
    
    @Override
    public E access(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int length() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int rank(E e, int index) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int select(E e, int nth) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
    private void construct(List<E> sequence, int index, int minElemInd, int maxElemInd) {
        IBitSequence bs = new BitArray(sequence.size());
        List<E> leftSequence = new ArrayList<E>(), rightSequence = new ArrayList<E>();
        int i = 0;
        this.nodes[index] = new Node(bs, minElemInd, maxElemInd);
        
        for (E e : sequence) {
            if (this.nodes[index].getElementValue(getAlphabet().indexOf(e))) {
                bs.set(i);
                rightSequence.add(e);
            } else {
                bs.clear(i);
                leftSequence.add(e);
            }
            
            i++;
        }
                    
        if (!this.nodes[index].isLeaf()) {
            construct(leftSequence, index * 2 + 1, minElemInd, this.nodes[index].getMiddleIndex());
            construct(rightSequence, index * 2 + 2, this.nodes[index].getMiddleIndex() + 1, maxElemInd);
        }
    }
}
