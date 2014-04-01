import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.DBObject;

public interface IBlogPostDAO {

	public abstract DB getDB() throws UnknownHostException;

	public abstract void insertPost(Post post) throws UnknownHostException;

	public abstract void deletePostById(String postId)
			throws UnknownHostException;

	public abstract DBObject findPostById(ObjectId postId);

	//incompleto
	public abstract void updatePostComments(String postId, String commentId)
			throws UnknownHostException;

}