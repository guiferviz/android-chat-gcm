

package com.blogger.programmingheroes.endpoint.chat.tasks;


import java.io.IOException;

import android.os.AsyncTask;
import android.util.Log;

import com.blogger.programmingheroes.chat.db.ContactMessage;
import com.blogger.programmingheroes.endpoint.chat.Chat;
import com.blogger.programmingheroes.endpoint.chat.model.Contact;
import com.blogspot.programmingheroes.chat.MainActivity;


/**
 * Envía un mensaje de forma asíncrona al servidor.
 * 
 * @author ProgrammingHeroes
 *
 */
public class SendMessageTask extends AsyncTask<Void, Void, ContactMessage>
{

	private static final String LOG_TAG =
			SendMessageTask.class.getCanonicalName();

	
    private String message;
    
    private MainActivity mainActivity;


    public SendMessageTask(MainActivity mainActivity, String message)
    {
    	this.mainActivity = mainActivity;
        this.message = message;
    }


    @Override
    protected ContactMessage doInBackground(Void ... unused)
    {
    	Contact contact = mainActivity.getUser();
    	ContactMessage msg = new ContactMessage("Yo", message);
    	
        try
        {
        	Chat chatEndpoint = mainActivity.getChatEndpoint();
            Chat.SendMessage sendMessage = chatEndpoint.sendMessage(
            		message, contact);
            sendMessage.execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return msg;
    }

    @Override
    protected void onPostExecute(ContactMessage result)
    {
        if (result == null)
        {
            Log.v(LOG_TAG, "No enviado, result == null");
            return;
        }
        
        Log.v(LOG_TAG, "Tu mensage enviado: " + result.msg);
        
        mainActivity.addMessage(result);
    }

}
