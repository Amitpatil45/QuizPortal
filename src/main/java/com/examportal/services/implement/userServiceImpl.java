package com.examportal.services.implement;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.model.User;
import com.examportal.model.userRole;
import com.examportal.model.repo.roleRepository;
import com.examportal.model.repo.userRepository;
import com.examportal.services.UserService;

@Service
public class userServiceImpl implements UserService {

	// @Autowired
	// private com.examportal.repo.userRepository userRepository;

	@Autowired
	private userRepository userRepository;

	@Autowired
	private roleRepository roleRepository;

	@Override
	public User createUser(User user, Set<userRole> userRoles) throws Exception {

		User local = userRepository.findByuserName(user.getUserName());

		if (local != null) {
			System.out.println("User already present");
			throw new Exception("User already present");
		} else {
			for (userRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getUser(String uname) {
		return userRepository.findByuserName(uname);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

}
