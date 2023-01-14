package com.jefersonwvs.manpeo.repositories;

import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.TimeZone;

@DataJpaTest
public class PersonRepositoryTests {

	@Autowired
	private PersonRepository personRepository;

	private long countTotalPeople;
	private long existingId;
	private long nonExisting;

	@BeforeEach
	void setUp() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		countTotalPeople = 2L;
		existingId = 1L;
		nonExisting = 1000L;
	}

	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Person person = Factory.createPerson();
		person.setId(null);

		person = personRepository.save(person);

		Assertions.assertNotNull(person.getId());
		Assertions.assertEquals(countTotalPeople + 1, person.getId());
	}

	@Test
	public void findByIdShouldReturnObjectWhenIdExists() {
		Optional<Person> result = personRepository.findById(existingId);
		Assertions.assertTrue(result.isPresent());
	}

	@Test
	public void findByIdShouldReturnEmptyObjectWhenIdDoesNotExist() {
		Optional<Person> result = personRepository.findById(nonExisting);
		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void saveShouldUpdateObjectWhenIdExists() {
		Optional<Person> optPerson = personRepository.findById(existingId);
		Person person = optPerson.get();
		Assertions.assertEquals("Machado de Assis", person.getName());
		System.out.println(person.getBirthDate());
		Assertions.assertEquals(LocalDate.parse("1839-06-21"), person.getBirthDate());

		person = Factory.createPerson();
		person.setId(existingId);
		personRepository.save(person);

		Optional<Person> optUpdated = personRepository.findById(existingId);
		Person updated = optUpdated.get();
		Assertions.assertEquals("Rui Barbosa", updated.getName());
		Assertions.assertEquals(LocalDate.parse("1902-05-03"), updated.getBirthDate());
	}

}
