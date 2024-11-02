
package mypackage.util;


import java.text.DecimalFormat;

/**
 * The ElectronicDevice class represents an electronic device and implements the Device interface.
 * <p>
 * It provides methods to access and modify device attributes such as category, name, price, and quantity.
 */
abstract class ElectronicDevice implements Device {
    /**
     * The category of the device.
     */
    protected String category;

    /**
     * The name of the device.
     */
    protected String name;

    /**
     * The price of the device.
     */
    protected Double price;

    /**
     * The quantity of the device.
     */
    protected Integer quantity;


    /**
     * Constructs an ElectronicDevice object with the specified attributes.
     * <p>
     * Time Complexity: O(1)
     *
     * @param category The category of the device.
     * @param name     The name of the device.
     * @param price    The price of the device.
     * @param quantity The quantity of the device.
     */
    ElectronicDevice(String category, String name, Double price, Integer quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double newPrice) {
        price = newPrice;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer newQuantity) {
        quantity = newQuantity;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00$");
        return "Category: " + this.getClass().getSimpleName() + ", "
            + "Name: " + this.getName() + ", "
            + "Price: " + df.format(this.getPrice()) + ", "
            + "Quantity: " + this.getQuantity();
    }
}
