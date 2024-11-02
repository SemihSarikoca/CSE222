package mypackage.util;

/**
 * The Headphones class represents a type of electronic device that extends ElectronicDevice.
 * <p>
 * It provides specific functionality and attributes for Headphones.
 */
public class Headphones extends ElectronicDevice {
	
	/**
	 * Constructs a Headphones object with the specified name, price, and quantity.
	 *
	 * @param name     The name of the Headphones.
	 * @param price    The price of the Headphones.
	 * @param quantity The quantity of the Headphones.
	 */
	public Headphones(String name, Double price, Integer quantity) {
		super("Headphones", name, price, quantity);
	}
}
