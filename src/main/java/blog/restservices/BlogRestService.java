package blog.restservices;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import blog.domain.BlogPage;
import blog.domain.BlogService;
import blog.domain.ServiceProvider;

@Path("/api/blogs")
public class BlogRestService {
private BlogService service=ServiceProvider.getService();

public String getAllBlogs() {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	for(BlogPage blog: service.getAllBlogs()) {
		jab.add(createJson(blog));
	}
	return jab.build().toString();
}

@Path("/{id}")
public String getBlogByID(@PathParam("id") int id) {
	BlogPage blog=service.getBlogById(id);
	return createJson(blog).build().toString();
}
private JsonObjectBuilder createJson(BlogPage blog) {
	JsonObjectBuilder job=Json.createObjectBuilder();
	job.add("id", blog.getId());
	
	return job;
}
}
