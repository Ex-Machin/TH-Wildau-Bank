package com.dmytro.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final CustomerRepository customerRepository;

    @Autowired
    public UserController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<User> getUser() {
        return customerRepository.findAll();
    }

    @PostMapping
    public void addUser(@RequestBody NewCustomerRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setAge(request.age());
        customerRepository.save(user);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{userId}")
    public void updateUser(@PathVariable("userId") Integer id, @RequestBody NewCustomerRequest request) {
        User user = customerRepository.getById(id);
        user.setName(request.name());
        user.setEmail(request.email());
        user.setAge(request.age());
        customerRepository.save(user);
    }
}
