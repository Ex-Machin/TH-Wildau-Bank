package com.dmytro.authentication;

import com.dmytro.customer.Customer;
import com.dmytro.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer) {
        customerService.registerNewCustomer(customer);
        return "redirect:/home";
    } //In summary, this method handles the registration of a new customer. It receives a Customer object as a parameter, registers it using a service (customerService), and then redirects the user to the home page. The specifics of the registration process and the service logic would be implemented in the registerNewCustomer method of the customerService.
}