package com.gabloz.appengine.api.datastore;

import java.util.ArrayList;
import java.util.Collection;

import com.gabloz.iaccountant.entity.ContabilidadUsuario;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.sun.istack.internal.Nullable;

public class ContabilidadUsuarioDatastore {
	
	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	public Entity getContabilidadUsuarioPorUsuario(String nicknameUsr){
		
		Query contabilidadUsuarioQuery = new Query("ContabilidadUsuario");
		contabilidadUsuarioQuery.setFilter(
				new Query.FilterPredicate("nicknameUsr", 
						Query.FilterOperator.EQUAL,
						nicknameUsr));
		
		PreparedQuery pqContabilidadUsuario = ds.prepare(contabilidadUsuarioQuery); 
		return pqContabilidadUsuario.asSingleEntity();
	}
	
	public Collection<ContabilidadUsuario> queryContabilidadUsuario(ContabilidadUsuario contabilidadUsuarioToQuery){
		
		Collection<ContabilidadUsuario> contabilidadesUsuario = null;
		
		if(contabilidadUsuarioToQuery.getNicknameUsr() != null ){
			
			ContabilidadUsuario contabilidadUsuarioByNicknameAndAnio = 
					getContabilidadByNicknameAndAnio(contabilidadUsuarioToQuery);
			if(contabilidadUsuarioByNicknameAndAnio == null){
				return null;
			}
			contabilidadesUsuario= new ArrayList<ContabilidadUsuario>();
			contabilidadesUsuario.add(contabilidadUsuarioByNicknameAndAnio);
			return contabilidadesUsuario;
			
		}else if(contabilidadUsuarioToQuery.getAnio() != 0){
			contabilidadesUsuario = getContabilidadesByAnio(contabilidadUsuarioToQuery);
		}
		return contabilidadesUsuario;
	}	

	private Collection<ContabilidadUsuario> getContabilidadesByAnio(
			ContabilidadUsuario contabilidadUsuarioToQuery) {
		Query contabilidadUsuarioQuery = new Query(ContabilidadUsuario.CONTABILIDAD_USUARIO_ENTITY_NAME);
		contabilidadUsuarioQuery.setFilter(
				new Query.FilterPredicate(ContabilidadUsuario.ANO, 
						Query.FilterOperator.EQUAL,
						contabilidadUsuarioToQuery.getAnio()));
		
		Collection<ContabilidadUsuario> contabilidadesUsuario= null;
		PreparedQuery pqContabilidadUsuario = ds.prepare(contabilidadUsuarioQuery); 
		for(Entity result : pqContabilidadUsuario.asIterable() ){
			if(contabilidadesUsuario == null ){
				contabilidadesUsuario = new ArrayList<ContabilidadUsuario>();
			}
			ContabilidadUsuario contabilidadUsuario = getFromEntity(result);
			contabilidadesUsuario.add(contabilidadUsuario);
		}
		
		return contabilidadesUsuario;
	}

	private ContabilidadUsuario getContabilidadByNicknameAndAnio(
			ContabilidadUsuario contabilidadUsuarioToQuery) {
		
		Entity contabilidadUsuarioEntity = getContabilidadUsuarioPorUsuario(contabilidadUsuarioToQuery.getNicknameUsr());
		ContabilidadUsuario contabilidadUsuario = getFromEntity(contabilidadUsuarioEntity);
		if(contabilidadUsuario.getAnio() != contabilidadUsuarioToQuery.getAnio()){
			return null;
		}
		return contabilidadUsuario;
	}

	public static ContabilidadUsuario getFromEntity(Entity contabilidadUsuarioEntity) {
		
		String nicknameUsr = (String) contabilidadUsuarioEntity.getProperty(ContabilidadUsuario.ANO);
		int ano = (int) contabilidadUsuarioEntity.getProperty(ContabilidadUsuario.ANO);
		double totalActivos = (double) contabilidadUsuarioEntity.getProperty(ContabilidadUsuario.TOTAL_ACTIVOS);
		double totalPasivos = (double) contabilidadUsuarioEntity.getProperty(ContabilidadUsuario.TOTAL_PASIVOS);
		double totalPatrimonio = (double) contabilidadUsuarioEntity.getProperty(ContabilidadUsuario.TOTAL_PATRIMONIO);
		
		ContabilidadUsuario contabilidadUsuario = new ContabilidadUsuario(nicknameUsr);
		contabilidadUsuario.setAnio(ano);
		contabilidadUsuario.setTotalActivos(totalActivos);
		contabilidadUsuario.setTotalPasivos(totalPasivos);
		contabilidadUsuario.setTotalPatrimonio(totalPatrimonio);
		
		return contabilidadUsuario;
	}
	
	public Collection<ContabilidadUsuario> getAllContabilidadUsuario() {
		
		Query contabilidadUsuarioQuery = new Query(ContabilidadUsuario.CONTABILIDAD_USUARIO_ENTITY_NAME);
		Collection<ContabilidadUsuario> contabilidadesUsuario= null;
		PreparedQuery pqContabilidadUsuario = ds.prepare(contabilidadUsuarioQuery); 
		for(Entity result : pqContabilidadUsuario.asIterable() ){
			if(contabilidadesUsuario == null ){
				contabilidadesUsuario = new ArrayList<ContabilidadUsuario>();
			}
			ContabilidadUsuario contabilidadUsuario = getFromEntity(result);
			contabilidadesUsuario.add(contabilidadUsuario);
		}
		
		return contabilidadesUsuario;
	}	
	
	public void delete(ContabilidadUsuario contabilidadUsuario){
		ds.delete(contabilidadUsuario.getKey());
	}

	@Nullable
	public ContabilidadUsuario findByKey(Key key) {
		
		Entity contabiilidadUsuarioEntity;
		try {
			contabiilidadUsuarioEntity = ds.get(key);
			ContabilidadUsuario contabilidadUsuario = getFromEntity(contabiilidadUsuarioEntity);
			
			return contabilidadUsuario;
			
		} catch (EntityNotFoundException e) {
			return null;
		}

		
	}
	
	
}
