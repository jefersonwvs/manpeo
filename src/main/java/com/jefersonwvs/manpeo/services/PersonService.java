package com.jefersonwvs.manpeo.services;

import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.entities.Person;
import com.jefersonwvs.manpeo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
