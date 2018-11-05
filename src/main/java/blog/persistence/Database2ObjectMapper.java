package blog.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import blog.domain.BlogCategory;
import blog.domain.BlogPage;
import blog.domain.User;

public class Database2ObjectMapper {

	public static BlogPage convertBlog(ResultSet rs) {
		try {
		int id = rs.getInt("id");
		String title = rs.getString("title");
		Date publicationdate = rs.getDate("publicationdate");
		Date editeddate = rs.getDate("editeddate");
		String content = rs.getString("content");
		return new BlogPage(id,title,publicationdate,editeddate,content);
	}catch(SQLException e) {
		e.printStackTrace();
		return null;
	}
}

	public static BlogCategory convertCategory(ResultSet rs) {
		try {
		int id=rs.getInt("id");
		String name=rs.getString("name");
		return new BlogCategory(id,name);
	}catch(SQLException e) {
		e.printStackTrace();
		return null;
	}
}

	public static User convertUser(ResultSet rs) {
		try {
		int id=rs.getInt("id");
		String username=rs.getString("username");
		String password=rs.getString("password");
		String role=rs.getString("role");
		Date registration=rs.getDate("registrationdate");
		return new User(id,username,password,role,registration);
	}catch(SQLException e) {
		e.printStackTrace();
		return null;
	}
}
}
