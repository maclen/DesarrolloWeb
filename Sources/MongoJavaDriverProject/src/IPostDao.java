import java.net.UnknownHostException;

import com.mongodb.DB;

public interface IPostDao {

	public abstract DB getDB() throws UnknownHostException;

	public abstract void insertPost(Post post) throws UnknownHostException;

	public abstract void deletePostById(String postId)
			throws UnknownHostException;

	public abstract Post findPostById(String postId) throws UnknownHostException;

	//incompleto
	public abstract void updatePostComments(String postId, String commentId)
			throws UnknownHostException;

}