package MyFramework.Collection;

public class ArrayStack<E> {
    private final ArrayList<E> theData;
    
    public ArrayStack() {
        theData = new ArrayList<>();
    }
    
    public E peek() {
        return theData.get(theData.size() - 1);
    }
    
    public E pop() {
        return theData.remove(theData.size() - 1);
    }
    
    public E push(E obj) {
        return theData.add(obj) ? obj : null;
    }
    
    public boolean isEmpty() {
        return theData.isEmpty();
    }
    
}
