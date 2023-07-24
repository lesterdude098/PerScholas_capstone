package com.example.capstone4;

import com.example.capstone4.model.Customer;
import com.example.capstone4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Capstone4Application {

	public static void main (String[] args) {
		SpringApplication.run(Capstone4Application.class, args);
	}
	@Autowired
	private CustomerRepository customerRepository;

//	@Override
//	public void run(String... args) throws Exception{
//		Customer customer1 = new Customer("test", "test1", "Test@email.com");
//		customerRepository.save(customer1);
//
//		Customer customer2 = new Customer("test2", "test2", "test2@email.com");
//		customerRepository.save(customer2);
//


//	}

}
