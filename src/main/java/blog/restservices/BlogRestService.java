package blog.restservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import blog.domain.BlogCategory;
import blog.domain.BlogPage;
import blog.domain.BlogPageDTO;
import blog.domain.BlogService;
import blog.domain.ServiceProvider;
import blog.domain.User;
import blog.domain.UserService;

@Path("blogs")
public class BlogRestService {
private BlogService service=ServiceProvider.getService();
private UserService userservice=ServiceProvider.getUserService();

@GET
@Produces("application/json")
public String getAllBlogs() {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	for(BlogPage blog: service.getAllBlogs()) {
		jab.add(createJson(blog));
	}
	return jab.build().toString();
}

@GET
@Path("/{id}")
@Produces("application/json")
public String getBlogByID(@PathParam("id") int id) {
	BlogPage blog=service.getBlogById(id);
	return createJson(blog).build().toString();
}

@PUT
public Response createBlog(BlogPageDTO blog) {
	try {
	User author=userservice.getUserById(blog.getAuthor());
	service.createBlog(author, blog.getTitle(), blog.getCategories(), blog.getContent());
	return Response.status(200).build();
	}catch(Exception e) {
		e.printStackTrace();
		return Response.status(404).build();
	}
}

@POST
@Path("/{id}")
public Response updateBlog(@PathParam("id") int id,@QueryParam("title") String title,@QueryParam("content") String content,@QueryParam("categories") String categories) {
	ArrayList<Integer>categorylist=new ArrayList<Integer>();
	BlogPage blog=service.getBlogById(id);
	blog.setTitle(title);
	blog.getContent();
	blog.setEditeddate(new Date());
	if (!categories.equals("")) {
	String[]ints=categories.split(",");
	for (String s:ints) {
		categorylist.add(Integer.parseInt(s));
	}}else {
		for (BlogCategory cat:service.getCategoriesByBlog(id)) {
			categorylist.add(cat.getId());
		}
	}
	service.editBlog(blog, categorylist);
	return Response.status(200).build();
}

private JsonObjectBuilder createJson(BlogPage blog) {
	SimpleDateFormat format=new SimpleDateFormat("d MMMM, yyyy");
	JsonObjectBuilder job=Json.createObjectBuilder();
	JsonObjectBuilder user=Json.createObjectBuilder();
	JsonArrayBuilder categories=Json.createArrayBuilder();
	for (BlogCategory cat:blog.getCategories()) {
		JsonObjectBuilder category=Json.createObjectBuilder();
		category.add("id",cat.getId());
		category.add("name", cat.getName());
		categories.add(category);
	}
	try {
	job.add("id", blog.getId());
	job.add("title", blog.getTitle());
	user.add("id", blog.getAuthor().getId());
	user.add("name", blog.getAuthor().getUsername());
	job.add("author", user);
	job.add("publicationdate", format.format(blog.getPublicationdate()));
	job.add("editeddate", format.format(blog.getEditeddate()));
	job.add("categories", categories);
	job.add("content", blog.getContent());
}catch(Exception e) {
	e.printStackTrace();
	return null;
}return job;
}
}
