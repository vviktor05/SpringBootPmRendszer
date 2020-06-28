package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.TeamMembership;

@Repository
public interface TeamMembershipRepo extends CrudRepository<TeamMembership, Integer> {
	List<TeamMembership> findAll();
}