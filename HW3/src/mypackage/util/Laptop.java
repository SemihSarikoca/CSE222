package mypackage.util;

/**
 * The Laptop class represents a type of electronic device that extends ElectronicDevice.
 * <p>
 * It provides specific functionality and attributes for Laptop.
 */
public class Laptop extends ElectronicDevice {
	/**
	 * Constructs a Laptop object with the specified name, price, and quantity.
	 *
	 * @param name     The name of the Laptop.
	 * @param price    The price of the Laptop.
	 * @param quantity The quantity of the Laptop.
	 */
	public Laptop(String name, Double price, Integer quantity) {
		super("Laptop", name, price, quantity);
	}
}
