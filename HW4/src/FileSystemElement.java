import java.sql.Timestamp;

/**
 * Represents an abstract file system element.
 */
public abstract class FileSystemElement {
    
    /**
     * The name of the file system element.
     */
    private final String name;
    
    /**
     * The timestamp when the file system element was created.
     */
    private final Timestamp dateCreated;
    
    /**
     * The parent directory of the file system element.
     */
    private FileSystemElement parent;
    
    /**
     * Constructs a new FileSystemElement with the specified name and parent directory.
     *
     * @param name   The name of the file system element.
     * @param parent The parent directory of the file system element.
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.parent = parent;
        dateCreated = new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * Retrieves the name of the file system element.
     *
     * @return The name of the file system element.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retrieves the timestamp when the file system element was created.
     *
     * @return The timestamp when the file system element was created.
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    
    /**
     * Retrieves the parent directory of the file system element.
     *
     * @return The parent directory of the file system element.
     */
    public FileSystemElement getParent() {
        return parent;
    }
    
    /**
     * Sets the parent directory of the file system element.
     *
     * @param parent The parent directory of the file system element.
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }
    
    /**
     * Constructs and returns the absolute path of the file system element.
     *
     * @return The absolute path of the file system element.
     */
    public String getPath() {
        if (parent == null) {
            return name;
        }
        return parent.getPath() + "/" + name;
    }
    
    /**
     * Abstract method for printing the file system element with a specified prefix.
     * Subclasses must implement this method.
     *
     * @param prefix The prefix to be used for printing.
     */
    public abstract void print(String prefix);
}
