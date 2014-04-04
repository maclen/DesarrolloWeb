import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;


public class CommentDaoTest {

	//mongo
	private MongoConnection mongo = new MongoConnection("blogDB");
	private DBCollection commentsCollection;
	
	//Clase a probar
	private ICommentDao commentsDao = new MongoCommentDao();
	
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
	public void borrarComentario(){
		commentsDao.borrarComentario("commenttest");
		assertNull("se encontro el comentario ;D", 
				commentsCollection.findOne(new BasicDBObject("_id", "commenttest")));
	}
	
	@Test
	public void insertarComentario() {
		Comment toInsert = new Comment();
		toInsert.setId("commenttest");
		toInsert.setAuthor("someting xD");
		toInsert.setDate(new Date());
		toInsert.setBody("Holo");
		
		commentsCollection.remove(new BasicDBObject("_id","commenttest"));
		commentsDao.insertarComentario(toInsert);
		
		DBObject found = commentsCollection.findOne(new BasicDBObject("_id","commenttest"));
		
		assertNotNull("El comentario debe existir",found);
		assertEquals("El comentario debe ser el correcto y el id debe ser igual a commenttest", "commenttest", found.get("_id"));
		assertEquals("El autor del coment debe ser someting xD", "someting xD", found.get("author"));
	}
	
	@Test
	public void editarComentario() {
		commentsDao.editarComentario(null,null);
	}

}
