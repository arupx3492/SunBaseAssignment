package com.sunbase.UserManagement.restTemplate;

import com.sunbase.UserManagement.entities.Customer;
import com.sunbase.UserManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SunbaseData {

    @Autowired
    CustomerRepository customerRepository;

    public String authenticateUser(){
        String url="https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        RestTemplate restTemplate =new RestTemplate();

        Map<String,String> requestBody=new HashMap<>();
        requestBody.put("login_id","test@sunbasedata.com");
        requestBody.put("password","Test@123");

        ResponseEntity<Map> response=restTemplate.postForEntity(url,requestBody,Map.class);

        if(response.getStatusCode()== HttpStatus.OK){


            Map<String,String> body=response.getBody();
            assert body != null;
            return body.get("token");
        }else{
            throw  new RuntimeException("Failed to authenticate User");
        }

    }


    public void syncCustomers(){
        String token = authenticateUser();
        String fetchUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+token);

        HttpEntity<String> entity=new HttpEntity<>(headers);

        ResponseEntity<Customer[]> response=restTemplate.exchange(fetchUrl, HttpMethod.GET,entity,Customer[].class);

        if(response.getStatusCode()==HttpStatus.OK){
            Customer[] customers=response.getBody();
            assert customers != null;
            for(Customer customer:customers){
                Optional<Customer> existingCustomer=customerRepository.findById(customer.getUuid());

                if(existingCustomer.isPresent()){
                    // update the existing customer

                }
            }
        }

    }
}
