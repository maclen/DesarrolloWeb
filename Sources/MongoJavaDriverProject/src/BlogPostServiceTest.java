import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


public class BlogPostServiceTest {

	//Clase bajo prueba
	private BlogPostService postService;
	
	//Objetos de negocio
	private Post post; 
	private Comment comment;
	private Date currentDate = new Date();
	
	//daos
	private IPostDao postsDao;
	private ICommentDao commentDao;
	
	@Before
	public void setup(){
		post = new Post();
		post.setId("postid");
		post.setOwner("emakeda@gmail.com");
		post.setPostedDate(currentDate);
		post.setBody("Ground breaking point of view about something");
	
		comment = new Comment();
		comment.setId("commentid");
		
		//mocks
		postsDao = Mockito.mock(IPostDao.class);
		
		commentDao = Mockito.mock(ICommentDao.class);
		Mockito.when(commentDao.existe(Mockito.anyString())).thenReturn(false);
		Mockito.when(commentDao.existe(Mockito.eq("commentid"))).thenReturn(true);
		
		postService = new BlogPostService(postsDao, commentDao);
		
		postService.setPostDao(postsDao);
		postService.setCommentDao(commentDao);
		
		
	}
	
	@Test
	public void addPostTest() throws Exception {
		postService.addPost(post);
		
		ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class); 
		Mockito.verify(postsDao, Mockito.times(1)).insertPost(postCaptor.capture());
		Post capturedPost = postCaptor.getValue();
		assertEquals("postid", capturedPost.getId());
	}
	
	@Test
	public void addCommentToPostTest() throws Exception {
		postService.addCommentToPost("postid", comment);
		
		Mockito.verify(commentDao, Mockito.times(1)).insertarComentario(comment);
		Mockito.verify(commentDao, Mockito.times(1)).existe(Mockito.anyString());
		Mockito.verify(postsDao, Mockito.times(1)).updatePostComments(Mockito.anyString(), Mockito.anyString());
	}

	@Test(expected = Exception.class)
	public void addCommentToPostTest_commentShouldNotExist() throws Exception {
		comment.setId("any");
		postService.addCommentToPost("postid", comment);
		fail("Debio de haber lanzado una excepcion");
	}

}
