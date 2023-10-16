package com.examportal.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.User;

public interface userRepository extends JpaRepository<User, Long> {

	User findByuserName(String userName);
}
