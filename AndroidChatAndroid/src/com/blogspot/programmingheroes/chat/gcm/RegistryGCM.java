

package com.blogspot.programmingheroes.chat.gcm;


import android.util.Log;

import com.blogspot.programmingheroes.chat.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;


/**
 * Clase que se encarga de obtener el regId del usuario para usar GCM.
 * 
 * @author ProgrammingHeroes
 *
 */
public class RegistryGCM
{
	
	private static final String LOG_TAG = RegistryGCM.class.getCanonicalName();
	
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	
    
	public static void doRegistryGCM(MainActivity mainActivity)
	{
		if (checkPlayServices(mainActivity))
        {
			GoogleCloudMessaging gcm =
					GoogleCloudMessaging.getInstance(mainActivity);
           
			RegistryTask registryTask = new RegistryTask(gcm, mainActivity);
            registryTask.execute();
        }
        else
        {
            Log.e(LOG_TAG, "No valid Google Play Services APK found.");
        }
	}
	
	
	/**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private static boolean checkPlayServices(MainActivity mainActivity)
    {
        int resultCode = GooglePlayServicesUtil
        		.isGooglePlayServicesAvailable(mainActivity);
        
        if (resultCode != ConnectionResult.SUCCESS)
        {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            {
                GooglePlayServicesUtil.getErrorDialog(resultCode, mainActivity,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            else
            {
                Log.i(LOG_TAG, "This device is not supported.");
                mainActivity.finish();
            }
            
            return false;
        }
        
        return true;
    }

}
