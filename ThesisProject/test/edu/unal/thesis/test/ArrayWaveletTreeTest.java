/**
 * 
 */
package edu.unal.thesis.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.unal.thesis.implementations.ArrayWaveletTree;

/**
 * @author fredy
 *
 */
public class ArrayWaveletTreeTest {

    /**
     * Test method for {@link edu.unal.thesis.implementations.ArrayWaveletTree#ArrayWaveletTree(java.util.List)}.
     */
    @Test
    public void testArrayWaveletTree() {
        List<Character> sequence = Arrays.asList(new Character[] { 'a', 'b', 'r', 'a',
                'c', 'a', 'd', 'a', 'b', 'r', 'a' });
        new ArrayWaveletTree<Character>(sequence);
        
        sequence = Arrays.asList(new Character[] { 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a' });
        
        new ArrayWaveletTree<Character>(sequence);
    }

    /**
     * Test method for {@link edu.unal.thesis.implementations.ArrayWaveletTree#access(int)}.
     */
    @Test
    public void testAccess() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link edu.unal.thesis.implementations.ArrayWaveletTree#length()}.
     */
    @Test
    public void testLength() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link edu.unal.thesis.implementations.ArrayWaveletTree#rank(java.lang.Comparable, int)}.
     */
    @Test
    public void testRank() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link edu.unal.thesis.implementations.ArrayWaveletTree#select(java.lang.Comparable, int)}.
     */
    @Test
    public void testSelect() {
        fail("Not yet implemented");
    }

}
