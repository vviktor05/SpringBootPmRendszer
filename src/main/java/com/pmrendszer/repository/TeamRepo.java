package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Team;

@Repository
public interface TeamRepo extends CrudRepository<Team, Integer> {
	List<Team> findAll();
}