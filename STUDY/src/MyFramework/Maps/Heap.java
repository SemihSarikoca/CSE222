package MyFramework.Maps;

import MyFramework.Collection.ArrayList;


public class Heap<E extends Comparable<E>> {
    // max heap
    protected ArrayList<E> table = new ArrayList<>();
    
    public Heap() {
    }
    
    public Heap(ArrayList<E> table) {
        var it = table.iterator();
        while (it.hasNext()) {
            insert(it.next());
        }
    }
    
    
    public int size() {
        return table.size();
    }
    
    // logn
    public void insert(E item) {
        table.add(item);
        int index = table.size() - 1;
        heapifyUp(index);
        
    }
    
    // logn
    public E delete() {
        if (table.isEmpty()) {
            return null;
        }
        
        swap(0, table.size() - 1);
        E maxVal = table.remove(table.size() - 1);
        
        if (table.isEmpty()) {
            return maxVal;
        }
        heapifyDown(0);
        
        return maxVal;  // Return the deleted value
    }
    
    // logn
    private void heapifyDown(int index) {
        int size = table.size();
        int largest = index;
        int leftChild = childLeft(index);
        int rightChild = childRight(index);
        
        // Compare the node with its left child
        if (leftChild < size && compare(table.get(leftChild), table.get(largest)) > 0) {
            largest = leftChild;
        }
        // Compare the node with its right child
        if (rightChild < size && compare(table.get(rightChild), table.get(largest)) > 0) {
            largest = rightChild;
        }
        // If the largest element is not the current node, swap them and heapify the affected subtree
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }
    
    private void heapifyUp(int index) {
        int size = table.size();
        int smallest = index;
        int parent = parent(index);
        
        if (parent >= 0 && compare(table.get(smallest), table.get(parent)) > 0) {
            smallest = parent;
            swap(index, smallest);
            heapifyUp(smallest);
        }
        
    }
    
    // Helper method to compare elements
    private int compare(E a, E b) {
        return a.compareTo(b);
    }
    
    private boolean swap(int i, int j) {
        return table.swap(i, j);
    }
    
    public int numBigger(E item) {
        if (size() == 0)
            return 0;
        return numBigger(0, item);
    }
    
    private int numBigger(int parent, E item) {
        if (item.compareTo(table.get(parent)) >= 0) {
            return 0;
        }
        
        int leftNum = 0, rightNum = 0;
        
        if (childLeft(parent) < size()) {
            leftNum = numBigger(childLeft(parent), item);
        }
        
        if (childRight(parent) < size()) {
            rightNum = numBigger(childRight(parent), item);
        }
        
        return 1 + leftNum + rightNum;
    }
    
    // tüm elemanları heape ekleme nlogn tüm elemanları heapten çıkarma yine nlogn
    // total complexity 2nlogn
    public String heapSort() {
        // Create a copy of the heap
        Heap<E> copy = new Heap<>(this.table);
        ArrayList<E> sorted = new ArrayList<>();
        
        while (!copy.table.isEmpty()) {
            sorted.add(copy.delete());
        }
        
        return sorted.toString();
    }
    
    private int parent(int i) {
        return (i - 1) / 2;
    }
    
    private int childLeft(int i) {
        return 2 * i + 1;
    }
    
    private int childRight(int i) {
        return 2 * i + 2;
    }
    
    @Override
    public String toString() {
        return table.toString();
    }
    
}
