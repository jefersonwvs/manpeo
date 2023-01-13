package com.jefersonwvs.manpeo.utils;

import com.jefersonwvs.manpeo.dtos.AddressDTO;
import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.entities.Address;
import com.jefersonwvs.manpeo.entities.Person;

import java.time.LocalDate;

public class Factory {

	public static Person createPerson() {
		return new Person(1L, "Rui Barbosa", LocalDate.parse("1902-05-03"));
	}

	public static PersonDTO createPersonDTO() {
		return new PersonDTO(null, "Rui Barbosa", LocalDate.parse("1902-05-03"));
	}

	public static Address createAddress() {
		return new Address(1L, "Rua Afonso Pena", "362", "10024-934", "Campo Grande", false, null);
	}

	public static AddressDTO createAddressDTO() {
		return new AddressDTO(null, "Rua Afonso Pena", "362", "10024-934", "Campo Grande", false);
	}

}
