import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnrollController implements Initializable {
	
	Connection conn = Connector.getConn();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	TextField id_box;
	
	@FXML
	TextField name_box;
	
	@FXML
	TextField program_box;
	
	@FXML
	Label valid_label;
	
	@FXML
	Label invalid_label;
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException, InterruptedException {
		if(id_box.getText().equals("") || name_box.getText().equals("") || program_box.getText().equals("")){
			invalid_label.setText("Please fill up all of the fields.");
		}
		else{
			insert(Integer.valueOf(id_box.getText()), name_box.getText(), program_box.getText());
			valid_label.setText("Account successfully enrolled.");
			id_box.clear();
			name_box.clear();
			program_box.clear();
		}
	}
	
	@FXML
	private void handleBack(ActionEvent event) throws IOException{
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/MainHomePage.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	}
	
	public void insert(int studentID, String name, String program) {
        String sql = "INSERT INTO Student(StudentID, Name, Email, Program) VALUES(?,?,null,?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            pstmt.setString(2, name);
            pstmt.setString(3, program);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
}
