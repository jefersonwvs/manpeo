package com.jefersonwvs.manpeo.services;

import com.jefersonwvs.manpeo.dtos.AddressDTO;
import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.dtos.PersonWithAddressesDTO;
import com.jefersonwvs.manpeo.entities.Address;
import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.repositories.AddressRepository;
import com.jefersonwvs.manpeo.repositories.PersonRepository;
import com.jefersonwvs.manpeo.services.exceptions.DatabaseException;
import com.jefersonwvs.manpeo.services.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	public PersonDTO create(PersonDTO requestDTO) {
		Person entity = new Person();
		entity.setName(requestDTO.getName());
		entity.setBirthDate(requestDTO.getBirthDate());
		entity = personRepository.save(entity);
		return new PersonDTO(entity);
	}

	@Transactional(readOnly = true)
	public PersonWithAddressesDTO retrieveById(Long id) {
		Optional<Person> optEntity = personRepository.findById(id);
		Person entity = optEntity.orElseThrow(
				() -> new NotFoundException("Pessoa (ID " + id + ") não encontrada."));
		return new PersonWithAddressesDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<PersonDTO> retrieveAll() {
		List<Person> entities = personRepository.findAll();
		return entities.stream()
									 .map(PersonDTO::new)
									 .toList();
	}

	@Transactional
	public PersonDTO update(Long id, PersonDTO requestDTO) {
		try {
			Person entity = personRepository.getReferenceById(id);
			entity.setName(requestDTO.getName());
			entity.setBirthDate(requestDTO.getBirthDate());
			entity = personRepository.save(entity);
			return new PersonDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new NotFoundException("Pessoa (ID " + id + ") não encontrada.");
		}
	}

	public void delete(Long id) {
		try {
			personRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Pessoa (ID " + id + ") não encontrada.");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade.");
		}
	}

	@Transactional
	public AddressDTO createAddress(Long personId, AddressDTO requestDTO) {
		Optional<Person> optPerson = personRepository.findById(personId);
		Person person = optPerson.orElseThrow(
				() -> new NotFoundException("Pessoa (ID " + personId + ") não encontrada."));

		Address address = new Address();

		address.setStreet(requestDTO.getStreet());
		address.setNumber(requestDTO.getNumber());
		address.setZipCode(requestDTO.getZipCode());
		address.setCity(requestDTO.getCity());
		address.setMain(false);
		address.setPerson(person);

		address = addressRepository.save(address);

		return new AddressDTO(address);
	}

}
