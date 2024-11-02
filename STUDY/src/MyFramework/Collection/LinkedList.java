package MyFramework.Collection;


import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
    
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    
    public LinkedList() {
    }
    
    public void addFirst(E obj) {
        add(0, obj);
    }
    
    public void addLast(E obj) {
        add(size, obj);
    }
    
    public void add(int index, E obj) {
        listIterator(index).add(obj);
    }
    
    @Override
    public E remove(int i) {
        var target = node(i);
        E result = target.data;
        remove(target);
        return result;
    }
    
    public E removeFirst() {
        return unlink(head);
    }
    
    public E removeLast() {
        return unlink(tail);
    }
    
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public E get(int index) {
        return listIterator(index).next();
    }
    
    public E getFirst() {
        return head.data;
    }
    
    public E getLast() {
        return tail.data;
    }
    
    public E set(int index, E newData) {
        listIterator(index).set(newData);
        return newData;
    }
    
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean add(E e) {
        return false;
    }
    
    
    @Override
    public String toString() {
        Node<E> currentNode = head;
        StringBuilder sb = new StringBuilder();
        while (currentNode != null) {
            sb.append(currentNode.data.toString());
            if (currentNode.next != null)
                sb.append(" <=> ");
            currentNode = currentNode.next;
        }
        return sb.toString();
    }
    
    public String toReverseString() {
        Node<E> currentNode = tail;
        StringBuilder sb = new StringBuilder();
        while (currentNode != null) {
            sb.append(currentNode.data.toString());
            if (currentNode.prev != null)
                sb.append(" <=> ");
            currentNode = currentNode.prev;
        }
        return sb.toString();
    }
    
    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }
    
    void linkLast(E e) {
        tail = new Node<>(tail, e, null);
        if (tail.prev == null)
            head = tail;
        size++;
    }
    
    void linkBefore(E e, Node<E> suc) {
        assert suc != null;
        new Node<>(suc.prev, e, suc);
        if (suc.prev.prev == null)
            head = suc.prev;
        size++;
    }
    
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.data;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;
        
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        
        x.data = null;
        size--;
        return element;
    }
    
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }
    
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }
    
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
        
        Node(Node<E> prevRef, E element, Node<E> nextRef) {
            this.data = element;
            this.next = nextRef;
            this.prev = prevRef;
            if (prevRef != null)
                prevRef.next = this;
            if (nextRef != null)
                nextRef.prev = this;
        }
    }
    
    private class ListItr implements ListIterator<E> {
        private Node<E> nextItem;
        private Node<E> lastReturned;
        private int nextIndex;
        
        public ListItr(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            nextItem = (index == size) ? null : node(index);
            nextIndex = index;
        }
        
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }
        
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = nextItem;
            nextItem = nextItem.next;
            nextIndex++;
            return lastReturned.data;
        }
        
        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }
        
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturned = nextItem = (nextItem == null) ? tail : nextItem.prev;
            nextIndex--;
            return lastReturned.data;
        }
        
        @Override
        public int nextIndex() {
            return nextIndex;
        }
        
        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }
        
        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (nextItem == lastReturned)
                nextItem = lastNext;
            else
                nextIndex--;
            lastReturned = null;
        }
        
        @Override
        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.data = e;
        }
        
        @Override
        public void add(E e) {
            lastReturned = null;
            if (nextItem == null) {
                linkLast(e);
            } else {
                linkBefore(e, nextItem);
            }
            nextIndex++;
        }
        
    }
}
