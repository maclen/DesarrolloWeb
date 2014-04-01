import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;


public class BlogPostDAOTest {

	//Clase a probar
	private IBlogPostDAO postDao = new BlogPostDAO();

	//Business Objects
	private Post post;
	
	//Mongo Stuff
	private MongoClient client;
	private DB blogDB;
	private DBCollection postsCollection;
	private BasicDBObject postDBObject;
	
	
	@Before
	public void setup() throws UnknownHostException{
		
		client = new MongoClient();
		blogDB = client.getDB("blogDB");
		postsCollection = blogDB.getCollection("posts");
		postDBObject = new BasicDBObject("_id","postdaotestid"); 
		
		post = new Post();
		post.setId("postdaotestid");
		post.setBody("Post content");
		post.setOwner("emakeda@gmail.com");
		post.setPostedDate(new Date()); 
		DBRef commentRef = new DBRef(blogDB, "comments","id");
		List<DBRef> commentsList = new ArrayList<DBRef>();
		commentsList.add(commentRef);
		post.setComments(commentsList);
	}
	
	@After
	public void tearDown(){		
		postsCollection.remove(postDBObject);
	}
	
	@Test
	public void insertPostTest() throws UnknownHostException {
		postDao.insertPost(post);
		assertNotNull("The post should exist in the posts collection", 
				postsCollection.findOne(postDBObject));
	}
	
	@Test
	public void deletePostTest() throws UnknownHostException {
		postDao.insertPost(post);
		postDao.deletePostById("postdaotestid");
		assertNull("The post shouldn't exist in the posts collection",
				postsCollection.findOne(postDBObject));
		
	}
	
	@Test
	public void updatePostCommentsTest() 
			throws UnknownHostException {
		//Insertar un post
		postDao.insertPost(post);
		//Llamada al metodo a probar
		postDao.updatePostComments(post.getId(),"commentId");
		//Agregar condiciones para que pase la prueba
		DBObject found = postsCollection.findOne(postDBObject);
		assertNotNull("El campo de comentario debe estar actualizado",
				found.get("comments"));
		assertNotNull("El metodo update solo debe actualizar los comentarios",
				found.get("body"));
		
		BasicDBList comments = 
				(BasicDBList)found.get("comments");
		
		assertTrue("El campo comments debe "
				+ "de tener mas de un valor", comments.size()>1);
	}

}
