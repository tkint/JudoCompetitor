package fr.competitor.model;

public class Contact extends Details {
	String lastName, firstName, eMail, phone;

	public Contact(Integer id, String address, String postalCode, String city, String country, String lastName,
			String firstName, String eMail, String phone) {
		super(id, address, postalCode, city, country);
		this.lastName = lastName;
		this.firstName = firstName;
		this.eMail = eMail;
		this.phone = phone;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Contact [lastName=" + lastName + ", firstName=" + firstName + ", eMail=" + eMail + ", phone=" + phone
				+ ", address=" + address + ", postalCode=" + postalCode + "]";
	}
}
