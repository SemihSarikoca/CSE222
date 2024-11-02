package MyFramework.Collection;


public class ArrayList<E> implements List<E> {
    protected E[] data;
    private int capacity;
    private int size = 0;
    
    @SuppressWarnings("unchecked")
    public ArrayList() {
        capacity = 10;
        
        this.data = (E[]) new Object[capacity];
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.data = (E[]) new Object[capacity];
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList(ArrayList<E> arr) {
        this.capacity = arr.capacity;
        this.size = arr.size;
        this.data = (E[]) new Object[capacity];
        if (arr.size >= 0) {
            System.arraycopy(arr.data, 0, this.data, 0, arr.size);
        }
    }
    
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[index];
    }
    
    public E set(int index, E newValue) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E oldValue = data[index];
        data[index] = newValue;
        return oldValue;
    }
    
    public boolean swap(int i, int j) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            return false;
        }
        
        var temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        return true;
    }
    
    public int size() {
        return size;
    }
    
    public boolean add(E anEntry) {
        if (size >= capacity) {
            reallocate();
        }
        data[size++] = anEntry;
        return true;
    }
    
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void add(int index, E anEntry) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size >= capacity) {
            reallocate();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = anEntry;
        size++;
    }
    
    public int indexOf(E target) {
        for (int i = 0; i < size; i++) {
            if (target == data[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void reallocate() {
        
        capacity *= 2;
        @SuppressWarnings("unchecked")
        E[] tempArray = (E[]) new Object[capacity];
        if (size >= 0)
            System.arraycopy(data, 0, tempArray, 0, size);
        data = tempArray;
    }
    
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E removedValue = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removedValue;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Iterator<E> iterator() {
        return new Itr();
    }
    
    
    public class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        
        public boolean hasNext() {
            return cursor != size;
        }
        
        @SuppressWarnings("unchecked")
        public E next() {
            int i = cursor;
            if (i >= size)
                return null;
            cursor = i + 1;
            return data[lastRet = i];
        }
        
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }
}
