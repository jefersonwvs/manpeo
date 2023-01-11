package com.jefersonwvs.manpeo.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tbl_address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String street;
	private String number;
	private String zipCode;
	private String city;
	private Boolean isMain;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public Address() {
	}

	public Address(Long id, String street, String number, String zipCode, String city,
								 Boolean isMain, Person person) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
		this.isMain = isMain;
		this.person = person;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(id, address.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
