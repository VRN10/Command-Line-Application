package Gestion;

import java.util.Date;

public class Transferencia {

	private static int contadorTransfers = 1;

	public int id;
	public int idOrdenante;
	public int idBeneficiario;
	public int importe;
	public String concepto;
	public String fecha;

	public Transferencia(int idOrdenante, int idBeneficiario, int importe, String concepto, String fecha ) {
		this.id = contadorTransfers++;
		this.idOrdenante = idOrdenante;
		this.idBeneficiario = idBeneficiario;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
	}
}
