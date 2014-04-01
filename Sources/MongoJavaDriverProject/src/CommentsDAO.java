import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;


public class CommentsDAO {
	
	private MongoConnection mongoConnection = new MongoConnection("blogDB");
	
	public Comment buscarComentarioPorId(String id){
		DBCollection commentsCollection = mongoConnection.getCollection("comments");
		BasicDBObject commentToFind = new BasicDBObject("_id", id);
		BasicDBObject commentFound = (BasicDBObject) commentsCollection.findOne(commentToFind);
		
		Comment comment = new Comment();
		comment.setId(commentFound.getString("_id"));
		comment.setAuthor(commentFound.getString("author"));
//		comment.setDate(commentFound.getString("date"));
//		comment.setBody(commentFound.getString("body"));
		
		return comment;
	}
	
	public void insertarComentario(Comment comment){
		
	}
	
	public void borrarComentario(String id){
		
	}
	
	//Solo debe actualizar el body del comentario
	public void editarComentario(String id, String body){
		
	}

	
}
