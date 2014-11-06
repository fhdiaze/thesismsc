/**
 * 
 */
package edu.unal.thesis.test;

import java.util.Arrays;
import java.util.List;

import edu.unal.thesis.implementations.ArrayWaveletTree;
import edu.unal.thesis.model.IWaveletTree;

/**
 * @author fredy
 *
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Character> sequence = Arrays.asList(new Character[] { 'a', 'b', 'r', 'a',
                'c', 'a', 'd', 'a', 'b', 'r', 'a' });
        IWaveletTree<Character> wt = new ArrayWaveletTree<Character>(sequence);
        wt.select('a', 12);
    }

}
