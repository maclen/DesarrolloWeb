import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class MongoConnection {

	private DB db;
	
	MongoConnection(String dbname) {
		MongoClient client;
		try {
			client = new MongoClient();
			this.db = client.getDB(dbname);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DBCollection getCollection(String name){
		return db.getCollection(name);
	}

	public DB getDb() {
		return db;
	}
	
	
	
}
