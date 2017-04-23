import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.sql.*;

//import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import javafx.util.Duration;

public class StartClassController2 implements Initializable {

	Connection conn = Connector.getConn();
	static int increment = 1;
	public static String subjectID;
	
	@FXML
	private Label invalid_label;
	
	@FXML
	private TextField subject_id;
	
	@FXML
	private DatePicker date_id;
	
	static String date = "";
	
	LocalDate datee;
	
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException, InterruptedException {
		datee = date_id.getValue();
		if(datee != null){
			date = date_id.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/AttendanceCheck.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		if(subject_id.getText().equals("") || date.equals("")){
			invalid_label.setText("Please fill up all of the fields.");
		}
		else{
			if(correct()){
				insert(date, Integer.valueOf(subject_id.getText()));
//				try{
//					Connector.closeConn();
//				}catch(SQLException e){
//					System.out.println(e.getMessage());
//				}
				subjectID = subject_id.getText();
				app_stage.hide();
				app_stage.setScene(home_page_scene);
				app_stage.show();
			}else{
				invalid_label.setText("Invalid SubjectID");
			}
		}
	}
	
	@FXML
	private void handleBack(ActionEvent event) throws IOException{
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/StartClass.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean correct() {
		
		boolean let_in = false;
		
		
		System.out.println(subject_id.getText());
		Statement stmt = null;
		
		try{
			conn.setAutoCommit(false);			
			
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Subject WHERE SubjectID = " + "'" + subject_id.getText() + "'");
			
			while(rs.next()){
				if(rs.getString("SubjectID") != null){
					String asd = rs.getString("SubjectID");
					System.out.println(asd);
					let_in = true;
				}
			}			
			
			rs.close();
			stmt.close();
			//conni.close();
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		
		return let_in;
	}

	public void insert(String date, int subjectID) {
	    String sql = "INSERT INTO Class(ClassID, Date, SubjectID) VALUES(?, ?, ?)";
	    
	    PreparedStatement pstmt = null;
	    
	    try{
	    	System.out.println("Database is open");
	    	pstmt = conn.prepareStatement(sql);
	    	
	    	pstmt.setInt(1, increment++);
	    	System.out.println(increment);
	    	pstmt.setString(2, date);
	    	System.out.println(date);
	    	pstmt.setInt(3, subjectID);
	    	System.out.println(subjectID);
	        pstmt.executeUpdate();
	        System.out.println("executed");
	        conn.commit();
	        pstmt.close();
//	        conn.close();
	        
	        if(conn.isClosed()){
	        	System.out.println("database connection closed");
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public static String getSubjectID(){
		return subjectID;
	}
	
	public static int getClassID(){
		return increment;
	}
	
}
