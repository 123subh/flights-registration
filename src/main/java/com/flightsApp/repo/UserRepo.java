package com.flightsApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightsApp.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String emailId);
//	User getByEmail(String emailId); this will also work
//	User readByEmail(String emailId); this will also work
}
