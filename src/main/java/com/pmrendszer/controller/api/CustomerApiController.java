package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Customer;
import com.pmrendszer.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {
	private CustomerService customerService;

	@RequestMapping("")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@Autowired
	public void setCustomerServicee(CustomerService customerService) {
		this.customerService = customerService;
	}
}