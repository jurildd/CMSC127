import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	
	static Connection conn;
	
	public static Connection getConn(){
		String url = "jdbc:sqlite:C://Users/Juril/sqlite/db_tab1.db";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
	}
	
	public static void closeConn() throws SQLException{
		if(!conn.isClosed()){
			conn.close();
		}
	}
	
}
