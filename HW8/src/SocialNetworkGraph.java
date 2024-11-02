import java.util.*;

/**
 * This class represents a social network graph.
 * It maintains a map of people and their friendships.
 * The 'people' map is a mapping from a person's name to their Person object.
 * The 'friendships' map is a mapping from a Person object to a list of their friends (Person objects).
 */
public class SocialNetworkGraph {
    /**
     * A map from a person's name to their Person object.
     * Each key is a person's name, and each value is the corresponding Person object.
     */
    Map<String, Person> people = new HashMap<>();
    
    /**
     * A map from a Person object to a list of their friends.
     * Each key is a Person object, and each value is a list of that person's friends (Person objects).
     */
    Map<Person, List<Person>> friendships = new HashMap<>();
    
    /**
     * This method is used to add a new person to the social network.
     * It creates a new Person object and adds it to the 'people' map using the person's name as the key.
     * It also initializes an empty list of friends for the new person in the 'friendships' map.
     * Finally, it prints a message indicating that the person has been added.
     *
     * @param name    The name of the person to be added. It should be a non-null and non-empty string.
     * @param age     The age of the person to be added. It should be a positive integer.
     * @param hobbies The list of hobbies of the person to be added. It should be a non-null list of strings.
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        // Check if a person with the same name already exists in the network
        Person p = people.get(name);
        if (p != null) {
            // If a person with the same name exists, print a message and return without adding the new person
            System.out.println("Person already exists in the network.");
            return;
        }
        Person person = new Person(name, age, hobbies);
        people.put(name, person);
        // Initialize an empty list of friends for the new person in the 'friendships' map
        friendships.put(person, new ArrayList<>());
        System.out.println("Person added: " + person);
    }
    
    /**
     * This method is used to add a friendship between two persons in the social network.
     * It retrieves the Person objects corresponding to the given names from the 'people' map.
     * If both persons exist in the network, it adds each person to the other's list of friends in the 'friendships' map.
     * Finally, it prints a message indicating that the friendship has been added.
     * If one or both persons do not exist in the network, it prints an error message.
     *
     * @param name1 The name of the first person in the friendship. It should correspond to a person in the network.
     * @param name2 The name of the second person in the friendship. It should correspond to a person in the network.
     */
    public void addFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            System.out.println("Friendship added between " + person1.name + " and " + person2.name);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }
    
    /**
     * This method is used to remove a person from the social network.
     * It retrieves the Person object corresponding to the given name from the 'people' map.
     * If the person exists in the network, it removes the person from the 'people' map and the 'friendships' map.
     * It also removes the person from the friends list of all other persons in the network.
     * Finally, it prints a message indicating that the person has been removed.
     * If the person does not exist in the network, it prints an error message.
     *
     * @param name The name of the person to be removed. It should correspond to a person in the network.
     */
    public void removePerson(String name) {
        Person person = people.get(name);
        if (person != null) {
            // Remove the person from the network
            people.remove(name);
            // Remove the person from the friends list of all other persons
            friendships.remove(person);
            for (List<Person> friends : friendships.values()) {
                friends.remove(person);
            }
            System.out.println("Person removed: " + person);
        } else {
            System.out.println("Person not found in the network.");
        }
    }
    
    /**
     * This method is used to remove a friendship between two persons in the social network.
     * It retrieves the Person objects corresponding to the given names from the 'people' map.
     * If both persons exist in the network, it removes each person from the other's list of friends in the 'friendships' map.
     * Finally, it prints a message indicating that the friendship has been removed.
     * If one or both persons do not exist in the network, it prints an error message.
     *
     * @param name1 The name of the first person in the friendship. It should correspond to a person in the network.
     * @param name2 The name of the second person in the friendship. It should correspond to a person in the network.
     */
    public void removeFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            // Remove the friendship in both directions
            friendships.get(person1).remove(person2);
            friendships.get(person2).remove(person1);
            System.out.println("Friendship removed between " + person1.name + " and " + person2.name);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }
    
    /**
     * This method is used to find the shortest path between two persons in the social network using Breadth-First Search (BFS).
     * It retrieves the Person objects corresponding to the given names from the 'people' map.
     * If both persons exist in the network and are not the same person, it performs a BFS to find the shortest path between them.
     * It maintains a queue of persons to visit, a set of visited persons, and a map of previous persons to reconstruct the path.
     * It starts the BFS from the start person, marking it as visited and adding it to the queue.
     * Then it enters a loop where it visits each person in the queue, checks if it's the end person, and if not, adds its unvisited neighbors to the queue.
     * If it finds the end person, it calls the 'printPath' method to print the shortest path and returns.
     * If it finishes visiting all persons without finding the end person, it prints a message indicating that no path was found.
     * If one or both persons do not exist in the network, or if they are the same person, it prints an appropriate error message.
     *
     * @param startName The name of the start person for the path. It should correspond to a person in the network.
     * @param endName   The name of the end person for the path. It should correspond to a person in the network.
     */
    public void findShortestPath(String startName, String endName) {
        Person start = people.get(startName);
        Person end = people.get(endName);
        
        if (start == null || end == null) {
            System.out.println("One or both persons not found in the network.");
        } else if (start == end) {
            System.out.println("Start and end are the same person.");
        } else {
            Queue<Person> queue = new LinkedList<>();
            Set<Person> visited = new HashSet<>();
            Map<Person, Person> prev = new HashMap<>();
            
            queue.add(start);
            visited.add(start);
            prev.put(start, null);
            
            while (!queue.isEmpty()) {
                Person current = queue.poll();
                // If the current person is the end person, print the path and return
                if (current == end) {
                    printPath(start, end, prev);
                    return;
                }
                for (Person neighbor : friendships.get(current)) {
                    // If the neighbor has not been visited, add it to the queue, mark it as visited, and set the previous person
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        prev.put(neighbor, current);
                    }
                }
            }
            
            System.out.println("No path found between " + start.name + " and " + end.name);
        }
    }
    
    /**
     * This private method is used to print the shortest path between two persons in the social network.
     * It takes as input the start and end persons and a map of previous persons used to reconstruct the path.
     * It creates a list of persons representing the path from the end person to the start person by following the previous persons.
     * It then reverses this list to get the path from the start person to the end person.
     * Finally, it prints this path.
     *
     * @param start The start person for the path. It should correspond to a person in the network.
     * @param end   The end person for the path. It should correspond to a person in the network.
     * @param prev  A map from each person to the person visited before it in the path.
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        Deque<String> path = new ArrayDeque<>();
        // Reconstruct the path from end to start
        for (Person at = end; at != null; at = prev.get(at)) {
            path.addFirst(at.name);
            if (at == start) {
                break;
            }
        }
        System.out.println("Shortest path: " + String.join(" -> ", path));
    }
    
    /**
     * This method is used to suggest friends for a person in the social network.
     * It takes as input the name of the person and the number of friend suggestions to generate.
     * It retrieves the Person object corresponding to the given name from the 'people' map.
     * If the person exists in the network, it calculates a score for each other person in the network who is not already a friend of the person.
     * The score is based on the number of mutual friends and common hobbies between the person and the other person.
     * It then sorts the other persons by their scores in descending order and prints the top N friend suggestions along with their scores.
     * If the person does not exist in the network, it prints an error message.
     *
     * @param name The name of the person for whom to suggest friends. It should correspond to a person in the network.
     * @param N    The number of friend suggestions to generate. It should be a non-negative integer.
     */
    public void suggestFriends(String name, int N) {
        Person person = people.get(name);
        if (person == null) {
            System.out.println("Person not found in the network.");
            return;
        }
        
        // Create a map to store the scores for each other person in the network
        Map<Person, Double> scores = new HashMap<>();
        // Iterate over all other persons in the network
        for (Person other : people.values()) {
            // If the other person is not the same as the person and is not already a friend of the person
            if (other != person && !friendships.get(person).contains(other)) {
                // Initialize the score for the other person
                double score = 0;
                
                // Add the number of mutual friends to the score, with each mutual friend contributing 1.0 to the score
                score += getMutualFriends(person, other).size() * 1.0;
                
                // Add the number of common hobbies to the score, with each common hobby contributing 0.5 to the score
                score += getCommonHobbies(person, other).size() * 0.5;
                
                // Add the score for the other person to the map
                scores.put(other, score);
            }
        }
        
        // Create a list of entries from the scores map and sort it in descending order of scores
        List<Map.Entry<Person, Double>> entries = new ArrayList<>(scores.entrySet());
        entries.sort(Map.Entry.<Person, Double>comparingByValue().reversed());
        
        
        // Print the top N friend suggestions for the person. If there is not enough suggestions, print all available suggestions which is less the N.
        // Also user can request 0 suggestions, in that case print nothing.
        System.out.println("Top " + N + " friend suggestions for " + person.name + ":");
        // Iterate over the top N entries in the sorted list
        for (int i = 0; i < N && i < entries.size(); i++) {
            Map.Entry<Person, Double> entry = entries.get(i);
            // Print the name and score of the other person, as well as the number of mutual friends and common hobbies
            System.out.println(entry.getKey().name + " (Score: " + entry.getValue()
                + ", Mutual Friends: " + getMutualFriends(person, entry.getKey()).size()
                + ", Common Hobbies: " + getCommonHobbies(person, entry.getKey()).size() + ")");
            
        }
    }
    
    /**
     * This method is used to count the number of clusters in the social network.
     * A cluster is defined as a group of persons where each person is a friend of "at least one" other person in the group, and there are no friends outside the group.
     * It uses a Breadth-First Search (BFS) to find all clusters.
     * It maintains a set of visited persons to avoid visiting the same person twice.
     * It starts from each person in the network, and if the person has not been visited, it performs a BFS to find all persons in the same cluster.
     * It adds all persons visited during the BFS to the 'visited' set and the 'cluster' list.
     * It then prints the persons in the cluster and increments the cluster count.
     * Finally, it prints the total number of clusters.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;
        
        System.out.println("Counting clusters in the social network...");
        
        // Iterate over all persons in the network
        for (Person person : people.values()) {
            // If the person has not been visited, perform a BFS to find all persons in the same cluster
            if (!visited.contains(person)) {
                // A list to store all persons in the same cluster
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster); // Perform BFS from the current person
                
                // Skip clusters with only one person
                if (cluster.size() <= 1) {
                    continue;
                }
                // Increment the cluster count
                clusterCount++;
                
                // Convert the list of persons in the cluster to a string and print it
                String clusterString = String.join("\n", cluster.stream().map(p -> p.name).toList());
                System.out.println("Cluster " + clusterCount + ":\n" + clusterString);
            }
        }
        
        // Print the total number of clusters
        System.out.println("Number of clusters: " + clusterCount);
    }
    
    /**
     * This private method is used to perform a Breadth-First Search (BFS) from a start person.
     * It takes as input the start person, a set of visited persons, and a list to store the persons in the same cluster.
     * It maintains a queue of persons to visit, starting with the start person.
     * It enters a loop where it visits each person in the queue, adds it to the 'visited' set and the 'cluster' list, and adds its unvisited neighbors to the queue.
     * The BFS ends when there are no more persons to visit.
     *
     * @param start   The start person for the BFS. It should correspond to a person in the network.
     * @param visited A set of persons that have already been visited.
     * @param cluster A list to store the persons in the same cluster as the start person.
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            // Visit the current person
            Person current = queue.poll();
            // Add the current person to the cluster
            cluster.add(current);
            // Add unvisited neighbors to the queue
            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
    
    /**
     * Return the list of mutual friends between two persons in the social network.
     *
     * @param person1 The first person. It should correspond to a person in the network.
     * @param person2 The second person. It should correspond to a person in the network.
     * @return A list of mutual friends between the first person and the second person.
     */
    private List<Person> getMutualFriends(Person person1, Person person2) {
        List<Person> mutualFriends = new ArrayList<>(friendships.get(person1));
        mutualFriends.retainAll(friendships.get(person2)); // Retain only the common friends
        return mutualFriends;
    }
    
    /**
     * Return the list of common hobbies between two persons in the social network.
     *
     * @param person1 The first person. It should correspond to a person in the network.
     * @param person2 The second person. It should correspond to a person in the network.
     * @return A list of common hobbies between the first person and the second person.
     */
    private List<String> getCommonHobbies(Person person1, Person person2) {
        List<String> commonHobbies = new ArrayList<>(person1.hobbies);
        commonHobbies.retainAll(person2.hobbies); // Retain only the common hobbies
        return commonHobbies;
    }
    
}
