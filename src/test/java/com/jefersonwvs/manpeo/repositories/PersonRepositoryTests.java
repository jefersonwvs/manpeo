package com.jefersonwvs.manpeo.repositories;

import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PersonRepositoryTests {

	@Autowired
	private PersonRepository personRepository;

	private long countTotalPeople;

	@BeforeEach
	void setUp() throws Exception {
		countTotalPeople = 2L;
	}

	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Person person = Factory.createPerson();
		person.setId(null);

		person = personRepository.save(person);

		Assertions.assertNotNull(person.getId());
		Assertions.assertEquals(countTotalPeople + 1, person.getId());
	}

}
