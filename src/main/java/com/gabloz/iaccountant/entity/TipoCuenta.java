package com.gabloz.iaccountant.entity;


import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class TipoCuenta {

	public static final String TIPO_CUENTA_ENTITY_NAME = "TipoCuenta";
	public static final String NOMBRE = "nombre";
	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	
	private Key key;
	private String nombre;
	private String descripcion;
	private String tipo;

	public TipoCuenta(Key key, String nombre, String descripcion, String tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.key = key;
	}
	
	/*
	 * Mirar si realmente este constructor es necesario
	 */
	public TipoCuenta( String nombre, String descripcion, String tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
	}	

	/**
	 * @return la representación JSON del objeto
	 */
	public String toJSON() {
		StringBuilder sB = new StringBuilder();

		sB.append("{\"id\":");
		sB.append("\"" + getKey() + "\"");
		sB.append(", \"descripcion\":");
		sB.append("\"" + getDescripcion() + "\"");
		sB.append(", \"tipo\": ");
		sB.append("\"" + getTipo() + "\"");
		sB.append(" }");

		return sB.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return toJSON();
	}	
	
	public Key getKey() {
		return key;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static TipoCuenta getFromEntity(Entity tipoCuentaEntity) {
		
		String nombre = (String) tipoCuentaEntity.getProperty(NOMBRE);
		String descripcion = (String) tipoCuentaEntity.getProperty(DESCRIPCION);
		String tipo = (String) tipoCuentaEntity.getProperty(TIPO);
		
		TipoCuenta tipoCuenta = new TipoCuenta(tipoCuentaEntity.getKey(),
				nombre, descripcion, tipo);
		
		return tipoCuenta;
	}

}
