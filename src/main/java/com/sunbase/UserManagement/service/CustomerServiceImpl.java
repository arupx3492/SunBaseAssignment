package com.sunbase.UserManagement.service;

import com.sunbase.UserManagement.entities.Customer;
import com.sunbase.UserManagement.exception.CustomerNotFoundException;
import com.sunbase.UserManagement.exception.InvalidArgument;
import com.sunbase.UserManagement.repository.CustomerRepository;
import com.sunbase.UserManagement.restTemplate.SunbaseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    SunbaseData sunbaseData;
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

    @Override
    public void syncCustomers() {

            String token = sunbaseData.authenticateUser();

        System.out.println("THis is token :- "+token);
            String fetchUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization","Bearer "+token);

            HttpEntity<String> entity=new HttpEntity<>(headers);

            ResponseEntity<Customer[]> response=restTemplate.exchange(fetchUrl, HttpMethod.GET,entity,Customer[].class);

            if(response.getStatusCode()== HttpStatus.OK){
                Customer[] customers=response.getBody();
                assert customers != null;
                for(Customer customer:customers){
                    Optional<Customer> existingCustomer=customerRepository.findById(customer.getUuid());

                    if(existingCustomer.isPresent()){
                        // update the existing customer
                        updateCustomer(existingCustomer.get().getUuid(),customer);

                    }else{

                        createCustomer(customer);
                        // add a new customer
                    }
                }
            }else{
                throw new RuntimeException("Failed to fetch customer list");
            }

        }

}
