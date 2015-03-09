

package com.blogspot.programmingheroes.endpoint;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class GuestMessage
{
	
	@Id
	public Long id;
	
	public String message;
	
	@Index
	public Date date;
	
	
	public GuestMessage()
	{
		this("");
	}
	
	public GuestMessage(String message)
	{
		this.message = message;
		this.date = new Date();
	}
	
}
