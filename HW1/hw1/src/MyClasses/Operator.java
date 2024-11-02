package MyClasses;

public class Operator extends Person {
	private final int wage;
	private final Customer[] customers;
	
	public Operator(String name, String surname, String address, String phone, int ID, int wage) {
		super(name, surname, address, phone, ID);
		this.wage = wage;
		this.customers = new Customer[100];
	}
	
	public void print_operator() {
		System.out.println("----------------------------");
		System.out.println(this);
		System.out.println("----------------------------");
		print_customers();
	}
	
	public void print_customers() {
		if (customers[0] == null) {
			System.out.println("This operator doesn't have any customer.");
			System.out.println("----------------------------");
			return;
		}
		for (int i = 0; customers[i] != null; i++) {
			System.out.print("Customer #" + (i + 1));
			
			if (customers[i] instanceof RetailCustomer) System.out.println(" (Retail Customer) :");
			else if (customers[i] instanceof CorporateCustomer) System.out.println(" (Corporate Customer) :");
			
			System.out.println(customers[i].toString());
			customers[i].print_orders();
			System.out.println("----------------------------");
		}
	}
	
	public void define_customers(Customer[] iCustomers) {
		int index = 0;
		for (int i = 0; iCustomers[i] != null; i++) {
			var cus = iCustomers[i];
			if (cus.getOperator_ID() == this.getID()) {
				this.customers[index++] = cus; // Assign customers with matching operator ID to the operator
			}
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + "Wage: " + wage;
	}
}