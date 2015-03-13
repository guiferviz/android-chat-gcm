

package com.blogspot.programmingheroes.chat.gcm;


import java.io.IOException;

import android.os.AsyncTask;

import com.blogger.programmingheroes.endpoint.chat.model.Contact;
import com.blogspot.programmingheroes.chat.MainActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;


/**
 * Obtiene el regId.
 * 
 * @author ProgrammingHeroes
 *
 */
public class RegistryTask extends AsyncTask<Void, Object, String>
{
	
	private GoogleCloudMessaging gcm;
	
	private MainActivity mainActivity;

	
    public RegistryTask(GoogleCloudMessaging gcm, MainActivity mainActivity)
    {
    	this.gcm = gcm;
    	this.mainActivity = mainActivity;
	}


	@Override
    protected String doInBackground(Void... params)
    {
        String regId = "";
        
        try
        {
            regId = gcm.register(ConstantsGCM.SENDER_ID);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return regId;
    }

	@Override
    protected void onPostExecute(String regId)
    {
		Contact user = mainActivity.getUser();
		user.setRegId(regId);
		
		mainActivity.registryUser();
    }

}
