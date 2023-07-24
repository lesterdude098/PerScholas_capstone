package com.example.capstone4.controller;

import com.example.capstone4.model.Customer;
import com.example.capstone4.repository.CustomerRepository;
import com.example.capstone4.services.CustomerService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Data
@Controller
public class CustomerController {
    private CustomerService customerService;
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService,
                              CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    //view list of customers
    @GetMapping("/customers")
    public String listCustomers(Model model){
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";

    }

    //direct to create customer form
    @GetMapping("/customers/new")
    public String createCustomerForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create_customer";

    }

    //bind to customer entity
    @PostMapping("/customers")
    public String saveStudent(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customers";

    }

//    @PostMapping("/customers")
//    public String saveStudent(@Valid Customer customer, BindingResult result, Model model){
//        if (result.hasErrors()){
//            return "customers/new";
//        }
//        customerRepository.save(customer);
//        model.addAttribute("customers", customerRepository.findAll());
//        return "customers";
//    }

}
