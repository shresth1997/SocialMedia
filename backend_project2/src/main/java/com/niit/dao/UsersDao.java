package com.niit.dao;

import java.util.List;

import com.niit.model.Users;

public interface UsersDao {

	void registration(Users users);
	List<Users> getAllUsers();
	Users login(Users users);
	Users updateUser(Users validUser);
	Users getUserByUsername (String username);

}
