
/**
 * Represents a file in the file system.
 * Inherits properties and methods from the FileSystemElement class.
 */
public class File extends FileSystemElement {
    
    /**
     * Constructs a new File object with the specified name and parent directory.
     *
     * @param name   The name of the file.
     * @param parent The parent directory of the file.
     */
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }
    
    /**
     * Prints the file name and its creation date in the specified format.
     *
     * @param prefix The prefix to prepend before printing the file name.
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName() + "  (" + getDateCreated().toLocalDateTime() + ")");
    }
}
