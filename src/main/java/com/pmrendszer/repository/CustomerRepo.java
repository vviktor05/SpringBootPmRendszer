package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	List<Customer> findAll();
}