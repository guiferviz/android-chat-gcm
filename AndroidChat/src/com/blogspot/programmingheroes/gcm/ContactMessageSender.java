

package com.blogspot.programmingheroes.gcm;


import java.io.IOException;
import java.util.List;

import com.blogspot.programmingheroes.db.Contact;
import com.blogspot.programmingheroes.db.ContactDAO;
import com.blogspot.programmingheroes.endpoint.ContactMessage;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;


/**
 * Clase que ayuda a enviar mensajes GCM a los contactos.
 * 
 * @author ProgrammingHeroes
 *
 */
public class ContactMessageSender
{
	
	private static final Sender SENDER;
	
	static
	{
		SENDER = new Sender(ConstantsGCM.GOOGLE_SERVER_KEY);
	}

	
	/**
	 * Envía un mensaje a todos los contactos registrados menos al que lo
	 * envía.
	 * 
	 * @param message Mensaje a enviar.
	 */
	public static void sendToAllContacts(ContactMessage msg)
	{
		List<Contact> contacts = ContactDAO.readAll();
		Message message = new Message.Builder()
				.addData("message", msg.msg)
				.addData("sender", msg.sender.name).build();
		
		for (Contact receiver : contacts)
		{
			if (!receiver.name.equals(msg.sender.name))
			{
				ContactMessageSender.send(receiver, message);
			}
		}
	}


	/**
	 * Envía un mensaje GCM al contacto indicado.
	 * 
	 * @param receiver El receptor del mensaje.
	 * @param message Mensaje a enviar.
	 */
	private static void send(Contact receiver, Message message)
	{
		try
		{
			SENDER.send(message, receiver.regId, 2);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
