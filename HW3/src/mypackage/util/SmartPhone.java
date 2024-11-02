package mypackage.util;

/**
 * The SmartPhone class represents a type of electronic device that extends ElectronicDevice.
 * <p>
 * It provides specific functionality and attributes for SmartPhone.
 */
public class SmartPhone extends ElectronicDevice {
	/**
	 * Constructs a SmartPhone object with the specified name, price, and quantity.
	 *
	 * @param name     The name of the SmartPhone.
	 * @param price    The price of the SmartPhone.
	 * @param quantity The quantity of the SmartPhone.
	 */
	public SmartPhone(String name, Double price, Integer quantity) {
		super("Smart Phone", name, price, quantity);
	}
}
