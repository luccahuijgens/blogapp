package blog.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import blog.domain.User;

public class UserDAO extends BaseDAO{

	public UserDAO() {}
	
	public ArrayList<User> getUsers() {
		ArrayList<User>results=new ArrayList<User>();
		User user=null;
		try(Connection connection=super.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM USERS");
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				results.add(Database2ObjectMapper.convertUser(rs));
			}
			return results;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User getUserByID(int id) {
		User user=null;
		try(Connection connection=super.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM USERS WHERE ID=?");
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				user=Database2ObjectMapper.convertUser(rs);
			}
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
