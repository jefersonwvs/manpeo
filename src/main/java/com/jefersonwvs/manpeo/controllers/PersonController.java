package com.jefersonwvs.manpeo.controllers;

import com.jefersonwvs.manpeo.dtos.AddressDTO;
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

	/***
	 * Método para criar uma pessoa.
	 */
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

	/**
	 * Método para consultar uma pessoa.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PersonWithAddressesDTO> retrieveById(@PathVariable Long id) {
		PersonWithAddressesDTO responseDTO = personService.retrieveById(id);
		return ResponseEntity.ok()
												 .body(responseDTO);
	}

	/**
	 * Método para listar as pessoas cadastradas.
	 * */
	@GetMapping
	public ResponseEntity<List<PersonDTO>> retrieveAll() {
		List<PersonDTO> list = personService.retrieveAll();
		return ResponseEntity.ok()
												 .body(list);
	}

	/**
	 * Método para editar uma pessoa.
	 * */
	@PutMapping("/{id}")
	public ResponseEntity<PersonDTO> update(@PathVariable Long id,
																					@RequestBody PersonDTO requestDTO) {
		PersonDTO responseDTO = personService.update(id, requestDTO);
		return ResponseEntity.ok()
												 .body(responseDTO);
	}

	/**
	 * Método para deletar uma pessoa.
	 * */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		personService.delete(id);
		return ResponseEntity.noContent()
												 .build();
	}

	/**
	 * Método para cadastrar um novo endereço.
	 * */
	@PostMapping("/{personId}/addresses")
	public ResponseEntity<AddressDTO> createAddress(@PathVariable Long personId,
																									@RequestBody AddressDTO requestDTO) {
		AddressDTO responseDTO = personService.createAddress(personId, requestDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
																				 .path("/{id}")
																				 .buildAndExpand(responseDTO.getId())
																				 .toUri();
		return ResponseEntity.created(uri)
												 .body(responseDTO);
	}

	/**
	 * Método para listar os endereços de uma pessoa.
	 * */
	@GetMapping("/{personId}/addresses")
	public ResponseEntity<List<AddressDTO>> retrieveAllAddresses(@PathVariable Long personId) {
		List<AddressDTO> list = personService.retrieveAllAddresses(personId);
		return ResponseEntity.ok()
												 .body(list);
	}

	/**
	 * Método para marcar uma endereço como principal.
	 * */
	@PutMapping("/{personId}/addresses/{addressId}")
	public ResponseEntity<AddressDTO> setMainAddress(@PathVariable Long personId,
																									 @PathVariable Long addressId) {
		AddressDTO responseDTO = personService.setMainAddress(personId, addressId);
		return ResponseEntity.ok()
												 .body(responseDTO);
	}

}
