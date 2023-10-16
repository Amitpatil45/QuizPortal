package com.examportal.services;

import java.util.Set;
import com.examportal.model.User;
import com.examportal.model.userRole;

public interface UserService {
    public User createUser(User user, Set<userRole> userRoles) throws Exception;
    public User getUser(String uname);
    public void deleteUser(Long userId);
}
