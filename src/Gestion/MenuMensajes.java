package Gestion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMensajes {

	private Scanner sc = new Scanner(System.in);
	private ArrayList<Mensajes> datos = new ArrayList<Mensajes>();
	private Statement st;
	private ResultSet resultado;
	private String query;

	public static void mostrarMenu() {
		System.out.println("----- MENÚ MENSAJES-----");
		System.out.println("1. Envío de un mensaje");
		System.out.println("2. Obtener un mensaje");
		System.out.println("3. Obtención de todos los mensajes");
		System.out.println("0. Volver");
		System.out.println("Elije una opción: ");
	}

	public void iniciarMenu(Connection conexion) throws SQLException {
		this.mostrarMenu();
		int selectMenuMens = this.sc.nextInt();

		switch (selectMenuMens) {
		case 1:
			this.insertar(conexion);
			iniciarMenu(conexion);
			break;
		case 2:
			this.obtencionMensaje(conexion);
			iniciarMenu(conexion);
		case 3:
			this.obtencionTodos(conexion);
			iniciarMenu(conexion);
			break;
		case 0:
			System.out.println("Volviendo al menú principal");
			break;
		}
	}

	public void insertar(Connection conexion) throws SQLException {
		System.out.println("1. Envío de un mensaje");
		System.out.println("¿Cuál es el id de origen?");
		int id_origen = this.sc.nextInt();
		System.out.println("¿Cuál es el id de destino?");
		int id_destino = this.sc.nextInt();
		System.out.println("Introduzca el texto del mensaje:");
		String texto = this.sc.next();
		System.out.println("¿Qué día es hoy? (Coloque la fecha en el formato yyyyMMdd)");
		String fecha = this.sc.next();

		st = conexion.createStatement();
		query = "INSERT INTO `mensaje` (`id`, `id_origen`, `id_destino`, `texto`, `fecha`) VALUES (null, '" + id_origen
				+ "','" + id_destino + "','" + texto + "', '" + fecha + "')"; 
		st.execute(query);

		Mensajes mensajes = new Mensajes(id_origen, id_destino, texto, fecha);
		this.datos.add(mensajes);
		System.out.println("Se ha creado un nuevo mensaje\n" + "IdOrigen: " + mensajes.id_origen + "\n" + "IdDestino: "
				+ mensajes.id_destino + "\n" + "Texto: " + mensajes.texto + "\n" + "Fecha: " + mensajes.fecha);
	}

	public void obtencionMensaje(Connection conexion) throws SQLException {
		System.out.println("2. Obtención de un mensaje");

		query = "SELECT `id`,`texto` FROM `mensaje`; ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		int i = 1;
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getString(2));
			i++;
		}
		System.out.println("Introduce el id de mensaje que quieres obtener: ");
		int select = sc.nextInt();
		query = "SELECT * FROM `mensaje` WHERE id = " + select;
		resultado = st.executeQuery(query);
		resultado.next();
		System.out.println("Id: " + resultado.getInt(1) +  "\n" + "IdOrigen: "  + resultado.getInt(2) + "\n" + "IdDestino: " + resultado.getInt(3) + "\n"
				+  "Texto: " + resultado.getString(4) + "\n" + "Fecha: " + resultado.getString(5));
	}

	public void obtencionTodos(Connection conexion) throws SQLException {
		System.out.println("3. Obtención de todos los mensajes");
		query = "SELECT * FROM `mensaje` ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		while (resultado.next()) {
			System.out.println("Id: " + resultado.getInt(1) +  "\n" + "IdOrigen: "  + resultado.getInt(2) + "\n" + "IdDestino: " + resultado.getInt(3) + "\n"
					+  "Texto: " + resultado.getString(4) + "\n" + "Fecha: " + resultado.getString(5));
		}
	}

}
