package com.sunbase.UserManagement.restTemplate;

import com.sunbase.UserManagement.entities.Customer;
import com.sunbase.UserManagement.repository.CustomerRepository;
import com.sunbase.UserManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class SunbaseData {




    public String authenticateUser(){
        String url="https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        RestTemplate restTemplate =new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter() {
            @Override
            public boolean canRead(Class<?> clazz, MediaType mediaType) {
                return mediaType != null && mediaType.getSubtype().equals("json");
            }
        });

        Map<String,String> requestBody=new HashMap<>();
        requestBody.put("login_id","test@sunbasedata.com");
        requestBody.put("password","Test@123");

        ResponseEntity<Map> response=restTemplate.postForEntity(url,requestBody,Map.class);

        System.out.println("This is response:- "+response);

        if(response.getStatusCode()== HttpStatus.OK){


            Map<String,String> body=response.getBody();
            assert body != null;
            return body.get("access_token");
        }else{
            throw  new RuntimeException("Failed to authenticate User");
        }

    }



}
