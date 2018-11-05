package blog.domain;

public class ServiceProvider {
	private static BlogService service = new BlogService();
	private static UserService userservice=new UserService();
	
	public ServiceProvider() {

	}

	public static BlogService getService() {
		return service;
	}
	
	public static UserService getUserService() {
	return userservice;
}
}