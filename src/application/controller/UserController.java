package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import application.model.User;
import application.utils.TextUtil;
import application.utils.UsersUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserController {
	
	private Stage stage;
	private Scene scene;
	private User usuario;
	
	public UserController(User usuario) {
		this.usuario = usuario;
	}

    @FXML
    private Button cerrarSesionButton;

    @FXML
    private BorderPane estadisticasButton;

    @FXML
    private BorderPane historialButton;

    @FXML
    private Text nameText;

    @FXML
    private BorderPane parkingButton;

    @FXML
    private Text rolText;
    
    public void initialize() {
    	nameText.setText("Nombre: " + TextUtil.capitalize(usuario.getUsuario()));
    	rolText.setText("Rol: " + TextUtil.capitalize(UsersUtil.getUserTypeString(usuario.getRol())));
    }

    @FXML
    void abrirEstadísticas(MouseEvent event) {
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
    void abrirHistorial(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ResponsiveHistorialUsuario.fxml"));
    	UserHistorialController controlador = new UserHistorialController(usuario);
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
    void abrirParking(MouseEvent event) {
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