

package com.blogspot.programmingheroes.endpoint;


import java.util.List;

import com.blogspot.programmingheroes.Constants;
import com.blogspot.programmingheroes.OfyService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.cmd.Query;


@Api(title = "guestBookEndpoint",
	 name = "gbe",
	 description = "Libro de visitas",
	 version = "v1",
	 scopes = {Constants.EMAIL_SCOPE},
	 clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID})

public class GuestBookEndpoint
{
	
	@ApiMethod(name = "get",
	 		   path = "get",
	 		   httpMethod = HttpMethod.GET)
		
	public List<GuestMessage> get()
	{
		Query<GuestMessage> query =
				OfyService.ofy().load().type(GuestMessage.class).order("date");
		return query.list();
	}
	
	
	@ApiMethod(name = "create",
	 		   path = "create",
	 		   httpMethod = HttpMethod.POST)
		
	public GuestMessage create(@Named("message") String message)
	{
		GuestMessage guestMessage = new GuestMessage(message);
		
		OfyService.ofy().save().entity(guestMessage).now();
		
		return guestMessage;
	}
	
	
	@ApiMethod(name = "delete",
	 		   path = "delete",
	 		   httpMethod = HttpMethod.GET)
		
	public void delete(@Named("id") Long id)
	{
		//Result<Void> result = 
		OfyService.ofy().delete().type(GuestMessage.class).id(id);
		
		return;
	}
	
}
