

package com.blogspot.programmingheroes.chat;


import com.blogger.programmingheroes.chat.db.ContactMessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * BroadcastReceiver creado para eclipsar al que recibirá los mensajes en
 * segundo plano. Haciendo que este tenga preferencia sobre el otro se evita
 * que se cree una notificación. Además, se añade al listview.
 * 
 * @author ProgrammingHeroes
 *
 */
public class MainActivityReceiver extends BroadcastReceiver
{

    private MainActivity mainActivity;
    

	public MainActivityReceiver(MainActivity mainActivity)
    {
    	this.mainActivity = mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
    	String sender = intent.getStringExtra("sender");
    	String msg = intent.getStringExtra("message");
    	ContactMessage message = new ContactMessage(sender, msg);
    	
    	mainActivity.addMessage(message);
        
        abortBroadcast();
    }

}