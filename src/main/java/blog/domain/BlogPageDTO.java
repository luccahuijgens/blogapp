package blog.domain;

import java.util.ArrayList;
import java.util.Date;

public class BlogPageDTO {
	private int id;
	private String title;
	private String header;
	private int author;
	private Date publicationdate;
	private Date editeddate;
	private ArrayList<Integer>categories;
	private String content;
	
	public BlogPageDTO(int id, String title, String header,int author, Date publicationdate, Date editeddate,
			ArrayList<Integer> categories, String content) {
		super();
		this.id = id;
		this.title = title;
		this.header = header;
		this.author = author;
		this.publicationdate = publicationdate;
		this.editeddate = editeddate;
		this.categories = categories;
		this.content = content;
	}
	
	public BlogPageDTO(String title, String header,int author, Date publicationdate,
			ArrayList<Integer> categories, String content) {
		super();
		this.id = 0;
		this.title = title;
		this.header=header;
		this.author = author;
		this.publicationdate = publicationdate;
		this.editeddate = null;
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

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Date getPublicationdate() {
		return publicationdate;
	}

	public void setPublicationdate(Date publicationdate) {
		this.publicationdate = publicationdate;
	}

	public Date getEditeddate() {
		return editeddate;
	}

	public void setEditeddate(Date editeddate) {
		this.editeddate = editeddate;
	}

	public ArrayList<Integer> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Integer> categories) {
		this.categories = categories;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
