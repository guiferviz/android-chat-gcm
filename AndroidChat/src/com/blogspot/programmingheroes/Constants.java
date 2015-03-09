

package com.blogspot.programmingheroes;


import com.google.api.server.spi.Constant;


/**
 * Contiene los IDs y los scopes para los clientes a los que se les
 * permite utilizar la API.
 */
public class Constants
{
	
	// Replace this with your web client ID.
	public static final String WEB_CLIENT_ID =
		"904173227385-sciqb53jtjlf4sdsgreh75vip6rhf845.apps.googleusercontent.com";
	
	// Replace this with your Android client ID.
	public static final String ANDROID_CLIENT_ID = "";
	
	// Replace this with your iOS client ID
	public static final String IOS_CLIENT_ID = "";
	
	public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
	
	public static final String API_EXPLORER_CLIENT_ID =
			Constant.API_EXPLORER_CLIENT_ID;
	
	public static final String EMAIL_SCOPE =
			"https://www.googleapis.com/auth/userinfo.email";
	
}