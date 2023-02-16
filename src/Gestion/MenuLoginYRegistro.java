package Gestion;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuLoginYRegistro {

	private Scanner sc = new Scanner(System.in);
	private ArrayList<Usuario> datos = new ArrayList<Usuario>();

	public static void mostrarMenu() {
		System.out.println("----- MENÚ LOGIN Y REGISTRO-----");
		System.out.println("1. Registrarse");
		System.out.println("2. Login");
		System.out.println("0. Volver");
		System.out.println("Elije una opción: ");
	}

	public void iniciarMenu() {
		this.mostrarMenu();
		int selectMenuLog = this.sc.nextInt();

		switch (selectMenuLog) {
		case 1:
			this.registrarse();
			iniciarMenu();
			break;
		case 2:
			this.login();
			iniciarMenu();
			break;
		case 0:
			System.out.println("Volviendo al menú principal");
			break;
		}
	}

	private void registrarse() {
		System.out.println("1. Registrarse");
		System.out.println("Introduzca el nombre de usuario");
		String nombreU = this.sc.next();
		System.out.println("Introduzca la contraseña:");
		String password = this.sc.next();

		Usuario usuario = new Usuario(nombreU, password);
		this.datos.add(usuario);
	}

	private void login() {
		System.out.println("2. Login");
		System.out.println("Introduzca el nombre de usuario");
		String nombreU = this.sc.next();
		System.out.println("Introduzca la contraseña:");
		String password = this.sc.next();

		for (int i = 0; i < datos.size(); i++) {
			if (nombreU.equals(this.datos.get(i).usuario) && password.equals(this.datos.get(i).password)) {
				System.out.println("Has iniciado sesión");
				return;
			}
		}

		System.out.println("Credenciales incorrectas");
	}

}
