

package com.blogger.programmingheroes.endpoint.chat.tasks;


import java.io.IOException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.blogger.programmingheroes.endpoint.chat.Chat;
import com.blogger.programmingheroes.endpoint.chat.model.Contact;
import com.blogspot.programmingheroes.chat.MainActivity;
import com.blogspot.programmingheroes.chat.R;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;


/**
 * Envía una solicitud de registro de un contacto al servidor.
 * 
 * @author ProgrammingHeroes
 *
 */
public class CreateContactTask extends AsyncTask<Void, Void, Boolean>
{

	private static final String LOG_TAG =
			CreateContactTask.class.getCanonicalName();

	
    private Contact contact;
    
    private MainActivity mainActivity;
    
    private int errorCode;


    public CreateContactTask(MainActivity mainActivity, Contact contact)
    {
    	this.mainActivity = mainActivity;
        this.contact = contact;
    }


    @Override
    protected Boolean doInBackground(Void ... unused)
    {
        try
        {
        	Chat chatEndpoint = mainActivity.getChatEndpoint();
            Chat.CreateContact createContact = chatEndpoint.createContact(
            		contact.getName(), contact.getRegId());
            createContact.execute();
        }
        catch (GoogleJsonResponseException e)
        {
			errorCode = e.getStatusCode();
			return false;
		}
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
			e.printStackTrace();
			return false;
		}
        
		return true;
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        if (result == null)
        {
            Log.e(LOG_TAG, "Error, result == null");
            return;
        }
        
        if (result)
        {
        	Log.v(LOG_TAG, "Usuario registrado con éxito.");
        	
        	mainActivity.saveUser();
        	mainActivity.initChat();
        }
        else
        {
        	Log.e(LOG_TAG, "El usuario no ha podido registrarse.");
        	
        	if (errorCode == 409)
        	{
        		Toast.makeText(mainActivity,
        				mainActivity.getResources().getString(
        						R.string.user_same_name_error),
        				Toast.LENGTH_LONG).show();
        	}
		}
    }

}
