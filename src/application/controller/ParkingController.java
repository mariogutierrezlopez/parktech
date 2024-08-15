package application.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import application.utils.*;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ParkingController {

	private User usuario;

	private Stage stage;
	private Scene scene;

	@FXML
	private Button volverButton;

	@FXML
	private RadioButton radio1;

	@FXML
	private Tooltip TT1;

	@FXML
	private RadioButton radio2;

	@FXML
	private Tooltip TT2;

	@FXML
	private RadioButton radio3;

	@FXML
	private Tooltip TT3;

	@FXML
	private RadioButton radio4;

	@FXML
	private Tooltip TT4;

	@FXML
	private RadioButton radio5;

	@FXML
	private Tooltip TT5;

	@FXML
	private RadioButton radio6;

	@FXML
	private Tooltip TT6;

	@FXML
	private RadioButton radio7;

	@FXML
	private Tooltip TT7;

	@FXML
	private RadioButton radio8;

	@FXML
	private Tooltip TT8;

	@FXML
	private RadioButton radio9;

	@FXML
	private Tooltip TT9;

	@FXML
	private RadioButton radio10;

	@FXML
	private Tooltip TT10;

	@FXML
	private RadioButton radio11;

	@FXML
	private Tooltip TT11;

	@FXML
	private RadioButton radio12;

	@FXML
	private Tooltip TT12;

	@FXML
	private RadioButton radio13;

	@FXML
	private Tooltip TT13;

	@FXML
	private RadioButton radio14;

	@FXML
	private Tooltip TT14;

	@FXML
	private RadioButton radio15;

	@FXML
	private Tooltip TT15;

	@FXML
	private RadioButton radio16;

	@FXML
	private Tooltip TT16;

	@FXML
	private RadioButton radio17;

	@FXML
	private Tooltip TT17;

	@FXML
	private RadioButton radio18;

	@FXML
	private Tooltip TT18;

	@FXML
	private RadioButton radio19;

	@FXML
	private Tooltip TT19;

	@FXML
	private RadioButton radio20;

	@FXML
	private Tooltip TT20;

	@FXML
	private RadioButton radio21;

	@FXML
	private Tooltip TT21;

	@FXML
	private RadioButton radio22;

	@FXML
	private Tooltip TT22;

	@FXML
	private RadioButton radio23;

	@FXML
	private Tooltip TT23;

	@FXML
	private RadioButton radio24;

	@FXML
	private Tooltip TT24;

	@FXML
	private RadioButton radio25;

	@FXML
	private Tooltip TT25;

	@FXML
	private RadioButton radio26;

	@FXML
	private Tooltip TT26;

	@FXML
	private RadioButton radio27;

	@FXML
	private Tooltip TT27;

	@FXML
	private RadioButton radio28;

	@FXML
	private Tooltip TT28;

	@FXML
	private RadioButton radio29;

	@FXML
	private Tooltip TT29;

	@FXML
	private RadioButton radio30;

	@FXML
	private Tooltip TT30;

	@FXML
	private RadioButton radio31;

	@FXML
	private Tooltip TT31;

	@FXML
	private RadioButton radio32;

	@FXML
	private Tooltip TT32;

	@FXML
	private RadioButton radio33;

	@FXML
	private Tooltip TT33;

	@FXML
	private RadioButton radio34;

	@FXML
	private Tooltip TT34;

	@FXML
	private RadioButton radio35;

	@FXML
	private Tooltip TT35;

	@FXML
	private RadioButton radio36;

	@FXML
	private Tooltip TT36;

	@FXML
	private Text rolText;

	@FXML
	private Text nameText;

	private RadioButton[] radioButtonArray;
	private Tooltip[] toolTipArray;

	public void initializeButtons() {
		radioButtonArray = new RadioButton[36];
		radioButtonArray[0] = radio1;
		radioButtonArray[1] = radio2;
		radioButtonArray[2] = radio3;
		radioButtonArray[3] = radio4;
		radioButtonArray[4] = radio5;
		radioButtonArray[5] = radio6;
		radioButtonArray[6] = radio7;
		radioButtonArray[7] = radio8;
		radioButtonArray[8] = radio9;
		radioButtonArray[9] = radio10;
		radioButtonArray[10] = radio11;
		radioButtonArray[11] = radio12;
		radioButtonArray[12] = radio13;
		radioButtonArray[13] = radio14;
		radioButtonArray[14] = radio15;
		radioButtonArray[15] = radio16;
		radioButtonArray[16] = radio17;
		radioButtonArray[17] = radio18;
		radioButtonArray[18] = radio19;
		radioButtonArray[19] = radio20;
		radioButtonArray[20] = radio21;
		radioButtonArray[21] = radio22;
		radioButtonArray[22] = radio23;
		radioButtonArray[23] = radio24;
		radioButtonArray[24] = radio25;
		radioButtonArray[25] = radio26;
		radioButtonArray[26] = radio27;
		radioButtonArray[27] = radio28;
		radioButtonArray[28] = radio29;
		radioButtonArray[29] = radio30;
		radioButtonArray[30] = radio31;
		radioButtonArray[31] = radio32;
		radioButtonArray[32] = radio33;
		radioButtonArray[33] = radio34;
		radioButtonArray[34] = radio35;
		radioButtonArray[35] = radio36;

		toolTipArray = new Tooltip[36];
		toolTipArray[0] = TT1;
		toolTipArray[1] = TT2;
		toolTipArray[2] = TT3;
		toolTipArray[3] = TT4;
		toolTipArray[4] = TT5;
		toolTipArray[5] = TT6;
		toolTipArray[6] = TT7;
		toolTipArray[7] = TT8;
		toolTipArray[8] = TT9;
		toolTipArray[9] = TT10;
		toolTipArray[10] = TT11;
		toolTipArray[11] = TT12;
		toolTipArray[12] = TT13;
		toolTipArray[13] = TT14;
		toolTipArray[14] = TT15;
		toolTipArray[15] = TT16;
		toolTipArray[16] = TT17;
		toolTipArray[17] = TT18;
		toolTipArray[18] = TT19;
		toolTipArray[19] = TT20;
		toolTipArray[20] = TT21;
		toolTipArray[21] = TT22;
		toolTipArray[22] = TT23;
		toolTipArray[23] = TT24;
		toolTipArray[24] = TT25;
		toolTipArray[25] = TT26;
		toolTipArray[26] = TT27;
		toolTipArray[27] = TT28;
		toolTipArray[28] = TT29;
		toolTipArray[29] = TT30;
		toolTipArray[30] = TT31;
		toolTipArray[31] = TT32;
		toolTipArray[32] = TT33;
		toolTipArray[33] = TT34;
		toolTipArray[34] = TT35;
		toolTipArray[35] = TT36;

	}

	// Constructor si lo abre un usuario
	public ParkingController(User usuario) {
		this.usuario = usuario;
	}

	public void initialize() {

		initializeButtons(); // Crea el array de RadioButtons
		File file = new File("./data/sensorPresencia.json"); // Creo el objeto del archivo json
		String content = null;
		try {
			content = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} // Leo el contenido
		JsonArray datos = JsonParser.parseString(content).getAsJsonArray(); // Creo el archivo json en java

		for (int i = 0; i < datos.size(); i++) { // Cargo los datos en los botones
			boolean disponibilidad = datos.get(i).getAsJsonObject().get("disponibilidad").getAsBoolean();
			radioButtonArray[i].setSelected(!disponibilidad);
			if (!disponibilidad) {
				toolTipArray[i].setText(datos.get(i).getAsJsonObject().get("matricula").getAsString());
			} else {
				toolTipArray[i].setText("Plaza vacía");
			}
		}
		nameText.setText("Nombre: " + TextUtil.capitalize(usuario.getUsuario()));
		rolText.setText("Rol: " + TextUtil.capitalize(UsersUtil.getUserTypeString(usuario.getRol())));
	}

	void cargarPlazasDiaI(int i) throws Exception {
		if (i >= 0 && i <= 5) {
			switch (i) {
			case 0:
				// TODO hacer metodo para leer de json
			}
		} else {
			throw new Exception("Dia incorrecto");
		}
	}

	@FXML
	void volver(ActionEvent event) {
		if (usuario.getRol().equals("driver")) { // User -> Driver
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

		} else if (usuario.getRol().equals("dueño")) { // User -> DueñoParking
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
		} else if (usuario.getRol().equals("security")) { // User -> Seguridad
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
