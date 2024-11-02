package MyFramework.Collection;

public interface Iterator<T> {
    
    boolean hasNext();
    
    T next();
    
    void remove();
}
