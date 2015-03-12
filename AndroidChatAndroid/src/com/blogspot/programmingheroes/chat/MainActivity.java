

package com.blogspot.programmingheroes.chat;


import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.blogger.programmingheroes.chat.db.ChatDatabase;
import com.blogger.programmingheroes.chat.db.ContactMessage;
import com.blogger.programmingheroes.endpoint.chat.Chat;
import com.blogger.programmingheroes.endpoint.chat.Chat.GetAllContacts;
import com.blogger.programmingheroes.endpoint.chat.model.Contact;
import com.blogger.programmingheroes.endpoint.chat.model.ContactCollection;
import com.blogspot.programmingheroes.chat.R;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;


/**
 * Si aparece en el LogCat algo como:
 * 
 * 07-28 03:25:39.011: W/System.err(575):
 * com.google.api.client.googleapis.json.GoogleJsonResponseException:
 * 404 Not Found
 * 
 * debes de asegurarte que la versión en la cual has hecho el deploy de tu
 * endpoint sea la versión default. Otra opción sería cambiar la constante
 * DEFAULT_ROOT_URL de la clase Gbe por la URL con protocolo https e indicando
 * la versión. Ej: https://guestbook-dot-pruebas-gae.appspot.com/_ah/api/
 * 
 * 
 * 
 * La aplicación se encuentra tan comprimida como he podido conseguir.
 * He borrado muchos jars de la carpeta libs.
 * google-api-client-1.18.0-rc.jar
 * google-api-client-android-1.18.0-rc.jar
 * google-http-client-1.18.0-rc.jar
 * google-http-client-android-1.18.0-rc.jar
 * google-http-client-gson-1.18.0-rc.jar
 * gson-2.1.jar
 * 
 * google-oauth-client-1.18.0-rc.jar se queda fuera porque no hace falta hacer
 * llamadas 'oautorizadas'
 * 
 * @author ProgrammingHeroes
 *
 */
public class MainActivity extends Activity implements OnClickListener
{

	private static final String LOG_TAG = MainActivity.class.getCanonicalName(); 
	
	
	private Chat chatEndpoint;
	
	private ListView listView;
	
	private ChatDatabase database;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.list_view);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		
		Chat.Builder builder = new Chat.Builder(
				AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		//builder.setApplicationName("gbe");
		chatEndpoint = builder.build();
		
		database = new ChatDatabase(this);
		List<ContactMessage> messages = database.getAllMessages();
    	MessageAdapter messageAdapter = new MessageAdapter(this, messages);
    	listView.setAdapter(messageAdapter);
    	
    	GetContacts getContacts = new GetContacts();
    	getContacts.execute();
	}
	
	private class SendMessage extends AsyncTask<Void, Void, ContactMessage>
    {

        private String message;
        
        public SendMessage(String message)
        {
            this.message = message;
        }
        
        @Override
        protected ContactMessage doInBackground(Void ... unused)
        {
        	Contact contact = MainActivity.this.getUser();
        	ContactMessage msg = new ContactMessage(message, "Yo");
        	
            try
            {
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
                Log.v("Resultados de enviar", "No enviado, result == null");
                return;
            }
            
            Log.v("Propiedades del mensaje", "Message: " + result.msg);
            
            MessageAdapter baseAdapter = (MessageAdapter) listView.getAdapter();
            baseAdapter.add(result);
            database.add(result);
        }
    }
	
	private class GetContacts extends AsyncTask<Void, Void, ContactCollection>
    {

        public GetContacts()
        {
        }
        
        @Override
        protected ContactCollection doInBackground(Void ... unused)
        {
        	ContactCollection contacts = null;
            
            try
            {
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
                Log.e("Resultados de GetContacts", "result == null");
                return;
            }
            
            List<Contact> list = result.getItems();
            
            if (list == null)
            {
            	Log.v("Resultados", "No hay contactos");
            	return;
            }
            
            for (int i = 0; i < list.size(); i++)
            {
            	Log.v("Propiedades", "Nombre: " + list.get(i).getName());
            	Log.v("Propiedades", "RegId: " + list.get(i).getRegId());
            	Log.v("Propiedades", "-------------");
            }

            // FIXME ¿Añadir contactos al listview?
        }
    }
	
	@Override
	public void onClick(View v)
	{
		EditText textView = (EditText) findViewById(R.id.edit_text);		
		Log.v(LOG_TAG, "Enviando mensaje: " + textView.getText().toString());
		SendMessage sendMessage = new SendMessage(
				textView.getText().toString());
		sendMessage.execute();
	}

	public Contact getUser()
	{
		// Hardcored user.
		Contact contact = new Contact();
		contact.setName("guiller");
		contact.setRegId("APA91bEZDKRCgfumTodfa7CHwVJAtQLqsFm8sROgHHjDthAlCFkDtkEjhH59AnAM2tF-GgO6MjPzwaGQ0g-O4x2-uq8efsEy82DywM4Sabuw5aMI1TtFNlLgi45g8SvlXbpCGqWebfLNbsZO0jr6o9pEg4oEkG47mp8Ycv5co1ZxcX73b5FqhRQ");
		
		return contact;
	}

}
