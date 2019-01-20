package blog.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import blog.domain.BlogCategory;
import blog.domain.BlogPage;
import blog.domain.User;

public class BlogDAO extends BaseDAO{
private UserDAO userDAO=new UserDAO();
	public BlogDAO() {}
	
	public ArrayList<BlogPage> getAllBlogs(){
		ArrayList<BlogPage>results=new ArrayList<BlogPage>();
		BlogPage blog=null;
		try (Connection connection = super.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM BLOGPAGES");
            ResultSet rs=preparedStatement.executeQuery();
            
            while(rs.next()) {
            	blog=Database2ObjectMapper.convertBlog(rs);
            	blog.setAuthor(userDAO.getUserByID(rs.getInt("author")));
            	blog.setCategories(getCategoriesByBlog(rs.getInt("id")));
            	results.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }return results;
	}
	
	public ArrayList<BlogPage> getBlogsByCategory(int id){
			ArrayList<BlogPage>results=new ArrayList<BlogPage>();
			BlogPage blog=null;
			try (Connection connection = super.getConnection()) {
	            PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM BLOGPAGES WHERE ID in (SELECT BLOG_ID FROM BLOG_CATEGORY WHERE CATEGORY_ID=?)");
	            preparedStatement.setInt(1, id);
	            ResultSet rs=preparedStatement.executeQuery();
	            
	            while(rs.next()) {
	            	blog=Database2ObjectMapper.convertBlog(rs);
	            	blog.setAuthor(userDAO.getUserByID(rs.getInt("author")));
	            	blog.setCategories(getCategoriesByBlog(rs.getInt("id")));
	            	results.add(blog);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }return results;
		}
	
	public ArrayList<BlogCategory> getAllCategories(){
		ArrayList<BlogCategory>results=new ArrayList<BlogCategory>();
		BlogCategory category=null;
		try (Connection connection = super.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM CATEGORIES");
            ResultSet rs=preparedStatement.executeQuery();
            
            while(rs.next()) {
            	category=Database2ObjectMapper.convertCategory(rs);
            	results.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }return results;
	}
	
public BlogCategory getCategoryByID(int id){
	BlogCategory category=null;
	try (Connection connection = super.getConnection()) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM CATEGORIES WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet rs=preparedStatement.executeQuery();
        
        while(rs.next()) {
        	category=Database2ObjectMapper.convertCategory(rs);
        	return category;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }return null;
	}

public BlogCategory getCategoryByID(String name){
	BlogCategory category=null;
	try (Connection connection = super.getConnection()) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM CATEGORIES WHERE NAME=?");
        preparedStatement.setString(1, name);
        ResultSet rs=preparedStatement.executeQuery();
        
        while(rs.next()) {
        	category=Database2ObjectMapper.convertCategory(rs);
        	return category;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }return null;
	}

public ArrayList<BlogCategory> getCategoriesByBlog(int id){
		ArrayList<BlogCategory>results=new ArrayList<BlogCategory>();
		BlogCategory category=null;
		try (Connection connection = super.getConnection()) {
	        PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM CATEGORIES WHERE ID IN (SELECT CATEGORY_ID FROM BLOG_CATEGORY WHERE BLOG_ID=?)");
	        preparedStatement.setInt(1, id);
	        ResultSet rs=preparedStatement.executeQuery();
	        
	        while(rs.next()) {
	        	category=Database2ObjectMapper.convertCategory(rs);
	        	results.add(category);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }return results;
		}
	
	public int createBlog(User user,String title,String header,ArrayList<BlogCategory>categories,String content) {
		int blogid=0;
		int userid=user.getId();
		Date pubdate=new Date();
		try (Connection connection = super.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT MAX(ID) as max_id FROM BLOGPAGES");
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
			blogid=rs.getInt("max_id");
			}
			preparedStatement= connection.prepareStatement("INSERT INTO BLOGPAGES(id,title,header,author,publicationdate,editeddate,content) VALUES(?,?,?,?,?,null,?)");
			preparedStatement.setInt(1, blogid);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, header);
			preparedStatement.setInt(4, userid);
			preparedStatement.setDate(5, new java.sql.Date(pubdate.getTime()));
			preparedStatement.setString(6,content);
			preparedStatement.executeUpdate();
			
			for (BlogCategory cat:categories) {	
			preparedStatement=connection.prepareStatement("INSERT INTO BLOG_CATEGORY VALUES(?,?)");
			preparedStatement.setInt(1, blogid);
			preparedStatement.setInt(2, cat.getId());
			preparedStatement.executeUpdate();
			}return blogid;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public boolean editBlog(BlogPage blog, ArrayList<BlogCategory>categories) {
			try (Connection connection = super.getConnection()){
				PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO BLOGPAGES(title,header,editeddate,content) VALUES(?,?,?,?)");
				preparedStatement.setString(1, blog.getTitle());
				preparedStatement.setString(2, blog.getHeader());
				preparedStatement.setDate(3, new java.sql.Date(new Date().getTime()));
				preparedStatement.setString(4, blog.getContent());
				preparedStatement.executeUpdate();
				
				for (BlogCategory cat:categories) {	
				preparedStatement=connection.prepareStatement("DELETE FROM BLOG_CATEGORY WHERE BLOG_ID=?");
				preparedStatement.setInt(1, blog.getId());
				preparedStatement.executeUpdate();
				preparedStatement=connection.prepareStatement("INSERT INTO BLOG_CATEGORY VALUES(?,?)");
				preparedStatement.setInt(1, blog.getId());
				preparedStatement.setInt(2, cat.getId());
				preparedStatement.executeUpdate();
				}return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public boolean deleteBlog(int id) {
		try (Connection connection = super.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM BLOGPAGES WHERE ID=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
