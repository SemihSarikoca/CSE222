package MyFramework.Collection;

public class DeqStack<E> {
    private final ArrayDeque<E> stack;
    
    public DeqStack() {
        stack = new ArrayDeque<>();
    }
    
    public E peek() {
        return stack.peekFirst();
    }
    
    public E pop() {
        return stack.pollFirst();
    }
    
    public E push(E obj) {
        return stack.offerFirst(obj) ? obj : null;
    }
    
    public boolean isEmpty() {
        return stack.getSize() == 0;
    }
}
