package com.leticia.personmanager.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leticia.personmanager.models.Address;
import com.leticia.personmanager.models.Person;
import com.leticia.personmanager.services.PersonService;

@WebMvcTest(PersonController.class)

public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Test
    public void personConsult_nameEmpty() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Address> addresses = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        Person person = new Person("Letícia", date, addresses);

        List<Person> people = new ArrayList<>();
        people.add(person);

        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString((people));

        when(this.service.list()).thenReturn(people);
        this.mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isFound()).andExpect(content().json(test));
    }

    @Test
    public void personConsult_name() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date(System.currentTimeMillis());
        List<Address> addresses = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        Person person = new Person("Letícia", date, addresses);
        people.add(person);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString((people));

        when(this.service.consultPersonByName(person.getName())).thenReturn(people);
        this.mockMvc.perform(get("/person?name=Letícia")).andExpect(status().isFound()).andExpect(content().json(test));
    }

    @Test 
    public void consultById_ok() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        List<Person> people = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        UUID id = UUID.randomUUID();
        List<Address> addresses = new ArrayList<>();
        Person person = new Person("Letícia", date, addresses);
        person.setId(id);
        people.add(person);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);

        when(this.service.searchPersonById(id)).thenReturn(Optional.of(person));
        this.mockMvc.perform(get("/person/"+id)).andExpect(status().isFound()).andExpect(content().json(test));

        }

    @Test
    public void consultById_notFound() throws Exception{
        List<Person> people = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        UUID id = UUID.randomUUID();
        List<Address> addresses = new ArrayList<>();
        Person person = new Person("Letícia", date, addresses);
        person.setId(id);
        people.add(person);

        when(this.service.searchPersonById(id)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/person/"+id)).andExpect(status().isNotFound());
    }

    @Test
    public void SavePerson_ok() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        List<Address> addresses = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        Person person = new Person("Letícia", date, addresses);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        when(this.service.savePerson(any(Person.class))).thenReturn(person);
        this.mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(test)).andDo(print()).andExpect(status().isCreated()).andExpect(content().json(test));
    }

    @Test
    public void updatePerson_isEmptyPerson() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        List<Address> addresses = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        UUID id = UUID.randomUUID();
        Person person = new Person("Letícia", date, addresses);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        when(this.service.searchPersonById(id)).thenReturn(Optional.empty());
        this.mockMvc.perform(put("/person/"+id).contentType(MediaType.APPLICATION_JSON).content(test)).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void updatePerson_notEmptyPerson() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        List<Address> addresses = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        UUID id = UUID.randomUUID();
        Person person = new Person("Letícia", date, addresses);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        when(this.service.searchPersonById(id)).thenReturn(Optional.of(person));
        when(this.service.savePerson(any(Person.class))).thenReturn(person);
        this.mockMvc.perform(put("/person/"+id).contentType(MediaType.APPLICATION_JSON).content(test)).andDo(print()).andExpect(status().isOk()).andExpect(content().json(test));
    }
}
