import java.sql.Connection;

public class MyApp {
	
	public static void main(String[] args) {
		ConDB db = new ConDB();
		Connection con = db.conAndCheckDB();
		
		Account acc = new Account();
		acc.setVisible(true);
	}
}