

package com.blogspot.programmingheroes;


import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Sender;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;


@SuppressWarnings("serial")
public class AndroidChatServlet extends HttpServlet
{	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException
	{
		resp.setContentType("text/plain");
		resp.getWriter().println("Android Chat");
		resp.getWriter().println("Enviando mensaje de prueba.");
		


		Result result = null;

		String share = req.getParameter("shareRegId");

		// GCM RedgId of Android device to send push notification
		String regId = "";
		if (share != null && !share.isEmpty()) {
			regId = req.getParameter("regId");
			PrintWriter writer = new PrintWriter("GCMRegId.txt");
			writer.println(regId);
			writer.close();
			req.setAttribute("pushStatus", "GCM RegId Received.");
			//req.getRequestDispatcher("index.jsp")
			//		.forward(req, resp);
		}
		else
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(
						"GCMRegId.txt"));
				regId = br.readLine();
				br.close();
				String userMessage = req.getParameter("message");
				Sender sender = new Sender(Constants.GOOGLE_SERVER_KEY);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData("123456789", userMessage).build();
				System.out.println("regId: " + regId);
				result = sender.send(message, regId, 1);
				req.setAttribute("pushStatus", result.toString());
			}
			catch (IOException e)
			{
				e.printStackTrace();
				req.setAttribute("pushStatus",
						"RegId required: " + e.toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				req.setAttribute("pushStatus", e.toString());
			}
			
			//req.getRequestDispatcher("index.jsp")
			//		.forward(req, resp);
		}
	}

}
