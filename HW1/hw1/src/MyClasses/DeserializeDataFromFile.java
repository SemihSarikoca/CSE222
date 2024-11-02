

package MyClasses;

import java.io.File;
import java.util.Scanner;

public class DeserializeDataFromFile {
	private final Operator[] operators = new Operator[100];
	private final Customer[] customers = new Customer[100];
	private final Order[] orders = new Order[100];
	
	private int operatorCount = 0;
	private int customerCount = 0;
	private int orderCount = 0;
	
	public DeserializeDataFromFile(String filePath) {
		readDataFromFile(filePath);
	}
	
	public void findByIdAndPrint(int id) {
		Operator operator = findOperatorByID(id);
		Customer customer = findCustomerByID(id);
		
		if (operator != null) {
			System.out.println("*** Operator Screen ***");
			operator.print_operator();
		} else if (customer != null) {
			System.out.println("*** Customer Screen ***");
			customer.print_customer();
		} else {
			System.out.println("No operator/customer was found with ID " + id + ". Please try again.");
		}
	}
	
	private Operator findOperatorByID(int id) {
		for (int i = 0; i < operatorCount; i++) {
			if (operators[i].getID() == id) {
				return operators[i];
			}
		}
		return null;
	}
	
	private Customer findCustomerByID(int id) {
		for (int i = 0; i < customerCount; i++) {
			if (customers[i].getID() == id) {
				return customers[i];
			}
		}
		return null;
	}
	
	private void readDataFromFile(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(";", -1);
				if (isValidLine(parts)) {
					switch (parts[0]) {
						case "operator":
							if (operatorCount < 100) {
								operators[operatorCount] = new Operator(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
								operators[operatorCount].define_customers(customers);
								operatorCount++;
							}
							break;
						case "retail_customer":
							if (customerCount < 100) {
								customers[customerCount] = new RetailCustomer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
								customers[customerCount].define_orders(orders);
								customerCount++;
							}
							break;
						case "corporate_customer":
							if (customerCount < 100) {
								customers[customerCount] = new CorporateCustomer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]);
								customers[customerCount].define_orders(orders);
								customerCount++;
							}
							break;
						case "order":
							if (orderCount < 100) {
								orders[orderCount++] = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
							}
							break;
						default:
							break;
					}
				}
			}
			scanner.close();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	private boolean isValidLine(String[] parts) {
		return switch (parts[0]) {
			case "operator" -> isValidOperator(parts);
			case "retail_customer" -> isValidRetailCustomer(parts);
			case "corporate_customer" -> isValidCorporateCustomer(parts);
			case "order" -> isValidOrder(parts);
			default -> false;
		};
	}
	
	private boolean isValidOperator(String[] parts) {
		if (parts.length != 7) {
			return false;
		}
		for (var in : parts) {
			if (in.trim().isEmpty()) {
				return false;
			}
		}
		try {
			int id = Integer.parseInt(parts[5]);
			int wage = Integer.parseInt(parts[6]);
			if (id <= 0 || wage <= 0) {
				return false;
			}
			for (int i = 0; i < operatorCount; i++) {
				if (operators[i].getID() == id) {
					return false;
				}
			}
			for (int i = 0; i < customerCount; i++) {
				if (customers[i].getID() == id) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private boolean isValidRetailCustomer(String[] parts) {
		if (parts.length != 7) {
			return false;
		}
		for (var in : parts) {
			if (in.trim().isEmpty()) {
				return false;
			}
		}
		try {
			int id = Integer.parseInt(parts[5]);
			int operator_id = Integer.parseInt(parts[6]);
			if (id <= 0 || operator_id <= 0) {
				return false;
			}
			for (int i = 0; i < operatorCount; i++) {
				if (operators[i].getID() == id) {
					return false;
				}
			}
			for (int i = 0; i < customerCount; i++) {
				if (customers[i].getID() == id) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private boolean isValidCorporateCustomer(String[] parts) {
		if (parts.length != 8) {
			return false;
		}
		for (var in : parts) {
			if (in.trim().isEmpty()) {
				return false;
			}
		}
		try {
			int id = Integer.parseInt(parts[5]);
			int operator_id = Integer.parseInt(parts[6]);
			if (id <= 0 || operator_id <= 0) {
				return false;
			}
			for (int i = 0; i < operatorCount; i++) {
				if (operators[i].getID() == id) {
					return false;
				}
			}
			for (int i = 0; i < customerCount; i++) {
				if (customers[i].getID() == id) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private boolean isValidOrder(String[] parts) {
		if (parts.length != 6) {
			return false;
		}
		for (var in : parts) {
			if (in.trim().isEmpty()) {
				return false;
			}
		}
		try {
			int count = Integer.parseInt(parts[2]);
			int totalPrice = Integer.parseInt(parts[3]);
			int status = Integer.parseInt(parts[4]);
			int customerID = Integer.parseInt(parts[5]);
			if (customerID <= 0 || status < 0 || status > 3
					|| count <= 0 || totalPrice <= 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
