package Gestion;

public class Mensajes {

	private static int contadorMensajes = 1;

	public int id;
	public int id_origen;
	public int id_destino;
	public String texto;
	public String fecha;

	public Mensajes(int id_origen, int id_destino, String texto, String fecha) {
		this.id = contadorMensajes++;
		this.id_origen = id_origen;
		this.id_destino = id_destino;
		this.texto = texto;
		this.fecha = fecha;
	}
}
