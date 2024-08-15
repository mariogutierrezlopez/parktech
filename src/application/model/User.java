package application.model;

public class User {
    private int id;
    private String rol;
    private String IBAN;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String password;
    private String matricula;
    
    
    public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String iBAN) {
        IBAN = iBAN;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }



    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String rol, String IBAN, String nombre, String apellidos, String usuario, String password) {
        this.rol = rol;
        this.IBAN = IBAN;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.password = password;
    }
    
    
    public User(int id, String rol, String IBAN, String nombre, String apellidos, String usuario) {
        this.id = id;
        this.rol = rol;
        this.IBAN = IBAN;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
    }
}