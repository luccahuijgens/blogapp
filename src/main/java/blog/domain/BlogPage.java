package blog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale.Category;

public class BlogPage {
private int id;
private String title;
private User author;
private Date pbulicationdate;
private Date editeddate;
private ArrayList<BlogCategory>categories;
private String content;

public BlogPage(int id, String title, User author, Date pbulicationdate, Date editeddate,
		ArrayList<BlogCategory> categories, String content) {
	super();
	this.id = id;
	this.title = title;
	this.author = author;
	this.pbulicationdate = pbulicationdate;
	this.editeddate = editeddate;
	this.categories = categories;
	this.content = content;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public User getAuthor() {
	return author;
}

public void setAuthor(User author) {
	this.author = author;
}

public Date getPbulicationdate() {
	return pbulicationdate;
}

public void setPbulicationdate(Date pbulicationdate) {
	this.pbulicationdate = pbulicationdate;
}

public Date getEditeddate() {
	return editeddate;
}

public void setEditeddate(Date editeddate) {
	this.editeddate = editeddate;
}

public ArrayList<BlogCategory> getCategories() {
	return categories;
}

public void setCategories(ArrayList<BlogCategory> categories) {
	this.categories = categories;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}


}
