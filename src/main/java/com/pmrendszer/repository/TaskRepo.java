package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer> {
	List<Task> findAll();
	List<Task> findByStatusId(int id);
	List<Task> findByTopicContainingOrderByTopic(String topic);
	Task findById(int id);
}