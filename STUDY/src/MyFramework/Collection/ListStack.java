package MyFramework.Collection;

public class ListStack<E> {
    private final LinkedList<E> stack;
    
    public ListStack() {
        stack = new LinkedList<>();
    }
    
    public E peek() {
        return stack.getFirst();
    }
    
    public E pop() {
        return stack.removeFirst();
    }
    
    public E push(E obj) {
        stack.addFirst(obj);
        return obj;
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
