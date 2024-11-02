package mypackage.util;

/**
 * The Device interface represents an electronic device.
 * <p>
 * It defines methods to retrieve and modify device attributes such as category, name, price, and quantity.
 */
public interface Device {

    /**
     * Returns the category of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @return The category of the device as a string.
     */
    String getCategory();

    /**
     * Returns the name of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @return The name of the device as a string.
     */
    String getName();

    /**
     * Sets the name of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @param newName The new name to set for the device.
     */
    void setName(String newName);

    /**
     * Returns the price of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @return The price of the device as a double.
     */
    Double getPrice();

    /**
     * Sets the price of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @param newPrice The new price to set for the device.
     */
    void setPrice(Double newPrice);

    /**
     * Returns the quantity of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @return The quantity of the device as an integer.
     */
    Integer getQuantity();

    /**
     * Sets the quantity of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @param newQuantity The new quantity to set for the device.
     */
    void setQuantity(Integer newQuantity);

    /**
     * Returns a string representation of the device.
     * <p>
     * Time Complexity: O(1)
     *
     * @return A string representation of the device.
     */
    @Override
    String toString();
}

