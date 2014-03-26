import java.util.Date;
import java.util.List;

import com.mongodb.DBRef;

public class Post {

	 private String id;
	 private String body;
	 private String owner;
	 private Date postedDate;
	 private List<DBRef> comments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public List<DBRef> getComments() {
		return comments;
	}
	public void setComments(List<DBRef> comments) {
		this.comments = comments;
	}
	 
	 
	
}
