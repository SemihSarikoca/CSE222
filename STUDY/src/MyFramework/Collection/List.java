package MyFramework.Collection;

public interface List<E> {
    int size();
    
    boolean isEmpty();
    
    boolean add(E e);
    
    boolean remove(Object o);
    
    E get(int i);
    
    E set(int i, E e);
    
    void add(int i, E e);
    
    E remove(int i);
}
