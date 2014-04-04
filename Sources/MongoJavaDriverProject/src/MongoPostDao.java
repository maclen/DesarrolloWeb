import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;


public class MongoPostDao implements IPostDao {

	//CRUD Create, retrieve, update, delete

	public static final String BLOG_DB = "blogDB";
	
	/* (non-Javadoc)
	 * @see IBlogPostDAO#getDB()
	 */
	@Override
	public DB getDB() throws UnknownHostException{
		MongoClient client = new MongoClient();
		return client.getDB(BLOG_DB);
	}
	
	/* (non-Javadoc)
	 * @see IBlogPostDAO#insertPost(Post)
	 */
	@Override
	public void insertPost(Post post) throws UnknownHostException{
		DBCollection postCollection = getDB().getCollection("posts");
		BasicDBObject toInsert = new BasicDBObject("_id",post.getId());
		toInsert.append("body", post.getBody());
		toInsert.append("owner", post.getOwner());
		toInsert.append("postedDate", post.getPostedDate());
		toInsert.append("comments", post.getComments());
		postCollection.insert(toInsert);
	}
	
	/* (non-Javadoc)
	 * @see IBlogPostDAO#deletePostById(java.lang.String)
	 */
	@Override
	public void deletePostById(String postId) throws UnknownHostException{
		DBCollection postCollection = getDB().getCollection("posts");
		BasicDBObject toDelete = new BasicDBObject("_id", postId);
		postCollection.remove(toDelete);
	}
	
	/* (non-Javadoc)
	 * @see IBlogPostDAO#findPostById(org.bson.types.ObjectId)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Post findPostById(String postId) throws UnknownHostException{
		DBCollection postsCollection = getDB().getCollection("posts");
		try {
			Post post = new Post();
			postsCollection = getDB().getCollection("posts");
			DBObject found = postsCollection.findOne(new BasicDBObject("_id", postId));
			post.setId((String)found.get("_id"));
			post.setOwner((String)found.get("owner"));
			post.setPostedDate((Date)found.get("postedDate"));
			post.setBody((String)found.get("body"));
			post.setComments((List<DBRef>)found.get("comments"));
			return post;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//incompleto
	/* (non-Javadoc)
	 * @see IBlogPostDAO#updatePostComments(java.lang.String, java.lang.String)
	 */
	@Override
	public void updatePostComments (String postId, 
			String commentId) throws UnknownHostException{
		DBCollection postCollection = getDB().getCollection("posts");
		DBRef commentRef = new DBRef(getDB(), "comments", commentId);
		BasicDBObject toUpdate = new BasicDBObject("_id",postId);
		BasicDBObject update = new BasicDBObject("comments",commentRef);
	
		postCollection.update(toUpdate,new BasicDBObject("$push",update));
	}
	
}
