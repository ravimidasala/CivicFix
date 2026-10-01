package com.civicfix.service;

import com.civicfix.dao.UserDAO;
import com.civicfix.model.User;
import com.civicfix.util.PasswordUtil;

public class UserService {

	private final UserDAO userDAO;

	public UserService() {

		userDAO = new UserDAO();

	}

	public boolean registerUser(String name, String email, String password, String phone) {

		if(name==null || name.trim().isEmpty()) {
			return false;
		}
		
		if(email==null || email.trim().isEmpty()) {
			return false;
		}
		
		if(password==null || password.trim().isEmpty()) {
			return false;
		}
		
		if(userDAO.emailExists(email)) {
			return false;
		}
		
		String hashedPssword=PasswordUtil.hashPassword(password);
		
		User user = new User(name.trim(),email.trim(),hashedPssword,phone,"CITIZEN");
		
		return userDAO.registerUser(user);
		
		
		
	}
	
	public User loginUser(String email, String password) {
		
		 if (email == null || email.trim().isEmpty()) {
		        return null;
		    }

		    if (password == null || password.isEmpty()) {
		        return null;
		    }

		    User user = userDAO.findByEmail(email.trim());

		    if (user == null) {
		        return null;
		    }
		    
		    
		    boolean passwordMatches=PasswordUtil.checkPassword(password, user.getPassword());
		
		    
		    if(!passwordMatches) {
		    	return null;
		    }
		    
		    return user;
	}

}
