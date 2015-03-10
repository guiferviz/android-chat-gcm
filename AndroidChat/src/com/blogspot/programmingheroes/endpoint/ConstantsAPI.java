

package com.blogspot.programmingheroes;


import com.google.api.server.spi.Constant;


/**
 * Contiene los IDs y los scopes para los clientes a los que se les
 * permite utilizar la API.
 */
public class Constants
{
	
	/**
	 * Sustituye esto por tu Web Client ID.
	 */
	public static final String WEB_CLIENT_ID =
		"904173227385-sciqb53jtjlf4sdsgreh75vip6rhf845.apps.googleusercontent.com";
	
	/**
	 * Sustituye esto por tu Android Client ID.
	 */
	public static final String ANDROID_CLIENT_ID = "";
	
	/**
	 * Sustituye esto por tu iOS Client ID.
	 */
	public static final String IOS_CLIENT_ID = "";
	
	public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
	
	public static final String API_EXPLORER_CLIENT_ID =
			Constant.API_EXPLORER_CLIENT_ID;
	
	/**
	 * Scope OAuth2.0 que permite acceder principalmente al email del usuario.
	 */
	public static final String EMAIL_SCOPE =
			"https://www.googleapis.com/auth/userinfo.email";
	
}