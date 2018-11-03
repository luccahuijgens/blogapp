package blog.domain;

import java.util.ArrayList;

import blog.persistence.BlogDAO;
import blog.persistence.UserDAO;

public class BlogService {
private BlogDAO blogDAO;
private UserDAO userDAO;

public BlogService() {
	super();
	this.blogDAO = new BlogDAO();
	this.userDAO = new UserDAO();
}

public ArrayList<BlogPage> getAllBlogs() {
	return blogDAO.getAllBlogs();
}
public BlogPage getBlogById(int id) {
	for (BlogPage blog:blogDAO.getAllBlogs()) {
		if (id==blog.getId()) {
			return blog;
		}
	}
	return null;
}
public ArrayList<BlogPage>getBlogsByCategory(String category) {
	return blogDAO.getBlogsByCategory(category);
}
public boolean createBlog(User user, String content) {
	return blogDAO.createBlog(user,content);
}

public boolean editBlog(int id, BlogPage editedBlog) {
	return blogDAO.editBlog(id,editedBlog);
}
public boolean deleteBlog(int id) {
	return blogDAO.deleteBlog(id);
}
}
