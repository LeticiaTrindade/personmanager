package com.leticia.personmanager.services;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leticia.personmanager.models.Person;
import com.leticia.personmanager.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
   private PersonRepository personRepository;

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> searchPersonById(UUID id) {

        return personRepository.findById(id);
    }

    public List<Person> list() {
        return personRepository.findAll();
    }
    
    public List<Person> consultPersonByName(String name) {
        return personRepository.consultPersonByName(name);
    }

}
