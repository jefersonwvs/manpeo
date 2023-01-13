package com.jefersonwvs.manpeo.services;

import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.repositories.PersonRepository;
import com.jefersonwvs.manpeo.services.exceptions.NotFoundException;
import com.jefersonwvs.manpeo.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PersonServiceTests {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;

	private long existingId;
	private long nonExistingId;

	private Person person;
	private PersonDTO requestDTO;

	public PersonServiceTests() {
	}

	@BeforeEach
	public void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 1000L;

		person = Factory.createPerson();
		requestDTO = Factory.createPersonDTO();

		// Mock do método personRepository.save()
		Mockito.when(personRepository.save(ArgumentMatchers.any()))
					 .thenReturn(person);

		Mockito.when(personRepository.findById(existingId))
					 .thenReturn(Optional.of(person));
		Mockito.when(personRepository.findById(nonExistingId))
					 .thenReturn(Optional.empty());

	}

	@Test
	public void createShouldReturnObject() {
		PersonDTO responseDTO = personService.create(requestDTO);
		Assertions.assertNotNull(responseDTO);
		Assertions.assertEquals(existingId, responseDTO.getId());
	}

	@Test
	public void retrieveByIdShouldReturnObjectWhenIdExists() {
		PersonDTO responseDTO = personService.retrieveById(existingId);
		Assertions.assertNotNull(responseDTO);
		Mockito.verify(personRepository, Mockito.times(1))
					 .findById(existingId);
	}

	@Test
	public void retrieveByIdShouldThrowNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(NotFoundException.class,
														() -> personService.retrieveById(nonExistingId));
		Mockito.verify(personRepository, Mockito.times(1))
					 .findById(nonExistingId);
	}

}
