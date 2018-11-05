package blog.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import blog.persistence.BlogDAO;

public class BlogService {
private BlogDAO blogDAO;

public BlogService() {
	super();
	this.blogDAO = new BlogDAO();
}

public ArrayList<BlogPage> getAllBlogs() {
	return sort(blogDAO.getAllBlogs());
}
public BlogPage getBlogById(int id) {
	for (BlogPage blog:blogDAO.getAllBlogs()) {
		if (id==blog.getId()) {
			return blog;
		}
	}
	return null;
}
public ArrayList<BlogPage>getBlogsByCategory(int category) {
	return sort(blogDAO.getBlogsByCategory(category));
}
public ArrayList<BlogCategory> getCategories(){
	return blogDAO.getAllCategories();
}
public BlogCategory getCategoryByID(int id){
	return blogDAO.getCategoryByID(id);
}
public ArrayList<BlogCategory> getCategoriesByBlog(int id){
	return blogDAO.getCategoriesByBlog(id);
}
public boolean createBlog(User user, String title, ArrayList<Integer>categoryids,String content) {
	ArrayList<BlogCategory>categories=new ArrayList<BlogCategory>();
	for (int i:categoryids) {
		categories.add(blogDAO.getCategoryByID(i));
	}
	return blogDAO.createBlog(user,title, categories, content);
}

public boolean editBlog(BlogPage editedBlog,ArrayList<Integer>categoryids) {
	ArrayList<BlogCategory>categories=new ArrayList<BlogCategory>();
	for (int i:categoryids) {
		categories.add(blogDAO.getCategoryByID(i));
	}
	return blogDAO.editBlog(editedBlog,categories);
}
public boolean deleteBlog(int id) {
	return blogDAO.deleteBlog(id);
}
private ArrayList<BlogPage> sort(ArrayList<BlogPage>list){
	Collections.sort(list, new Comparator<BlogPage>() {
    public int compare(BlogPage o1, BlogPage o2) {
        return o1.getPublicationdate().compareTo(o2.getPublicationdate());
    }
});
	Collections.reverse(list);
    return list;
}
}
