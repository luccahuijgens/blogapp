package blog.restservices;

import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import blog.domain.ServiceProvider;
import blog.domain.User;
import blog.domain.UserService;

@Path("users")
public class UserRestService {
private UserService service=ServiceProvider.getUserService();

@GET
public String getUsers() {
	JsonArrayBuilder jab=Json.createArrayBuilder();
	for (User u:service.getUsers()) {
		jab.add(createJson(u));
	}
	return jab.build().toString();
}

private JsonObjectBuilder createJson(User u) {
SimpleDateFormat format=new SimpleDateFormat("d MMMM, yyyy");
	JsonObjectBuilder job=Json.createObjectBuilder();
	job.add("id", u.getId());
	job.add("username", u.getUsername());
	job.add("registrationdate",format.format(u.getRegistrationdate()));
	job.add("description", u.getDescription());
	return job;
}
}
