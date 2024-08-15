package application.controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import application.model.User;
import application.utils.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecurityController {
	
	private Stage stage; 
	private Scene scene;
	private User usuario;

    @FXML
    private Button cerrarSesionButton;

    @FXML
    private BorderPane estadisticasButton;

    @FXML
    private BorderPane historialEntradaSalidaButton;

    @FXML
    private Text nameText;

    @FXML
    private BorderPane ocupacionesButton;

    @FXML
    private Text rolText;
    
    public SecurityController(User usuario) {
		this.usuario = usuario;
	}
    
    public void initialize() {
    	nameText.setText("Nombre: " + usuario.getUsuario());
    	rolText.setText("Rol: " + UsersUtil.getUserTypeString(usuario.getRol()));	
    }

    @FXML
    void abrirCochesDentro(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ResponsiveEntradaSalida.fxml"));
    	InOutController controlador = new InOutController(usuario);
    	loader.setController(controlador);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	try {
    		scene = new Scene(loader.load());
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	stage.setScene(scene);
    	stage.centerOnScreen();
    	stage.show();
    }

    @FXML
    void abrirEstadisticas(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ResponsiveEstadisticasCS.fxml"));
    	StatsController controlador = new StatsController(usuario);
    	loader.setController(controlador);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	try {
    		scene = new Scene(loader.load());
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	stage.setScene(scene);
    	stage.centerOnScreen();
    	stage.show();

    }

    @FXML
    void abrirOcupaciones(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ResponsiveParking.fxml"));
    	ParkingController controlador = new ParkingController(usuario);
    	loader.setController(controlador);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	try {
    		scene = new Scene(loader.load());
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	stage.setScene(scene);
    	stage.centerOnScreen();
    	stage.show();
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Login.fxml"));
    	ControladorLogin controlador = new ControladorLogin(null);
    	loader.setController(controlador);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	try {
    		scene = new Scene(loader.load());
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	stage.setScene(scene);
    	stage.centerOnScreen();
    	stage.show();

    }

}