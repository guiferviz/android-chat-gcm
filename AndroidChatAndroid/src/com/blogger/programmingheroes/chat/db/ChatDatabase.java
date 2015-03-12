

package com.blogger.programmingheroes.chat.db;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Base de datos SQLite para los mensajes recibidos.
 * 
 * @author ProgrammingHeroes
 *
 */
public class ChatDatabase extends SQLiteOpenHelper
{

	private static final String DB_NAME = "chatDatabase";
	
	private static final int DB_VERSION = 1;
	
	private static final String MESSAGES_TABLE_NAME = "messages";

	
	public ChatDatabase(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE " + MESSAGES_TABLE_NAME + " ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "message text,"
				+ "sender text,"
				+ "date datetime default current_timestamp);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		onCreate(db);
	}
	
	
	/**
	 * Cuenta el número de mensajes de la base de datos.
	 * 
	 * @return Número de mensajes guardados en el dispositivo.
	 */
    public int getMessagesCount()
    {
    	int count = 0;

    	SQLiteDatabase db = this.getReadableDatabase();
    	String countQuery = "SELECT  * FROM " + MESSAGES_TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        
        if (cursor != null && !cursor.isClosed())
        {
            count = cursor.getCount();
            cursor.close();
        }
        
        return count;
    }
    
    
    /**
     * Devuelve todos los mensajes de la base de datos.
     * 
     * @return Todos los mensajes almacenados en el dispositivo.
     */
    public List<ContactMessage> getAllMessages()
    {
    	ArrayList<ContactMessage> messages = new ArrayList<>();
    	
    	SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + MESSAGES_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst())
        {
            do
            {
            	String msg = cursor.getString(1);
            	String sender = cursor.getString(2);
            	
                ContactMessage message = new ContactMessage(sender, msg);
                messages.add(message);
            }
            while (cursor.moveToNext());
        }
        
        return messages;
    }


    /**
     * Añade un mensaje a la base de datos.
     * 
     * @param msg Mensaje a añadir.
     */
	public void add(ContactMessage msg)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("sender", msg.sender);
		contentValues.put("message", msg.msg);
		
        db.insert(MESSAGES_TABLE_NAME, null, contentValues);
	}

}
