package MyClasses;

public abstract class Person {
	private final String name;
	private final String surname;
	private final String address;
	private final String phone;
	private final int ID;
	
	public Person(String name, String surname, String address, String phone, int ID) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	@Override
	public String toString() {
		return "Name & Surname: " + name + " " + surname
				+ "\nAddress: " + address
				+ "\nPhone: " + phone
				+ "\nID: " + ID;
	}
}
