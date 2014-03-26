import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.UnknownHostException;
import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class BlogPostDAOTest {

	//Clase a probar
	private BlogPostDAO postDao = new BlogPostDAO();

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
		post.setComments(null);
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

}
