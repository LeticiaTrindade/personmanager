package com.leticia.personmanager.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leticia.personmanager.models.Address;
import com.leticia.personmanager.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> listAddresses() {
        return addressRepository.findAll();
    }

    public List<Address> findMainAddress(UUID id, boolean main) {
        return addressRepository.findMainAddress(id, main);
    }

    public List<Address> saveAll(List<Address> address) {
        return addressRepository.saveAll(address);
    }

    public List<Address> findByPersonId(UUID id) {
        return addressRepository.findByPersonId(id);
    }
}
