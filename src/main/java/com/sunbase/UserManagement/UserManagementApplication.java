package com.sunbase.UserManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
public class UserManagementApplication {

	public UserManagementApplication() throws NoSuchAlgorithmException {
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}

