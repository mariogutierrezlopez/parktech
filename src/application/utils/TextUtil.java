package application.utils;

import java.text.Normalizer;

public class TextUtil {
	public static String normalizeText(String text) {
		//https://es.stackoverflow.com/questions/31178/c%C3%B3mo-limpiar-string-de-tildes-en-java
		String cadenaNormalize = Normalizer.normalize(text, Normalizer.Form.NFD);   
		String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
		
		return (cadenaSinAcentos.toUpperCase());
	}
	
	public static String capitalize(String str){
		//https://www.javatpoint.com/how-to-capitalize-the-first-letter-of-a-string-in-java
		if (str == null || str.length() == 0)
			return str;
		
		return str.substring(0, 1).toUpperCase() + str.substring(1);  
	}  
}
