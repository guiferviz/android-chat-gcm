

package com.blogspot.programmingheroes;


import com.blogspot.programmingheroes.endpoint.GuestMessage;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;


public class OfyService
{
	
    static
    {
        factory().register(GuestMessage.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

	public static ObjectifyFactory factory()
	{
		return ObjectifyService.factory();
	}
	
}
