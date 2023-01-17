package com.leticia.personmanager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leticia.personmanager.models.Address;
import com.leticia.personmanager.services.AddressService;

@WebMvcTest(AddressController.class)

public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService service;

    @Test
    public void ListAddressByPersonId_EmptyArray() throws Exception {
        UUID id = UUID.randomUUID();
        List<Address> address = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);
        when(this.service.findByPersonId(id)).thenReturn(address);
        this.mockMvc.perform(get("/person/" + id + "/address")).andExpect(status().isFound())
                .andExpect(content().json(test));
    }

    @Test
    public void ListAddressByPersonId_NotEmptyArray() throws Exception {
        List<Address> addresses = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Address address = new Address("rua", "123", 26, "paulista", true);
        UUID id = UUID.randomUUID();
        address.setPersonId(id);
        addresses.add(address);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(addresses);
        when(this.service.findByPersonId(id)).thenReturn(addresses);
        this.mockMvc.perform(get("/person/" + id + "/address")).andExpect(status().isFound())
                .andExpect(content().json(test));
    }

    @Test
    public void ListAddressByPersonId_MainIsPresent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Address> addresses = new ArrayList<>();
        Address address = new Address("rua", "123", 26, "paulista", true);
        UUID id = UUID.randomUUID();
        address.setPersonId(id);
        addresses.add(address);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString((addresses));
        when(this.service.findMainAddress(id, true)).thenReturn(addresses);
        this.mockMvc.perform(get("/person/" + id + "/address?main=true")).andExpect(status().isFound())
                .andExpect(content().json(test));
    }

    @Test
    public void SaveAddress_notMain() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Address> addresses = new ArrayList<>();
        Address address = new Address("rua", "123", 26, "paulista", false);
        UUID id = UUID.randomUUID();
        address.setPersonId(id);
        addresses.add(address);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString((address));
        when(this.service.saveAll(anyList())).thenReturn(addresses);
        this.mockMvc.perform(post("/person/" + id + "/address").contentType(MediaType.APPLICATION_JSON).content(test))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(test));
    }

    @Test
    public void SaveAddress_main() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Address> addresses = new ArrayList<>();
        Address address = new Address("rua", "123", 26, "paulista", true);
        UUID id = UUID.randomUUID();
        address.setPersonId(id);
        addresses.add(address);
        String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString((address));
        when(this.service.listAddresses()).thenReturn(addresses);
        when(this.service.saveAll(anyList())).thenReturn(addresses);
        this.mockMvc.perform(post("/person/" + id + "/address").contentType(MediaType.APPLICATION_JSON).content(test))
                .andDo(print()).andExpect(status().isCreated()).andExpect(content().json(test));
    }
}
