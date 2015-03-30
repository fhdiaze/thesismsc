package edu.unal.thesis.implementations;

import java.util.BitSet;

import edu.unal.thesis.model.IBitSequence;

public class BitArray implements IBitSequence {
	private BitSet bits;
	private int length;

	/**
	 * Create a {@code BitArray} with length {@code length}
	 * 
	 * @param length
	 *            The initial length of the {@code BitArray}
	 */
	public BitArray(int length) {
		this.bits = new BitSet(length);
		this.length = length;
	}

	/**
	 * 
	 * @param bits
	 * @param length
	 */
	private BitArray(BitSet bits, int length) {
		this.bits = bits;
		this.length = length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#clear(int)
	 */
	@Override
	public void clear(int bitIndex) {
		this.checkIndexBounds(bitIndex);
		this.bits.clear(bitIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#get(int)
	 */
	@Override
	public boolean get(int bitIndex) {
		this.checkIndexBounds(bitIndex);
		return this.bits.get(bitIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#getIndexOf(boolean,
	 * int)
	 */
	@Override
	public int select(boolean status, int nth) {
		int i = 0, indexOfNth = -1;

		if (nth < 0)
			throw new IndexOutOfBoundsException("nth < 0: " + nth);

		do {
			indexOfNth++;
			indexOfNth = status ? this.bits.nextSetBit(indexOfNth) : this.bits
					.nextClearBit(indexOfNth);

		} while (i++ < nth && indexOfNth >= 0 && indexOfNth < this.length);

		indexOfNth = indexOfNth >= this.length ? -1 : indexOfNth;

		return indexOfNth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#length()
	 */
	@Override
	public int length() {
		return this.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#rank(boolean, int)
	 */
	@Override
	public int rank(boolean status, int index) {
		this.checkIndexBounds(index);
		BitSet bs = this.bits.get(0, index);
		int count = bs.cardinality();

		if (!status) {
			count = index - count;
		}

		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.implementations.IBitSequence#set(int)
	 */
	@Override
	public void set(int bitIndex) {
		this.checkIndexBounds(bitIndex);
		this.set(bitIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.model.IBitSequence#get(int, int)
	 */
	@Override
	public BitArray get(int fromIndex, int toIndex) {
		this.checkIndexRange(fromIndex, toIndex);
		return new BitArray(this.bits.get(fromIndex, toIndex), toIndex
				- fromIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.unal.thesis.model.IBitSequence#access(int)
	 */
	@Override
	public void access(int bitIndex) {
		this.checkIndexBounds(bitIndex);
		this.bits.get(bitIndex);
	}

	/**
	 * Check if the index is between the bounds
	 * 
	 * @param index
	 *            The index to check
	 * @throws IndexOutOfBoundsException
	 *             if the {@code index} is out of range (
	 *             <tt>index &lt; 0 || index &gt;= length()</tt>)
	 */
	private void checkIndexBounds(int index) {
		if (index < 0 || index >= this.length)
			throw new IndexOutOfBoundsException(this.getOutOfBoundsMsg(index));
	}

	/**
	 * Check if the index is between the bounds
	 * 
	 * @param index
	 *            The index to check
	 * @throws IndexOutOfBoundsException
	 *             if ( <tt>fromIndex &lt; 0 || fromIndex &gt;= length() 
	 *              	|| toIndex &lt; 1 || toIndex &gt; length() ||fromIndex > toIndex</tt>
	 *             )
	 */
	private void checkIndexRange(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex >= this.length)
			throw new IndexOutOfBoundsException(
					this.getOutOfBoundsMsg(fromIndex));

		if (toIndex < 0 || toIndex > this.length)
			throw new IndexOutOfBoundsException(this.getOutOfBoundsMsg(toIndex));

		if (fromIndex >= toIndex)
			throw new IndexOutOfBoundsException(this.getOutOfRangeMsg(fromIndex, toIndex));
	}

	/**
	 * Constructs an IndexOutOfBoundsException detail message. Of the many
	 * possible refactorings of the error handling code, this "outlining"
	 * performs best with both server and client VMs.
	 * 
	 * @param index
	 *            Index which is out of bound
	 * @return The message
	 */
	private String getOutOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + this.length;
	}
	
	/**
	 * Constructs an IndexOutOfBoundsException detail message.
	 * 
	 * @param index
	 *            Index which is out of bound
	 * @param limit
	 * @return The message
	 */
	private String getOutOfRangeMsg(int index, int limit) {
		return "Index: " + index + ", limit: " + limit;
	}
}
