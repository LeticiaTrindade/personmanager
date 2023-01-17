package com.leticia.personmanager.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leticia.personmanager.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    @Query(value = "SELECT * FROM address a WHERE a.main = :main and a.person_id = :person_id", nativeQuery = true)
    List<Address> findMainAddress(@Param("person_id") UUID person_id, @Param("main") boolean main);

    @Query(value = "SELECT * FROM address a WHERE a.person_id = :person_id", nativeQuery = true)
    List<Address> findByPersonId(@Param("person_id") UUID person_id);
}

