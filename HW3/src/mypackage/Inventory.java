package mypackage;

import mypackage.util.*;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * The Inventory class represents an inventory system that stores devices categorized into different lists.
 * Each list contains devices of the same category.
 */
class Inventory {
    /**
     * The list of device categories, where each category has a list of devices.
     */
    LinkedList<ArrayList<Device>> inventoryList;

    /**
     * Constructs a new Inventory object with an empty inventory list.
     */
    Inventory() {
        inventoryList = new LinkedList<>();
    }


    /**
     * Adds a new device to the inventory.
     * <p>
     * This method creates a new device object based on the provided category name, device name, price, and quantity. It
     * then adds the device to the inventory by finding the appropriate category list and appending the new device. If
     * the category doesn't exist in the inventory, a new category list is created, and the device is added to it.
     * <p>
     * Time Complexity: O(n) The time complexity of this method is proportional to the number of categories (n) in the
     * inventory. Since the number of categories are bounded by constant (in this example n=5 at max).
     * So time complexity can be reduced to constant time O(1).
     *
     * @param categoryName The category name of the device.
     * @param deviceName   The name of the device.
     * @param price        The price of the device.
     * @param quantity     The quantity of the device.
     */
    void addDevice(String categoryName, String deviceName, double price, int quantity) {
        Device newDevice;
        switch (categoryName) {
            case "TV":
                newDevice = new TV(deviceName, price, quantity);
                break;
            case "Headphones":
                newDevice = new Headphones(deviceName, price, quantity);
                break;
            case "Smart Phone":
                newDevice = new SmartPhone(deviceName, price, quantity);
                break;
            case "Smart Watch":
                newDevice = new SmartWatch(deviceName, price, quantity);
                break;
            case "Laptop":
                newDevice = new Laptop(deviceName, price, quantity);
                break;
            default:
                return;
        }
        System.out.println(categoryName + ", " + deviceName + ", " + price + "$, " + quantity + " amount added...");
        for (ArrayList<Device> deviceList : inventoryList) {
            if (deviceList.getFirst().getCategory().equals(categoryName)) {
                deviceList.add(newDevice);
                return;
            }
        }
        ArrayList<Device> newDeviceList = new ArrayList<>();
        newDeviceList.add(newDevice);
        inventoryList.add(newDeviceList);
    }

    /**
     * Removes a device from the inventory.
     * <p>
     * This method removes a device with the specified name and category from the inventory. It searches through all
     * device lists in the inventory to find the appropriate category first. Then, it iterates through devices in that
     * category to find the device with the specified name. If found, the device is removed from the list. If the
     * category becomes empty after removal, it is also removed from the inventory.
     * <p>
     * Time Complexity: O(n * m) The time complexity of this method is proportional to the product of the number of
     * device lists (m) and the average number of devices in each category (n) in the inventory.
     * <p>
     * Since device lists (category) is bounded by constant value (in this example 5), Time complexity can be considered as O(n).
     *
     * @param deviceName   The name of the device to be removed.
     * @param categoryName The category of the device to be removed.
     */
    void removeDevice(String deviceName, String categoryName) {
        for (ArrayList<Device> deviceList : inventoryList) {
            if (deviceList.getFirst().getCategory().equals(categoryName)) {
                for (Device device : deviceList) {
                    if (device.getName().equals(deviceName)) {
                        deviceList.remove(device);
                        if (deviceList.isEmpty()) {
                            inventoryList.remove(deviceList);
                        }
                        System.out.println("Device " + deviceName + " from category " + categoryName + " removed.");
                        return;
                    }
                }
            }
        }
        System.out.println("Device " + deviceName + " from category " + categoryName + " not found.");
    }

    /**
     * Updates details of a device in the inventory.
     * <p>
     * This method updates the price and quantity details of a device in the inventory based on the provided inputs. If
     * the new price or quantity is not specified (left blank), the current price or quantity of the device is retained.
     * <p>
     * Time Complexity: O(n * m) The time complexity of this method is proportional to the product of the number of
     * device lists (m) and the average number of devices in each category (n) in the inventory.
     * <p>
     * Since device lists (category) is bounded by constant value (in this example 5), Time complexity can be considered as O(n).
     *
     * @param deviceName       The name of the device to update.
     * @param newPriceInput    The new price for the device (leave blank to keep the current price).
     * @param newQuantityInput The new quantity for the device (leave blank to keep the current quantity).
     * @throws NumberFormatException if the new price or quantity input cannot be parsed to Double or Integer.
     */
    void updateDeviceDetails(String deviceName, String newPriceInput, String newQuantityInput) {
        for (ArrayList<Device> deviceList : inventoryList) {
            for (Device device : deviceList) {
                if (device.getName().equals(deviceName)) {
                    Double newPrice = newPriceInput.isEmpty() ? device.getPrice() : Double.parseDouble(newPriceInput.replace("$", ""));
                    Integer newQuantity = newQuantityInput.isEmpty() ? device.getQuantity() : ((Integer) Integer.parseInt(newQuantityInput));

                    device.setPrice(newPrice);
                    device.setQuantity(newQuantity);
                    System.out.println(
                        deviceName + " details updated: Price - " + newPriceInput + ", Quantity - " + newQuantity);
                    return;
                }
            }
        }

        System.out.println("Device not found in inventory.");
    }

    /**
     * Lists all devices in the inventory.
     * <p>
     * This method lists all devices currently stored in the inventory. It iterates through each device list in the
     * inventory and prints information about each device.
     * <p>
     * The time complexity of this method is O(n*m) where m is the number of categories in the inventoryList and n is the
     * average number of devices in each category.
     *
     * <p>
     * Since number of categories are bounded by constant (in this example 5), complexity can be considered as O(n)
     */
    void listAllDevices() {
        int line = 1;
        System.out.println("Device List:");
        for (ArrayList<Device> deviceList : inventoryList) {
            for (Device device : deviceList) {
                System.out.print(line++ + ". ");
                System.out.println(device);
            }
        }
    }

    /**
     * Finds and prints the cheapest device in the inventory.
     * <p>
     * This method searches through all devices in the inventory to find the one with the lowest price. It starts by
     * assuming the first device is the cheapest, then compares its price with all other devices. If a device with a
     * lower price is found, it becomes the new cheapest device.
     * <p>
     * Time Complexity: O(n * m) The time complexity of this method is proportional to the product of the number of
     * device lists (m) and the average number of devices in each category (n) in the inventory.
     * <p>
     * Since device lists (category) is bounded by constant value (in this example 5), Time complexity can be considered as O(n).
     *
     * @throws NullPointerException if the inventory is empty or if any device in the inventory has a null price.
     */
    void findCheapestDevice() {
        Device cheapest = inventoryList.getFirst().getFirst();
        for (ArrayList<Device> deviceList : inventoryList) {
            for (Device device : deviceList) {
                if (device.getPrice() < cheapest.getPrice()) {
                    cheapest = device;
                }
            }
        }
        System.out.println("The cheapest device is:");
        System.out.println(cheapest);
    }


    /**
     * Sorts devices in the inventory by price and display it.
     * <p>
     * Time Complexity: The time complexity of this method is O(n logn).
     * <p>
     * where n is the total number of devices in all the device lists in the inventory.
     * This is because the method first iterates through all the device lists to create a single list of all devices (O(n)),
     * then sorts this list using a comparison based sorting algorithm (O(n logn)).
     */
    void sortDevicesByPrice() {
        ArrayList<Device> allDevices = new ArrayList<>();
        for (ArrayList<Device> deviceList : inventoryList) {
            allDevices.addAll(deviceList);
        }
        Comparator<Device> priceComparator = Comparator.comparingDouble(Device::getPrice);
        allDevices.sort(priceComparator);
        for (var device : allDevices) {
            System.out.println(device);
        }
    }

    /**
     * Calculates the total value of the inventory by summing the prices of all devices multiplied by their quantities.
     * <p>
     * This method iterates through each device in the inventory and computes its value by multiplying its price with its
     * quantity. The total value is then calculated by summing the values of all devices.
     * <p>
     * Time Complexity: O(n * m) The time complexity of this method is proportional to the product of the number of
     * device lists (m) and the average number of devices in each category (n) in the inventory.
     * <p>
     * Since device lists (category) is bounded by constant value (in this example 5), Time complexity can be considered as O(n).
     *
     * @return The total value of the inventory.
     * @throws NullPointerException if any device in the inventory has a null price or quantity.
     */
    String calculateTotalInventoryValue() {
        DecimalFormat df = new DecimalFormat("#,##0.00$");
        double totalInventoryValue = 0;
        for (ArrayList<Device> deviceList : inventoryList) {
            for (Device device : deviceList) {
                totalInventoryValue += device.getPrice() * device.getQuantity();
            }
        }
        return df.format(totalInventoryValue);
    }

    /**
     * Restocks or reduces the quantity of a device in the inventory.
     * <p>
     * This method searches for a device with the specified name in the inventory. If the device is found, its quantity
     * is adjusted based on the provided quantity change and operation type.
     * <p>
     * Time Complexity: O(n * m) The time complexity of this method is proportional to the product of the number of
     * device lists (m) and the average number of devices in each category (n) in the inventory.
     * <p>
     * Since device lists (category) is bounded by constant value (in this example 5), Time complexity can be considered as O(n).
     *
     * @param deviceName     The name of the device to restock or reduce.
     * @param quantityChange The amount by which to change the quantity of the device.
     * @param isAdd          A string indicating the operation type ("Add" or "Remove"). If "Add", the quantity of the
     *                       device is increased. If "Remove", the quantity of the device is decreased.
     * @throws IllegalArgumentException if the operation type is neither "Add" nor "Remove".
     */
    void restockDevice(String deviceName, int quantityChange, String isAdd) {
        for (ArrayList<Device> deviceList : inventoryList) {
            for (Device device : deviceList) {
                if (device.getName().equals(deviceName)) {
                    if (isAdd.equals("Add")) {
                        device.setQuantity(device.getQuantity() + quantityChange);
                        System.out.println(deviceName + " restocked. New quantity: " + device.getQuantity());
                    } else if (isAdd.equals("Remove")) {
                        device.setQuantity(device.getQuantity() - quantityChange);
                        System.out.println(deviceName + " reduced. New quantity: " + device.getQuantity());
                    } else {
                        throw new IllegalArgumentException("Invalid input: Operation type must be either 'Add' or 'Remove'.");
                    }
                    return;
                }
            }
        }
    }

    /**
     * Exports an inventory report to the specified file.
     * <p>
     * This method exports the current inventory information to a text file with the provided filename address.
     * The report includes the date of generation, a list of devices categorized by category, and a summary
     * containing the total number of devices and the total inventory value.
     * <p>
     * Time Complexity: O(n * m), where (n) is the average number of devices in the category and (m) is the number of
     * categories in the inventory. There are a bunch of constant operations and a function call which has O(n) complexity.
     * <p>
     * Since device lists (category) is bounded by constant value (in this example 5), Time complexity can be considered as O(n).
     *
     * @param filename The name of the file to export the inventory report to.
     */
    void exportInventoryReport(String filename) {
        // Define the filename and create FileWriter and BufferedWriter
        try (FileWriter fileWriter = new FileWriter(filename);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            // Write the header
            writer.write("Electronics Shop Inventory Report\n");
            writer.write("Generated on: " + LocalDate.now() + "\n");
            writer.write("---------------------------------------\n");
            writer.write("| No. | Category | Name | Price | Quantity |\n");
            writer.write("---------------------------------------\n");
            // Write each device's information
            DecimalFormat df = new DecimalFormat("#,##0.00$");
            int itemCount = 0;
            for (List<Device> deviceList : inventoryList) {
                for (Device device : deviceList) {
                    writer.write(String.format("|%-2d | %-12s | %-12s | $%-8s | %-6d |\n",
                        ++itemCount,
                        device.getCategory(),
                        device.getName(),
                        df.format(device.getPrice()),
                        device.getQuantity()));
                }
            }
            // Write summary
            writer.write("---------------------------------------\n");
            writer.write("Summary:\n");
            writer.write("- Total Number of Devices: " + itemCount + "\n");
            writer.write("- Total Inventory Value: " + calculateTotalInventoryValue() + "\n");
            writer.write("End of Report\n");

            System.out.println("Inventory report exported successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error occurred while exporting inventory report: " + e.getMessage());
        }
    }
}
