import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConDB {
	
	public static String dbName = "Hospital_Appointment";
	public static String user = "root";
	public static String pass = "";
	public static String hostName = "localhost";
	public static String dbDriver = "com.mysql.jdbc.Driver";
	
	public Connection conAndCheckDB() {
		try {
			
			Class.forName(dbDriver);
			String url = "jdbc:mysql://" + hostName + "/" + dbName;
			Connection connect = DriverManager.getConnection(url, user, pass);
			
			if (connect != null) {
				JOptionPane.showMessageDialog(null,"Connected Database");
			}
			
			return connect;
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Not Connected Database");
		}
		
		return null;
	}
	
	public Connection onlyConDB() {
		 try {
			 
			 Class.forName(dbDriver);
			 String url = "jdbc:mysql://" + hostName + "/" + dbName;
			 Connection connect = DriverManager.getConnection(url, user, pass);
			 
			 return connect;
	   
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 return null;
	}
}