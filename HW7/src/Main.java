import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * The Main class provides a menu for the user to choose an operation to perform.
 * The operations include performing a performance analysis with an input file, performing a simple performance analysis, and exiting the application.
 */
public class Main {
    /**
     * The main method of the application.
     * It provides a menu for the user to choose an operation to perform.
     * The operations include performing a performance analysis with an input file, performing a simple performance analysis,
     * performs simple avl tree test and exiting the application.
     *
     * @param args The command line arguments. Not used in this method.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("Please choose an operation:");
            System.out.println("1. Perform performance analysis with input file");
            System.out.println("2. Perform simple performance analysis");
            System.out.println("3. Perform AVL Tree Test with input file");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            System.out.println("-----------------------------------------");
            
            switch (choice) {
                case 1:
                    PerformanceAnalysisWithInputFile();
                    break;
                case 2:
                    SimplePerformanceAnalysis();
                    break;
                case 3:
                    AVLTreeTestWithInput();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    /**
     * This method tests the AVLTree with user input.
     * It prompts the user to enter the input file and operation values (NODE, ADD, REMOVE, SEARCH, UPDATE).
     * It then generates the input file and processes the operations on the AVLTree.
     * Finally, it prints the performance of each operation in microseconds.
     */
    public static void AVLTreeTestWithInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the <input_file>, NODE, ADD, REMOVE, SEARCH, UPDATE (separated by spaces): ");
        String[] inputs = scanner.nextLine().split(" ");
        
        String inputFile = inputs[0];
        int nodeValue = Integer.parseInt(inputs[1]);
        int addValue = Integer.parseInt(inputs[2]);
        int removeValue = Integer.parseInt(inputs[3]);
        int searchValue = Integer.parseInt(inputs[4]);
        int updateValue = Integer.parseInt(inputs[5]);
        
        
        InputFileGenerator generator = new InputFileGenerator();
        generator.generate(new String[]{inputFile, String.valueOf(nodeValue), String.valueOf(addValue), String.valueOf(removeValue), String.valueOf(searchValue), String.valueOf(updateValue)});
        List<Long> times = ProcessAndAnalyzePerformance(inputFile, nodeValue);
        
        System.out.println("ADD Operation Performance: " + times.get(0) + " microseconds");
        System.out.println("REMOVE Operation Performance: " + times.get(1) + " microseconds");
        System.out.println("SEARCH Operation Performance: " + times.get(2) + " microseconds");
        System.out.println("UPDATE Operation Performance: " + times.get(3) + " microseconds");
        
    }
    
    /**
     * This method performs a performance analysis with an input file.
     * It prompts the user to enter the input file, start value, end value, and step size.
     * It then generates an input file for each step size from the start value to the end value.
     * It processes and analyzes the performance of the operations in the input file and displays the performance.
     */
    public static void PerformanceAnalysisWithInputFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the <input_file>, start value, end value, and step size (separated by spaces): ");
        String[] inputs = scanner.nextLine().split(" ");
        String inputFile = inputs[0];
        int startValue = Integer.parseInt(inputs[1]);
        int endValue = Integer.parseInt(inputs[2]);
        int stepSize = Integer.parseInt(inputs[3]);
        
        List<Integer> dataPointsX = new ArrayList<>();
        List<Long> addTimes = new ArrayList<>();
        List<Long> removeTimes = new ArrayList<>();
        List<Long> searchTimes = new ArrayList<>();
        List<Long> updateTimes = new ArrayList<>();
        
        for (int i = startValue; i <= endValue; i += stepSize) {
            InputFileGenerator generator = new InputFileGenerator();
            generator.generate(new String[]{inputFile, String.valueOf(i), String.valueOf(10), String.valueOf(10), String.valueOf(10), String.valueOf(10)});
            List<Long> data = ProcessAndAnalyzePerformance(inputFile, i);
            
            addTimes.add(data.get(0) / 10);
            removeTimes.add(data.get(1) / 10);
            searchTimes.add(data.get(2) / 10);
            updateTimes.add(data.get(3) / 10);
            dataPointsX.add(i);
        }
        
        displayPerformance("ADD Operation Performance", dataPointsX, addTimes);
        displayPerformance("SEARCH Operation Performance", dataPointsX, searchTimes);
        displayPerformance("REMOVE Operation Performance", dataPointsX, removeTimes);
        displayPerformance("UPDATE Operation Performance", dataPointsX, updateTimes);
    }
    
    /**
     * This method performs a simple performance analysis.
     * It creates a list of sizes for the performance analysis, ranging from 10 to 1000, with a step size of 10.
     * It then calls the performPerformanceAnalysis method with the list of sizes.
     */
    public static void SimplePerformanceAnalysis() {
        // Create a list of sizes for the performance analysis
        List<Integer> sizes = new ArrayList<>();
        for (int i = 10; i <= 1000; i += 10) {
            sizes.add(i);
        }
        // Perform a simple performance analysis
        PerformanceAnalysisWithRandomValues(sizes);
    }
    
    
    /**
     * Performs a performance analysis on the StockDataManager.
     * It measures the time taken for ADD, SEARCH, UPDATE, and REMOVE operations and displays the performance.
     *
     * @param sizes The list of sizes for the performance analysis.
     */
    private static void PerformanceAnalysisWithRandomValues(List<Integer> sizes) {
        // Lists to store the data points for the x-axis and the times for each operation
        List<Integer> dataPointsX = new ArrayList<>();
        List<Long> addTimes = new ArrayList<>();
        List<Long> removeTimes = new ArrayList<>();
        List<Long> searchTimes = new ArrayList<>();
        List<Long> updateTimes = new ArrayList<>();
        
        // For each size, generate random strings and measure the time for each operation
        for (int size : sizes) {
            dataPointsX.add(size);
            
            // Create a new StockDataManager
            final StockDataManager manager = new StockDataManager();
            // Generate random stocks and add them to the manager
            List<String> generatedStrings = generateRandomStocks(manager, size);
            
            // Measure time for SEARCH operation
            // The operation is to search a random stock from the generated strings
            // The post operation does nothing
            searchTimes.add(measureTime(() -> {
                String randomString = getRandomElement(generatedStrings);
                manager.searchStock(randomString);
                return randomString;
            }, _ -> {
            }));
            
            // Measure time for UPDATE operation
            // The operation is to update a random stock from the generated strings
            // The post operation does nothing
            updateTimes.add(measureTime(() -> {
                String randomString = getRandomElement(generatedStrings);
                manager.updateStock(randomString, Generator.randomSymbol(4), Math.random() * 100, (long) (Math.random() * 100000), (long) (Math.random() * 100000));
                return randomString;
            }, _ -> {
            }));
            
            // Measure time for ADD operation
            // The operation is to add a new random stock
            // The post operation is to remove the added stock
            addTimes.add(measureTime(() -> {
                String randomString = Generator.randomSymbol(4);
                manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 100000), (long) (Math.random() * 100000));
                return randomString;
            }, manager::removeStock));
            
            // Measure time for REMOVE operation
            // The operation is to remove a random stock from the generated strings
            // The post operation is to add the removed stock back
            removeTimes.add(measureTime(() -> {
                String randomString = getRandomElement(generatedStrings);
                manager.removeStock(randomString);
                return randomString;
            }, (randomString) -> manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 100000), (long) (Math.random() * 100000))));
            
        }
        
        // Display the performance of each operation
        displayPerformance("ADD Operation Performance", dataPointsX, addTimes);
        displayPerformance("SEARCH Operation Performance", dataPointsX, searchTimes);
        displayPerformance("REMOVE Operation Performance", dataPointsX, removeTimes);
        displayPerformance("UPDATE Operation Performance", dataPointsX, updateTimes);
    }
    
    
    /**
     * Displays the performance of an operation.
     * It creates a GUIVisualization frame and sets the title of the frame to the given title.
     * It then makes the frame visible.
     *
     * @param title       The title of the frame.
     * @param dataPointsX The x-axis data points for the line chart.
     * @param dataPointsY The y-axis data points for the line chart.
     */
    private static void displayPerformance(String title, List<Integer> dataPointsX, List<Long> dataPointsY) {
        GUIVisualization frame = new GUIVisualization("scatter", dataPointsX, dataPointsY);
        frame.setTitle(title);
        frame.setVisible(true);
    }
    
    /**
     * Generates a list of random strings and adds them to the StockDataManager.
     * It generates a random string of length 4 for each size, adds the string to the list, and adds or updates the stock in the StockDataManager.
     *
     * @param manager The StockDataManager to add or update the stock.
     * @param size    The number of random strings to generate.
     * @return The list of generated random strings.
     */
    private static List<String> generateRandomStocks(StockDataManager manager, int size) {
        List<String> generatedStrings = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String randomString = Generator.randomSymbol(4);
            generatedStrings.add(randomString);
            manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 100000), (long) (Math.random() * 100000));
        }
        return generatedStrings;
    }
    
    /**
     * Measures the time taken to execute a given operation and its post operation.
     * The operation and post operation are executed 100 times, and the total time taken is returned.
     *
     * @param <T>           The type of the result produced by the operation and consumed by the post operation.
     * @param operation     The operation to be timed. This is a Supplier that returns a result of type T.
     * @param postOperation The operation to be executed after the main operation. This is a Consumer that takes a result of type T.
     * @return The total time taken to execute the operation and post operation 100 times, in microseconds.
     */
    private static <T> long measureTime(Supplier<T> operation, Consumer<T> postOperation) {
        long startTime, endTime, totalTime = 0;
        for (int i = 0; i < 100; i++) {
            
            startTime = System.nanoTime();
            T result = operation.get();
            endTime = System.nanoTime();
            
            postOperation.accept(result);
            
            totalTime += (endTime - startTime);
        }
        return totalTime / (100 * 1000);
    }
    
    /**
     * Returns a random element from a given list.
     * The index of the element is chosen randomly.
     *
     * @param list The list from which to get a random element.
     * @return A random element from the given list.
     */
    private static String getRandomElement(List<String> list) {
        int randomIndex = (int) (Math.random() * (list.size()));
        return list.get(randomIndex);
    }
    
    /**
     * This method processes and analyzes the performance of various operations on a StockDataManager.
     * It reads commands from an input file and performs the corresponding operations on the StockDataManager.
     * It measures the time taken for each operation and returns a list of the total times for each operation.
     *
     * @param inputFile The path to the input file containing the commands to be processed.
     * @param size      The size of the data to be processed.
     * @return A list of the total times for each operation (ADD, REMOVE, SEARCH, UPDATE).
     */
    private static List<Long> ProcessAndAnalyzePerformance(String inputFile, int size) {
        StockDataManager manager = new StockDataManager();
        long addTimes = 0L, removeTimes = 0L, searchTimes = 0L, updateTimes = 0L;
        int nodeCounter = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String command = tokens[0];
                long startTime, endTime;
                
                switch (command) {
                    case "ADD":
                        nodeCounter++;
                        startTime = System.nanoTime();
                        manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                        endTime = System.nanoTime();
                        if (nodeCounter > size)
                            addTimes += (endTime - startTime) / 1000;
                        break;
                    case "REMOVE":
                        startTime = System.nanoTime();
                        manager.removeStock(tokens[1]);
                        endTime = System.nanoTime();
                        removeTimes += (endTime - startTime) / 1000;
                        break;
                    case "SEARCH":
                        startTime = System.nanoTime();
                        manager.searchStock(tokens[1]);
                        endTime = System.nanoTime();
                        searchTimes += (endTime - startTime) / 1000;
                        break;
                    case "UPDATE":
                        startTime = System.nanoTime();
                        manager.updateStock(tokens[1], tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                        endTime = System.nanoTime();
                        updateTimes += (endTime - startTime) / 1000;
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(addTimes, removeTimes, searchTimes, updateTimes);
        
    }
    
}
