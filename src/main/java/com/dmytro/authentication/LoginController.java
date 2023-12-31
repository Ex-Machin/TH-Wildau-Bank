package com.dmytro.authentication;

import com.dmytro.customer.Customer;
import com.dmytro.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (customerService.authenticate(username, password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        } //In summary, this method handles a login request, attempts to authenticate the provided username and password using a service (customerService), and redirects the user to the home page if successful. If the authentication fails, it adds an error message attribute to the model and returns to the login page for another attempt.
    }

}
