

package com.blogspot.programmingheroes.endpoint;


import java.util.Date;

import com.blogspot.programmingheroes.db.Contact;


/**
 * Representa el mensaje enviado por un contacto. En principio se trata de
 * una clase que solo va a ser usada en el servidor, no almacenaremos los
 * mensajes en la base de datos.
 * 
 * @author ProgrammingHeroes
 *
 */
public class ContactMessage
{

	public Contact sender;

	public String msg;

	public Date date;


	public ContactMessage(Contact sender, String msg)
	{
		this.sender = sender;
		this.msg = msg;

		date = new Date();
	}

}
