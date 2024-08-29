package com.sunbase.UserManagement.service;

import com.sunbase.UserManagement.entities.Customer;
import com.sunbase.UserManagement.exception.CustomerNotFoundException;
import com.sunbase.UserManagement.exception.InvalidArgument;
import com.sunbase.UserManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(String uuid, Customer customer) {
        Customer existingCustomer = customerRepository.findById(uuid).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        existingCustomer.setFirst_name(customer.getFirst_name());
        existingCustomer.setLast_name(customer.getLast_name());
        existingCustomer.setStreet(customer.getStreet());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setState(customer.getState());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public Page<Customer> getCustomers(String searchBy, String searchTerm, Pageable pageable) {

        if(searchBy != null && searchTerm != null) {

            return switch (searchBy.toLowerCase()) {
                case "first_name" -> customerRepository.findByFirstName(searchTerm, pageable);
                case "city" -> customerRepository.findByCity(searchTerm, pageable);
                case "email" -> customerRepository.findByEmail(searchTerm, pageable);
                case "phone" -> customerRepository.findByPhone(searchTerm, pageable);
                default ->  throw new InvalidArgument("Invalid search criteria: " + searchBy);
            };
        }else{
            return customerRepository.findAll(pageable);
        }

    }

    @Override
    public Customer getCustomerByUUID(String uuid) {
        return customerRepository.findById(uuid).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
    }

    @Override
    public void deleteCustomer(String uuid) {
        Customer customer=customerRepository.findById(uuid).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
        customerRepository.delete(customer);

    }
}
