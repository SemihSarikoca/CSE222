package MyFramework.Collection;

public interface Deque<E> {
    int getSize();
    
    boolean offer(E e);
    
    E poll();
    
    E peek();
    
    boolean isEmpty();
}
