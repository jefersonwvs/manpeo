package com.jefersonwvs.manpeo.repositories;

import com.jefersonwvs.manpeo.entities.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AddressRepositoryTests {

	@Autowired
	private AddressRepository addressRepository;

	private long idOfPersonWithAddresses;
	private long idOfPersonWithNoAddresses;
	private long existingAddressId;
	private long nonExistingAddressId;

	@BeforeEach
	public void setUp() {
		idOfPersonWithAddresses = 1L;
		idOfPersonWithNoAddresses = 3L;
		existingAddressId = 1L;
		nonExistingAddressId = 1000L;
	}

	@Test
	public void findAllByPersonIdShouldReturnNotEmptyListWhenPersonHasAddresses() {
		List<Address> addresses = addressRepository.findAllByPersonId(idOfPersonWithAddresses);
		Assertions.assertNotNull(addresses);
		Assertions.assertTrue(addresses.size() > 0);
	}

	@Test
	public void findAllByPersonIdShouldReturnEmptyListWhenPersonHasNoAddresses() {
		List<Address> addresses = addressRepository.findAllByPersonId(idOfPersonWithNoAddresses);
		Assertions.assertNotNull(addresses);
		Assertions.assertEquals(0, addresses.size());
	}

	@Test
	public void findAddressByIdAndPersonIdShouldReturnObjectWhenIdExists() {
		Optional<Address> optAddress = addressRepository.findAddressByIdAndPersonId(existingAddressId,
																																						 idOfPersonWithAddresses);
		Assertions.assertTrue(optAddress.isPresent());
	}

	@Test
	public void findAddressByIdAndPersonIdShouldReturnEmptyObjectWhenIdDoesNotExist() {
		Optional<Address> optAddress = addressRepository.findAddressByIdAndPersonId(nonExistingAddressId,
																																								idOfPersonWithAddresses);
		Assertions.assertTrue(optAddress.isEmpty());
	}

}
