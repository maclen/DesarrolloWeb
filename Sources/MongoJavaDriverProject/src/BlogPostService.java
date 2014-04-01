import java.net.UnknownHostException;
import java.util.Date;


public class BlogPostService {

	private IBlogPostDAO postDao;
	
	public void addPost(Post post) throws UnknownHostException{
		
		if(post.getBody() == null ||
				post.getBody().trim().equals("")){
			
		}
		
		post.setPostedDate(new Date());
		postDao = new BlogPostDAO();
		postDao.insertPost(post);
		
	}
	
}
