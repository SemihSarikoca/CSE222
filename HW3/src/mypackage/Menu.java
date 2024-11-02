package mypackage;


import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * The Menu class represents a menu system for interacting with the Devices Inventory Management System.
 * It provides some methods for management.
 */
public class Menu {
    /**
     * The inventory associated with the menu.
     */
    private final Inventory inventory;
    /**
     * Scanner for user input.
     */
    private final Scanner scanner = new Scanner(System.in);
    
    /**
     * Constructs a new Menu object with an associated inventory.
     */
    public Menu() {
        inventory = new Inventory();
    }
    
    /**
     * Runs the menu system, allowing users to interact with the inventory management system.
     * <p>
     * This method provides a menu interface where users can select various options to manage the inventory,
     * such as adding devices, removing devices, updating device details, and exporting inventory reports.
     *
     * @see Inventory#addDevice(String, String, double, int)
     * @see Inventory#removeDevice(String, String)
     * @see Inventory#updateDeviceDetails(String, String, String)
     * @see Inventory#listAllDevices()
     * @see Inventory#findCheapestDevice()
     * @see Inventory#sortDevicesByPrice()
     * @see Inventory#calculateTotalInventoryValue()
     * @see Inventory#restockDevice(String, int, String)
     * @see Inventory#exportInventoryReport(String)
     */
    public void run() {
        int choice;
        
        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // Add device
                    addDevice();
                    
                    break;
                case 2:
                    // Remove a device
                    removeDevice();
                    
                    break;
                case 3:
                    // Update device details
                    updateDeviceDetails();
                    
                    break;
                case 4:
                    // List all devices
                    inventory.listAllDevices();
                    
                    break;
                case 5:
                    // Find the cheapest device
                    inventory.findCheapestDevice();
                    
                    break;
                case 6:
                    // Sort devices by price
                    inventory.sortDevicesByPrice();
                    
                    break;
                case 7:
                    // Calculate total inventory value
                    System.out.println("Total Inventory Value: " + inventory.calculateTotalInventoryValue());
                    
                    break;
                case 8:
                    // Restock device add or remove
                    restockDevice();
                    
                    break;
                case 9:
                    // Export inventory report
                    inventory.exportInventoryReport("src/inventory_report.txt");
                    
                    break;
                case 0:
                    // Exit
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
            System.out.println(); // Add a blank line for readability
        } while (choice != 0);
        
        scanner.close();
    }
    
    /**
     * Print menu as a text block.
     * Time Complexity: O(1)
     */
    private void printMenu() {
        System.out.print(
            """
            Menu System
            Welcome to the Electronics Inventory Management System!
                                                                      
            Please select an option:
            1. Add a new device
            2. Remove a device
            3. Update device details
            4. List all devices
            5. Find the cheapest device
            6. Sort devices by price
            7. Calculate total inventory value
            8. Restock a device
            9. Export inventory report
            0. Exit
                                 
            Enter your choice:\s""");
    }
    
    /**
     * Adds a new device to the inventory.
     * Time Complexity: Same as addDevice method of the inventory class
     *
     * @throws InputMismatchException if the user inputs invalid data for price or quantity
     * @see Inventory#addDevice(String, String, double, int)
     */
    private void addDevice() {
        System.out.print("Enter category name: ");
        String categoryNameToAdd = scanner.nextLine();
        System.out.print("Enter device name: ");
        String deviceNameToAdd = scanner.nextLine();
        System.out.print("Enter price: ");
        double priceToAdd = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantityToAdd = scanner.nextInt();
        
        inventory.addDevice(categoryNameToAdd, deviceNameToAdd, priceToAdd, quantityToAdd);
    }
    
    /**
     * Removes a device from the inventory.
     *
     * @throws NoSuchElementException if the device to remove is not found in the inventory
     * @see Inventory#removeDevice(String, String)
     */
    private void removeDevice() {
        System.out.print("Enter device category: ");
        String deviceCategoryToRemove = scanner.next();
        System.out.print("Enter device name: ");
        String deviceNameToRemove = scanner.next();
        
        inventory.removeDevice(deviceNameToRemove, deviceCategoryToRemove);
    }
    
    /**
     * Updates the details of a device in the inventory.
     *
     * @throws NoSuchElementException if the device to update is not found in the inventory
     * @throws InputMismatchException if the user inputs invalid data for price or quantity
     * @see Inventory#updateDeviceDetails(String, String, String)
     */
    private void updateDeviceDetails() {
        System.out.print("Enter the name of the device to update: ");
        String deviceNameToUpdate = scanner.nextLine();
        System.out.print("Enter new price (leave blank to keep current price): ");
        String newPrice = scanner.nextLine();
        System.out.print("Enter new quantity (leave blank to keep current quantity): ");
        String newQuantity = scanner.nextLine();
        
        inventory.updateDeviceDetails(deviceNameToUpdate, newPrice, newQuantity);
    }
    
    /**
     * Updates the details of a device in the inventory.
     *
     * @throws NoSuchElementException if the device to update is not found in the inventory
     * @throws InputMismatchException if the user inputs invalid data for price or quantity
     * @see Inventory#restockDevice(String, int, String)
     */
    private void restockDevice() {
        System.out.print("Enter the name of the device to restock: ");
        String deviceNameToRestock = scanner.next();
        
        System.out.print("Do you want to add or remove stock? (Add/Remove): ");
        String isAdd = scanner.next();
        
        if (!Objects.equals(isAdd, "Add") && !Objects.equals(isAdd, "Remove")) {
            System.out.println("Wrong input!");
            return;
        }
        
        System.out.print("Enter the quantity change: ");
        int quantityChange = scanner.nextInt();
        
        inventory.restockDevice(deviceNameToRestock, quantityChange, isAdd);
    }
    
    
}
