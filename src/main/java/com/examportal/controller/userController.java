package com.examportal.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.userRole;
import com.examportal.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class userController {

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		user.setProfile("default.png");

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		Role role = new Role();
		role.setRoleName("NORMAL");
		Set<userRole> userRoleSet = new HashSet<>();
		userRole userRole = new userRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		return userService.createUser(user, userRoleSet);
	}

	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String uname) {
		System.out.println(uname);

		return userService.getUser(uname);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long uid) {
		System.out.println(uid);
		userService.deleteUser(uid);
	}

}
