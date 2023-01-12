package com.jefersonwvs.manpeo.services;

import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.dtos.PersonWithAddressesDTO;
import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.repositories.PersonRepository;
import com.jefersonwvs.manpeo.services.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

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

}
