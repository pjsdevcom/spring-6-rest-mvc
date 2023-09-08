package com.pjsdev.spring6restmvc.controllers;

import com.pjsdev.spring6restmvc.model.CustomerDTO;
import com.pjsdev.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customers";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{id}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listCustomers() {
        return customerService.listCustomers();
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<CustomerDTO> handlePost(@RequestBody CustomerDTO customer) {

        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customers/" + savedCustomer.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> updateById(@PathVariable UUID id, @RequestBody CustomerDTO customer) {

        if (customerService.updateCustomerById(id, customer).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> deleteById(@PathVariable UUID id) {

        if (!customerService.deleteCustomerById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> patchById(@PathVariable UUID id, @RequestBody CustomerDTO customer) {

        if (customerService.patchCustomerById(id, customer).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
