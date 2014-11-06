package edu.unal.thesis.model;

import java.util.ArrayList;
import java.util.List;

public abstract class WaveletTree<E extends Comparable<? super E>> implements IWaveletTree<E> {
    private List<E> alphabet;
    
    /**
     * Create a WaveletTree with the specified sequence
     * @param sequence The sequence to be stored in the WaveletTree
     */
    public WaveletTree(List<E> sequence) {
        this.alphabet = this.getAlphabet(sequence);
    }

    /**
     * @return the alphabet
     */
    public List<E> getAlphabet() {
        return alphabet;
    }
    
    /**
     * Take the alphabet which is the sequence write over
     * @param sequence The sequence to be stored in the WaveletTree
     * @return
     */
    private List<E> getAlphabet(List<E> sequence) {
        List<E> alphabet = new ArrayList<E>();

        for (int i = 0; i < sequence.size(); i++) {
            E symbol = sequence.get(i);
            if (!alphabet.contains(symbol))
                alphabet.add(symbol);
        }

        alphabet.sort(null);

        return alphabet;
    }
}
