import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * This class is used to generate an input file for testing the performance of a stock management system.
 */
public class InputFileGenerator {
    /**
     * The main method of the InputFileGenerator class.
     * It creates an instance of the InputFileGenerator and calls the generate method with the command line arguments.
     *
     * @param args The command line arguments. These should include the output file name, the size of the tree, and the number of ADD, REMOVE, UPDATE, and SEARCH operations.
     */
    public static void main(String[] args) {
        InputFileGenerator generator = new InputFileGenerator();
        generator.generate(args);
    }
    
    /**
     * This method generates an input file for testing the performance of a stock management system.
     * It takes an array of arguments which include the output file name, the size of the tree, and the number of ADD, REMOVE, UPDATE, and SEARCH operations.
     * It generates random stock data and writes the operations to the output file.
     *
     * @param args An array of arguments which include the output file name, the size of the tree, and the number of ADD, REMOVE, UPDATE, and SEARCH operations.
     */
    public void generate(String[] args) {
        if (args.length != 6) {
            System.out.println("Usage: java InputFileGenerator <output_file> <treeSize> <numAdd> <numRemove> <numUpdate> <numSearch>");
            return;
        }
        String filename = args[0];
        int treeSize = Integer.parseInt(args[1]);
        int numAdd = Integer.parseInt(args[2]);
        int numRemove = Integer.parseInt(args[3]);
        int numUpdate = Integer.parseInt(args[4]);
        int numSearch = Integer.parseInt(args[5]);
        
        // List to store symbols of stocks added
        List<String> addedSymbols = new ArrayList<>();
        Random random = new Random();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Loop to generate AVL NODES commands
            for (int i = 0; i < treeSize; i++) {
                String symbol = Generator.randomSymbol(4);
                double price = 10 + (150 - 10) * random.nextDouble();
                long volume = 100000 + random.nextInt(100000);
                long marketCap = 100000L + random.nextInt(100000);
                writer.write(String.format("ADD %s %s %d %d\n", symbol, df.format(price), volume, marketCap));
                addedSymbols.add(symbol);
            }
            
            // List to store operation commands
            List<String> operations = new ArrayList<>();
            
            // Calculate the maximum number of operations
            int maxOperations = Math.max(Math.max(numAdd, numRemove), Math.max(numUpdate, numSearch));
            
            // Loop to generate operations in a mixed manner
            for (int i = 0; i < maxOperations; i++) {
                // Generate ADD command
                if (i < numAdd) {
                    String symbol = Generator.randomSymbol(4);
                    double price = 10 + (150 - 10) * random.nextDouble();
                    long volume = 100000 + random.nextInt(100000);
                    long marketCap = 100000L + random.nextInt(100000);
                    operations.add(String.format("ADD %s %s %d %d\n", symbol, df.format(price), volume, marketCap));
                    addedSymbols.add(symbol);
                }
                
                // Generate REMOVE command
                if (i < numRemove && !addedSymbols.isEmpty()) {
                    int index = random.nextInt(addedSymbols.size());
                    String removeSymbol = addedSymbols.get(index);
                    operations.add(String.format("REMOVE %s\n", removeSymbol));
                    addedSymbols.remove(index);
                }
                
                // Generate SEARCH command
                if (i < numSearch && !addedSymbols.isEmpty()) {
                    String searchSymbol = addedSymbols.get(random.nextInt(addedSymbols.size()));
                    operations.add(String.format("SEARCH %s\n", searchSymbol));
                }
                
                // Generate UPDATE command
                if (i < numUpdate && !addedSymbols.isEmpty()) {
                    String updateSymbol = addedSymbols.get(random.nextInt(addedSymbols.size()));
                    String newSymbol = Generator.randomSymbol(4);
                    double newPrice = 10 + (150 - 10) * random.nextDouble();
                    long newVolume = 100000 + random.nextInt(100000);
                    long newMarketCap = 100000L + random.nextInt(100000);
                    operations.add(String.format("UPDATE %s %s %s %d %d\n", updateSymbol, newSymbol, df.format(newPrice), newVolume, newMarketCap));
                    
                    
                    addedSymbols.remove(updateSymbol);
                    addedSymbols.add(newSymbol);
                }
            }
            
            for (String operation : operations) {
                writer.write(operation);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}