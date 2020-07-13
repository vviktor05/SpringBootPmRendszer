package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Status;

@Repository
public interface StatusRepo extends CrudRepository<Status, Integer> {
	List<Status> findAll();
}