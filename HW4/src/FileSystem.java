import java.util.Comparator;

/**
 * Constructs a new FileSystem object with a root directory named "root".
 * The root directory has no parent directory as it represents the top-level directory in the file system.
 */
public class FileSystem {
    /**
     * root of the File System tree
     */
    private final Directory root;
    
    public FileSystem() {
        this.root = new Directory("root", null);
    }
    
    /**
     * Creates a new directory with the specified name.
     *
     * @param name The name of the directory to be created.
     */
    public void createDirectory(String name, Directory parent) {
        Directory newDirectory = new Directory(name, parent);
        parent.addElement(newDirectory);
        System.out.println("Directory created: " + name + "/");
    }
    
    /**
     * Creates a new file with the specified name.
     *
     * @param name The name of the file to be created.
     */
    public void createFile(String name, Directory parent) {
        File newFile = new File(name, parent);
        parent.addElement(newFile);
        System.out.println("File created: " + name);
    }
    
    /**
     * Deletes the file with the specified name from the specified parent directory.
     *
     * @param name   The name of the file to be deleted.
     * @param parent The parent directory from which the file should be deleted.
     */
    public void deleteFile(String name, Directory parent) {
        FileSystemElement fileToRemove = parent.getChild(name);
        if (fileToRemove instanceof File) {
            parent.removeElement(fileToRemove);
            System.out.println("File deleted: " + name);
        } else {
            System.out.println("File: " + name + " not found in current directory!");
        }
    }
    
    /**
     * Recursively deletes the directory with the specified name from the specified parent directory,
     * along with all its contents (files and subdirectories).
     * If the directory does not exist within the parent directory or if it's not a directory,
     * the method does nothing.
     *
     * @param name   The name of the directory to be recursively deleted.
     * @param parent The parent directory from which the directory will be recursively deleted.
     */
    public void deleteDirectory(String name, Directory parent) {
        FileSystemElement directoryToRemove = parent.getChild(name);
        if (directoryToRemove instanceof Directory directory) {
            for (FileSystemElement child : directory.getChildren()) {
                if (child instanceof File) {
                    directory.removeElement(child);
                } else if (child instanceof Directory) {
                    deleteDirectory(child.getName(), directory);
                }
            }
            parent.removeElement(directory);
            System.out.println("Directory deleted: " + name + "/");
        } else {
            System.out.println("Directory: " + name + " not found in current directory!");
        }
    }
    
    
    /**
     * Moves the file or directory with the specified name from the current parent directory to a new parent directory.
     * If the element does not exist in the current parent directory or if any of the directories (current or new parent) is null,
     * the method does nothing.
     *
     * @param name      The name of the file or directory to be moved.
     * @param parent    The current parent directory from which the element will be moved.
     * @param newParent The new parent directory to which the element will be moved.
     */
    public void moveElement(String name, Directory parent, Directory newParent) {
        if (parent != null) {
            FileSystemElement element = parent.getChild(name);
            if (element != null) {
                element.setParent(newParent);
                parent.removeElement(element);
                newParent.addElement(element);
            }
        }
        
    }
    
    /**
     * Searches for a file or directory with the specified name starting from the root directory.
     * Returns the path to the element found if it exists, otherwise returns null.
     *
     * @param name The name of the file or directory to search for.
     * @return The path to the element found, or null if not found.
     */
    public String search(String name) {
        return root.search(name);
    }
    
    /**
     * Prints the path to the current directory from the root directory, followed by the directory tree.
     *
     * @param dir The current directory.
     */
    public void printDirectoryTree(Directory dir) {
        System.out.print("Path to current directory from root:");
        int depth = getDepth(dir);
        printDirectoryPath(dir, depth);
        System.out.println(" (Current Directory)");
        for (var a : dir.getChildren()) {
            if (a instanceof Directory) {
                System.out.println("  ".repeat(depth + 1) + "* " + a.getName() + "/");
            } else {
                System.out.println("  ".repeat(depth + 1) + a.getName());
            }
        }
    }
    
    /**
     * Recursively prints the path from the root directory to the given directory, followed by its contents.
     *
     * @param dir   The directory to print the path and contents for.
     * @param depth The depth of the directory in the directory tree.
     */
    private void printDirectoryPath(Directory dir, int depth) {
        if (dir == null) {
            return;
        }
        if (dir.getParent() != null) {
            printDirectoryPath((Directory) dir.getParent(), depth - 1);
        }
        System.out.print("\n" + "  ".repeat(depth) + "* " + dir.getName() + "/");
    }
    
    /**
     * Lists the contents (files and subdirectories) of the given directory.
     *
     * @param dir The directory whose contents are to be listed.
     */
    public void listContents(Directory dir) {
        System.out.println("Listing contents of " + dir.getPath() + ":");
        for (FileSystemElement child : dir.getChildren()) {
            if (child instanceof Directory) {
                System.out.println("* " + child.getName() + "/");
            } else {
                System.out.println(child.getName());
            }
        }
    }
    
    /**
     * Sorts the contents of the given directory by date created and prints the sorted list.
     *
     * @param dir The directory whose contents are to be sorted.
     */
    public void sortDirectoryByDate(Directory dir) {
        System.out.println("Sorted contents of " + dir.getPath() + " by date created:");
        
        
        dir.getChildren().sort(Comparator.comparing(FileSystemElement::getDateCreated));
        
        dir.print("");
    }
    
    /**
     * Changes the current directory to the specified directory. (This part is mainly executed in main code)
     *
     * @param currentDirectory The current directory.
     * @param newDirectory     The new directory to change to.
     * @return The new directory if found, otherwise returns the current directory.
     */
    public Directory changeDirectory(Directory currentDirectory, Directory newDirectory) {
        if (newDirectory != null) {
            return newDirectory;
        } else {
            System.out.println("Directory not found");
            return currentDirectory;
        }
    }
    
    /**
     * Gets the current path of the given directory.
     *
     * @param dir The directory to get the current path for.
     * @return The current path of the directory.
     */
    public String getCurrentPath(Directory dir) {
        return dir.getPath();
    }
    
    /**
     * Gets the root directory of the file system.
     *
     * @return The root directory.
     */
    public Directory getRoot() {
        return root;
    }
    
    /**
     * Calculates the depth of the given directory in the directory tree.
     *
     * @param dir The directory to calculate the depth for.
     * @return The depth of the directory.
     */
    public int getDepth(Directory dir) {
        Directory curr = dir;
        int depth = 0;
        while (curr.getParent() != null) {
            depth++;
            curr = (Directory) curr.getParent();
        }
        return depth;
    }
}
