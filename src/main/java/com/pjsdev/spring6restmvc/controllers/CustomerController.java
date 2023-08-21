package com.pjsdev.spring6restmvc.controllers;

import com.pjsdev.spring6restmvc.model.Customer;
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
    public Customer getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> listCustomers() {
        return customerService.listCustomers();
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<Customer> handlePost(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customers/" + savedCustomer.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> updateById(@PathVariable UUID id, @RequestBody Customer customer) {

        customerService.updateCustomerById(id, customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> deleteById(@PathVariable UUID id) {

        customerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> patchById(@PathVariable UUID id, @RequestBody Customer customer) {

        customerService.patchCustomerById(id, customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
