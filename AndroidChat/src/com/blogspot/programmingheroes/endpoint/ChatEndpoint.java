

package com.blogspot.programmingheroes.endpoint;


import java.util.List;

import com.blogspot.programmingheroes.Constants;
import com.blogspot.programmingheroes.db.OfyService;
import com.blogspot.programmingheroes.db.Contact;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.cmd.Query;


/**
 * API de un sistema de chat. Los usuarios deberán de darse de alta en primer
 * lugar con un nombre único y su 
 * @author gazpacho4you
 *
 */
@Api(title = "chatEndpoint",
	 name = "chatendpoint",
	 description = "Endpoint para un sistema de chat.",
	 version = "v1",
	 //scopes = {Constants.EMAIL_SCOPE},
	 clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID})

public class ChatEndpoint
{
	
	/**
	 * Obtiene todos los contactos que se encuentran registrados en el
	 * datastore.
	 * 
	 * @return Todos los contactos.
	 * 
	 */
	@ApiMethod(name = "getAllContacts",
	 		   path = "getallcontacts",
	 		   httpMethod = HttpMethod.GET)
		
	public List<Contact> getAllContacts()
	{
		Query<Contact> query =
				OfyService.ofy().load().type(Contact.class).order("date");
		return query.list();
	}
	
	
	@ApiMethod(name = "createContact",
	 		   path = "createcontact",
	 		   httpMethod = HttpMethod.POST)
		
	public Contact create(@Named("name") String name,
						  @Named("regid") String regId)
	{
		Contact contact = new Contact(name, regId);
		
		OfyService.ofy().save().entity(contact).now();
		
		return contact;
	}
	
	
	@ApiMethod(name = "delete",
	 		   path = "delete",
	 		   httpMethod = HttpMethod.GET)
		
	public void delete(@Named("id") Long id)
	{
		//Result<Void> result = 
		OfyService.ofy().delete().type(Contact.class).id(id);
		
		return;
	}
	
}
