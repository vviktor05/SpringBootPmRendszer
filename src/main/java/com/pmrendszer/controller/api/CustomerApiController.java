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
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Customer;
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
	public Customer getCustomerById(@PathVariable("id") int id) throws EntityNotFoundException {
		return customerService.getCustomerById(id);
	}

	@PostMapping("")
	public void addCustomer(@Valid @RequestBody Customer customer) {
		customerService.addCustomer(customer);
	}

	@PutMapping("/{id}")
	public void updateCustomer(@PathVariable(value = "id") int id, @Valid @RequestBody Customer customerDetails)
			throws EntityNotFoundException {
		customerService.updateCustomer(id, customerDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		customerService.deleteCustomer(id);
	}

	@Autowired
	public void setCustomerServicee(CustomerService customerService) {
		this.customerService = customerService;
	}
}