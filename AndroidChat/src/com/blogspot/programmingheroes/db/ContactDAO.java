

package com.blogspot.programmingheroes.db;

import java.util.List;

import com.blogspot.programmingheroes.aigames.entities.Profile;
import com.blogspot.programmingheroes.aigames.util.OfyService;
import com.google.api.server.spi.response.ConflictException;
import com.googlecode.objectify.cmd.Query;


/**
 * Data Access Object para los contactos.
 * 
 * @author ProgrammingHeroes
 *
 */
public class ContactDAO
{

	/**
	 * Lee un usuario a partir de su nombre.
	 * 
	 * @param name Nombre que identifica al usuario.
	 * 
	 * @return Objeto Contact con los datos del usuario.
	 */
	public static Contact read(String name)
	{
		Contact contact =
				OfyService.ofy().load().type(Contact.class).id(name).now();
		
		return contact;
	}
	
	/**
	 * Devuelve una lista con todos los contactos de la aplicación.
	 * 
	 * @return Todos los contactos en una lista.
	 */
	public List<Contact> getAllContacts()
	{
		Query<Contact> query = OfyService.ofy().load().type(Contact.class);
		return query.list();
	}
	
	/**
	 * Comprueba que el nombre esté disponible para ser usado por un nuevo
	 * usuario.
	 * 
	 * @param name Nombre que identifica al usuario.
	 * 
	 * @return True si el nombre de usuario es único.
	 */
	public static boolean checkUniqueName(String name)
	{
		int nContactsWithName = OfyService.ofy().load().type(Contact.class)
				.filter("name =", name).count();
		
		return (nContactsWithName == 0);
	}
	
}
