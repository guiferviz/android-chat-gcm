

package com.blogspot.programmingheroes;


import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogspot.programmingheroes.db.Contact;
import com.blogspot.programmingheroes.endpoint.ContactMessage;
import com.blogspot.programmingheroes.gcm.ContactMessageSender;


@SuppressWarnings("serial")
public class AndroidChatServlet extends HttpServlet
{	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException
	{
		resp.setContentType("text/plain");
		resp.getWriter().println("Android Chat");
		resp.getWriter().println("Enviando mensaje de prueba.");
		
		
		try
		{
			//"APA91bEziSGrotNJY4AgzMQsgL2uS2RrYadsRc7wIme4hXu29gQ8LK_4-xfiKQZLxSRkCt3LQsFHWTzQKWAYr4bxDKU_VLLncmCmMRVcCttr4O_QUVyNH4dhc4r69rGfauHK_eSdU3ZetvX1YrT4Xq84acHoJtMxpFFVesYcH8_xV_pWm9om7-oXK24KTGYojHdeIhbhJAci";
			String senderName = req.getParameter("sender");
			Contact sender = new Contact(senderName, "");
			String message = req.getParameter("message");
			ContactMessage msg = new ContactMessage(sender, message);
			
			resp.getWriter().println();
			resp.getWriter().println("Sender name: " + senderName);
			resp.getWriter().println("Sender message: " + message);
			
			ContactMessageSender.sendToAllContacts(msg);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
