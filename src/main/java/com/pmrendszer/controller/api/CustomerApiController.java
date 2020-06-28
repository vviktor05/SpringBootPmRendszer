package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmrendszer.domain.Customer;
import com.pmrendszer.service.CheckerClass;
import com.pmrendszer.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {
	private CustomerService customerService;

	@GetMapping("")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/id/{id}")
	public Customer getCustomerById(@PathVariable("id") int id) {
		Customer customer = customerService.getCustomerById(id);
		CheckerClass.notEmptyOrThrow(customer);
		
		return customer;
	}

	@PostMapping("")
	public void addCustomer(@Valid @RequestBody Customer customer) {
		customerService.addCustomer(customer);
	}

	@PutMapping("/{id}")
	public void updateCustomer(@PathVariable(value = "id") int id, @Valid @RequestBody Customer customerDetails) {
		Customer customer = customerService.getCustomerById(id);
		CheckerClass.notEmptyOrThrow(customer);
		
		customerService.updateCustomer(customer, customerDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable(value = "id") int id) {
		Customer customer = customerService.getCustomerById(id);
		CheckerClass.notEmptyOrThrow(customer);
		
		customerService.deleteCustomer(customer);
	}

	@Autowired
	public void setCustomerServicee(CustomerService customerService) {
		this.customerService = customerService;
	}
}