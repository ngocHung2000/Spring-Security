package com.vn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(
        HttpSecurity http
    ) throws Exception {
        http.csrf().disable();
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/contact","/notices","/register").permitAll()
                        .requestMatchers("/myAccount","/myBalance","myLoans","myCards").authenticated()
                        .anyRequest().denyAll() // If have any request not exist or not permission =>> Access Denied (ERROR: 403)
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

//        /**
//         * Approach 1 where we use withDefaultPasswordEncoder() method
//         * while creating the user details
//         */
    // Before Approach 1
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("READ")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
    // End Approach 1
    /**
     * Approach 2 where we use NoOpPasswordEncoder Bean
     * while creating the user details
     */
    // Before Approach 2
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//
//        UserDetails admin = User.withUsername("admin").password("admin").authorities("ADMIN").build();
//        UserDetails user = User.withUsername("user").password("user").authorities("READ").build();
//
//        inMemoryUserDetailsManager.createUser(admin);
//        inMemoryUserDetailsManager.createUser(user);
//
//        return inMemoryUserDetailsManager;
//    }

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod
     * @return PasswordEncoder
     */
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
    // End Approach 2

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
