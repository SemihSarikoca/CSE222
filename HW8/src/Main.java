import java.util.*;

/**
 * This is the main class for the social network analysis application.
 * It provides a menu for the user to interact with the social network graph.
 */
public class Main {
    /**
     * The main method of the application.
     * It creates a new social network graph and a scanner for user input.
     * It then enters a loop where it displays a menu and performs the selected operation.
     */
    public static void main(String[] args) {
        // Create a new social network graph and a scanner for user input
        SocialNetworkGraph network = new SocialNetworkGraph();
        Scanner scanner = new Scanner(System.in);
        
        // Enter a loop where we display a menu and perform the selected operation
        while (true) {
            // Display the menu
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");
            
            // Get the selected option from the user
            String option = scanner.nextLine();
            
            // Perform the selected operation
            switch (option) {
                case "1":
                    addPerson(scanner, network);
                    break;
                case "2":
                    removePerson(scanner, network);
                    break;
                case "3":
                    addFriendship(scanner, network);
                    break;
                case "4":
                    removeFriendship(scanner, network);
                    break;
                case "5":
                    findShortestPath(scanner, network);
                    break;
                case "6":
                    suggestFriends(scanner, network);
                    break;
                case "7":
                    network.countClusters();
                    break;
                case "8":
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    /**
     * This method is used to add a person to the social network.
     * It prompts the user for the person's name, age, and hobbies, and then adds the person to the network.
     *
     * @param scanner The scanner for user input.
     * @param network The social network graph.
     */
    private static void addPerson(Scanner scanner, SocialNetworkGraph network) {
        // Prompt the user for the person's name, age, and hobbies
        System.out.println("-----ADD PERSON TO NETWORK-----");
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        
        int age;
        while (true) {
            try {
                System.out.print("Enter age: ");
                age = scanner.nextInt();
                scanner.nextLine();  // consume newline left-over
                assert age >= 0;
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an positive integer.");
                scanner.nextLine();  // consume the invalid input
            } catch (AssertionError e) {
                System.out.println("Invalid age input. Age must be non-negative integer.");
            }
        }
        
        System.out.print("Enter hobbies (comma-separated): ");
        List<String> hobbies = List.of(scanner.nextLine().split(","));
        // Add the person to the network
        network.addPerson(name, age, hobbies);
    }
    
    /**
     * This method is used to remove a person from the social network.
     * It prompts the user for the person's name, and then removes the person from the network.
     *
     * @param scanner The scanner for user input.
     * @param network The social network graph.
     */
    private static void removePerson(Scanner scanner, SocialNetworkGraph network) {
        // Prompt the user for the person's name
        System.out.println("-----REMOVE PERSON FROM NETWORK-----");
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        
        // Remove the person from the network
        network.removePerson(name);
    }
    
    /**
     * This method is used to add a friendship between two persons in the social network.
     * It prompts the user for the names of the two persons, and then adds a friendship between them in the network.
     *
     * @param scanner The scanner for user input.
     * @param network The social network graph.
     */
    private static void addFriendship(Scanner scanner, SocialNetworkGraph network) {
        // Prompt the user for the names of the two persons
        System.out.println("-----ADD FRIENDSHIP-----");
        System.out.print("Enter first person’s name: ");
        String name1 = scanner.nextLine().trim();
        
        System.out.print("Enter second person’s name: ");
        String name2 = scanner.nextLine().trim();
        
        // Add a friendship between the two persons in the network
        network.addFriendship(name1, name2);
    }
    
    /**
     * This method is used to remove a friendship between two persons in the social network.
     * It prompts the user for the names of the two persons, and then removes the friendship between them in the network.
     *
     * @param scanner The scanner for user input.
     * @param network The social network graph.
     */
    private static void removeFriendship(Scanner scanner, SocialNetworkGraph network) {
        // Prompt the user for the names of the two persons
        System.out.println("-----REMOVE FRIENDSHIP-----");
        System.out.print("Enter first person’s name: ");
        String name1 = scanner.nextLine().trim();
        
        System.out.print("Enter second person’s name: ");
        String name2 = scanner.nextLine().trim();
        
        // Remove the friendship between the two persons in the network
        network.removeFriendship(name1, name2);
    }
    
    /**
     * This method is used to find the shortest path between two persons in the social network.
     * It prompts the user for the names of the source and destination persons, and then finds the shortest path between them in the network.
     *
     * @param scanner The scanner for user input.
     * @param network The social network graph.
     */
    private static void findShortestPath(Scanner scanner, SocialNetworkGraph network) {
        // Prompt the user for the names of the source and destination persons
        System.out.println("-----FIND SHORTEST PATH-----");
        System.out.print("Enter source person’s name: ");
        String source = scanner.nextLine().trim();
        
        System.out.print("Enter destination person’s name: ");
        String destination = scanner.nextLine().trim();
        
        // Find the shortest path between the source and destination persons in the network
        network.findShortestPath(source, destination);
    }
    
    /**
     * This method is used to suggest friends for a person in the social network.
     * It prompts the user for the person's name and the number of friends to suggest, and then suggests friends for the person in the network.
     *
     * @param scanner The scanner for user input.
     * @param network The social network graph.
     */
    private static void suggestFriends(Scanner scanner, SocialNetworkGraph network) {
        // Prompt the user for the person's name and the number of friends to suggest
        System.out.println("-----SUGGEST FRIENDS-----");
        System.out.print("Enter person’s name: ");
        String name = scanner.nextLine().trim();
        
        int num;
        while (true) {
            try {
                System.out.print("Enter number of friends to suggest: ");
                num = scanner.nextInt();
                scanner.nextLine();  // consume newline left-over
                assert num >= 0;
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an non-negative integer.");
                scanner.nextLine();  // consume the invalid input
            } catch (AssertionError e) {
                System.out.println("Invalid number of friends input. Please enter an non-negative integer.");
            }
        }
        
        // Suggest friends for the person in the network
        network.suggestFriends(name, num);
    }
}