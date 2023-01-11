package com.jefersonwvs.manpeo.dtos;

import com.jefersonwvs.manpeo.entities.Address;

public class AddressDTO {

	private Long id;
	private String street;
	private String number;
	private String zipCode;
	private String city;
	private Boolean isMain;

	public AddressDTO() {
	}

	public AddressDTO(Long id, String street, String number, String zipCode, String city,
										Boolean isMain) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
		this.isMain = isMain;
	}

	public AddressDTO(Address entity) {
		this.id = entity.getId();
		this.street = entity.getStreet();
		this.number = entity.getNumber();
		this.zipCode = entity.getZipCode();
		this.city = entity.getCity();
		this.isMain = entity.getMain();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getMain() {
		return isMain;
	}

	public void setMain(Boolean main) {
		isMain = main;
	}

}
