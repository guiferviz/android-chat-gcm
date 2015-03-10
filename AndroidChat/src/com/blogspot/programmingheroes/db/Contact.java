

package com.blogspot.programmingheroes.db;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * Representa un contacto, es decir, un usuario de la aplicación al que se
 * pueden enviar mensajes.
 * 
 * @author ProgrammingHeroes
 *
 */
@Entity
public class Contact
{
	
	/**
	 * Identifica de forma única a este contacto.
	 */
	@Id
	@Index
	public String name;
	
	/**
	 * Identificador que permitirá el envío de GCM a este contacto.
	 * Debe de ser único puesto que se hay más de uno indicaría que una misma
	 * persona tiene dos nombres distintos.
	 */
	@Index
	public String regId;
	
	
	/**
	 * Crea un contacto vacío, sin nombre y sin regId.
	 */
	public Contact()
	{	
	}
	
	/**
	 * Crea un contacto a partir de un nombre y un regId para GCM.
	 * 
	 * @param name Nombre del contacto.
	 * @param regId Id para GCM.
	 */
	public Contact(String name, String regId)
	{
		this.name = name;
		this.regId = regId;
	}
	
}
