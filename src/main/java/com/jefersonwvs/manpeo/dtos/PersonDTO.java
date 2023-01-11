package com.jefersonwvs.manpeo.dtos;

import com.jefersonwvs.manpeo.entities.Address;
import com.jefersonwvs.manpeo.entities.Person;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PersonDTO {

	private Long id;
	private String name;
	private LocalDate birthDate;
	private final Set<AddressDTO> addresses = new HashSet<>();

	public PersonDTO() {
	}

	public PersonDTO(Long id, String name, LocalDate birthDate, Set<Address> addresses) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		addresses.forEach(address -> this.addresses.add(new AddressDTO(address)));
	}

	public PersonDTO(Person entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.birthDate = entity.getBirthDate();
		entity.getAddresses()
					.forEach(address -> this.addresses.add(new AddressDTO(address)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Set<AddressDTO> getAddresses() {
		return addresses;
	}

}
