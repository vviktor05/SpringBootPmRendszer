package com.pmrendszer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pmrendszer.service.CheckerClass;

@Entity(name = "Customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@NotNull
	@Size(max = 50, message = "{customer.name.max}")
	private String name;
	@NotNull
	@Size(max = 20, message = "{customer.phone.max}")
	private String phone;
	@NotNull
	@Email(message = "{email.valid}")
	@Size(max = 100, message = "{email.max}")
	private String email;
	@Nullable
	@Size(max = 100, message = "{customer.website.max}")
	private String website;
	@NotNull
	@Size(max = 20, message = "{customer.zipCode.max}")
	private String zipCode;
	@NotNull
	@Size(max = 50, message = "{customer.locality.max}")
	private String locality;
	@NotNull
	@Size(max = 100, message = "{customer.streetAddress.max}")
	private String streetAddress;
	@Transient
	@JsonIgnore
	private boolean updateMode;

	public Customer() {
		;
	}

	public Customer(int id, String name, String phone, String email, String website, String zipCode, String locality,
			String streetAddress) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.website = website;
		this.zipCode = zipCode;
		this.locality = locality;
		this.streetAddress = streetAddress;
	}
	
	@JsonIgnore
	@AssertTrue(message = "{id.valid}")
	public boolean isValidId() {
		if (updateMode) {
			return true;
		}
		return id == 0;
	}

	@JsonIgnore
	@AssertTrue(message = "{customer.name.min}")
	public boolean isValidNameMinLength() {
		return CheckerClass.isValidMinLength(name, 5);
	}

	@JsonIgnore
	@AssertTrue(message = "{customer.phone.min}")
	public boolean isValidPhoneMinLength() {
		return CheckerClass.isValidMinLength(phone, 5);
	}

	@JsonIgnore
	@AssertTrue(message = "{customer.zipCode.min}")
	public boolean isValidZipCodeMinLength() {
		return CheckerClass.isValidMinLength(zipCode, 3);
	}

	@JsonIgnore
	@AssertTrue(message = "{customer.locality.min}")
	public boolean isValidLocalityMinLength() {
		return CheckerClass.isValidMinLength(locality, 3);
	}

	@JsonIgnore
	@AssertTrue(message = "{customer.streetAddress.min}")
	public boolean isValidStreetAddress() {
		return CheckerClass.isValidMinLength(streetAddress, 5);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
}