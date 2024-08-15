package application.utils;

import java.io.File;
import application.model.*;

public class UsersUtil {	
	
	public static String getUserTypeString(String type) {
		String userTypeString = "";
		
		if(type.equals("driver")) {
			userTypeString = "Conductor";
		}else if(type.equals("dueño")) {
			userTypeString = "Dueño Parking";
		}else if(type.equals("security")) {
			userTypeString = "Vigilante de seguridad";
		}
		return userTypeString;
	}
}
