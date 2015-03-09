

/**
 * AI Tanks script.
 * 
 * Para ver más información sobre cómo funciona la librería de endpoints de
 * JavaScript visitar:
 * https://developers.google.com/api-client-library/javascript/
 * 
 * @author ProgrammingHeroes
 */

var ROOT = "https://guestbook-dot-pruebas-gae.appspot.com/_ah/api";

function init()
{
	console.log("Loanding API")
	gapi.client.load('gbe', 'v1', gbeLoaded, ROOT);
}

function gbeLoaded()
{
	console.log("GuestBook API loaded");
	gapi.client.gbe.get().execute(recieve);
}

function recieve(response)
{
	for (var i = 0; i < response.items.length; i++)
	{
		addMessage(response.items[i]);
	}
}

function send(e)
{
	var text = document.getElementById("text-message").value;
	gapi.client.gbe.create({message: text}).execute(addMessage);
}

function addMessage(e)
{
	var msg = document.createElement("div");
	msg.innerHTML = "<hr /><h4>" + e.date + "</h4>" + e.message + "<br /><br />" +
		"<button class='btn' onclick='deleteMessage(this," + e.id +
		")'><span class='glyphicon glyphicon-remove'></span>&nbsp;&nbsp;&nbsp;&nbsp;Borrar</button>";
	var div = document.getElementById("messages");
	div.appendChild(msg);
}

function deleteMessage(src, id)
{
	var div = document.getElementById("messages");	
	div.removeChild(src.parentElement)
	gapi.client.gbe.delete({"id": id}).execute(deleted);
}

function deleted()
{
	console.log("borrada");
}
