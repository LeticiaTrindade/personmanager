package com.leticia.personmanager.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import com.leticia.personmanager.models.Address;
import com.leticia.personmanager.models.Person;
import com.leticia.personmanager.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Test
    public void save_ok() throws Exception{  
        List<Address> addresses = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        Person person = new Person("Letícia", date, addresses);  
        when(this.repository.save(person)).thenReturn(person);
        Person serviceSave = this.service.savePerson(person);
        assertThat(serviceSave).usingRecursiveComparison().isEqualTo(person);
    }

    @Test
    public void search_ok() throws Exception{  
        List<Address> addresses = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        Person person = new Person("Letícia", date, addresses); 
        UUID id = UUID.randomUUID();
        when(this.repository.findById(id)).thenReturn(Optional.of(person));
        Optional<Person> serviceSearch= this.service.searchPersonById(id);
        assertThat(serviceSearch).usingRecursiveComparison().isEqualTo(Optional.of(person));
    }

    @Test
    public void list_ok() throws Exception{
        List<Person> people = new ArrayList<>();
        when(this.repository.findAll()).thenReturn(people);
        List<Person> serviceList = this.service.list();
        assertThat(serviceList).usingRecursiveComparison().isEqualTo(people);
    }

    @Test
    public void personConsult_ok() throws Exception{
        String name = "letícia";

        List<Person> people = new ArrayList<>();
        when(this.repository.consultPersonByName(name)).thenReturn(people);
        List<Person> serviceConsult = this.service.consultPersonByName(name);
        assertThat(serviceConsult).usingRecursiveComparison().isEqualTo(people);
    }
}
