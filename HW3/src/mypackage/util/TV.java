package mypackage.util;

/**
 * The TV class represents a type of electronic device that extends ElectronicDevice.
 * <p>
 * It provides specific functionality and attributes for TV.
 */
public class TV extends ElectronicDevice {
	/**
	 * Constructs a TV object with the specified name, price, and quantity.
	 *
	 * @param name     The name of the TV.
	 * @param price    The price of the TV.
	 * @param quantity The quantity of the TV.
	 */
	public TV(String name, Double price, Integer quantity) {
		super("TV", name, price, quantity);
	}
}
