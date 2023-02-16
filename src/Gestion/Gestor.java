package Gestion;

public class Gestor {

	private static int contadorGestores = 1;

	public int id;
	public String usuario;
	public String password;
	public String correo;

	public Gestor (String usuario, String password, String correo) {
		this.id = contadorGestores++;
		this.usuario = usuario; 
		this.password = password;
		this.correo = correo;
	}
}