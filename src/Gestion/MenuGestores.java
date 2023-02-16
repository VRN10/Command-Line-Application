package Gestion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuGestores {

	private Scanner sc = new Scanner(System.in);
	private ArrayList<Gestor> datos = new ArrayList<Gestor>();
	private Statement st;
	private ResultSet resultado;
	private String query;

	public static void mostrarMenu() {
		System.out.println("----- MENÚ GESTORES-----");
		System.out.println("1. Inserción de un gestor");
		System.out.println("2. Inserción masiva de gestores aleatorios");
		System.out.println("3. Obtención de un gestor");
		System.out.println("4. Obtención de todos los gestores");
		System.out.println("5. Actualización de un gestor");
		System.out.println("6. Eliminación de un gestor");
		System.out.println("0. Salir al menú principal");
		System.out.println("Elije una opción: ");
	}

	public void iniciarMenu(Connection conexion) throws Exception {
		this.mostrarMenu();
		int selectMenuG = this.sc.nextInt();

		switch (selectMenuG) {
		case 1:
			this.insertar(conexion);
			iniciarMenu(conexion);
			break;
		case 2:
			this.insertarGestorMasivo(conexion);
			iniciarMenu(conexion);
			break;
		case 3:
			this.obtencionGestor(conexion);
			iniciarMenu(conexion);
			break;
		case 4:
			this.obtencionTodos(conexion);
			iniciarMenu(conexion);
			break;
		case 5:
			this.actualizarGestor(conexion);
			iniciarMenu(conexion);
			break;
		case 6:
			this.eliminarGestor(conexion);
			iniciarMenu(conexion);
			break;
		case 0:
			System.out.println("Volviendo al menú principal");
			break;
		}
	}

	public void insertar(Connection conexion) throws Exception {
		System.out.println("Introduzca el nombre de usuario");
		String usuario = this.sc.next();
		System.out.println("Introduzca la contraseña");
		String password = this.sc.next();
		System.out.println("Introduzca el e-mail");
		String correo = this.sc.next();

		st = conexion.createStatement();
		query = "INSERT INTO gestor VALUES(null," + usuario + "," + password + "," + correo + ")";
		st.execute(query);

		Gestor gestor = new Gestor(usuario, password, correo);
		this.datos.add(gestor);
		System.out.println("Se ha creado un nuevo gestor\n" + "Usuario: " + gestor.usuario
				+ "\n" + "Contraseña: " + gestor.password + "\n" + "Correo: " + gestor.correo);
	}

	public void insertarGestorMasivo(Connection conexion) throws SQLException {

		while (true) {
			System.out.println("Introduzca el nombre de usuario");
			String usuario = this.sc.next();
			System.out.println("Introduzca la contraseña");
			String password = this.sc.next();
			System.out.println("Introduzca el e-mail");
			String correo = this.sc.next();

			st = conexion.createStatement();
			query = "INSERT INTO gestor VALUES(null," + usuario + "," + password + "," + correo + ")";
			st.execute(query);

			Gestor gestor = new Gestor(usuario, password, correo);
			this.datos.add(gestor);
			System.out.println("Id: " + gestor.id + "\n" + "Usuario: " + gestor.usuario + "\n" + "Contraseña: "
					+ gestor.password + "\n" + "Correo: " + gestor.correo);

			System.out.println("Quieres agregar otro gestor? (1) si quieres salir (0)");
			int eleccion = sc.nextInt();
			if (eleccion == 0) {
				break;
			}
		}
	}

	public void obtencionGestor(Connection conexion) throws SQLException {
		System.out.println("3. Obtención de un gestor");

		query = "SELECT id,usuario from gestor ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		int i = 1;
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getString(2));
			i++;
		}
		System.out.println("Introduce el id de usuario que quieres obtener: ");
		int select = sc.nextInt();
		query = "SELECT * FROM gestor where id = " + select;
		resultado = st.executeQuery(query);
		resultado.next();
		System.out.println(resultado.getInt(1) + " " + resultado.getString(2) + " " + resultado.getString(3) + " "
				+ resultado.getString(4));
	}

	public void obtencionTodos(Connection conexion) throws SQLException {
		System.out.println("4. Obtención de todos los gestores");
		query = "SELECT * FROM gestor ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " " + resultado.getString(2) + " " + resultado.getString(3) + " "
					+ resultado.getString(4));
		}
	}

	public void actualizarGestor(Connection conexion) throws SQLException {
		System.out.println("5. Actualización de un gestor");
		query = "Select id,usuario from gestor";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getString(2));
		}
		System.out.println("Elegir un usuario para actualizarlo : ");
		String select = sc.next();
		System.out.println("Cual es el usuario ? ");
		String usuario = sc.next();
		System.out.println("Cual es el Contraseña");
		String password = sc.next();
		System.out.println("Cual es el Correo electronico ?");
		String correo = sc.next();
		query = "UPDATE `gestor` SET `usuario` = '" + usuario + "', `password` = '" + password + "', `correo` = '"
				+ correo + "' WHERE `id` LIKE " + select + " ";
		st.execute(query);
	}

	public void eliminarGestor(Connection conexion) throws SQLException {
		String select;
		query = "Select id,usuario from gestor";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getString(2));
		}
		System.out.println("Elegir un usuario para eliminarlo : ");
		select = sc.next();
		query = "DELETE FROM `gestor` WHERE `gestor`.`id` = " + select + "  ";
		st.execute(query);
		System.out.println("Se ha eliminado el usuario seleccionado");
	}
}
