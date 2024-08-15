package application.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import application.utils.*;
import application.model.User;
import application.model.UserEntradaSalida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserHistorialController {
	
	private Scene scene;
	private Stage stage; 
	
	ArrayList<UserEntradaSalida> personArrayList = new ArrayList<>();

    @FXML
    private Button volverButton;

    @FXML
    private TableView<UserEntradaSalida> tableView;

    @FXML
    private TableColumn<UserEntradaSalida, String> nombreColumn;

    @FXML
    private TableColumn<UserEntradaSalida, String> apellidoColumn;

    @FXML
    private TableColumn<UserEntradaSalida, String> matriculaColumn;

    @FXML
    private TableColumn<UserEntradaSalida, String> horaEntradaColumn;

    @FXML
    private TableColumn<UserEntradaSalida, String> horaSalidaColumn;

    @FXML
    private ChoiceBox<String> dayChoiceBox;

    @FXML
    private Button actualizarButton;

    @FXML
    private Label errorText;

    @FXML
    private Text rolText;

    @FXML
    private Text nameText;
    
    private User usuario;
	
	public UserHistorialController(User usuario) {
		this.usuario = usuario;
	}
	
	 public void initialize() {
	    	//Escribo el nombre y el rol en la vista
			nameText.setText("Nombre: " + TextUtil.capitalize(usuario.getUsuario()));
	    	rolText.setText("Rol: " + TextUtil.capitalize(UsersUtil.getUserTypeString(usuario.getRol())));
	    	
			//Establezco las propiedades que van a leer cada una de las columnas
			nombreColumn.setCellValueFactory(new PropertyValueFactory<UserEntradaSalida, String>("user"));
			apellidoColumn.setCellValueFactory(new PropertyValueFactory<UserEntradaSalida, String>("email"));
			matriculaColumn.setCellValueFactory(new PropertyValueFactory<UserEntradaSalida, String>("matricula"));
			horaEntradaColumn.setCellValueFactory(new PropertyValueFactory<UserEntradaSalida, String>("horaEntrada"));
			horaSalidaColumn.setCellValueFactory(new PropertyValueFactory<UserEntradaSalida, String>("horaSalida"));
			
			//Añado las siguientes opciones a la choiceBox
	    	dayChoiceBox.getItems().add("Seleccione una opcion");
	    	dayChoiceBox.getItems().add("Lunes");
	    	dayChoiceBox.getItems().add("Martes");
	    	dayChoiceBox.getItems().add("Miercoles");
	    	dayChoiceBox.getItems().add("Jueves");
	    	dayChoiceBox.getItems().add("Viernes");
	    	dayChoiceBox.setValue("Seleccione una opcion"); //Marco por defecto la opcion de seleccionar
	    	
	    	
	    	//Cargo los datos por defecto dependiendo de la fecha local del ordenador
	    	
	    	//https://stackoverflow.com/questions/5574673/what-is-the-easiest-way-to-get-the-current-day-of-the-week-in-android
	    	Calendar calendar = Calendar.getInstance();
	    	int day = calendar.getFirstDayOfWeek();
	    	
	    	switch(day) {
	    		case Calendar.MONDAY:
	    			try {
	    				cargarDatosDeDiaI(1, usuario);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
				break;
				
	    		case Calendar.TUESDAY:
	    			try {
	    				cargarDatosDeDiaI(2, usuario);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
				break;
				
	    		case Calendar.WEDNESDAY:
	    			try {
	    				cargarDatosDeDiaI(3, usuario);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
				break;
				
	    		case Calendar.THURSDAY:
	    			try {
	    				cargarDatosDeDiaI(4, usuario);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
				break;
				
	    		case Calendar.FRIDAY:
	    			try {
	    				cargarDatosDeDiaI(5, usuario);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
				break;
				
				default: //En caso de que sea fin de semana cargo los datos del lunes
	    			try {
	    				cargarDatosDeDiaI(1, usuario);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
				break;
	    	}
	    	
		}
	 
	 void cargarDatosDeDiaI(int i) throws Exception {
	    	/**
	    	 * Esta funcion carga los datos en los elementos FXML según el dia de la semana que le
	    	 * pasamos como parámetro
	    	 * @param i --> Dia de la semana (1-5)
	    	 * 
	    	 * Lo que hace es cargar la información de entradaSalida.json y busca el array historial dentro
	    	 * del objeto dia para cargarlo dentro de la tabla
	    	 */
	    	if(i >= 1 && i <=5) {
	    		File file = new File("./data/entradaSalida.json");
	    		String content = FileUtils.readFileToString(file, "UTF-8");
				JsonObject datos = JsonParser.parseString(content).getAsJsonObject();
				
				JsonArray dia = datos.getAsJsonArray("dias").get(i-1).getAsJsonObject().getAsJsonArray("historial");
				Gson gson = new Gson();
				UserEntradaSalida[] arrayUsuarios = gson.fromJson(dia, UserEntradaSalida[].class);
				ObservableList<UserEntradaSalida> usuariosHistorial = FXCollections.observableArrayList(arrayUsuarios);
				
				tableView.setItems(usuariosHistorial);
	    	}else {
	    		throw new Exception("Dia invalido");
	    	}
	    }
	    
	    void cargarDatosDeDiaI(int i, User usuario) throws Exception {
	    	/**
	    	 * A diferencia de la función anterior, esta va a recibir también como parámetro la clase
	    	 * driver para cargar en la tabla solo la información de ese mismo driver comparando
	    	 * @Driver.getUser() a el @user del archivo JSON
	    	 */
	    	if(i >= 1 && i <=5) {
	    		File file = new File("./data/entradaSalida.json");
	    		String content = FileUtils.readFileToString(file, "UTF-8");
				JsonObject datos = JsonParser.parseString(content).getAsJsonObject();
				
				JsonArray dia = datos.getAsJsonArray("dias").get(i-1).getAsJsonObject().getAsJsonArray("historial");
				Gson gson = new Gson();
				UserEntradaSalida[] arrayUsuarios = gson.fromJson(dia, UserEntradaSalida[].class);
				UserEntradaSalida[] infoUsuario;
				ObservableList<UserEntradaSalida> usuarioHistorial = FXCollections.observableArrayList();
				for(int j = 0; j < arrayUsuarios.length; j++) {
					if(arrayUsuarios[j].getUser().equalsIgnoreCase(usuario.getUsuario())) {
						usuarioHistorial.add(arrayUsuarios[j]);
					}
				}
				//ObservableList<UserEntradaSalida> usuariosHistorial = FXCollections.observableArrayList(arrayUsuarios);
				
				tableView.setItems(usuarioHistorial);
	    	}else {
	    		throw new Exception("Dia invalido");
	    	}
	    }
	

    @FXML
    void actualizar(ActionEvent event) {
    	errorText.setText(""); //Reseteo el texto de error
    	String dia = dayChoiceBox.getValue();
    	switch(dia) {
    		case "Lunes": //Lunes
				try {
					cargarDatosDeDiaI(1, usuario);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
    		case "Martes": //martes
    			try {
					cargarDatosDeDiaI(2, usuario);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
    		case "Miercoles": //miercoles
    			try {
					cargarDatosDeDiaI(3, usuario);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
    		case "Jueves": //Jueves
    			try {
					cargarDatosDeDiaI(4, usuario);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
    		case "Viernes": //Viernes
    			try {
					cargarDatosDeDiaI(5, usuario);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			break;
			default: 	//Default (Si no se ha seleccionado ninguna opcion)
				errorText.setText("Seleccione una opción antes de actualizar");
				break;
    	}

    }

    @FXML
    void volver(ActionEvent event) {
     	//Botón que vuelve a la vista anterior
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MainUser.fxml"));
    	UserController controlador = new UserController(usuario);
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
