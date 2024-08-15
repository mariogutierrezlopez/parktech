package application.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import application.utils.*;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatsController {

	@FXML
	private Button volverButton;

	@FXML
	private ChoiceBox<String> diaChoiceBox;

	@FXML
	private Button actualizarButton;

	@FXML
	private ChoiceBox<String> horaChoiceBox;

	@FXML
	private Label temperaturaText;

	@FXML
	private Label errorText;

	@FXML
	private Label tiempoText;

	@FXML
	private Label plazasLibresText;

	@FXML
	private Label horaPuntaText;

	@FXML
	private Text rolText;

	@FXML
	private Text nameText;

	private Stage stage;
	private Scene scene;
	private User usuario;

	public StatsController(User usuario) {
		this.usuario = usuario;
	}

	public void initialize() {
		nameText.setText("Nombre: " + TextUtil.capitalize(usuario.getUsuario()));
		rolText.setText("Rol: " + TextUtil.capitalize(UsersUtil.getUserTypeString(usuario.getRol())));

		File file = new File("./data/sensorClima.json");
		String content = null;
		try {
			content = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObject datos = JsonParser.parseString(content).getAsJsonObject();

		diaChoiceBox.getItems().add("Seleccione una opcion");
		diaChoiceBox.getItems().add("Lunes");
		diaChoiceBox.getItems().add("Martes");
		diaChoiceBox.getItems().add("Miercoles");
		diaChoiceBox.getItems().add("Jueves");
		diaChoiceBox.getItems().add("Viernes");
		diaChoiceBox.setValue("Seleccione una opcion");

		horaChoiceBox.getItems().add("Seleccione una opcion");
		horaChoiceBox.getItems().add("10:00");
		horaChoiceBox.getItems().add("14:00");
		horaChoiceBox.getItems().add("18:00");
		horaChoiceBox.getItems().add("22:00");
		horaChoiceBox.setValue("Seleccione una opcion");
	}

	void cargarDatos(String dia, String hora) {
		File file = new File("./data/sensorClima.json");
		String content = null;
		try {
			content = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObject datos = JsonParser.parseString(content).getAsJsonObject();

		boolean datosCargados = false;
		JsonArray dias = datos.get("dias").getAsJsonArray();
		for (int i = 0; i < dias.size(); i++) {
			if (dias.get(i).getAsJsonObject().get("dia").getAsString().equals(dia)) {
				for (int j = 0; j < dias.get(i).getAsJsonObject().get("horas").getAsJsonArray().size(); j++) {
					if (dias.get(i).getAsJsonObject().get("horas").getAsJsonArray().get(j).getAsJsonObject().get("hora")
							.getAsString().equals(hora)) {
						JsonObject horaJsonObject = dias.get(i).getAsJsonObject().get("horas").getAsJsonArray().get(j)
								.getAsJsonObject();
						temperaturaText.setText(horaJsonObject.get("temperatura").getAsString());
						tiempoText.setText(horaJsonObject.get("tiempo").getAsString());

						plazasLibresText.setText(Integer.toString(horaJsonObject.get("plazasLibres").getAsInt()));

						JsonArray horas = dias.get(i).getAsJsonObject().get("horas").getAsJsonArray();
						JsonObject horaPunta = horas.get(0).getAsJsonObject();
						for (int k = 0; k < horas.size(); k++) {
							if (horas.get(k).getAsJsonObject().get("plazasLibres").getAsInt() < horaPunta
									.get("plazasLibres").getAsInt()) {
								horaPunta = horas.get(k).getAsJsonObject();
							}
						}
						horaPuntaText.setText(horaPunta.get("hora").getAsString());
						datosCargados = true;
					}
				}
			}

		}

		if (!datosCargados) {
			errorText.setText("Seleccione un dia y una hora correctos");
		} else {
			errorText.setText("");
		}
	}

	@FXML
	void actualizar(ActionEvent event) {
		String dia = diaChoiceBox.getValue();
		String hora = horaChoiceBox.getValue();

		cargarDatos(dia, hora);
	}

	@FXML
	void volver(ActionEvent event) {
		if (usuario.getRol().equals("dueño")) {
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
		} else if (usuario.getRol().equals("driver")) {
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
		} else {
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