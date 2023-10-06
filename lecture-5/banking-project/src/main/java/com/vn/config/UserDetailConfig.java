package com.vn.config;

import com.vn.model.Customer;
import com.vn.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailConfig implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, passwd = null;

        List<GrantedAuthority> authorities = null;
        List<Customer> customers = customerRepo.findByEmail(username);
        if (customers.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else {
            userName = customers.get(0).getEmail();
            passwd = customers.get(0).getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        }

        return new User(username,passwd,authorities);
    }
}
