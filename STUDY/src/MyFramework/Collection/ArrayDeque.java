package MyFramework.Collection;

public class ArrayDeque<E> implements Deque<E> {
    private E[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        capacity = 10;
        data = (E[]) new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean offerFirst(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        front = (front - 1 + capacity) % capacity;
        data[front] = item;
        return true;
    }
    
    public boolean offerLast(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;
        data[rear] = item;
        return true;
    }
    
    public E pollFirst() {
        size--;
        E result = data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        return result;
    }
    
    public E pollLast() {
        size--;
        E result = data[rear];
        data[rear] = null;
        rear = (rear - 1 + capacity) % capacity;
        return result;
    }
    
    public E peekFirst() {
        return data[front];
    }
    
    public E peekLast() {
        return data[rear];
    }
    
    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }
    
    @Override
    public E poll() {
        return pollFirst();
    }
    
    @Override
    public E peek() {
        return peekFirst();
    }
    
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
    
    private void reallocate() {
        @SuppressWarnings("unchecked")
        E[] newData = (E[]) new Object[capacity * 2];
        for (int i = 0; i < size; front = (front + 1) % capacity, i++) {
            newData[i] = data[front];
        }
        capacity *= 2;
        data = newData;
        front = 0;
        rear = size - 1;
    }
    
}
