import java.util.Scanner;

/**
 * Main class for File System Management.
 * Allows users to interact with a file system by providing various options.
 */
public class Main {
    private static final FileSystem fs = new FileSystem();
    private static final Scanner scanner = new Scanner(System.in);
    private static Directory currentDirectory;
    
    public static void main(String[] args) {
        currentDirectory = fs.getRoot();
        while (true) {
            System.out.println("===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date created");
            System.out.println("9. Exit");
            System.out.print("Please select an option: ");
            
            // Get user input for the selected option
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Perform the selected operation based on the user's choice
            switch (choice) {
                case 1:
                    changeDirectory(); // Change directory
                    break;
                case 2:
                    listContents(); // List directory contents
                    break;
                case 3:
                    createElement(); // Create file/directory
                    break;
                case 4:
                    deleteElement(); // Delete file/directory
                    break;
                case 5:
                    moveElement(); // Move file/directory
                    break;
                case 6:
                    search(); // Search file/directory
                    break;
                case 7:
                    printDirectoryTree(); // Print directory tree
                    break;
                case 8:
                    sortDirectoryByDate(); // Sort contents by date created
                    break;
                case 9:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                case 0:
                    fs.getRoot().print(""); // Print directory tree for debugging
                default:
                    System.out.println("Invalid option. Please try again"); // Invalid option
            }
        }
    }
    
    /**
     * Changes the current directory based on user input.
     * Prompts the user to enter a new directory path and updates the current directory accordingly.
     */
    private static void changeDirectory() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
        Directory newDir = getDirectoryFromPath(newPath);
        
        currentDirectory = fs.changeDirectory(currentDirectory, newDir);
        System.out.println("*************************");
    }
    
    /**
     * Lists the contents (files and directories) of the current directory.
     */
    private static void listContents() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        fs.listContents(currentDirectory);
        System.out.println("*************************");
        
    }
    
    /**
     * Creates a new file or directory in the current directory based on user input.
     * Prompts the user to specify whether to create a file or directory, and then prompts for the name.
     */
    private static void createElement() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Create file or directory (f/d): ");
        String ch = scanner.nextLine();
        String name;
        switch (ch) {
            case "f":
            case "F":
                System.out.print("Enter file name to create: ");
                name = scanner.nextLine();
                fs.createFile(name, currentDirectory);
                break;
            case "d":
            case "D":
                System.out.print("Enter directory name to create: ");
                name = scanner.nextLine();
                fs.createDirectory(name, currentDirectory);
                break;
            default:
                System.out.println("Wrong input returning to menu...");
        }
        System.out.println("*************************");
    }
    
    /**
     * Deletes a file or directory in the current directory based on user input.
     * Prompts the user to specify whether to delete a file or directory, and then prompts for the name.
     */
    private static void deleteElement() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Delete file or directory (f/d): ");
        String ch = scanner.nextLine();
        String name;
        switch (ch) {
            case "f":
            case "F":
                System.out.print("Enter file name to delete: ");
                name = scanner.nextLine();
                fs.deleteFile(name, currentDirectory);
                break;
            case "d":
            case "D":
                System.out.print("Enter directory name to delete: ");
                name = scanner.nextLine();
                fs.deleteDirectory(name, currentDirectory);
                break;
            default:
                System.out.println("Wrong input returning to menu...");
        }
        System.out.println("*************************");
    }
    
    /**
     * Moves a file or directory to a new directory based on user input.
     * Prompts the user to enter the name of the element to move and the new directory path.
     */
    private static void moveElement() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Enter the name of file/directory to move: ");
        String name = scanner.nextLine();
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
        Directory newParent = getDirectoryFromPath(newPath);
        
        if (newParent != null) {
            fs.moveElement(name, currentDirectory, newParent);
            System.out.println("File moved: " + name + " to " + newPath);
        }
        System.out.println("*************************");
    }
    
    /**
     * Searches for files or directories based on user input query.
     * Prompts the user to enter a search query and displays the search result.
     */
    private static void search() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        System.out.println("Search result: " + fs.search(query));
        System.out.println("*************************");
    }
    
    /**
     * Prints the directory tree from the current directory to root.
     */
    private static void printDirectoryTree() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        fs.printDirectoryTree(currentDirectory);
        System.out.println("*************************");
    }
    
    /**
     * Sorts the contents of the current directory by date created and prints the sorted list.
     */
    private static void sortDirectoryByDate() {
        System.out.println("*************************");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        fs.sortDirectoryByDate(currentDirectory);
        System.out.println("*************************");
    }
    
    /**
     * Retrieves the directory from the given path.
     * Parses the path and navigates through the directory structure to find the target directory.
     *
     * @param path The path to the directory (e.g., "/home/user/Documents").
     * @return The directory object if found, or null if not found.
     */
    private static Directory getDirectoryFromPath(String path) {
        Directory rootDirectory = fs.getRoot();
        // removes the first "/" that indicates its path from root
        String[] directoryNames = path.substring(1).split("/", -1);
        Directory current = rootDirectory;
        
        for (String dirName : directoryNames) {
            if (dirName.isEmpty()) {
                continue;
            }
            FileSystemElement child = current.getChild(dirName);
            if (child instanceof Directory) {
                current = (Directory) child;
            } else {
                return null;
            }
        }
        return current;
    }
    
}
