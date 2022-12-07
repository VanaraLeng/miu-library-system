package business;

import java.io.Serializable;

import utility.Validator;

public class Person implements Serializable, Validable {
	private static final long serialVersionUID = 3665880920647848288L;
	private String firstName;
	private String lastName;
	private String telephone;
	private Address address;
	public Person(String f, String l, String t, Address a) {
		firstName = f;
		lastName = l;
		telephone = t;
		address = a;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getTelephone() {
		return telephone;
	}
	public Address getAddress() {
		return address;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getValidationMessage() {
		if (!Validator.isFilled(firstName)) {
			return "First name is incorrect";
		}
		
		if (!Validator.isFilled(lastName)) {
			return "Last name is incorrect";
		}
		
		if (!Validator.isCorrectPhoneNumber(telephone)) {
			return "Telephone is incorrect";
		}
		
		return address.getValidationMessage();
	}
}
