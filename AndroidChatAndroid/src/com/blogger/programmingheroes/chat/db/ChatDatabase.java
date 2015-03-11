

package com.blogger.programmingheroes.chat.db;


import android.content.Context;
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

	private static final String NAME = "chatDatabase";
	
	private static final int VERSION = 1;

	
	public ChatDatabase(Context context)
	{
		super(context, NAME, null, VERSION);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE messages ("
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

}
