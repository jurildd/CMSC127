import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	Connection conn = Connector.getConn();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/StartClass.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	}
	@FXML
	private void handleButtonAction2(ActionEvent event) throws IOException{
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/Enroll.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	}
	@FXML
	private void handleButtonAction3(ActionEvent event) throws IOException{
	
		PreparedStatement ps = null;
		String query = "DELETE FROM Checker";
		
		try{
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			
		}catch(SQLException e){
			System.out.print(e.getMessage());
		}
		Platform.exit();
		System.exit(0);
	}


}
