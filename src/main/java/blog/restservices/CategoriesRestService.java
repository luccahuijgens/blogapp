package blog.restservices;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import blog.domain.BlogCategory;
import blog.domain.BlogService;
import blog.domain.ServiceProvider;

@Path("categories")
public class CategoriesRestService {
private BlogService service=ServiceProvider.getService();

@GET
public String getCategories() {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	for (BlogCategory cat: service.getCategories()) {
		jab.add(createJson(cat));
	}
	return jab.build().toString();
}

@GET
@Path("{id}")
public String getCategoryById(@PathParam("id") int id) {
	JsonObjectBuilder job=createJson(service.getCategoryByID(id));
	return job.build().toString();
}

@GET
@Path("blog/{id}")
public String getCategories(@PathParam("id") int id) {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	for (BlogCategory cat: service.getCategoriesByBlog(id)) {
		jab.add(createJson(cat));
	}
	return jab.build().toString();
}

private JsonObjectBuilder createJson(BlogCategory cat){
	JsonObjectBuilder job=Json.createObjectBuilder();
	job.add("id", cat.getId());
	job.add("name", cat.getName());
	return job;
}
}
