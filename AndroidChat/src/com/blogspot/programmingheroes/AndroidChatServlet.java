

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

		//String share = req.getParameter("shareRegId");

		// GCM RedgId of Android device to send push notification
		String regId = "";
		/*if (share != null && !share.isEmpty()) {
			regId = req.getParameter("regId");
			PrintWriter writer = new PrintWriter("GCMRegId.txt");
			writer.println(regId);
			writer.close();
			req.setAttribute("pushStatus", "GCM RegId Received.");
			//req.getRequestDispatcher("index.jsp")
			//		.forward(req, resp);
		}
		else*/
		{
			try
			{
				//BufferedReader br = new BufferedReader(new FileReader(
				//		"GCMRegId.txt"));
				//regId = br.readLine();
				//br.close();
				regId = "APA91bEziSGrotNJY4AgzMQsgL2uS2RrYadsRc7wIme4hXu29gQ8LK_4-xfiKQZLxSRkCt3LQsFHWTzQKWAYr4bxDKU_VLLncmCmMRVcCttr4O_QUVyNH4dhc4r69rGfauHK_eSdU3ZetvX1YrT4Xq84acHoJtMxpFFVesYcH8_xV_pWm9om7-oXK24KTGYojHdeIhbhJAci";
				String userMessage = "pene";//req.getParameter("message");
				System.out.println("regId: " + regId);
				Sender sender = new Sender(Constants.GOOGLE_SERVER_KEY);
				Message message = new Message.Builder()
						.addData("message", userMessage).build();
				result = sender.sendNoRetry(message, regId);
				//req.setAttribute("pushStatus", result.toString());
			}
			catch (IOException e)
			{
				e.printStackTrace();
				//req.setAttribute("pushStatus",
				//		"RegId required: " + e.toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				//req.setAttribute("pushStatus", e.toString());
			}
			
			//req.getRequestDispatcher("index.jsp")
			//		.forward(req, resp);
		}
	}

}
