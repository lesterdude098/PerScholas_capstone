package com.example.capstone4.controller;

import com.example.capstone4.model.Customer;
import com.example.capstone4.repository.CustomerRepository;
import com.example.capstone4.services.CustomerService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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

//has validation
   @PostMapping(path = "/customers")
    public String saveCustomer(@ModelAttribute("customer")@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()){
            return "create_customer";
        }
        customerRepository.save(customer);
        return "redirect:/customers";
    }
//edit customers from list
    @GetMapping("/customer/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model){
    model.addAttribute("customer", customerService.getCustomerById(id));
    return "edit_customer";
    }

    //handler for update
    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id,
        @ModelAttribute("customer") Customer customer, Model model){
    //from database by ID
        Customer currentCustomer = customerService.getCustomerById(id);
        currentCustomer.setId(id);
        currentCustomer.setFirstName(customer.getFirstName());
        currentCustomer.setLastName(customer.getLastName());
        currentCustomer.setEmail(customer.getEmail());

        //save current customer
        customerService.editCustomer(currentCustomer);
        return "redirect:/customers";

    }


}





