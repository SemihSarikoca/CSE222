package MyClasses;

public class CorporateCustomer extends Customer {
	private final String company_name;
	
	public CorporateCustomer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
		super(name, surname, address, phone, ID, operator_ID);
		this.company_name = company_name;
	}
	
	@Override
	public void print_customer() {
		System.out.println(super.toString());
		System.out.println("Company name: " + company_name);
		print_orders();
	}
	
}