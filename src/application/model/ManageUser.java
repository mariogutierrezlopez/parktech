package application.model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ManageUser {
	
		  private List<User> users; // Lista de usuarios registrados

		  public ManageUser() {
		    // Inicializa la lista de usuarios
		    this.users = new ArrayList<>();
		  }

		  // Método para registrar un usuario
		  public void register(String rol, String iban, String nombre, String apellidos, String usuario, String password ) {
		    // Crea un nuevo objeto User con el nombre de usuario y la contraseña proporcionados
		    User newUser = new User(rol, iban, nombre, apellidos, usuario, password);
		    // Agrega el nuevo usuario a la lista de usuarios registrados
		    this.users.add(newUser);

		    // Convierte el objeto User en una cadena JSON
		    Gson gson = new Gson();
		    String json = gson.toJson(newUser);

		    // Guarda el usuario en un archivo de texto
		    try {
		      FileWriter writer = new FileWriter("users.json");
		      writer.write(json);
		      writer.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

		  // Método para verificar si un usuario existe en la lista de usuarios registrados
		  public boolean userExists(String username) {
		    for (User user : this.users) {
		      if (user.getUsuario().equals(username)) {
		        return true;
		      }
		    }
		    return false;
		  }
}
