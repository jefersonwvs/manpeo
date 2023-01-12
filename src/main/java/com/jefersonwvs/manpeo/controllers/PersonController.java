package com.jefersonwvs.manpeo.controllers;

import com.jefersonwvs.manpeo.dtos.PersonDTO;
import com.jefersonwvs.manpeo.dtos.PersonWithAddressesDTO;
import com.jefersonwvs.manpeo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

	@GetMapping("/{id}")
	public ResponseEntity<PersonWithAddressesDTO> retrieveById(@PathVariable Long id) {
		PersonWithAddressesDTO responseDTO = personService.retrieveById(id);
		return ResponseEntity.ok()
												 .body(responseDTO);
	}

	@GetMapping
	public ResponseEntity<List<PersonDTO>> retrieveAll() {
		List<PersonDTO> list = personService.retrieveAll();
		return ResponseEntity.ok()
												 .body(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonDTO> update(@PathVariable Long id,
																					@RequestBody PersonDTO requestDTO) {
		PersonDTO responseDTO = personService.update(id, requestDTO);
		return ResponseEntity.ok()
												 .body(responseDTO);
	}

}
