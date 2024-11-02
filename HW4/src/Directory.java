import java.util.LinkedList;

/**
 * Represents a directory in the file system.
 * Inherits properties and methods from the FileSystemElement class.
 */
public class Directory extends FileSystemElement {
    private final LinkedList<FileSystemElement> children;
    
    /**
     * Constructs a new Directory object with the specified name and parent directory.
     * Initializes an empty list of children elements.
     *
     * @param name   The name of the directory.
     * @param parent The parent directory of the directory.
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<>();
    }
    
    /**
     * Gets the list of children elements contained within the directory.
     *
     * @return The list of children elements.
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }
    
    /**
     * Prints the directory name, its creation date, and recursively prints its children elements.
     *
     * @param prefix The prefix to prepend before printing the directory name and children.
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + "* " + getName() + "/" + "  (" + getDateCreated().toLocalDateTime() + ")");
        for (FileSystemElement elem : children) {
            elem.print(prefix + "  ");
        }
    }
    
    /**
     * Adds the specified child element to the directory's list of children elements.
     *
     * @param child The child element to add to the directory.
     */
    public void addElement(FileSystemElement child) {
        children.add(child);
    }
    
    /**
     * Removes the specified child element from the directory's list of children elements.
     *
     * @param child The child element to remove from the directory.
     */
    public void removeElement(FileSystemElement child) {
        children.remove(child);
    }
    
    /**
     * Retrieves the child element with the specified name.
     *
     * @param name The name of the child element to retrieve.
     * @return The child element with the specified name, or null if not found.
     */
    public FileSystemElement getChild(String name) {
        for (FileSystemElement child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }
    
    /**
     * Searches for a file or directory with the specified name within the current directory and its subdirectories.
     * Returns the path to the element found, including the names of all parent directories leading up to the element.
     * If the element is not found, returns null.
     *
     * @param name The name of the file or directory to search for.
     * @return The path to the element found, or null if the element is not found.
     */
    public String search(String name) {
        for (FileSystemElement child : children) {
            if (child.getName().equals(name)) {
                return "/" + child.getName();
            } else if (child instanceof Directory) {
                String path = ((Directory) child).search(name);
                if (path != null) {
                    return "/" + child.getName() + path;
                }
            }
        }
        return null;
    }
    
}
