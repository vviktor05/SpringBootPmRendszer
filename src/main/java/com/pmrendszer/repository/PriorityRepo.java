package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Priority;

@Repository
public interface PriorityRepo extends CrudRepository<Priority, Integer> {
	List<Priority> findAll();
}