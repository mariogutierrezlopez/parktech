package application.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.model.Conexion;
import application.model.User;
import application.utils.TextUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import application.model.*;

public class ControladorLogin {

	private Conexion conne;
	@FXML
	private Button registerButton;

	@FXML
	private TextField userTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button loginButton;

	@FXML
	private Text errorText;

	private Stage stage;
	private Scene scene;

	@FXML
	private Button volverButton;

	public ControladorLogin(Conexion conn) {
		this.conne = conn;
	}

	@FXML
	void loginFunction(ActionEvent event) throws SQLException {

		String textoUsuario = userTextField.getText(); // El valor de texto que se introduzca en la interfaz
		String contraseñaUsuario = passwordTextField.getText();// El valor de contraseña que se introduzca en la
																// interfaz
		User usuario = Conexion.consultarLogin(conne, textoUsuario, contraseñaUsuario);
		if (usuario == null) {
		    System.out.println("El usuario no existe");
		} else { // Ahora distingo el tipo de usuario para cargar su vista respectiva

			if (usuario.getRol().equals("driver")) { // TIPO CONDUCTOR
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MainUser.fxml"));

			
				UserController controlador = new UserController(usuario);
				loader.setController(controlador);
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				try {
					scene = new Scene(loader.load());
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			} else if (usuario.getRol().equals("dueño")) { // TIPO DUEÑO PARKING
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MainOwner.fxml"));
				
				OwnerController controlador = new OwnerController(usuario);
				loader.setController(controlador);
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				try {
					scene = new Scene(loader.load());
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			} else if (usuario.getRol().equals("security")) { // TIPO VIGILANTE DE SEGURIDAD
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MainSecurity.fxml"));

				SecurityController controlador = new SecurityController(usuario);
				loader.setController(controlador);
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
	}

	@FXML
	void registerFunction(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/ResponsiveRegister.fxml"));
		RegisterController controlador = new RegisterController(conne);
		loader.setController(controlador);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
