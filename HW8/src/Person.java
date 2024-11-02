import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class represents a person in a social network.
 * Each person has a name, age, list of hobbies, and a timestamp indicating when they were added to the network.
 */
public class Person {
    String name;
    int age;
    List<String> hobbies;
    Date timestamp;
    
    /**
     * This constructor is used to create a new person.
     * It initializes the name, age, and hobbies of the person with the given values.
     * It also initializes the timestamp with the current date and time.
     *
     * @param name    The name of the person. It should be a non-null and non-empty string.
     * @param age     The age of the person. It should be a positive integer.
     * @param hobbies The list of hobbies of the person. It should be a non-null list of strings.
     */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
    }
    
    /**
     * This method is used to get a string representation of the person.
     * It returns a string containing the name, age, and hobbies of the person. The timestamp is also included.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        // Format the timestamp as a string
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Join the list of hobbies into a comma-separated string
        String hobbiesString = String.join(", ", hobbies);
        return name + " (Age: " + age + ", Hobbies: [" + hobbiesString + "], Timestamp: " + formatter.format(timestamp) + ")";
    }
}