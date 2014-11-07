package edu.unal.thesis.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.unal.thesis.implementations.LinkedWaveletTree;
import edu.unal.thesis.model.IWaveletTree;

/**
 * @author fredy
 *
 */
public class LinkedWaveletTreeTest {

    /**
     * Test method for
     * {@link edu.unal.thesis.implementations.LinkedWaveletTree#LinkedWaveletTree(java.lang.String)}.
     */
    @Test
    public void testLinkedWaveletTree() {
        List<Character> sequence = Arrays.asList(new Character[] { 'a', 'b', 'r', 'a',
                'c', 'a', 'd', 'a', 'b', 'r', 'a' });
        new LinkedWaveletTree<Character>(sequence);
        
        sequence = Arrays.asList(new Character[] { 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a' });
        
        new LinkedWaveletTree<Character>(sequence);
    }

    /**
     * Test method for
     * {@link edu.unal.thesis.implementations.LinkedWaveletTree#select(char, int)}.
     */
    @Test
    public void testSelect() {
        List<Character> sequence = Arrays.asList(new Character[] { 'a', 'b', 'r', 'a',
                'c', 'a', 'd', 'a', 'b', 'r', 'a' });
        IWaveletTree<Character> wt = new LinkedWaveletTree<Character>(sequence);
        assertEquals(8, wt.select('b', 1));
        assertEquals(5, wt.select('a', 2));
        assertEquals(-1, wt.select('b', 2));
        
        sequence = Arrays.asList(new Character[] { 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a' });
        
        wt = new LinkedWaveletTree<Character>(sequence);
        
        assertEquals(-1, wt.select('b', 2));
        assertEquals(-1, wt.select('a', 12));        
        assertEquals(2, wt.select('a', 2));
        
        try {
            wt.select('a', -1); 
            fail();
         } catch (Exception e) {
            // expected
            // could also check for message of exception, etc.
         }
    }

    /**
     * Test method for
     * {@link edu.unal.thesis.implementations.LinkedWaveletTree#access(int)}.
     */
    @Test
    public void testAccess() {
        List<Character> sequence = Arrays.asList(new Character[] { 'a', 'b', 'r', 'a',
                'c', 'a', 'd', 'a', 'b', 'r', 'a' });
        IWaveletTree<Character> wt = new LinkedWaveletTree<Character>(sequence);
        assertEquals(new Character('a'), wt.access(5));
        
        try {
            wt.access(18); 
            fail();
         } catch (IndexOutOfBoundsException e) {
            // expected
            // could also check for message of exception, etc.
         }
    }

    /**
     * Test method for
     * {@link edu.unal.thesis.implementations.LinkedWaveletTree#rank(char, int)}.
     */
    @Test
    public void testRank() {
        List<Character> sequence = Arrays.asList(new Character[] { 'a', 'b', 'r', 'a',
                'c', 'a', 'd', 'a', 'b', 'r', 'a' });
        IWaveletTree<Character> wt = new LinkedWaveletTree<Character>(sequence);
        
        assertEquals(4, wt.rank('a', 8));
        assertEquals(1, wt.rank('a', 3));
        assertEquals(2, wt.rank('a', 4));
        
        sequence = Arrays.asList(new Character[] { 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a' });
        
        wt = new LinkedWaveletTree<Character>(sequence);
        
        assertEquals(0, wt.rank('b', 8));
        
        wt.rank('c', 18);
        
        try {
            wt.rank('a', 18); 
            fail();
         } catch (Exception e) {
            // expected
            // could also check for message of exception, etc.
         }
    }

}
