package com.dmytro.customer;

import com.dmytro.authorities.Authority;
import com.dmytro.authorities.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public void registerNewCustomer(Customer customer) {
        // Assuming password encoding is handled elsewhere
        customer.setEnabled(true); // or based on your application logic

        // Save the customer
        customer = customerRepository.save(customer);

        // Assign an authority to the customer
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER"); // or any default role
        authority.setCustomer(customer);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        customer.setAuthorities(authorities);

        // Save the authority
        authorityRepository.save(authority);
    }

    public boolean authenticate(String username, String password) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByUsername(username));
        return customer.map(value -> value.getPassword().equals(password)).orElse(false);
    }
}

