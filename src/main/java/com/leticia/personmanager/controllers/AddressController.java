package com.leticia.personmanager.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leticia.personmanager.dtos.AddressDto;
import com.leticia.personmanager.models.Address;
import com.leticia.personmanager.services.AddressService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("person/{personId}/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> saveAddresses(@RequestBody @Valid AddressDto addressDto,
            @PathVariable UUID personId) {
        Address newAddress = new Address(addressDto.getPublicPlace(), addressDto.getZipCode(), addressDto.getNumber(),
                addressDto.getCity(), addressDto.isMain());
        newAddress.setPersonId(personId);

        List<Address> addresses = new ArrayList<>();

        if (newAddress.isMain() == true) {
            addresses = addressService.listAddresses();

            for (Address address : addresses) {
                address.setMain(false);
            }
            addresses.add(newAddress);
            addressService.saveAll(addresses);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
        }
        addresses.add(newAddress);
        addressService.saveAll(addresses);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }

    @GetMapping
    public ResponseEntity<List<Address>> fetchAddress(@RequestParam Optional<Boolean> main,
            @PathVariable UUID personId) {

        if (main.isPresent()) {
            List<Address> foudMain = addressService.findMainAddress(personId, main.get());
            return ResponseEntity.status(HttpStatus.FOUND).body(foudMain);
        }
        List<Address> foundById = addressService.findByPersonId(personId);
        return ResponseEntity.status(HttpStatus.FOUND).body(foundById);
    }

}
