package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated
@RequestMapping("/api")
public class CustomerApiController implements Roles{
	private CustomerService customerService;

	@GetMapping("/project_manager/customers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/project_manager/customers/id/{id}")
	public Customer getCustomerById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return customerService.getCustomerById(id);
	}

	@GetMapping("/project_manager/customers/number_of")
	public int getNumberOfCustomers() {
		return customerService.getNumberOfCustomers();
	}
	
	@PostMapping("/project_manager/customers")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@PutMapping("/project_manager/customers/{id}")
	public Customer updateCustomer(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Customer customerDetails) throws EntityNotFoundException {

		return customerService.updateCustomer(id, customerDetails);
	}

	@DeleteMapping("/project_manager/customers/{id}")
	public void deleteCustomer(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		
		customerService.deleteCustomer(id);
	}

	@Autowired
	public void setCustomerServicee(CustomerService customerService) {
		this.customerService = customerService;
	}
}