package Gestion;

public class Cliente {

	private static int contadorGestores = 1;

	public int id;
	public String usuario;
	public String password;
	public String correo;
	public int id_gestor;
	public String saldo;

	public Cliente(String usuario, String password, String correo, int id_gestor, String saldo) {
		this.id = contadorGestores++;
		this.usuario = usuario;
		this.password = password;
		this.correo = correo;
		this.id_gestor = id_gestor;
		this.saldo = saldo;
	}
}
