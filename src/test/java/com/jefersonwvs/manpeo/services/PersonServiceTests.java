package com.jefersonwvs.manpeo.services;

import com.jefersonwvs.manpeo.dtos.AddressDTO;
import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.entities.Address;
import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.repositories.AddressRepository;
import com.jefersonwvs.manpeo.repositories.PersonRepository;
import com.jefersonwvs.manpeo.services.exceptions.NotFoundException;
import com.jefersonwvs.manpeo.utils.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PersonServiceTests {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;

	@Mock
	private AddressRepository addressRepository;

	private long existingId;
	private long nonExistingId;
	private long addressId;

	private Person person;
	private PersonDTO requestDTO;
	private List<Person> people;
	private Address address;
	private AddressDTO addressDTO;
	private List<Address> addresses;

	@BeforeEach
	public void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 1000L;
		addressId = 1L;

		person = Factory.createPerson();
		requestDTO = Factory.createPersonDTO();
		people = List.of(person);
		address = Factory.createAddress();
		addressDTO = Factory.createAddressDTO();
		addresses = List.of(address);

		// Mock do método personRepository.save()
		Mockito.when(personRepository.save(ArgumentMatchers.any()))
				.thenReturn(person);

		Mockito.when(personRepository.findById(existingId))
				.thenReturn(Optional.of(person));
		Mockito.when(personRepository.findById(nonExistingId))
				.thenReturn(Optional.empty());

		Mockito.when(personRepository.findAll())
				.thenReturn(people);

		Mockito.when(personRepository.getReferenceById(existingId))
				.thenReturn(person);
		Mockito.when(personRepository.getReferenceById(nonExistingId))
				.thenThrow(EntityNotFoundException.class);

		Mockito.when(addressRepository.save(ArgumentMatchers.any()))
				.thenReturn(address);

		Mockito.when(addressRepository.findAllByPersonId(existingId))
				.thenReturn(addresses);

		Mockito.when(addressRepository.findAddressByIdAndPersonId(addressId, existingId))
				.thenReturn(Optional.of(address));

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

	@Test
	public void retrieveAllShouldReturnListOfObjects() {
		List<PersonDTO> peopleDTO = personService.retrieveAll();
		Assertions.assertNotNull(peopleDTO);
		Assertions.assertEquals(1, peopleDTO.size());
	}

	@Test
	public void updateShouldReturnObjectWhenIdExists() {
		PersonDTO responseDTO = personService.update(existingId, requestDTO);
		Assertions.assertNotNull(responseDTO);
		Assertions.assertEquals(existingId, responseDTO.getId());
		Mockito.verify(personRepository, Mockito.times(1))
				.getReferenceById(existingId);
		Mockito.verify(personRepository, Mockito.times(1))
				.save(person);
	}

	@Test
	public void updateShouldThrowNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(NotFoundException.class,
				() -> personService.update(nonExistingId, requestDTO));
		Mockito.verify(personRepository, Mockito.times(1))
				.getReferenceById(nonExistingId);
	}

	@Test
	public void createAddressShouldReturnObjectWhenPersonExists() {
		AddressDTO responseDTO = personService.createAddress(existingId, addressDTO);
		Assertions.assertNotNull(responseDTO);
		Assertions.assertEquals(address.getId(), responseDTO.getId());
	}

	@Test
	public void createAddressShouldThrowNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(NotFoundException.class,
				() -> personService.createAddress(nonExistingId, addressDTO));
		Mockito.verify(personRepository, Mockito.times(1))
				.findById(nonExistingId);
	}

	@Test
	public void retrieveAllAddressesShouldReturnListOfObjects() {
		List<AddressDTO> addressDTOS = personService.retrieveAllAddresses(existingId);
		Assertions.assertNotNull(addressDTOS);
		Assertions.assertEquals(1, addressDTOS.size());
	}

	@Test
	public void setMainAddressShouldSetAnAddressAsMainWhenPersonAndAddressExists() {
		AddressDTO addressDTO = personService.setMainAddress(existingId, addressId);
		Assertions.assertNotNull(addressDTO);
		Assertions.assertEquals(true, addressDTO.getMain());
		Mockito.verify(personRepository, Mockito.times(1))
				.findById(existingId);
	}

	@Test
	public void setMainAddressShouldThrowNotFoundExceptionWhenPersonDoesNotExist() {
		Assertions.assertThrows(NotFoundException.class,
				() -> personService.setMainAddress(nonExistingId, addressId));
		Mockito.verify(personRepository, Mockito.times(1))
				.findById(nonExistingId);
	}

	@Test
	public void setMainAddressShouldThrowNotFoundExceptionWhenPersonDoesNotHaveTheAddress() {
		long nonExistingAddressId = 6L;
		Assertions.assertThrows(NotFoundException.class,
				() -> personService.setMainAddress(existingId, nonExistingAddressId));
		Mockito.verify(personRepository, Mockito.times(1))
				.findById(existingId);
	}

}
