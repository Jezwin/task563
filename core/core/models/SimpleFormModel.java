package com.auki.core.models;

public class SimpleFormModel {
	
	private String firstName;
	private String lastName;
	private String birthMonth;
	private String birthDay;
	private String birthYear;
	private String phoneNumber;
	private String zipCode;
	private String email;
	private String radioValue;
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}
	public SimpleFormModel(String firstName, String lastName, String birthMonth, String birthDay, String birthYear,
			String phoneNumber, String zipCode, String email, String radioValue) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthMonth = birthMonth;
		this.birthDay = birthDay;
		this.birthYear = birthYear;
		this.phoneNumber = phoneNumber;
		this.zipCode = zipCode;
		this.email = email;
		this.radioValue = radioValue;
	}
	public SimpleFormModel() {
		// TODO Auto-generated constructor stub
	}
	
	

}
