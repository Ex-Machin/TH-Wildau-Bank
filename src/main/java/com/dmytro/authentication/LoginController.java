package com.dmytro.registration;

import com.dmytro.customer.Customer;
import com.dmytro.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        System.out.println("login controller ");
        if (customerService.authenticate(username, password)) {
            System.out.println("authentificated");
            return "redirect:/home";
        } else {
            System.out.println("not authentificated");
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }
    }

}
