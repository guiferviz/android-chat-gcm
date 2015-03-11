

package com.blogspot.programmingheroes.endpoint;


import java.util.Date;
import java.util.List;

import android.os.Message;

import com.blogspot.programmingheroes.db.ContactDAO;
import com.blogspot.programmingheroes.db.Contact;
import com.google.android.gcm.server.Sender;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.UnauthorizedException;


/**
 * API de un sistema de chat. Los usuarios deberán de darse de alta en primer
 * lugar con un nombre único y su id para poder ser contactados mediante GCM.
 * 
 * @author ProgrammingHeroes
 *
 */
@Api(title = ConstantsAPI.TITLE,
	 name = ConstantsAPI.NAME,
	 description = ConstantsAPI.DESCRIPTION,
	 version = ConstantsAPI.VERSION,
	 namespace = @ApiNamespace(
			 ownerDomain = ConstantsAPI.NAMESPACE_OWNER_DOMAIN,
			 ownerName = ConstantsAPI.NAMESPACE_OWNER_NAME,
			 packagePath = ConstantsAPI.NAMESPACE_PACKAGE_PATH
	 ),
	 clientIds =
 	 {
		 ConstantsAPI.WEB_CLIENT_ID,
		 ConstantsAPI.ANDROID_CLIENT_ID,
		 ConstantsAPI.IOS_CLIENT_ID,
		 ConstantsAPI.API_EXPLORER_CLIENT_ID
	 },
	 audiences =
 	 {
		 ConstantsAPI.ANDROID_AUDIENCE
	 },
	 scopes =
 	 {
		 ConstantsAPI.EMAIL_SCOPE
	 })

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
		return ContactDAO.readAll();
	}
	
	
	/**
	 * Añade un nuevo contacto a nuestra base de datos.
	 * Comprueba si el nombre es único, si no lo es se lanza ConflictException.
	 * En el caso de que regId no sea único, se cambiará el nombre del usuario
	 * que ya existe con ese regId por el nuevo nombre indicado.
	 * 
	 * @param name Nombre único del contacto.
	 * @param regId Id de GCM.
	 * 
	 * @return El objeto Contact creado.
	 * @throws ConflictException Si ya existe un usurio con el mismo nombre.
	 */
	@ApiMethod(name = "createContact",
	 		   path = "createcontact",
	 		   httpMethod = HttpMethod.POST)
		
	public Contact create(@Named("name") String name,
						  @Named("regid") String regId) throws ConflictException
	{
		Contact contact = new Contact(name, regId);
		
		if (ContactDAO.checkUniqueName(name))
		{
			Contact contact2 = ContactDAO.readByRegID(regId);
			
			if (contact2 != null)
			{
				// Único nombre, regId duplicado (cambio de nombre)
				ContactDAO.delete(contact2);
			}
			
			ContactDAO.save(contact);
		}
		else
		{
			throw new ConflictException("Not unique name for the user.");
		}
		
		return contact;
	}
	
	
	/**
	 * Borra un usuario a partir de su nombre y su regId.
	 * 
	 * @param name Nombre del usuario a borrar.
	 */
	@ApiMethod(name = "deleteContact",
	 		   path = "deleteContact",
	 		   httpMethod = HttpMethod.POST)
		
	public void delete(@Named("name") String name,
					   @Named("regid") String regId)
	{
		Contact contact = new Contact(name, regId);
		ContactDAO.delete(contact);
	}
	
	
	/**
	 * Envía un mensaje a todos los usuarios de la app.
	 * 
	 * @param contact Usuario que envía el mensaje.
	 * @param msg Mensaje a enviar.
	 * @throws UnauthorizedException 
	 */
	@ApiMethod(name = "sendMessage",
	 		   path = "sendmessage",
	 		   httpMethod = HttpMethod.POST)
		
	public void sendMessage(@Named("sender") Contact sender,
					   		@Named("message") String msg)
					   				throws UnauthorizedException
	{
		Contact receiver = ContactDAO.read(sender.name);
		
		if (sender.equals(receiver))
		{
			ContactMessage message = new ContactMessage(sender, msg);
			ContactMessageSender.sendToAllContacts(message);
		}
		else
		{
			throw new UnauthorizedException("Usuario incorrecto.");
		}
	}

}
