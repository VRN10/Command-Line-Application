package Gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

	public static void mostrarMenu() {
		System.out.println("----- MENÚ-----");
		System.out.println("1. Gestores");
		System.out.println("2. Clientes");
		System.out.println("3. Mensajes");
		System.out.println("4. Transferencias");
		System.out.println("5. Login");
		System.out.println("0. Salir");
		System.out.println("Elije una opción: ");
	}
	
	public static void main(String[] args) throws Exception {

		boolean salir = false;
		Scanner sc = new Scanner(System.in);
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "banco", "banco");
		
		MenuGestores menuGestor = new MenuGestores();
		MenuClientes menuCliente = new MenuClientes();
		MenuMensajes menuMensajes = new MenuMensajes();
		MenuTransferencias menuTransferencias = new MenuTransferencias();
		MenuLoginYRegistro menuLogin = new MenuLoginYRegistro();
		
		while (!salir) {
			mostrarMenu();
			int opcion = sc.nextInt();
			
			switch (opcion) {

			case 1:
				menuGestor.iniciarMenu(conexion);
				break;
			case 2:
				menuCliente.iniciarMenu(conexion);
				break;
			case 3:
				menuMensajes.iniciarMenu(conexion);
				break;
			case 4:
				menuTransferencias.iniciarMenu(conexion);
				break;
			case 5:
				menuLogin.iniciarMenu();
				break;
			case 0:
				salir = true;
				System.out.println("Saliendo del programa");
				break;

			default:
				System.out.println("La opción no es válida");
				break;

			}
		}
	}
}
