package com.gabloz.iaccountant.bo;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gabloz.appengine.api.datastore.ContabilidadUsuarioDatastore;
import com.gabloz.iaccountant.entity.ContabilidadUsuario;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class ContabilidadUsuarioBO {

	private static final Logger logger = Logger
			.getLogger(ContabilidadUsuarioBO.class.getCanonicalName());

	/**
	 * Crea ó actualiza una ContabilidadUsuario.
	 * 
	 * @param contabilidadUsuario
	 */
	public void createOrUpdate(ContabilidadUsuario contabilidadUsuario) {

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity contabilidadUsuarioEntity = new Entity(ContabilidadUsuario.CONTABILIDAD_USUARIO_ENTITY_NAME);
		contabilidadUsuarioEntity.setProperty(ContabilidadUsuario.ANO, contabilidadUsuario.getAnio());
		contabilidadUsuarioEntity.setProperty(ContabilidadUsuario.NICKNAME_USR, contabilidadUsuario.getNicknameUsr());
		contabilidadUsuarioEntity.setProperty(ContabilidadUsuario.TOTAL_ACTIVOS, contabilidadUsuario.getTotalActivos());
		contabilidadUsuarioEntity.setProperty(ContabilidadUsuario.TOTAL_PASIVOS, contabilidadUsuario.getTotalActivos());
		contabilidadUsuarioEntity.setProperty(ContabilidadUsuario.TOTAL_PATRIMONIO, contabilidadUsuario.getTotalPatrimonio());
		
		ds.put(contabilidadUsuarioEntity);
	}

	public ContabilidadUsuario creatIfNotExistByUser(ContabilidadUsuario contabilidadUsuario) {

		ContabilidadUsuario contabilidadUsuarioByNicknameUsr = getContabilidadUsuarioForUser(contabilidadUsuario.getNicknameUsr());
		
		if( contabilidadUsuarioByNicknameUsr == null ){	
			logger.log(Level.INFO, "Usuario "+ contabilidadUsuario.getNicknameUsr() +" no encontrado. Se creara. ");
			createOrUpdate(contabilidadUsuario);
		}
		
		return contabilidadUsuario;
	}	
	
	private ContabilidadUsuario getContabilidadUsuarioForUser(String nicknameUsr) {
		
		ContabilidadUsuarioDatastore contabilidadUsuarioDatastore = new ContabilidadUsuarioDatastore();
		Entity contabilidadUsuarioEntity = contabilidadUsuarioDatastore.getContabilidadUsuarioPorUsuario(nicknameUsr);
		ContabilidadUsuario contabilidadUsuarioPorNicknameUsr = ContabilidadUsuarioDatastore.getFromEntity(contabilidadUsuarioEntity);
		
		return contabilidadUsuarioPorNicknameUsr;
	}

	/**
	 * Crea una ContabilidadUsuario si esta no existe.
	 * 
	 * @param contabilidadUsuario
	 */
	public ContabilidadUsuario creatIfNotExist(ContabilidadUsuario contabilidadUsuario) {

		ContabilidadUsuario contabilidadUsuarioTemp = findByKey(contabilidadUsuario.getKey());
			
		if( contabilidadUsuarioTemp == null ){
			
			createOrUpdate(contabilidadUsuario);
			contabilidadUsuarioTemp = findByKey(contabilidadUsuario.getKey());
		}
		
		return contabilidadUsuarioTemp;
	}	
	
	/**
	 * Borra un contabilidadUsuario.
	 * 
	 * @param contabilidadUsuario
	 */
	public void delete(ContabilidadUsuario contabilidadUsuario) {
		
		ContabilidadUsuarioDatastore contabilidadUsuarioDatastore = new ContabilidadUsuarioDatastore();
		contabilidadUsuarioDatastore.delete(contabilidadUsuario);
	}

	public Collection<ContabilidadUsuario> find( ContabilidadUsuario contabilidadUsuario){
		
		ContabilidadUsuarioDatastore contabilidadUsuarioDatastore = new ContabilidadUsuarioDatastore();
		
		Collection<ContabilidadUsuario> contabilidadesUsuario =
				contabilidadUsuarioDatastore.queryContabilidadUsuario(contabilidadUsuario);
		
		return contabilidadesUsuario;
	}
	
	/**
	 * Retorna la contabilidad de un usuario. 
	 * TODO Revisar que asume que el usuario solo tiene una contabilidad.
	 * 
	 * @param usuario
	 */
	public ContabilidadUsuario findByUsuario(String usuario) {

		ContabilidadUsuario contabilidadUsuario = new ContabilidadUsuario(usuario); 

		Collection<ContabilidadUsuario> contabilidadUsuarioList = find(contabilidadUsuario);
		
		ContabilidadUsuario contabilidadUsuarioReturned = new ContabilidadUsuario(usuario);
		if( !contabilidadUsuarioList.isEmpty() ){
			contabilidadUsuarioReturned = contabilidadUsuarioList.iterator().next();
		}
		
		return contabilidadUsuarioReturned;
	}	

	public ContabilidadUsuario findByKey(Key key) {
		
		if( key == null ){
			return null;
		}
		
		ContabilidadUsuarioDatastore contabilidadUsuarioDatastore = new ContabilidadUsuarioDatastore();
		ContabilidadUsuario contabilidadUsuario = contabilidadUsuarioDatastore.findByKey(key);
		
		return contabilidadUsuario;
	}


	public Collection<ContabilidadUsuario> findAll() {
		
		ContabilidadUsuarioDatastore contabilidadUsuarioDatastore = new ContabilidadUsuarioDatastore();
		Collection<ContabilidadUsuario> contabilidadUsuarioList = 
				contabilidadUsuarioDatastore.getAllContabilidadUsuario();

		return contabilidadUsuarioList;
	}

}
