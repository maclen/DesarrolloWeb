import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;


public class MongoPostDaoTest {

	//mongo
	MongoConnection mongo = new MongoConnection("blogDB");
	DBCollection postsCollection = mongo.getCollection("posts");
	
	//clase a probar
	MongoPostDao postDao = new MongoPostDao();
	
	//bo
	private Post post;
	private Date date = new Date();
	
	@Before
	public void setup(){
		//instanciamos un objeto Post para probar los metodos que lo utilizen como parametro 
		post = new Post();
		post.setId("postid");
		post.setOwner("emakeda@gmail.com");
		post.setPostedDate(date);
		post.setBody("post body");
		List<DBRef> comments = new ArrayList<DBRef>();
		DBRef commentRef = new DBRef(mongo.getDb(), "comments", "commentid");
		comments.add(commentRef);
		post.setComments(comments);
		
		//insertamos un post para probar los metodos que necesiten que exista un post previamente
		BasicDBObject postToInsert = new BasicDBObject();
		postToInsert.append("_id", post.getId());
		postToInsert.append("owner", post.getOwner());
		postToInsert.append("postedDate", date);
		postToInsert.append("body", post.getBody());
		postToInsert.append("comments", post.getComments());
		postsCollection.insert(postToInsert);
	}
	
	@After
	public void teardown(){
		postsCollection.remove(new BasicDBObject("_id","postid"));
	}
	
	@Test
	public void insertPostTest() throws UnknownHostException {
		//removemos el post previamente insertado para probar nuestro metodo insert
		postsCollection.remove(new BasicDBObject("_id","postid"));
		
		postDao.insertPost(post);
		DBObject found = postsCollection.findOne(new BasicDBObject("_id","postid")); 
		assertNotNull("El post debe de existir",found);
		assertEquals("Los valores deben conincidir", post.getId(), found.get("_id"));
		assertEquals("Los valores deben conincidir", post.getOwner(), found.get("owner"));
		assertEquals("Los valores deben conincidir", post.getBody(), found.get("body"));
		assertNotNull("La fecha no debe de ser nula", found.get("postedDate"));
		
	}

	@Test
	public void deletePostByIdTest() throws UnknownHostException {
		postDao.deletePostById("postid");
		assertNull("El post no debe de existir",
				postsCollection.findOne(new BasicDBObject("_id","postid")));
	}
	
	@Test
	public void findPostByIdTest() throws UnknownHostException {
		Post found = postDao.findPostById("postid");
		assertNotNull("El post debe ser encontrado", found);
	}
	
	@Test
	public void updatePostComments() {

	}
}
