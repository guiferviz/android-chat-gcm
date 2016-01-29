# **Android Chat README** #

Chat de Android con Google App Engine.

Este proyecto no usa ningún tipo de autenticación de usuarios.
Entras en la aplicación, introduces tu nombre de usuario y comienzas a chatear con cualquier usuario que tenga la aplicación instalada y haya introducido su nombre de usuario.

Al usar Google Cloud Message (GCM), los mensajes llegan aún sin estar dentro de la aplicación. Se informa al usuario con una notificación.

Si lo deseas, puedes [descargar el APK](https://github.com/guiferviz/android-chat-gcm/releases/download/v1.0/AndroidChatAndroid.apk) directamente sin la necesidad de compilar el proyecto.

![](https://raw.githubusercontent.com/guiferviz/android-chat-gcm/gh-pages/captura.jpg)

### ¿Para qué es este repositorio? ###

* Practicar la creación de **APIs** con Google App Engine.

* Aprender a crear un **cliente web** y un **cliente Android** que usen Google App Engine como backend.

* Aprender a usar **GCM** (Google Cloud Messaging).

### ¿Cómo usarlo? ###

Clonar el repositorio e importar los dos proyectos a Eclipse. Debes de tener instalado el plugin de Google con el SDK de Google App Engine y el SDK de Android.

* **AndroidChat/**: Contiene el proyecto de Google App Engine. Se ha estudiado la posibilidad de añadir cliente web usando la API Channel de Google App Engine pero se dejará para implementaciones futuras.
* **AndroidChatAndroid/**: Contiene la aplicación para Android.

### ¿Con quién puedo contactar? ###

Cualquier duda enviar un correo a [ProgrammingHeroes](mailto:programmingh@gmail.com).

![](https://bytebucket.org/programmingheroes/android-chat/raw/532fc3797050f6a0c82d5e79d0bce822bea001d9/AndroidChatAndroid/ic_launcher-web.png?token=83f92eff2c014d760b20e535698f2643495267bb)
