import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;


public class MongoCommentDao implements ICommentDao {
	
	private MongoConnection mongoConnection = new MongoConnection("blogDB");
	
	/* (non-Javadoc)
	 * @see ICommentsDao#buscarComentarioPorId(java.lang.String)
	 */
	@Override
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
	
	public boolean existe(String commentId){
		DBCollection commentsCollection = mongoConnection.getCollection("comments");
		
		return commentsCollection.count(new BasicDBObject("_id",commentId)) > 0;
	}
	
	/* (non-Javadoc)
	 * @see ICommentsDao#insertarComentario(Comment)
	 */
	@Override
	public void insertarComentario(Comment comment){
		DBCollection coleccion = mongoConnection.getCollection("comments");
		BasicDBObject toInsert = new BasicDBObject("_id",comment.getId());
		toInsert.append("author", comment.getAuthor());
		coleccion.insert(toInsert);
	}
	
	/* (non-Javadoc)
	 * @see ICommentsDao#borrarComentario(java.lang.String)
	 */
	@Override
	public void borrarComentario(String id){
		DBCollection coleccion = mongoConnection.getCollection("comments");
		coleccion.remove(new BasicDBObject("_id", id));
	}
	
	//Solo debe actualizar el body del comentario
	/* (non-Javadoc)
	 * @see ICommentsDao#editarComentario(java.lang.String, java.lang.String)
	 */
	@Override
	public void editarComentario(String id, String body){
		
	}

	
}
