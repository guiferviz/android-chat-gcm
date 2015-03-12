

package com.blogger.programmingheroes.endpoint.chat.tasks;


import java.io.IOException;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.blogger.programmingheroes.endpoint.chat.Chat;
import com.blogger.programmingheroes.endpoint.chat.Chat.GetAllContacts;
import com.blogger.programmingheroes.endpoint.chat.model.Contact;
import com.blogger.programmingheroes.endpoint.chat.model.ContactCollection;
import com.blogspot.programmingheroes.chat.MainActivity;


/**
 * Obtiene una lista de los contactos que hay registrados en la aplicación.
 * 
 * @author ProgrammingHeroes
 *
 */
public class GetContactsTask extends AsyncTask<Void, Void, ContactCollection>
{
	
	private static final String LOG_TAG =
			GetContactsTask.class.getCanonicalName();

	
    private MainActivity mainActivity;
    

	public GetContactsTask(MainActivity mainActivity)
    {
    	this.mainActivity = mainActivity;
    }
    
	
    @Override
    protected ContactCollection doInBackground(Void ... unused)
    {
    	ContactCollection contacts = null;
        
        try
        {
        	Chat chatEndpoint = mainActivity.getChatEndpoint();
            GetAllContacts create = chatEndpoint.getAllContacts();
            contacts = create.execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return contacts;
    }

	@Override
    protected void onPostExecute(ContactCollection result)
    {
        if (result == null)
        {
            Log.e(LOG_TAG, "Error al obtener contactos, result == null");
            return;
        }
        
        List<Contact> list = result.getItems();
        
        if (list == null)
        {
        	Log.v(LOG_TAG, "No hay contactos");
        	return;
        }
        
        for (int i = 0; i < list.size(); i++)
        {
        	Log.v(LOG_TAG, "Nombre: " + list.get(i).getName());
        	Log.v(LOG_TAG, "RegId: " + list.get(i).getRegId());
        	Log.v(LOG_TAG, "-------------");
        }

        // FIXME ¿Añadir contactos al listview?
    }
}
