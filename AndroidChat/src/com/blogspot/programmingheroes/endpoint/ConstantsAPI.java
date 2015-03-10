

package com.blogspot.programmingheroes.endpoint;


import com.google.api.server.spi.Constant;


/**
 * Información básica de la API.
 * Contiene los IDs y los scopes para los clientes a los que se les
 * permite utilizar la API.
 */
public class ConstantsAPI
{

	/**
	 * Título de la API.
	 */
	static final String TITLE = "Chat";

	/**
	 * Nombre de la API.
	 */
	static final String NAME = "chat";
	
	/**
	 * Versión de la API.
	 */
	static final String VERSION = "v1";
	
	/**
	 * Descripción de la API.
	 */
	static final String DESCRIPTION = "API para una aplicación de chats.";
	
	/**
	 * Dominio. Constante para @ApiNamespace.
	 */
	static final String NAMESPACE_OWNER_DOMAIN =
			"programmingheroes.blogger.com";
	
	/**
	 * Nombre. Constante para @ApiNamespace.
	 */
	static final String NAMESPACE_OWNER_NAME = "ProgrammingHeroes";
	
	/**
	 * Namespace. Constante para @ApiNamespace.
	 */
	static final String NAMESPACE_PACKAGE_PATH =
			"programmingheroes/chat";
	
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
	
	/**
	 * Android audience, igual al Web Client ID.
	 */
	public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
	
	/**
	 * Permite ser acceder al enpoint desde el explorador de APIs de Google.
	 */
	public static final String API_EXPLORER_CLIENT_ID =
			Constant.API_EXPLORER_CLIENT_ID;
	
	/**
	 * Scope OAuth2.0 que permite acceder principalmente al email del usuario.
	 */
	public static final String EMAIL_SCOPE =
			"https://www.googleapis.com/auth/userinfo.email";
	
}