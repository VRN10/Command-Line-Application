package Gestion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MenuTransferencias {

	private Scanner sc = new Scanner(System.in);
	private ArrayList<Transferencia> datos = new ArrayList<Transferencia>();
	private Statement st;
	private ResultSet resultado;
	private String query;

	public static void mostrarMenu() {
		System.out.println("----- MENÚ TRANSFERENCIAS-----");
		System.out.println("1. Envío de una transferencia");
		System.out.println("2. Obtener una transferencia");
		System.out.println("3. Obtención de todas las transferencias");
		System.out.println("0. Volver");
		System.out.println("Elije una opción: ");
	}

	public void iniciarMenu(Connection conexion) throws SQLException {
		this.mostrarMenu();
		int selectMenuTr = this.sc.nextInt();

		switch (selectMenuTr) {
		case 1:
			this.insertar(conexion);
			iniciarMenu(conexion);
			break;
		case 2:
			this.obtencionTransferencia(conexion);
			iniciarMenu(conexion);
			break;
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
		System.out.println("Introduzca el id del ordenante");
		int idOrdenante = this.sc.nextInt();
		System.out.println("Introduzca el id del beneficiario");
		int idBeneficiario = this.sc.nextInt();
		System.out.println("Introduzca el importe");
		int importe = this.sc.nextInt();
		System.out.println("Introduzca el concepto");
		String concepto = this.sc.next();
		System.out.println("¿Qué día es hoy? (Coloque la fecha en el formato yyyyMMdd)");
		String fecha = this.sc.next();

		st = conexion.createStatement();
		query = "INSERT INTO `transferencia` (`id`, `id_ordenante`, `id_beneficiario`, `importe`, `concepto`, `fecha`)  VALUES (null,'" + idOrdenante +
				"','" + idBeneficiario + "','" + importe + "','" + concepto + "','" + fecha + "')";
		st.execute(query);

		Transferencia transferencias = new Transferencia(idOrdenante, idBeneficiario, importe, concepto, fecha);
		this.datos.add(transferencias);
		System.out
				.println("Se ha creado una nueva transferencia\n" + "ID: " + transferencias.id + "\n" + "IdOrdenante: "
						+ transferencias.idOrdenante + "\n" + "IdBeneficiario: " + transferencias.idBeneficiario + "\n"
						+ "Importe: " + transferencias.importe + "\n" + "Concepto: " + transferencias.concepto + "Fecha: " + transferencias.fecha);
	}
	public void obtencionTransferencia(Connection conexion) throws SQLException {
		System.out.println("2. Obtener una transferencia");
		query="SELECT `id`,`importe` FROM `transferencia`; ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		int i = 1;
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getInt(2));
			i++;
		}
		System.out.println("¿Cuál es el id de transferencia que desea obtener?");
		int eleccion = this.sc.nextInt();
		System.out.println(eleccion);
		query = "SELECT * FROM `transferencia` WHERE id = " + eleccion;
		resultado = st.executeQuery(query);
		resultado.next();
		System.out.println(resultado.getInt(1) + " " + resultado.getInt(2) + " " + resultado.getString(3) + " "
				+ resultado.getString(4));

	}

	public void obtencionTodos(Connection conexion) throws SQLException {
		System.out.println("3. Obtención de todas las transferencias");
		query = "SELECT * FROM transferencia ";
		st = conexion.createStatement();
		resultado = st.executeQuery(query);
		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " " + resultado.getInt(2) + " " + resultado.getString(3) + " "
					+ resultado.getString(4));
		}
	}
}
