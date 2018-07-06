package fr.competitor.model;

public class Details {
	Integer id;
	String address, postalCode, city, country;

	public Details(Integer id, String address, String postalCode, String city, String country) {
		super();
		this.id = id;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Details [id=" + id + ", address=" + address + ", postalCode=" + postalCode + ", city=" + city
				+ ", country=" + country + "]";
	}
}
