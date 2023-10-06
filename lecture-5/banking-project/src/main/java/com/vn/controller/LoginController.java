package com.vn.controller;

import com.vn.model.Customer;
import com.vn.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
        @RequestBody Customer customer
    ) {
       Customer savedCustomer = null;
       ResponseEntity response = null;
       try {
           // Hash Password with BCryptPasswordEncoder
           String hashPwd = passwordEncoder.encode(customer.getPwd());
           customer.setPwd(hashPwd);
           // Save Customer
           savedCustomer = customerRepo.save(customer);

           if (savedCustomer.getId() > 0) {
               response = ResponseEntity.status(HttpStatus.CREATED).body("Given user details are successfully registered");
           }
       } catch (Exception ex) {
           response =  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occured due to " + ex.getMessage());
       }
       return response;
    }
}
