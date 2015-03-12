

package com.blogspot.programmingheroes.chat;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Clase encargada del manejo de SharedPreferences.
 * Inicialmente contendrá el nombre del usuario actual y
 * el regId asociado a él.
 * 
 * @author ProgrammingHeroes
 *
 */
public class SharedPreferencesManager
{

    private static final String PREFERENCES_NAME = "chat_preferences";

    private static final String CONTACT_NAME = "name";
    
    private static final String CONTACT_REG_ID = "regIg";


    private SharedPreferencesManager() {}


    private static SharedPreferences getPreferences(Context context)
    {
        return context.getSharedPreferences(PREFERENCES_NAME,
        			Context.MODE_PRIVATE);
    }

    public static String getContactName(Context context)
    {
        return getPreferences(context).getString(CONTACT_NAME, null);
    }

    public static void setContactName(Context context, String newValue)
    {
        final SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(CONTACT_NAME, newValue);
        editor.commit();
    }
    
    public static String getContactRegID(Context context)
    {
        return getPreferences(context).getString(CONTACT_REG_ID, null);
    }

    public static void setContactRegID(Context context, String newValue)
    {
        final SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(CONTACT_REG_ID, newValue);
        editor.commit();
    }

}
