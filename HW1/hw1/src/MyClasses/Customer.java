package MyClasses;

public abstract class Customer extends Person {
	private final Order[] orders;
	private final int operator_ID;
	
	public Customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
		super(name, surname, address, phone, ID);
		this.operator_ID = operator_ID;
		this.orders = new Order[100];
	}
	
	public int getOperator_ID() {
		return this.operator_ID;
	}
	
	public void print_customer() {
		System.out.println(this);
		print_orders();
	}
	
	public void print_orders() {
		for (int i = 0; orders[i] != null; i++) {
			System.out.print("Order #" + (i + 1) + " => ");
			orders[i].print_order();
			System.out.println();
		}
	}
	
	public void define_orders(Order[] iOrders) {
		int index = 0;
		for (int i = 0; iOrders[i] != null; i++) {
			Order ord = iOrders[i];
			if (this.getID() == ord.getCustomer_ID()) {
				this.orders[index++] = ord;
			}
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + "Operator ID: " + operator_ID;
	}
}
