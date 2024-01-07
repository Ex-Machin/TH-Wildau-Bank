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

    public void deductAmount(Integer accountId, double amount) {
        Customer customer = customerRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        if (customer.getMoney() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        double newBalance = customer.getMoney() - amount;
        customer.setMoney(newBalance);
        customerRepository.save(customer);
    } //In summary, this method deducts a specified amount from a customer's account balance, performing necessary checks for the validity of the account ID and the sufficiency of funds. If successful, it updates the customer's balance in the repository.

    public void addAmount(Integer accountId, double amount) {
        Customer customer = customerRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        double newBalance = customer.getMoney() + amount;
        customer.setMoney(newBalance);
        customerRepository.save(customer);
    } // In summary, this method adds a specified amount to a customer's account balance, performing necessary checks for the validity of the account ID. If successful, it updates the customer's balance in the repository.


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

        // Give a new customer bonus
        customer.setMoney(300);

        // Save the authority
        authorityRepository.save(authority);
    }

    public boolean authenticate(String username, String password) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByUsername(username));
        return customer.map(value -> value.getPassword().equals(password)).orElse(false);
    }
}

