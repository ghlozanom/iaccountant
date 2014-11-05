package com.gabloz.iaccountant.entity;

import com.google.appengine.api.datastore.Key;

public class Operacion {

    private Key key;
    private String tipo;
    private String cuentaUsuarioKey;
    private CuentaUsuario cuentaUsuario; 
    
	/**
	 * Construye un JSON a partir de los valores de las propiedades del objeto.
	 * 
	 * @return la representación JSON
	 */
	public String toJSON() {
		StringBuilder sB = new StringBuilder();

		sB.append("{\"id\":");
		sB.append("\"" + key + "\"");
		sB.append(", \"tipo\":");
		sB.append("\"" + getTipo() + "\"");
		sB.append(", \"cuentaUsuarioKey\": ");
		sB.append("\"" + getCuentaUsuarioKey() + "\"");	
		sB.append(", \"cuentaUsuario\": ");
		sB.append("\"" + getCuentaUsuario() + "\"");			
		sB.append(" }");

		return sB.toString();
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return toJSON();
	}	    

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCuentaUsuarioKey() {
		return cuentaUsuarioKey;
	}

	public void setCuentaUsuarioKey(String cuentaUsuario) {
		this.cuentaUsuarioKey = cuentaUsuario;
	}

	public Key getKey() {
		return key;
	}

	public CuentaUsuario getCuentaUsuario() {
		return cuentaUsuario;
	}

	public void setCuentaUsuario(CuentaUsuario cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
	}
    
}
