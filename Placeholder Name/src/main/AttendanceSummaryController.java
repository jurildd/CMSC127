import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AttendanceSummaryController implements Initializable{
	
	Connection conn;
	
	@FXML
	TableView<Table> table = new TableView<>();
	
	@FXML
	TableColumn<Table, String> student_name;
	
	@FXML
	TableColumn<Table, String> student_id;
	
	@FXML
	TableColumn<Table, String> program;
	
	@FXML
	TableColumn<Table, String> time;
	
	@FXML
	final ObservableList<Table> data = FXCollections.observableArrayList();
	
	@FXML
	private Label subject_id;
	
	@FXML
	private Label subject_name;
	
	@FXML
	private Label prof_id;
	
	@FXML
	private Label prof_name;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
				String query = "SELECT Student.Name AS student_name, Student.StudentID AS student_id, Student.Program AS student_program, Checker.Time AS checker_time from Checker JOIN Student ON Checker.StudentID = Student.StudentID";
				String query2 = "SELECT DISTINCT Subject.SubjectID AS subj_id, Subject.Name AS subj_name, Professor.ProfessorID AS prof_id, Professor.Name AS prof_name FROM Class JOIN Subject ON Class.SubjectID = Subject.SubjectID JOIN Professor ON Subject.ProfessorID = Professor.ProfessorID";
				
				conn = Connector.getConn();
				Statement stmt = null;
				ResultSet rs = null;
				
				try{
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query);
					
					while (rs.next()) {
		                System.out.println(rs.getString("student_name") +  "\t" + 
		                                   rs.getInt("student_id") + "\t" +
		                                   rs.getString("student_program") + "\t" +
		                                   rs.getString("checker_time"));
		                
		                data.add(new Table(rs.getString("student_name"), 
		                        String.valueOf(rs.getInt("student_id")),
		                        rs.getString("student_program"),
		                        rs.getString("checker_time"))
		                		);
		            }
					table.setItems(data);
				}catch(SQLException e){
					System.out.print(e.getMessage());
				}
				
				try{
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query2);
					
					while (rs.next()) {
		                System.out.println(rs.getInt("subj_id") +  "\t" + 
		                                   rs.getString("subj_name") + "\t" +
		                                   rs.getInt("prof_id") + "\t" +
		                                   rs.getString("prof_name"));
		                
		                subject_id.setText(String.valueOf(rs.getInt("subj_id")));
		                subject_name.setText(rs.getString("subj_name"));
		                prof_id.setText(String.valueOf(rs.getInt("prof_id")));
		                prof_name.setText(rs.getString("prof_name"));
//		                data.add(new Table(rs.getString("student_name"), 
//		                        String.valueOf(rs.getInt("student_id")),
//		                        rs.getString("student_program"),
//		                        rs.getString("checker_time"))
//		                		);
		            }
				}catch(SQLException e){
					System.out.print(e.getMessage());
				}
				student_name.setCellValueFactory(new PropertyValueFactory<Table, String>("StudentName"));
				student_id.setCellValueFactory(new PropertyValueFactory<Table, String>("StudentID"));
				program.setCellValueFactory(new PropertyValueFactory<Table, String>("Program"));
				time.setCellValueFactory(new PropertyValueFactory<Table, String>("LogIn"));
	}

	@FXML
	private void handleButtonAction2(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/MainHomePage.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	}

}
