/**
 * 
 */
package edu.unal.thesis.test;

import java.util.Arrays;
import java.util.List;

import edu.unal.thesis.implementations.LinkedWaveletTree;
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
        IWaveletTree<Character> wt = new LinkedWaveletTree<Character>(sequence);
        wt.select('b', 2);
    }

}
