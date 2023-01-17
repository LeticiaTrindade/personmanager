package com.leticia.personmanager.services;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.leticia.personmanager.models.Address;
import com.leticia.personmanager.repositories.AddressRepository;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository repository;

    @InjectMocks
    private AddressService service;

    @Test
    public void ListAddress_ok() throws Exception{
        List<Address> addresses = new ArrayList<>();
        when(this.repository.findAll()).thenReturn(addresses);
        List<Address> serviceList = this.service.listAddresses();
        assertThat(serviceList).usingRecursiveComparison().isEqualTo(addresses);
    }
    
    @Test
    public void mainAddress_ok() throws Exception{
        List<Address> addresses = new ArrayList<>();
        UUID id = UUID.randomUUID();
        when(this.repository.findMainAddress(id, false)).thenReturn(addresses);
        List<Address> serviceMainAddress = this.service.findMainAddress(id, false);
        assertThat(serviceMainAddress).usingRecursiveComparison().isEqualTo(addresses);
    }

    @Test
    public void saveAll_ok() throws Exception{
        List<Address> addresses = new ArrayList<>();
        when(this.repository.saveAll(addresses)).thenReturn(addresses);
        List<Address> serviceSaveAll = this.service.saveAll(addresses);
        assertThat(serviceSaveAll).usingRecursiveComparison().isEqualTo(addresses);
    }

    @Test
    public void findByPersonId_ok() throws Exception{
        UUID id = UUID.randomUUID();
        List<Address> addresses = new ArrayList<>();
        when(this.repository.findByPersonId(id)).thenReturn(addresses);
        List<Address> serviceFindAllByPersonId = this.service.findByPersonId(id);
        assertThat(serviceFindAllByPersonId).usingRecursiveComparison().isEqualTo(addresses);
    }
}
