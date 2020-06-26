package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Customer;
import com.pmrendszer.repository.CustomerRepo;

@Service
public class CustomerService {
	private CustomerRepo customerRepo;
	
	public List<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	@Autowired
	public void setCustomerRepo(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}
}