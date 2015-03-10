

package com.blogspot.programmingheroes.db;


import java.util.List;

import android.R.integer;

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
	 * Guarda un contacto en el datastore.
	 * 
	 * @param contact Contacto a guardar.
	 * 
	 * @return Objeto Contact con los datos del usuario (el mismo objeto que
	 * se le pasa por parámetro).
	 */
	public static Contact save(Contact contact)
	{
		OfyService.ofy().save().entity(contact).now();
		
		return contact;
	}
	
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
	public static List<Contact> readAll()
	{
		Query<Contact> query = OfyService.ofy().load().type(Contact.class);
		return query.list();
	}
	
	/**
	 * Lee un usuario a partir de su GCM ID.
	 * 
	 * @param regId Id de GCM por el cual buscar al usuario.
	 * 
	 * @return Objeto Contact con los datos del usuario.
	 */
	public static Contact readByRegID(String regId)
	{
		Contact contact = null;
		List<Contact> contacts = OfyService.ofy().load().type(Contact.class)
				.filter("regid =", regId).list();
		int size = contacts.size();
		
		if (size > 1)
		{
			throw new Error("Duplicated regId.");
		}
		else if (size == 1)
		{
			contact = contacts.get(0);
		}
		
		return contact;
	}
	
	/**
	 * Borra un contacto determinado.
	 * 
	 * @param contact Contacto a borrar.
	 * 
	 */
	public static void delete(Contact contact)
	{
		OfyService.ofy().delete().type(Contact.class).id(contact.name).now();
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
