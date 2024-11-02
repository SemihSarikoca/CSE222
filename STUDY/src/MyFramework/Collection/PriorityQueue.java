package MyFramework.Collection;

import java.util.Comparator;

public class PriorityQueue<E extends Comparable<E>> {
    List<E> heap;
    Comparator<E> comparator;
    
    public PriorityQueue() {
        this.heap = new ArrayList<>();
        
    }
    
    public PriorityQueue(Comparator<E> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }
    
    public void offer(E element) {
        heap.add(element);
        int index = heap.size() - 1;
        if (comparator == null) {
            while (index > 0 && heap.get(index).compareTo(heap.get(parent(index))) < 0) {
                swap(index, parent(index));
                index = parent(index);
            }
        } else {
            while (index > 0 && comparator.compare(heap.get(index), heap.get(parent(index))) < 0) {
                swap(index, parent(index));
                index = parent(index);
            }
        }
    }
    
    public E poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        
        swap(0, heap.size() - 1);
        E removedElement = heap.remove(heap.size() - 1);
        
        if (!isEmpty()) {
            heapifyDown(0);
        }
        
        return removedElement;
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap.get(0);
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    private void heapifyDown(int index) {
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        int smallest = index;
        
        if (leftChild < heap.size() && compare(heap.get(leftChild), heap.get(index)) < 0) {
            smallest = leftChild;
        }
        
        if (rightChild < heap.size() && compare(heap.get(rightChild), heap.get(smallest)) < 0) {
            smallest = rightChild;
        }
        
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }
    
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    private int parent(int i) {
        return (i - 1) / 2;
    }
    
    private int leftChild(int i) {
        return 2 * i + 1;
    }
    
    private int rightChild(int i) {
        return 2 * i + 2;
    }
    
    private int compare(E a, E b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            return a.compareTo(b);
        }
    }
}

