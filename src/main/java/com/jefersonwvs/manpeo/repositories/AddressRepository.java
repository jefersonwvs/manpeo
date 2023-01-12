package com.jefersonwvs.manpeo.repositories;

import com.jefersonwvs.manpeo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findAllByPersonId(Long personId);

	@Query(value = "SELECT * FROM tbl_address WHERE id = :id AND person_id = :personId",
				 nativeQuery = true)
	Optional<Address> findAddressByIdAndPersonId(Long id, Long personId);

	@Query(value = "SELECT * FROM tbl_address WHERE person_id = :personId AND is_main = true",
				 nativeQuery = true)
	Optional<Address> findMainAddressByPersonId(Long personId);

}
