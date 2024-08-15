package application.controller;

import java.io.IOException;

import application.model.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorRegisterExitoso {
	private Stage stage;
	private Scene scene;

    @FXML
    private Button iniciarSesionButton;

    @FXML
    void volverLoginButton(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Login.fxml"));
    	ControladorLogin controlador = new ControladorLogin(null);
    	loader.setController(controlador);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	try {
			scene = new Scene(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	stage.setScene(scene);
    	stage.centerOnScreen();
    	stage.show();
    }

}
