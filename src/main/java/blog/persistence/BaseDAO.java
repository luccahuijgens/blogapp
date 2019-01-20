package blog.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
private final String url="jdbc:mysql://localhost:3306/blog";
private final String user="lhuijgens";
private final String password="lhuijgens";

	public BaseDAO() {}
	
	protected Connection getConnection() {
		Connection con=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
