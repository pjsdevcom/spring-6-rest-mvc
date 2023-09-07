package com.pjsdev.spring6restmvc.controllers;

import com.pjsdev.spring6restmvc.entities.Customer;
import com.pjsdev.spring6restmvc.mappers.CustomerMapper;
import com.pjsdev.spring6restmvc.model.CustomerDTO;
import com.pjsdev.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();
    }

    @Test
    void testGetCustomerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(UUID.randomUUID()));
    }

    @Test
    void testListCustomers() {
        assertThat(customerController.listCustomers().size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        customerRepository.deleteAll();
        assertThat(customerController.listCustomers().size()).isEqualTo(0);
    }

    @Rollback
    @Transactional
    @Test
    void testSaveNewCustomer() {

        CustomerDTO customerDTO = CustomerDTO.builder().name("Bob the Tester").build();

        ResponseEntity<CustomerDTO>  responseEntity = customerController.handlePost(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] location = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(location[4]);

        Customer savedCustomer = customerRepository.findById(savedUUID).orElse(null);
        assertThat(savedCustomer).isNotNull();
    }

    @Test
    void testUpdateCustomer() {

        Customer existingCustomer = customerRepository.findAll().get(0);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(existingCustomer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);

        final String updatedName = "Bob the Updated Tester";
        customerDTO.setName(updatedName);

        ResponseEntity<CustomerDTO> responseEntity = customerController.updateById(existingCustomer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(existingCustomer.getId()).get();
        assertThat(updatedCustomer.getName()).isEqualTo(updatedName);
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> customerController.updateById(UUID.randomUUID(), CustomerDTO.builder().build()));
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteCustomer() {
        Customer customer = customerRepository.findAll().get(0);

        ResponseEntity<CustomerDTO> responseEntity = customerController.deleteById(customer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(customerRepository.count()).isEqualTo(2);

        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(foundCustomer).isNull();
    }

    @Test
    void testDeleteCustomerNotFound() {
        assertThrows(NotFoundException.class, () -> customerController.deleteById(UUID.randomUUID()));
    }

    @Test
    void testPatchCustomer() {

        Customer existingCustomer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = CustomerDTO.builder().name("Bob the Patched Tester").build();

        ResponseEntity<CustomerDTO> responseEntity = customerController.patchById(existingCustomer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer patchedCustomer = customerRepository.findById(existingCustomer.getId()).get();
        assertThat(patchedCustomer.getName()).isEqualTo(customerDTO.getName());
    }
}