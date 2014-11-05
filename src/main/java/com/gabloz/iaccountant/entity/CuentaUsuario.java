package com.gabloz.iaccountant.entity;

import com.gabloz.iaccountant.util.Constantes;
import com.gabloz.iaccountant.util.Util;
import com.google.appengine.api.datastore.Key;

public class CuentaUsuario {

	private String cuentaUsuarioKey;

	private double saldo;
	private Key contabilidadUsuarioKey;
	private Key tipoCuentaKey;
	private TipoCuenta tipoCuenta;
	private ContabilidadUsuario contabilidadUsuario;

	/**
	 * Construye un JSON a partir de los valores de las propiedades del objeto.
	 * 
	 * @return la representación JSON
	 */
	public String toJSON() {
		StringBuilder sB = new StringBuilder();

		sB.append("{\"id\":");
		sB.append("\"" + getCuentaUsuarioKey() + "\"");
		sB.append(", \"saldo\":");
		sB.append("\"" + Util.numberToString(getSaldo(), Constantes.FORMATO_NUM_ESTANDAR) + "\"");
		sB.append(", \"contabilidadUsuarioKey\": ");
		sB.append("\"" + getContabilidadUsuarioKey() + "\"");
		sB.append(", \"contabilidadUsuario\": ");
		sB.append("\"" + getContabilidadUsuario() + "\"");		
		sB.append(", \"tipoCuenta\": ");
		sB.append("\"" + getTipoCuenta() + "\"");
		sB.append(" }");

		return sB.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return toJSON();
	}	

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Key getContabilidadUsuarioKey() {
		return contabilidadUsuarioKey;
	}

	public void setContabilidadUsuarioKey(Key contabilidadUsuarioKey) {
		this.contabilidadUsuarioKey = contabilidadUsuarioKey;
	}

	public Key getTipoCuentaKey() {
		return tipoCuentaKey;
	}

	public void setTipoCuentaKey(Key tipoCuentaKey) {
		this.tipoCuentaKey = tipoCuentaKey;
	}

	public String getCuentaUsuarioKey() {
		return cuentaUsuarioKey;
	}

	public void setCuentaUsuarioKey(String userIdTipoCuenta) {
		this.cuentaUsuarioKey = userIdTipoCuenta;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public ContabilidadUsuario getContabilidadUsuario() {
		return contabilidadUsuario;
	}

	public void setContabilidadUsuario(ContabilidadUsuario contabilidadUsuario) {
		this.contabilidadUsuario = contabilidadUsuario;
	}

}
