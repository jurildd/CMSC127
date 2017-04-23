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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartClassController implements Initializable {
	
	Connection conn = Connector.getConn();
	
	@FXML
	private Label invalid_label;
	
	@FXML
	private TextField user_id;
	
	@FXML
	private PasswordField password_id;
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/StartClass2.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		if(correct()){
			app_stage.hide();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}
		else{
			user_id.clear();
			password_id.clear();
			invalid_label.setText("Invalid password or username.");
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
	
	
	private boolean correct() {
		
		boolean let_in = false;
		
		
		System.out.println(user_id.getText());
		System.out.println(password_id.getText());
		Statement stmt = null;
		
		try{				
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Professor WHERE ProfessorID = " + "'" + user_id.getText() + "'"
					+ " AND Password = " + "'" + password_id.getText() + "'");
			
			while(rs.next()){
				if(rs.getString("ProfessorID") != null && rs.getString("Password") != null){
					String asd = rs.getString("ProfessorID");
					String dsa = rs.getString("Password");
					System.out.println(asd);
					System.out.println(dsa);
					let_in = true;
				}
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		
		return let_in;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}
