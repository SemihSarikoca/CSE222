package MyFramework.Collection;

public class ArrayQueue<E> implements Deque<E> {
    private E[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        capacity = 10;
        data = (E[]) new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    public boolean offer(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;
        data[rear] = item;
        return true;
    }
    
    
    public E poll() {
        size--;
        E result = data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        return result;
    }
    
    public E peek() {
        return data[front];
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
