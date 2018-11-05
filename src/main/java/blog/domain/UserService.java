package blog.domain;

import java.util.ArrayList;

import blog.persistence.UserDAO;

public class UserService {
	private UserDAO userDAO;
	
	public UserService() {
		userDAO=new UserDAO();
	}
	
	public ArrayList<User>getUsers() {
		return userDAO.getUsers();
	}
	
	public User getUserById(int id) {
		return userDAO.getUserByID(id);
	}
}
