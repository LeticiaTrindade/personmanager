package com.leticia.personmanager.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.leticia.personmanager.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query(value = "SELECT * FROM person p WHERE p.name = :name", nativeQuery = true)
    List<Person> consultPersonByName(String name);
}