package task6.MovieRentals;

public class Contact {
	private String name;
	private String number;
	
	public Contact(String name, String number) {
		this.setName(name);
		this.setNumber(number);
	}
	
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	String getNumber() {
		return number;
	}
	void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Contact Name: " + name + ", phone: " + number;
	}
}
