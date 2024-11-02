package mypackage.util;

/**
 * The SmartWatch class represents a type of electronic device that extends ElectronicDevice.
 * <p>
 * It provides specific functionality and attributes for SmartWatch.
 */
public class SmartWatch extends ElectronicDevice {
	/**
	 * Constructs a SmartWatch object with the specified name, price, and quantity.
	 *
	 * @param name     The name of the SmartWatch.
	 * @param price    The price of the SmartWatch.
	 * @param quantity The quantity of the SmartWatch.
	 */
	public SmartWatch(String name, Double price, Integer quantity) {
		super("Smart Watch", name, price, quantity);
	}
}
