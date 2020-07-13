package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Customer;
import com.pmrendszer.repository.CustomerRepo;

@Service
public class CustomerService {
	private CustomerRepo customerRepo;

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomerById(int id) throws EntityNotFoundException {
		Customer customer = customerRepo.findById(id);
		CheckerClass.ifEmptyThrowException(customer);

		return customer;
	}

	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer updateCustomer(int id, Customer customerDetails) throws EntityNotFoundException {
		Customer customer = customerRepo.findById(id);
		CheckerClass.ifEmptyThrowException(customer);

		return customerRepo.save(updateCustomerDetails(customer, customerDetails));
	}

	public void deleteCustomer(int id) throws EntityNotFoundException {
		Customer customer = customerRepo.findById(id);
		CheckerClass.ifEmptyThrowException(customer);

		customerRepo.delete(customer);
	}

	private Customer updateCustomerDetails(Customer customer, Customer customerDetails) {
		customer.setName(customerDetails.getName());
		customer.setPhone(customerDetails.getPhone());
		customer.setEmail(customerDetails.getEmail());
		customer.setWebsite(customerDetails.getWebsite());
		customer.setZipCode(customerDetails.getZipCode());
		customer.setLocality(customerDetails.getLocality());
		customer.setStreetAddress(customerDetails.getStreetAddress());
		customer.setUpdateMode(true);
		return customer;
	}

	@Autowired
	public void setCustomerRepo(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}
}