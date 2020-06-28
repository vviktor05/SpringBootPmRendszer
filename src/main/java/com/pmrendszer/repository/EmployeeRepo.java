package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
	List<Employee> findAll();
	Employee findById(int id);
}