package com.dmytro.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getUser() {
        return customerRepository.findAll();
    }

    @PostMapping
    public void addUser(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{userId}")
    public void updateUser(@PathVariable("userId") Integer id, @RequestBody NewCustomerRequest request) {
        Customer customer = customerRepository.getById(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }
}
