

package com.blogspot.programmingheroes.chat;


import java.util.List;

import android.app.Activity;
import android.content.IntentFilter;
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
import com.blogger.programmingheroes.endpoint.chat.model.Contact;
import com.blogger.programmingheroes.endpoint.chat.tasks.CreateContactTask;
import com.blogger.programmingheroes.endpoint.chat.tasks.GetContactsTask;
import com.blogger.programmingheroes.endpoint.chat.tasks.SendMessageTask;
import com.blogspot.programmingheroes.chat.R;
import com.blogspot.programmingheroes.chat.gcm.RegistryGCM;
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
	
	private MainActivityReceiver mainActivityReceiver;
	
	private Contact user;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		loadUser();
		loadChatEndpoint();
		
		if (user.getName() == null || user.getName().equals(""))
		{
			registry();
		}
		else
		{
			initChat();
		}
	}
	
	private void loadChatEndpoint()
	{
		Chat.Builder builder = new Chat.Builder(
				AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		//builder.setApplicationName("gbe");
		chatEndpoint = builder.build();
	}

	private void registry()
	{
		setContentView(R.layout.activity_registry);
		
		Button button = (Button) findViewById(R.id.button_login);
		button.setOnClickListener(this);
	}

	public void initChat()
	{
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.list_view);
		Button button = (Button) findViewById(R.id.button_send);
		button.setOnClickListener(this);
		
		database = new ChatDatabase(this);
		List<ContactMessage> messages = database.getAllMessages();
    	MessageAdapter messageAdapter = new MessageAdapter(this, messages);
    	listView.setAdapter(messageAdapter);
    	
    	GetContactsTask getContacts = new GetContactsTask(this);
    	getContacts.execute();
    	
    	mainActivityReceiver = new MainActivityReceiver(this);
    	IntentFilter filter = new IntentFilter(
	    		"com.google.android.c2dm.intent.RECEIVE");
	    filter.setPriority(1);
	    registerReceiver(mainActivityReceiver, filter);
	    
	    // Para borrar usuario mientras no ponemos opción en un menú.
	    //user.setName("");
	    //user.setRegId("");
	    //saveUser();
	}

	@Override
	public void onResume()
	{
		// Registramos un broadcast receiver para evitar la aparición de
		// notificaciones. Directamente se añadirá el mensaje.
		if (mainActivityReceiver != null)
		{
		    IntentFilter filter = new IntentFilter(
		    		"com.google.android.c2dm.intent.RECEIVE");
		    filter.setPriority(1);
		    registerReceiver(mainActivityReceiver, filter);
		}

	    super.onResume();
	}

	@Override
	protected void onPause()
	{
		if (mainActivityReceiver != null)
		{
			unregisterReceiver(mainActivityReceiver);
		}
		
		super.onPause();
	}

	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.button_send)
		{
			// Send button clicked.
			EditText textView = (EditText) findViewById(R.id.edit_text);
			Log.v(LOG_TAG, "Enviando mensaje: " + textView.getText().toString());
			SendMessageTask sendMessage = new SendMessageTask(this,
					textView.getText().toString());
			sendMessage.execute();
		}
		else if (v.getId() == R.id.button_login)
		{
			EditText textView = (EditText) findViewById(R.id.user_name);
			user.setName(textView.getText().toString());
						
			if (!user.getName().equals(""))
			{
				RegistryGCM.doRegistryGCM(this);
			}
		}
	}
	
	public void registryUser()
	{
		CreateContactTask createContactTask =
				new CreateContactTask(this, user);
		createContactTask.execute();
	}

	/**
	 * Devuelve el usuario actual que se encuentra registrado en la app.
	 * 
	 * @return Usuario actual.
	 */
	public Contact getUser()
	{
		return user;
	}
	
	private void loadUser()
	{
		user = new Contact();
		user.setName(SharedPreferencesManager.getContactName(this));
		user.setRegId(SharedPreferencesManager.getContactRegID(this));
	}
	
	public void saveUser()
	{
		SharedPreferencesManager.setContactName(this, user.getName());
		SharedPreferencesManager.setContactRegID(this, user.getRegId());
	}

	/**
	 * Añade un mensaje tanto al listview como a la base de datos.
	 * 
	 * @param message Mensaje a añadir.
	 */
	public void addMessage(ContactMessage message)
	{
		MessageAdapter baseAdapter = (MessageAdapter) listView.getAdapter();
        baseAdapter.add(message);
        database.add(message);
	}

	/**
	 * Devuelve el endpoint para poder realizar peticiones al servidor.
	 * 
	 * @return El endpoint para el chat.
	 */
	public Chat getChatEndpoint()
	{
		return chatEndpoint;
	}
	
	/**
	 * Borra un mensaje tanto del listview como de la base de datos.
	 * 
	 * @param message Mensage a borrar.
	 */

	public void deleteMessage(ContactMessage message)
	{
		MessageAdapter baseAdapter = (MessageAdapter) listView.getAdapter();
        baseAdapter.delete(message);
        database.delete(message);
	}

}
