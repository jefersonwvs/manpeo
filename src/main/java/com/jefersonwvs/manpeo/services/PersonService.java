package com.jefersonwvs.manpeo.services;

import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.repositories.PersonRepository;
import com.jefersonwvs.manpeo.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public PersonDTO retrieveById(Long id) {
		Optional<Person> optEntity = personRepository.findById(id);
		Person entity = optEntity.orElseThrow(
				() -> new NotFoundException("Pessoa (ID " + id + ") não encontrada."));
		return new PersonDTO(entity);
	}

}
