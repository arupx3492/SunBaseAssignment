package com.sunbase.UserManagement.controller;

import com.sunbase.UserManagement.entities.Customer;
import com.sunbase.UserManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")

    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer){
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customerService.getCustomerByUUID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);

        return ResponseEntity.ok("Customer Deleted Successfully");

    }
    @GetMapping("/customers")
    public Page<Customer> getCustomers(
            @RequestParam(value="searchBy",required = false) String searchBy,
            @RequestParam(value="searchTerm",required = false) String searchTerm,
            @RequestParam(value="page",defaultValue="0",required = false) int page,
            @RequestParam(value="size",defaultValue="100",required = false) int size,
            @RequestParam(value="sortBy",defaultValue = "Phone",required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue="asc",required = false) String sortDir
    ){
        Pageable pageable= PageRequest.of(page,size,sortDir.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        return customerService.getCustomers(searchBy,searchTerm,pageable);
    }
    @PostMapping("/sync")
    public  ResponseEntity<String> syncCustomers(){
        try {
            customerService.syncCustomers();
            return ResponseEntity.ok("Customer Synced Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to Sync Customers");
        }
    }

}
