package queue;

import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> {
	private ArrayList<T> heap;

	/**
	 * Default constructor
	 */
	public BinaryHeap() {
		heap = new ArrayList<T>();
	}

	/**
	 * Constructor that takes an array of objects and turns it into a heap.
	 * 
	 * @param heapInput
	 */
	public BinaryHeap(T[] heapInput) {
		heap = new ArrayList<T>();
		for (T element : heapInput) {
			heap.add(element);
		}
		for (int i = (heap.size() - 1)/2; i >= 0; i--) {
			filterDown(i);
		}
	}

	/**
	 * String representation of the heap
	 */
	public String toString() {
		return heap.toString();
	}

	/**
	 * Returns true if the heap is empty
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/**
	 * Adds an element to the heap
	 * filterUp is called to ensure the heap property is maintained
	 * @param element
	 */
	public void add(T element) {
		if (!heap.contains(element)) {
			heap.add(element);
			filterUp(heap.size() - 1);
		}
	}

	/**
	 * Filters the heap from the bottom up
	 * @param pos
	 */
	private void filterUp(int pos) {
		while (pos > 0 && heap.get(getIndexOfParent(pos)).compareTo(heap.get(pos)) > 0) {
			Collections.swap(heap, getIndexOfParent(pos), pos);
			pos = getIndexOfParent(pos);
		}
	}

	/**
	 * Returns the index of the parent of the element at position pos
	 */
	private int getIndexOfParent(int pos) {
		return (pos - 1) / 2;
	}

	/**
	 * Returns the element at the top of the heap
	 */
	public T getMin() {
		T root = heap.get(0);
		T last = heap.remove(heap.size() - 1);
		if (!heap.isEmpty()) {
			heap.set(0, last);
		}
		filterDown(0);
		return root;
	}

	/**
	 * Filters the heap from the top down
	 * @param pos
	 */
	private void filterDown(int pos) {
		while (!isLeaf(pos)) {
			int leftChild = pos * 2 + 1;

			if (leftChild + 1 < heap.size()) {
				if (heap.get(leftChild).compareTo(heap.get(leftChild + 1)) > 0) {
					leftChild++;
				}
			}

			if (heap.get(pos).compareTo(heap.get(leftChild)) > 0) {
				Collections.swap(heap, pos, leftChild);
			} else {
				break;
			}
			pos = leftChild;
		}
	}

	/**
	 * Returns true if the element at position pos is a leaf
	 * @param i
	 * @return
	 */
	private boolean isLeaf(int i) {
		return i >= heap.size() / 2;
	}

}
