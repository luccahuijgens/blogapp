package blog.persistence;

import java.util.ArrayList;

import blog.domain.BlogPage;
import blog.domain.User;

public class BlogDAO extends BaseDAO {

	public BlogDAO() {
	}

	public ArrayList<BlogPage> getAllBlogs() {
		ArrayList<BlogPage> list = new ArrayList<BlogPage>();

		return list;
	}

	public ArrayList<BlogPage> getBlogsByCategory(String category) {
		ArrayList<BlogPage> list = new ArrayList<BlogPage>();

		return list;
	}

	public boolean createBlog(User user, String content) {

	}

	public boolean editBlog(int id, BlogPage blog) {

	}

	public boolean deleteBlog(int id) {

	}
}
