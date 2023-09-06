package com.pjsdev.spring6restmvc.services;

import com.pjsdev.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(UUID id);

    List<CustomerDTO> listCustomers();

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customer);

    void deleteCustomerById(UUID id);

    void patchCustomerById(UUID id, CustomerDTO customer);
}
