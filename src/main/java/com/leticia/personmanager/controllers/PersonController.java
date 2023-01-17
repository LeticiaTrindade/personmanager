package com.leticia.personmanager.controllers;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leticia.personmanager.dtos.PersonDto;
import com.leticia.personmanager.models.Person;
import com.leticia.personmanager.services.PersonService;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/person")

public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody @Valid PersonDto personDto) {
        Person person = new Person(personDto.getName(), personDto.getBirthDate(), personDto.getAddress());
        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> updatePerson(@RequestBody @Valid PersonDto personDto, @PathVariable UUID personId) {

        Optional<Person> person = personService.searchPersonById(personId);
        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Person updatedPerson = person.get();
        updatedPerson.setName(personDto.getName());
        updatedPerson.setBirthDate(personDto.getBirthDate());
        updatedPerson.setAddress(personDto.getAddress());
        Person savedPerson = personService.savePerson(updatedPerson);
        return ResponseEntity.status(HttpStatus.OK).body(personService.savePerson(savedPerson));
    }

    @GetMapping
    public ResponseEntity<List<Person>> consultPerson(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            List<Person> foundPeople = personService.consultPersonByName(name.get());
            return ResponseEntity.status(HttpStatus.FOUND).body(foundPeople);
        }
        List<Person> allPeople = personService.list();
        return ResponseEntity.status(HttpStatus.FOUND).body(allPeople);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> consultPersonById(@PathVariable UUID personId) {
        Optional<Person> srPerson = personService.searchPersonById(personId);
        if (srPerson.isPresent()) {
            Person person = srPerson.get();
            return ResponseEntity.status(HttpStatus.FOUND).body(person);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
