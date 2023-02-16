package Gestion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuClientes {

	private Scanner sc = new Scanner(System.in);
	private ArrayList<Cliente> datos = new ArrayList<Cliente>();
	private Statement st;
	private ResultSet resultado;
	private String query;

	public static void mostrarMenu() {
		System.out.println("----- MENÚ CLIENTES-----");
		System.out.println("1. Inserción de un cliente");
		System.out.println("2. Obtención de un cliente");
		System.out.println("3. Obtención de todos los clientes");
		System.out.println("4. Actualización de un cliente");
		System.out.println("5. Eliminación de un cliente");
		System.out.println("0. Volver");
		System.out.println("Elije una opción: ");
	}

	public void iniciarMenu(Connection conexion) throws Exception {
		this.mostrarMenu();
		int selectMenuCl = this.sc.nextInt();

		switch (selectMenuCl) {
		case 1:
			this.insertar(conexion);
			iniciarMenu(conexion);
			break;
		case 2:
			this.obtencionCliente(conexion);
			iniciarMenu(conexion);
			break;
		case 3:
			this.obtencionTodos(conexion);
			iniciarMenu(conexion);
			break;
		case 4:
			this.actualizarCliente(conexion);
			iniciarMenu(conexion);
			break;
		case 5:
			this.eliminarCliente(conexion);
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
		System.out.println("Introduzca el idGestor que tiene");
		int id_gestor = this.sc.nextInt();
		System.out.println("Introduzca el saldo que tiene");
		String saldo = this.sc.next();

		st = conexion.createStatement();
		query="INSERT INTO `cliente` (`id`, `id_gestor`, `usuario`, `password`, `correo`, `saldo`) VALUES "
				+ "(NULL, '" + id_gestor + "', '" + usuario +"', '"+ password +"', '"+ correo +"', '"+ saldo +"'); ";
		st.execute(query);

		Cliente cliente = new Cliente(usuario, password, correo, id_gestor, saldo);
		this.datos.add(cliente);
		System.out.println("Se ha creado un nuevo cliente\n" + "Usuario: " + cliente.usuario + "\n" + "Contraseña: "
				+ cliente.password + "\n" + "Correo: " + cliente.correo + "Correo: " + cliente.id_gestor + "Correo: "
				+ cliente.saldo);
	}

	public void obtencionCliente(Connection conexion) throws SQLException {

		System.out.println("2. Obtención de un cliente");

		query = "SELECT id,usuario from cliente ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		int i = 1;
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getString(2));
			i++;
		}
		System.out.println("Introduce el id de usuario que quieres obtener: ");
		int select = sc.nextInt();
		query = "SELECT * FROM cliente where id = " + select;
		resultado = st.executeQuery(query);
		resultado.next();
		System.out.println(resultado.getInt(1) + " " + resultado.getInt(2) + " " + resultado.getString(3) + " "
				+ resultado.getString(4) + " " + resultado.getString(5) + " " + resultado.getString(6));
	}

	public void obtencionTodos(Connection conexion) throws SQLException {

		System.out.println("3. Obtención de todos los clientes");
		query = "SELECT * FROM cliente ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " " + resultado.getInt(2) + " " + resultado.getString(3) + " "
					+ resultado.getString(4) + " " + resultado.getString(5) + " " + resultado.getString(6));
		}
	}

	public void actualizarCliente(Connection conexion) throws SQLException {
		System.out.println("4. Actualización de un cliente");
		query = "Select id,usuario from cliente";
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
		System.out.println("Introduzca el idGestor que tiene");
		int id_gestor = this.sc.nextInt();
		System.out.println("Introduzca el saldo que tiene");
		String saldo = this.sc.next();
		query = "UPDATE `cliente` SET `usuario` = '" + usuario + "', `password` = '" + password + "', `correo` = '"
				+ correo + "', `id_gestor` = '" + id_gestor + "', `saldo` = '" + saldo + "' WHERE `id` LIKE " + select + " ";
		st.execute(query);
		System.out.println("Se ha actualizado el cliente");
	}

	public void eliminarCliente(Connection conexion) throws SQLException {
		String select;
		query = "Select id,usuario from cliente";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getString(2));
		}
		System.out.println("Elegir un id de usuario para eliminarlo : ");
		select = sc.next();
		query = "DELETE FROM `cliente` WHERE `cliente`.`id` = " + select + "  ";
		st.execute(query);
		System.out.println("Se ha eliminado el usuario seleccionado");
	}
}
