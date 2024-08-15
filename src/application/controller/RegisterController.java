package application.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.model.Conexion;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterController {

	private Stage stage;
	private Scene scene;
	private Conexion conne;

	// Array con las opciones entre las que va a escoger el usuario
	private ObservableList<String> listaTipoUsuario = FXCollections.observableArrayList("Conductor", "Seguridad",
			"Administrador");

	@FXML
	private Button registrarseButton;

	@FXML
	private Button volverButton;

	@FXML
	private Text errorText;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField ibanTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private ChoiceBox<String> tipoUsuarioSelect;

	@FXML
	private TextField surnameTextField;

	@FXML
	private PasswordField passwordTextField;

	public RegisterController(Conexion conne) {
		this.conne = conne;
	}

	@FXML
	public void initialize() {
		// Set the items in the ChoiceBox
		tipoUsuarioSelect.setItems(listaTipoUsuario);
		tipoUsuarioSelect.setValue("Seleccione su tipo de usuario que va a crear");
	}

	@FXML
	void verificarRegistrarse(ActionEvent event) throws SQLException {

		String nombre = nameTextField.getText();
		String surname = surnameTextField.getText();
		String iban = ibanTextField.getText();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		String tipoUsuario = (String) tipoUsuarioSelect.getValue();

		// Tipos de usuarios: "Conductor","Seguridad","Administrador"
		if (tipoUsuario.equals("Conductor")) {
			tipoUsuario = "driver";
		} else if (tipoUsuario.equals("Seguridad")) {
			tipoUsuario = "security";
		} else if (tipoUsuario.equals("Administrador")) {
			tipoUsuario = "dueño";
		}

		User usuario = null;

		if (Conexion.usuarioRepetido(conne, username) || username.isEmpty()) {
			errorText.setText("Usuario Repetido o inválido");
		} else if (iban.isEmpty()) {
			errorText.setText("El iban esta vacio");
		} else if (password.isEmpty()) {
			errorText.setText("Escribe una contraseña");
		} else {
			usuario = new User(tipoUsuario, iban, nombre, surname, username, password);

			try {
				Conexion.registrarse(conne, usuario);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/application/view/ResponsiveRegisterExitoso.fxml"));
			ControladorRegisterExitoso controlador = new ControladorRegisterExitoso();
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

	@FXML
	void volverLogin(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Login.fxml"));
		ControladorLogin controlador = new ControladorLogin(null);
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