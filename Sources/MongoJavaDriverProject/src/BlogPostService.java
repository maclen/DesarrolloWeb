import java.net.UnknownHostException;
import java.util.Date;


public class BlogPostService {

	private IPostDao postDao;
	private ICommentDao commentDao;
	
	 
	BlogPostService(IPostDao postDao, ICommentDao commentDao){
		this.postDao = postDao;
		this.commentDao = commentDao;
	}
	
	public void addPost(Post post) throws Exception{
		
		if(post.getOwner() == null || post.getOwner().trim().equals("")){
			throw new Exception("Owner is a required field");
		}
		
		if(post.getBody() == null || post.getBody().trim().equals("")){
			throw new Exception("Body is a required field");
		}
		
		if(post.getPostedDate() == null){
			post.setPostedDate(new Date());
		}else{
			post.setPostedDate(post.getPostedDate());
		}
		postDao.insertPost(post);
		
	}

	public void addCommentToPost(String postId, Comment comment) throws Exception{
		commentDao.insertarComentario(comment);
		
		if(!commentDao.existe(comment.getId())){
			throw new Exception("El comentario no existe");
		}		
		
		//postDao.updatePostComments(postId, comment.getId());
	}
	
	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}

	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	
	
}
