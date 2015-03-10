

package com.blogspot.programmingheroes.db;


import com.blogspot.programmingheroes.gcm.Contact;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;


/**
 * Esta clase garantiza que las entidades son registradas antes de usar los
 * métodos de Objectify. Además, no impacta de forma negativa a peticiones
 * que no hacen uso del acceso al datastore.
 * 
 * @see https://code.google.com/p/objectify-appengine/wiki/BestPractices
 * 
 * @author ProgrammingHeroes
 *
 */
public class OfyService
{
	
    static
    {
    	/*
    	 * Registra aquí todas las clases de las que
    	 * quieras guardar objetos en el datastore
    	 * usando la librería Objectify.
    	 */
        factory().register(Contact.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

	public static ObjectifyFactory factory()
	{
		return ObjectifyService.factory();
	}
	
}
