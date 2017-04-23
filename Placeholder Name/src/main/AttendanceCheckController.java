import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AttendanceCheckController implements Initializable {
	
	Connection conn = Connector.getConn();

	Stage stage;
	
	@FXML
	private Label invalid_label;
	
	@FXML
	private Label invalid_label2;
	
	@FXML
	private PasswordField password_id2;
	
	@FXML
	private Label valid_label;
	
	@FXML
	private TextField student_id;
	
	@FXML
	private Button btn1;
	
	@FXML
	private Button btn2;
	
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		if(student_id.getText().equals("")){
			valid_label.setText("");
			invalid_label.setText("Invalid student ID");
		}
		else{
			if(correct()){
				String temp = StartClassController2.getSubjectID();
				int tmp = StartClassController2.getClassID();
				System.out.print(temp);
				System.out.print(tmp);
				insert(tmp,Integer.valueOf(temp),Integer.valueOf(student_id.getText()));
				invalid_label.setText("");
				valid_label.setText("Successfully logged in.");
				student_id.clear();
			}else{
				valid_label.setText("");
				invalid_label.setText("Student not found in database.");
				student_id.clear();
			}
		}
	}
	
	@FXML
	private void handleButtonAction2(ActionEvent event) throws IOException{
		Parent root;
		stage = new Stage();
		stage.setTitle("Sign-out");
		root = FXMLLoader.load(getClass().getResource("/fxml/PopUpWindow.fxml"));
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(btn1.getScene().getWindow());
		stage.showAndWait();
	}
	
	@FXML
	private void handleButtonAction3(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/AttendanceSummary.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		if(correct2()){
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.hide();
			try{
				Connector.closeConn();
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
			app_stage.hide();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}
		else{
			invalid_label2.setText("Wrong password.");
			password_id2.clear();
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean correct() {
		
		boolean let_in = false;
		
		Statement stmt = null;
		
		try{		
			
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Student WHERE StudentID = " + "'" + student_id.getText() + "'");
			
			while(rs.next()){
				if(rs.getString("StudentID") != null){
					String asd = rs.getString("StudentID");
					System.out.println(asd);
					let_in = true;
				}
			}
			
//			rs = stmt.executeQuery("SELECT * FROM Checker WHERE StudentID = " + "'" + student_id.getText() + "'");
//			while(rs.next()){
//				if(rs.getString("StudentID") != null){
//					String asd = rs.getString("StudentID");
//					System.out.println(asd);
//					let_in = false;
//				}
//			}
			
			rs.close();
			stmt.close();
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return let_in;
	}
	
	private boolean correct2() {
		
		boolean let_in = false;
		
		Statement stmt = null;
		
		try{		
			
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Professor WHERE Password = " + "'" + password_id2.getText() + "'");
			
			while(rs.next()){
				if(rs.getString("Password") != null){
					String asd = rs.getString("Password");
					System.out.println(asd);
					let_in = true;
				}
			}
			
			rs.close();
			stmt.close();
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return let_in;
	}
	
	public void insert(int classID, int subjectID, int studentID) {
        String sql = "INSERT INTO Checker(ClassID, SubjectID, StudentID, Time) VALUES(?,?,?,?)";
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("hh:mm a");
        String time = df.format(cal.getTime());
        System.out.print(time);
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, classID - 1);
            pstmt.setInt(2, subjectID);
            pstmt.setInt(3, studentID);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
            System.out.println("executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
