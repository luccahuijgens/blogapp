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
	
	public ArrayList<User>getUsersByRole(String role) {
		ArrayList<User>results=new ArrayList<User>();
		for (User u:userDAO.getUsers()) {
			if (u.getRole().equals(role)) {
				results.add(u);
			}
		}
		return results;
	}
	
	public User getUserById(int id) {
		return userDAO.getUserByID(id);
	}
}
