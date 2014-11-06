package edu.unal.thesis.model;

import java.util.List;

/**
 * @author fredy
 *
 */
public interface PrototypeManager<E extends Comparable<? super E>> {

    public IBitSequence getBitSequence(List<E> sequence);
    
    public void putSymbol(E symbol);
}
