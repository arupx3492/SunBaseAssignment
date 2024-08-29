package com.sunbase.UserManagement.service;

import com.sunbase.UserManagement.entities.Customer;
import com.sunbase.UserManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;


public interface CustomerService {

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(String uuid,Customer customer);
    public Page<Customer> getCustomers(String searchBy, String searchTerm, Pageable pageable);

    public Customer getCustomerByUUID(String UUID);
    public  void deleteCustomer(String uuid);

}
