public interface ICommentDao {

	public abstract Comment buscarComentarioPorId(String id);

	public abstract void insertarComentario(Comment comment);

	public abstract void borrarComentario(String id);

	//Solo debe actualizar el body del comentario
	public abstract void editarComentario(String id, String body);
	
	public boolean existe(String commentId);
	
	

}