package com.addressbook;

public class Contact {
	private String name;
	private String phone;	
	private String email; 
	private String street; 
	private String place;
	
	public Contact(String name, String phone, String email, String street, String place) {
		this.setName(name);
		this.setPhone(phone);
		this.setEmail(email);
		this.setStreet(street);
		this.setPlace(place);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			name = "Unnamed";
		}
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone == null) {
			phone = " No phone added ";
		}
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null) {
			email = " No email given ";
		}
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if (street == null) {
			street = " No street given ";
		}
		this.street = street;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		if (place == null) {
			place = " No address given ";
		}
		this.place = place;
	}
	
}
