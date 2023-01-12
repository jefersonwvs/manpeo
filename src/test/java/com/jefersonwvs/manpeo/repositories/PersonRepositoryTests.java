package com.jefersonwvs.manpeo.repositories;

import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PersonRepositoryTests {

	@Autowired
	private PersonRepository personRepository;

	private long countTotalPeople;
	private long existingId;

	@BeforeEach
	void setUp() throws Exception {
		countTotalPeople = 2L;
		existingId = 1L;
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

}
