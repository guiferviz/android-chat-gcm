

package com.blogspot.programmingheroes.chat;


import java.util.List;

import com.blogger.programmingheroes.chat.db.ContactMessage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Se encarga de rellenar el listview con el contenido de los mensajes.
 * 
 * @author ProgrammingHeroes
 *
 */
public class MessageAdapter extends BaseAdapter
{
	
	private List<ContactMessage> data;
	
	private LayoutInflater inflater;
	
	
	public MessageAdapter(Context context, List<ContactMessage> data)
	{
		this.inflater = LayoutInflater.from(context);
		this.data = data;
	}
	

	@Override
	public int getCount()
	{
		return data.size();
	}

	@Override
	public Object getItem(int position)
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	@SuppressLint("InflateParams")
	public View getView(int n, View view, ViewGroup parent)
	{
		ContactMessage message = data.get(n);
		
		if (view == null)
		{
			view = inflater.inflate(R.layout.message_row, null);
		}
		
		TextView user = (TextView) view.findViewById(R.id.text_view_date);
		TextView msg = (TextView) view.findViewById(R.id.text_view_message);
		
		user.setText(message.sender);
		msg.setText(message.msg);
		
		return view;
	}


	/**
	 * Añade un nuevo mensaje al listview.
	 * 
	 * @param newMessage Nuevo mensaje.
	 */
	public void add(ContactMessage newMessage)
	{
		data.add(newMessage);
		notifyDataSetChanged();
	}

}
