package com.jefersonwvs.manpeo.repositories;

import com.jefersonwvs.manpeo.entities.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AddressRepositoryTests {

	@Autowired
	private AddressRepository addressRepository;

	private long idOfPersonWithAddresses;
	private long idOfPersonWithNoAddresses;

	@BeforeEach
	public void setUp() {
		idOfPersonWithAddresses = 1L;
		idOfPersonWithNoAddresses = 3L;
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

}
