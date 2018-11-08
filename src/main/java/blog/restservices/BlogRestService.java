package blog.restservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
private Random randomGenerator=new Random();

@GET
@Produces("application/json")
public String getAllBlogs(@QueryParam("amount") int amount) {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	if (amount != 0) {
		 ArrayList<BlogPage> al = new ArrayList<BlogPage>(service.getAllBlogs().subList(0, amount));
		 for (BlogPage blog: al) {
			 jab.add(createJson(blog));
		 }
	}else {
	for(BlogPage blog: service.getAllBlogs()) {
		jab.add(createJson(blog));
	}}
	return jab.build().toString();
}

@Path("random")
@GET
@Produces("application/json")
public String pages(@QueryParam("amount") int amount) {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	if (amount != 0) {
		for(BlogPage blog: getRandomBlogs(amount)) {
			jab.add(createJson(blog));
		}
	}else {
	for(BlogPage blog: service.getAllBlogs()) {
		jab.add(createJson(blog));
	}}
	return jab.build().toString();
}


@GET
@Path("/{id}")
@Produces("application/json")
public String getBlogByID(@PathParam("id") int id) {
	JsonObjectBuilder job=createJson(service.getBlogById(id));
	return job.build().toString();
}

@GET
@Path("category/{id}")
@Produces("application/json")
public String getBlogByCategory(@PathParam("id") int id) {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	for(BlogPage blog: service.getBlogsByCategory(id)) {
		jab.add(createJson(blog));
	}
	return jab.build().toString();
}

@PUT
public Response createBlog(BlogPageDTO blog) {
	try {
	User author=userservice.getUserById(blog.getAuthor());
	service.createBlog(author, blog.getTitle(),blog.getHeader(), blog.getCategories(), blog.getContent());
	return Response.status(200).build();
	}catch(Exception e) {
		e.printStackTrace();
		return Response.status(404).build();
	}
}

@POST
@Path("/{id}")
public Response updateBlog(@PathParam("id") int id,@QueryParam("title") String title,@QueryParam("header") String header,@QueryParam("content") String content,@QueryParam("categories") String categories) {
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

private ArrayList<BlogPage> getRandomBlogs(int amount) {
	ArrayList<BlogPage>list=service.getAllBlogs();
	ArrayList<BlogPage>result=new ArrayList<BlogPage>();
	ArrayList<Integer>used=new ArrayList<Integer>();
	int counter=0;
	int index=0;
		while(amount>counter) {
			index=randomGenerator.nextInt(list.size());
			while(containsCheck(used,index)==true) {
				index=randomGenerator.nextInt(list.size());
			}
			System.out.println(index);
			result.add(list.get(index));
			used.add(index);
			counter++;
		}
	return result;
}

private boolean containsCheck(ArrayList<Integer>list,int target) {
	for (int i: list) {
		if (i==target) {
			return true;
		}
	}return false;
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
	job.add("title", nullCheck(blog.getTitle()));
	job.add("header", nullCheck(blog.getHeader()));
	user.add("id", blog.getAuthor().getId());
	user.add("name", blog.getAuthor().getUsername());
	job.add("author", user);
	job.add("publicationdate", format.format(blog.getPublicationdate()));
	job.add("editeddate", format.format(blog.getEditeddate()));
	job.add("categories", categories);
	job.add("content", nullCheck(blog.getContent()));
}catch(Exception e) {
	e.printStackTrace();
	return null;
}return job;
}

private String nullCheck(String s) {
	if (s==null){
		return "";
	}return s;
}
}
