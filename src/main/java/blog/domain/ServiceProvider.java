package blog.domain;

public class ServiceProvider {
	private static BlogService service = new BlogService();

	public ServiceProvider() {

	}

	public static BlogService getService() {
		return service;
	}
}
