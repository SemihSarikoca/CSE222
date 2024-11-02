package MyFramework.Collection;

public class SinglyLinkedList<E> {
    private Node<E> head = null;
    private int size = 0;
    
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }
    
    private Node<E> getNode(int index) {
        Node<E> curr = head;
        for (int i = 0; i < index && curr != null; i++) curr = curr.next;
        return curr;
    }
    
    public E set(int index, E newData) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newData;
        return result;
    }
    
    public int size() {
        return size;
    }
    
    public boolean add(E item) {
        add(size, item);
        return true;
    }
    
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(item);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(item, node);
        }
        
    }
    
    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }
    
    private void addAfter(E item, Node<E> node) {
        node.next = new Node<>(item, node.next);
        size++;
    }
    
    @Override
    public String toString() {
        Node<E> currentNode = head;
        StringBuilder sb = new StringBuilder();
        while (currentNode != null) {
            sb.append(currentNode.data.toString());
            if (currentNode.next != null)
                sb.append(" => ");
            currentNode = currentNode.next;
        }
        return sb.toString();
    }
    
    private E removeFirst() {
        Node<E> temp = head;
        if (temp != null) {
            head = head.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }
    
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }
    
    private static class Node<E> {
        private E data;
        private Node<E> next;
        
        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }
        
        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
        
    }
    
    
}
