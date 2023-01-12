package com.jefersonwvs.manpeo.utils;

import com.jefersonwvs.manpeo.entities.Person;

import java.time.LocalDate;

public class Factory {

	public static Person createPerson() {
		return new Person(1L, "Rui Barbosa", LocalDate.parse("1902-05-03"));
	}

}
