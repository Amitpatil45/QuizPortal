package com.examportal.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.Role;

public interface roleRepository extends JpaRepository<Role, Long> {

}
