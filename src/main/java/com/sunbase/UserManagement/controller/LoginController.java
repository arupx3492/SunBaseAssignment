package com.sunbase.UserManagement.controller;

import com.sunbase.UserManagement.entities.User;
import com.sunbase.UserManagement.repository.UserRepository;
import com.sunbase.UserManagement.security.JwtUtil;
import com.sunbase.UserManagement.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody User user) {

        // the bellow code is to save a new user

//
//
//        User user1=new User();
//        user1.setEmail("test@sunbasedata.com");
//        user1.setPassword(passwordEncoder.encode("Test@123"));
//
//        System.out.println(userRepository.save(user1));
//
//

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String jwt =jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);
        }
    }


}

