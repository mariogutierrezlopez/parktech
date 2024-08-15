package application;

import application.controller.ControladorLogin;
import application.model.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Parktech");
			primaryStage.getIcons().add(new Image("file:icon.png"));
			//primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("./icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Login.fxml"));
			Conexion conn = new Conexion();
			ControladorLogin control = new ControladorLogin(conn);
			
			loader.setController(control);
			
			Parent root = loader.load();
			
			primaryStage.setScene(new Scene(root));
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}