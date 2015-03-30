/**
 * 
 */
package edu.unal.thesis.implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

import edu.unal.thesis.model.IBitSequence;

/**
 * @author fredy
 *
 */
public class RRRBitSequence implements IBitSequence {
	private BitSet classes; // Stores de clases (C_{i})
	private BitSet offsets; // Stores the offsets (o_{i})
	private short[] patterns; // Stores every combination of u bits
	private short[] indexes; // Stores the initial position of each class in
								// patterns
	private final int blockSize = 15; // This is the block size
	private final int sbfactor = 3; // This is the superblock factor

	public RRRBitSequence(BitArray bits) {
		int numBlocks = bits.length() / this.blockSize;
		BitArray block;

		/* This is the array of combinations for all classes */
		this.patterns = new short[32768];

		/* Initialize the indexes of the beginning of each class */
		indexes = new short[] { 0, 1, 16, 121, 576, 1941, 4944, 9949, 16384,
				22819, 27824, 30827, 32192, 32647, 32752, 32767 };

		for (int i = 0; i < numBlocks; i++) {
			block = bits.get(this.blockSize * i, this.blockSize * (i + 1));
		}

		this.initializePatterns();
	}

	/**
	 * Initialize the pattern for each class
	 */
	private void initializePatterns() {
		int combs, fromIndex;

		for (short i = 0; i < 16; i++) {
			fromIndex = this.indexes[i];
			combs = indexes[i + 1] - fromIndex;

			for (short j = 0; j < combs; j++) {
				this.patterns[fromIndex + j] = (short) (0 + j);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#clear(int)
	 */
	@Override
	public void clear(int bitIndex) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#get(int)
	 */
	@Override
	public boolean get(int bitIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#getIndexOf(boolean,
	 * int)
	 */
	@Override
	public int select(boolean status, int nth) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#rank(boolean, int)
	 */
	@Override
	public int rank(boolean status, int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#set(int)
	 */
	@Override
	public void set(int bitIndex) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.model.IBitSequence#get(int, int)
	 */
	@Override
	public IBitSequence get(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.model.IBitSequence#access(int)
	 */
	@Override
	public void access(int bitIndex) {
		// TODO Auto-generated method stub

	}
}
