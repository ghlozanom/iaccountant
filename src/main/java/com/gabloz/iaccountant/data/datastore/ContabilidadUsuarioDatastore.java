package com.gabloz.iaccountant.data.datastore;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.User;

public class ContabilidadUsuarioDatastore {
	
	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	public Entity getContabilidadUsuarioPorUsuario(User usuario){
		
		Query contabilidadUsuarioQuery = new Query("ContabilidadUsuario");
		contabilidadUsuarioQuery.setFilter(
				new Query.FilterPredicate("nicknameUsr", 
						Query.FilterOperator.EQUAL,
						usuario.getNickname()));
		
		PreparedQuery pqContabilidadUsuario = ds.prepare(contabilidadUsuarioQuery); 
		return pqContabilidadUsuario.asSingleEntity();
	}
	
	
}
