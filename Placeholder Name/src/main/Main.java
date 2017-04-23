import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class Main {
     /**
     * Connect to a sample database
     */
//    public static void connect() {
//        Connection conn = null;
//        try {
//            // db parameters
//            String url = "jdbc:sqlite:C:/Users/Juril/sqlite/lol.db";
//            // create a connection to the database
//            conn = DriverManager.getConnection(url);
//            
//            System.out.println("Connection to SQLite has been established.");
//            
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
    
    public void insert(String name, Connection conn) {
        String sql = "INSERT INTO Student(name) VALUES(?)";
 
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
          pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
////    private static Connection connect() {
//        // SQLite connection string
//        String url = "jdbc:sqlite:C://Users/Juril/sqlite/lol.db";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }
    
    public void show(Connection conn){
        String sql = "SELECT * FROM Student";
        
        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(Connection conn) {
        String sql = "DELETE from () VALUES(?)";
 
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

 //       Main app = new Main();
 //       Connection conn = connect();
        // insert three new rows
        
    }
}