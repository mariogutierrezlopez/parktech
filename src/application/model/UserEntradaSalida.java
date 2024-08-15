package application.model;

public class UserEntradaSalida {
	private String user;
	private String email;
	private String matricula;
	private String horaEntrada;
	private String horaSalida;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public UserEntradaSalida(String user, String email, String matricula, String horaEntrada, String horaSalida) {
		super();
		this.user = user;
		this.email = email;
		this.matricula = matricula;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}
	
	
}
