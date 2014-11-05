package com.gabloz.iaccountant.bo;

import java.util.ArrayList;
import java.util.List;

import com.gabloz.iaccountant.entity.TipoCuenta;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TipoCuentaBO {

	/**
	 * Crea ó actualiza un tipo cuenta.
	 * 
	 * @param tipoCuenta
	 */
	public void createOrUpdate(TipoCuenta tipoCuenta) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity tipoCuentaEntity = new Entity(TipoCuenta.TIPO_CUENTA_ENTITY_NAME);
		tipoCuentaEntity.setProperty(TipoCuenta.NOMBRE, tipoCuenta.getNombre());
		tipoCuentaEntity.setProperty(TipoCuenta.DESCRIPCION, tipoCuenta.getDescripcion());
		tipoCuentaEntity.setProperty(TipoCuenta.TIPO, tipoCuenta.getTipo());
		
		ds.put(tipoCuentaEntity);

	}

	/**
	 * Retorna un objeto tipoCuenta encontrado ó en caso de no hallarlo, el que
	 * crea de acuerdo a lo que viendo en los parámetros
	 * 
	 * @param TipoCuenta
	 *            los parametros de busqueda
	 * @return un objeto TipoCuenta
	 */
	public TipoCuenta createIfNotExistByNombre(TipoCuenta tipoCuenta) {

		//Query por nombre de tipo de cuenta
		TipoCuenta tipoCuentaInDataStore = getTipoCuentaByNombre(tipoCuenta.getNombre());
		if(tipoCuentaInDataStore == null){
			createOrUpdate(tipoCuenta);
		}

		return tipoCuenta;

	}

	/**
	 * Borra un tipo cuenta.
	 * 
	 * @param tipoCuenta
	 */
	public void delete(TipoCuenta tipoCuenta) {

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.delete(tipoCuenta.getKey());

	}

	/**
	 * Busca un tipoCuenta por la clave.
	 * 
	 * @param key
	 */
	public TipoCuenta findByKey(long id) {
			
		try {
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			
			Entity tipoCuentaEntity = ds.get(KeyFactory.createKey(TipoCuenta.TIPO_CUENTA_ENTITY_NAME, id));
			TipoCuenta tipoCuenta = TipoCuenta.getFromEntity(tipoCuentaEntity);
			
			return tipoCuenta;			
		} catch (EntityNotFoundException e) {
			return null;
		}
		


	}

	/**
	 * Busca un tipoCuenta por la clave.
	 * 
	 * @param key
	 */
	public List<TipoCuenta> findAll() {

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(TipoCuenta.TIPO_CUENTA_ENTITY_NAME);
		q.addSort(TipoCuenta.NOMBRE);
		
		PreparedQuery pq = ds.prepare(q);
		
		List<TipoCuenta> tiposCuenta = new ArrayList<>();
		for(Entity tipoCuentaEntity : pq.asIterable() ){
			TipoCuenta tipoCuenta = TipoCuenta.getFromEntity(tipoCuentaEntity);
			tiposCuenta.add(tipoCuenta);
		}
		
		return tiposCuenta;
	}
	
	public String getIdCuenta(String nombreCuenta){
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("TipoCuenta");
		q.setFilter(new Query.FilterPredicate("nombre",
				Query.FilterOperator.EQUAL,
				nombreCuenta));
		
		PreparedQuery pq = ds.prepare(q);
		Entity cuenta = pq.asSingleEntity();
		
		if(cuenta == null) {
			return "";
		}
		
		return "" + cuenta.getKey().getId();
	}
	
	public TipoCuenta getTipoCuentaByNombre(String nombreCuenta){
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("TipoCuenta");
		q.setFilter(new Query.FilterPredicate("nombre",
				Query.FilterOperator.EQUAL,
				nombreCuenta));
		
		PreparedQuery pq = ds.prepare(q);
		Entity tipoCuentaEntity = pq.asSingleEntity();
		
		if( tipoCuentaEntity != null ){
			return null;
		}
		
		TipoCuenta tipoCuenta = TipoCuenta.getFromEntity(tipoCuentaEntity);
		return tipoCuenta;
	}	

}
