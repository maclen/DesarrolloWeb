import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CommentsDAOTest {

	//Clase a probar
	private CommentsDAO commentsDao = new CommentsDAO();
	
	
	@Before
	public void setup(){
		
	}
	
	@After
	public void teardown(){
		
	}
	
	@Test
	public void buscarComentarioPorId() {
		commentsDao.buscarComentarioPorId(null);
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
