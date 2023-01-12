package com.jefersonwvs.manpeo.dtos;

import com.jefersonwvs.manpeo.entities.Address;
import com.jefersonwvs.manpeo.entities.Person;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PersonWithAddressesDTO extends PersonDTO {

	private final Set<AddressDTO> addresses = new HashSet<>();

	public PersonWithAddressesDTO(Long id, String name, LocalDate birthDate, Set<Address> addresses) {
		super(id, name, birthDate);
		addresses.forEach(address -> this.addresses.add(new AddressDTO(address)));
	}

	public PersonWithAddressesDTO(Person entity) {
		super(entity);
		entity.getAddresses()
					.forEach(address -> this.addresses.add(new AddressDTO(address)));
	}

	public Set<AddressDTO> getAddresses() {
		return addresses;
	}

}
