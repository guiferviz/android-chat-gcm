

package com.blogger.programmingheroes.chat.db;


//import java.util.Date;


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

	public String sender;

	public String msg;

	//public Date date;


	public ContactMessage(String sender, String msg)
	{
		this.sender = sender;
		this.msg = msg;
	}

}
