import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;


public class CommentsDAOTest {

	//mongo
	private MongoConnection mongo = new MongoConnection("blogDB");
	private DBCollection commentsCollection;
	
	//Clase a probar
	private CommentsDAO commentsDao = new CommentsDAO();
	
	//BO
	private BasicDBObject comment;
	
	@Before
	public void setup(){
		//Mongo
		commentsCollection= mongo.getCollection("comments");
		
		//BO	
		BasicDBObject comment = new BasicDBObject("_id","commenttest");
		comment.append("author", "emakeda@gmail.com");
		
		commentsCollection.insert(comment);
	}
	
	@After
	public void teardown(){
		commentsCollection.remove(new BasicDBObject("_id","commenttest"));
	}
	
	@Test
	public void buscarComentarioPorId() {
		Comment comentario = commentsDao.buscarComentarioPorId("commenttest");
		assertNotNull("comentario no debe de ser nulo",comentario);
		assertEquals("el id debe de ser igual", "commenttest", comentario.getId());
	}
	
	@Test
	public void insertarComentario() {
		commentsDao.insertarComentario(null);
	}
	
	@Test
	public void editarComentario() {
		commentsDao.editarComentario(null,null);
	}

}
