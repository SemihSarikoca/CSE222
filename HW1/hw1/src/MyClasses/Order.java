package MyClasses;

public class Order {
	private final String product_name;
	private final int count;
	private final int total_price;
	private final int status;
	private final int customer_ID;
	
	public Order(String product_name, int count, int total_price, int status, int customer_ID) {
		this.product_name = product_name;
		this.count = count;
		this.total_price = total_price;
		this.status = status;
		this.customer_ID = customer_ID;
	}
	
	public int getCustomer_ID() {
		return customer_ID;
	}
	
	public void print_order() {
		System.out.print(this);
	}
	
	@Override
	public String toString() {
		StringBuilder visual = new StringBuilder();
		visual.append("Product Name: ")
				.append(product_name)
				.append(" - Count: ")
				.append(count)
				.append(" - Total Price: ")
				.append(total_price)
				.append(" - Status: ");
		
		switch (status) {
			case 0 -> visual.append("Initialized.");
			case 1 -> visual.append("Processing.");
			case 2 -> visual.append("Status: Completed.");
			case 3 -> visual.append("Status: Cancelled.");
			default -> visual.append("Status: Error.");
		}
		return visual.toString();
	}
}
