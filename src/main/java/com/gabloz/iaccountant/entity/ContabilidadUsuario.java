package com.gabloz.iaccountant.entity;


import com.gabloz.iaccountant.util.Constantes;
import com.gabloz.iaccountant.util.Util;
import com.google.appengine.api.datastore.Key;

public class ContabilidadUsuario {

	private Key key;

	public static String CONTABILIDAD_USUARIO_ENTITY_NAME = "ContabilidadUsuario";
	public static final String ANO = "anio";
	public static final String NICKNAME_USR = "nicknameUsr";
	public static final String TOTAL_ACTIVOS = "totalActivos";
	public static final String TOTAL_PASIVOS = "totalPasivos";
	public static final String TOTAL_PATRIMONIO = "totalPatrimonio";
	
	private String nicknameUsr;
	private double totalActivos;
	private double totalPasivos;
	private double totalPatrimonio;
	private int anio;
	
	/**
	 * Construye un JSON a partir de los valores de las propiedades del objeto.
	 * 
	 * @return la representación JSON
	 */
	public String toJSON() {
		StringBuilder sB = new StringBuilder();

		sB.append("{\"totalActivos\":");
		sB.append("\"" + Util.numberToString(getTotalActivos(), Constantes.FORMATO_NUM_ESTANDAR) + "\"");
		sB.append(", \"totalPasivos\":");
		sB.append("\"" + Util.numberToString(getTotalPasivos(), Constantes.FORMATO_NUM_ESTANDAR) + "\"");
		sB.append(", \"totalPatrimonio\": ");
		sB.append("\"" + Util.numberToString(getTotalPatrimonio(), Constantes.FORMATO_NUM_ESTANDAR) + "\"");
		sB.append(", \"nicknameUsr\": ");
		sB.append("\"" + getNicknameUsr() + "\"");
		sB.append(" }");

		return sB.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return toJSON();
	}	

	public ContabilidadUsuario(String nicknameUsr) {
		this.nicknameUsr = nicknameUsr;
	}

	public ContabilidadUsuario(String nicknameUsr, int anio) {
		this.nicknameUsr = nicknameUsr;
		this.anio = anio;
	}

	public double getTotalActivos() {
		return totalActivos;
	}

	public void setTotalActivos(double totalActivos) {
		this.totalActivos = totalActivos;
	}

	public double getTotalPasivos() {
		return totalPasivos;
	}

	public void setTotalPasivos(double totalPasivos) {
		this.totalPasivos = totalPasivos;
	}

	public double getTotalPatrimonio() {
		return totalPatrimonio;
	}

	public void setTotalPatrimonio(double totalPatrimonio) {
		this.totalPatrimonio = totalPatrimonio;
	}

	public String getNicknameUsr() {
		return nicknameUsr;
	}

	public void setNicknameUsr(String nicknameUsr) {
		this.nicknameUsr = nicknameUsr;
	}

	public Key getKey() {
		return key;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

}
