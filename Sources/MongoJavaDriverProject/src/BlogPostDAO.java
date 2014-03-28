import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;


public class BlogPostDAO {

	//CRUD Create, retrieve, update, delete

	public static final String BLOG_DB = "blogDB";
	
	public DB getDB() throws UnknownHostException{
		MongoClient client = new MongoClient();
		return client.getDB(BLOG_DB);
	}
	
	public void insertPost(Post post) throws UnknownHostException{
		DBCollection postCollection = getDB().getCollection("posts");
		BasicDBObject toInsert = new BasicDBObject("_id",post.getId());
		toInsert.append("body", post.getBody());
		toInsert.append("owner", post.getOwner());
		toInsert.append("postedDate", post.getPostedDate());
		toInsert.append("comments", post.getComments());
		postCollection.insert(toInsert);
	}
	
	public void deletePostById(String postId) throws UnknownHostException{
		DBCollection postCollection = getDB().getCollection("posts");
		BasicDBObject toDelete = new BasicDBObject("_id", postId);
		postCollection.remove(toDelete);
	}
	
	public DBObject findPostById(ObjectId postId){
		return null;
	}
	
	//incompleto
	public void updatePostComments (String postId, 
			String commentId) throws UnknownHostException{
		DBCollection postCollection = getDB().getCollection("posts");
		DBRef commentRef = new DBRef(getDB(), "comments", commentId);
		BasicDBObject toUpdate = new BasicDBObject("_id",postId);
		BasicDBObject update = new BasicDBObject("comments",commentRef);
	
		postCollection.update(toUpdate,new BasicDBObject("$push",update));
	}
	
}
