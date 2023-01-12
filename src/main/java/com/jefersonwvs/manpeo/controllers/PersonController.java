package com.jefersonwvs.manpeo.controllers;

import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO requestDTO) {
		PersonDTO responseDTO = personService.create(requestDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
																				 .path("/{id}")
																				 .buildAndExpand(responseDTO.getId())
																				 .toUri();
		return ResponseEntity.created(uri)
												 .body(responseDTO);
	}

}
