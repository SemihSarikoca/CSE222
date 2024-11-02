package MyFramework.Collection;

public interface ListIterator<T> extends Iterator<T> {
    
    boolean hasNext();
    
    T next();
    
    void remove();
    
    boolean hasPrevious();
    
    T previous();
    
    int nextIndex();
    
    int previousIndex();
    
    void add(T e);
    
    void set(T e);
}
