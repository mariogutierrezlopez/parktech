package application.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	private Connection conn;
	private Statement stmt;

	private static void step3(Connection conn, Statement stmt, String sql) throws SQLException {
		System.out.println("Creando la tabla si no existe...");
		System.out.println("sql Create Table: " + sql);
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public Connection getConn() {
		return conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public Conexion() {
		this.conn = null;
		this.stmt = null;
		// STEP 1: Register JDBC driver
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// STEP 2: Open a connection
		System.out.println("Connecting to a selected database...");

		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bd_parktech", USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connectado a la Base de Datos...");
	}

	public static void createTables(Connection conn, Statement stmt) {
		// STEP 3: Execute a query
		String sentencias[] = {
				"CREATE TABLE IF NOT EXISTS `user` (\r\n" + "	`ID` INT(8) NOT NULL AUTO_INCREMENT,\r\n"
						+ "	`rol` CHAR(16) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`IBAN` CHAR(32) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`nombre` CHAR(32) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`apellidos` CHAR(32) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`usuario` CHAR(32) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`password` CHAR(32) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	PRIMARY KEY (`ID`) USING BTREE\r\n" + ")\r\n" + "COLLATE='utf8_unicode_ci'\r\n"
						+ "ENGINE=InnoDB\r\n" + "AUTO_INCREMENT=5\r\n" + ";\r\n" + "",
				"CREATE TABLE IF NOT EXISTS `parking` (\r\n" + "	`ID` INT(4) NOT NULL,\r\n"
						+ "	`ubicacionParking` CHAR(43) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`fechaAlta` DATE NOT NULL,\r\n" + "	`fechaBaja` DATE NOT NULL\r\n" + ")\r\n"
						+ "COLLATE='utf8_unicode_ci'\r\n" + "ENGINE=InnoDB\r\n" + ";\r\n" + "",
				"CREATE TABLE IF NOT EXISTS `mensajes` (\r\n" + "	`ID` INT(16) NOT NULL AUTO_INCREMENT,\r\n"
						+ "	`mensaje` VARCHAR(256) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`idUserNameManda` INT(8) NOT NULL,\r\n" + "	`idUserNameRecibe` INT(8) NOT NULL,\r\n"
						+ "	`fecha` DATE NOT NULL,\r\n" + "	PRIMARY KEY (`ID`) USING BTREE\r\n" + ")\r\n"
						+ "COLLATE='utf8_unicode_ci'\r\n" + "ENGINE=InnoDB\r\n" + ";\r\n" + "",
				"CREATE TABLE IF NOT EXISTS `plazasparking` (\r\n" + "	`ID` INT(16) NOT NULL AUTO_INCREMENT,\r\n"
						+ "	`idParking` INT(8) NOT NULL,\r\n"
						+ "	`idPropietarioAparcado` INT(8) NULL DEFAULT NULL,\r\n"
						+ "	PRIMARY KEY (`ID`) USING BTREE\r\n" + ")\r\n" + "COLLATE='utf8_unicode_ci'\r\n"
						+ "ENGINE=InnoDB\r\n" + "AUTO_INCREMENT=3\r\n" + ";\r\n" + "",
				"CREATE TABLE IF NOT EXISTS `coche` (\r\n" + "	`ID` INT(8) NOT NULL,\r\n"
						+ "	`matricula` CHAR(8) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`idPropietario` INT(8) NOT NULL,\r\n" + "	`fechaAlta` DATE NOT NULL,\r\n"
						+ "	PRIMARY KEY (`ID`) USING BTREE,\r\n"
						+ "	INDEX `Propietario` (`idPropietario`) USING BTREE\r\n" + ")\r\n"
						+ "COLLATE='utf8_unicode_ci'\r\n" + "ENGINE=InnoDB\r\n" + ";\r\n" + "",
				"CREATE TABLE IF NOT EXISTS `aparca` (\r\n" + "	`idCoche` INT(8) NOT NULL,\r\n"
						+ "	`idPlazaParking` INT(8) NOT NULL,\r\n" + "	`fechaEntrada` DATE NOT NULL,\r\n"
						+ "	`fechaSalida` DATE NOT NULL,\r\n"
						+ "	PRIMARY KEY (`idCoche`, `idPlazaParking`) USING BTREE,\r\n"
						+ "	INDEX `Plaza Parking` (`idPlazaParking`) USING BTREE\r\n" + ")\r\n"
						+ "COLLATE='utf8_unicode_ci'\r\n" + "ENGINE=InnoDB\r\n" + ";\r\n" + "",
				"CREATE TABLE IF NOT EXISTS `sensor` (\r\n" + "	`ID` INT(8) NOT NULL,\r\n"
						+ "	`tipo` CHAR(32) NOT NULL COLLATE 'utf8_unicode_ci',\r\n"
						+ "	`idPlazaParking` INT(16) NOT NULL,\r\n" + "	PRIMARY KEY (`ID`) USING BTREE\r\n" + ")\r\n"
						+ "COLLATE='utf8_unicode_ci'\r\n" + "ENGINE=InnoDB\r\n" + ";\r\n" + "",
				"CREATE TABLE IF NOT EXISTS `lecturas` (\r\n" + "	`ID` INT(32) NOT NULL AUTO_INCREMENT,\r\n"
						+ "	`idSensor` INT(8) NOT NULL,\r\n"
						+ "	`dato` CHAR(32) NOT NULL COLLATE 'utf8_unicode_ci',\r\n" + "	`fecha` DATE NOT NULL,\r\n"
						+ "	PRIMARY KEY (`ID`) USING BTREE,\r\n" + "	INDEX `Id_Sensor` (`idSensor`) USING BTREE,\r\n"
						+ "	CONSTRAINT `Id_Sensor` FOREIGN KEY (`idSensor`) REFERENCES `sensor` (`ID`) ON UPDATE NO ACTION ON DELETE NO ACTION\r\n"
						+ ")\r\n" + "COLLATE='utf8_unicode_ci'\r\n" + "ENGINE=InnoDB\r\n" + "AUTO_INCREMENT=9\r\n"
						+ ";\r\n" + "",
		};
		
		for (int i = 0; i < sentencias.length; i++) {
			try {
				step3(conn, stmt, sentencias[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static User consultarLogin(Conexion conne, String textoUsuario, String contraseñaUsuario)
			throws SQLException {
		User usuario = null; // Objeto usuario que será el usuario una vez se encuentre
		Connection conn = conne.getConn();
		Statement stmt = conne.getStmt();

		String sql = "SELECT * FROM `user`\r\n" + "WHERE `usuario` = '" + textoUsuario + "' AND `password` = MD5('"
				+ contraseñaUsuario + "')";
		System.out.println("sql Select: " + sql);
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			int id = rs.getInt("ID");
			String rol = rs.getString("rol");
			String iban = rs.getString("IBAN");
			String nombre = rs.getString("nombre");
			String apellidos = rs.getString("apellidos");
			String usuarioStr = rs.getString("usuario");
			usuario = new User(id, rol, iban, nombre, apellidos, usuarioStr);
		}
		return usuario;
	}

	public static void registrarse(Conexion conne, User usuario) throws SQLException {
		Connection conn = conne.getConn();
		Statement stmt = conne.getStmt();

		String sql = "INSERT INTO user(rol, IBAN, nombre, apellidos, usuario, `password`) " + "VALUES('"
				+ usuario.getRol() + "', '" + usuario.getIBAN() + "', '" + usuario.getNombre() + "', '"
				+ usuario.getApellidos() + "', '" + usuario.getUsuario() + "', MD5('" + usuario.getPassword() + "'))";

		System.out.println("sql Select: " + sql);
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}

	public static boolean usuarioRepetido(Conexion conne, String usuario) throws SQLException {
		boolean usuarioRepetido = false;
		Connection conn = conne.getConn();
		Statement stmt = conne.getStmt();

		String sql = "SELECT COUNT(*) FROM user WHERE usuario='" + usuario + "';";
		System.out.println("sql Select: " + sql);
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			int count = rs.getInt(1);
			if (count > 0) {
				usuarioRepetido = true;
			}
		}

		return usuarioRepetido;
	}

}