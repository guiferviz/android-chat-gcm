

package com.blogspot.programmingheroes.chat;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class MessageAdapter extends BaseAdapter implements OnClickListener
{
	
	/*private List<GuestMessage> data;
	
	private LayoutInflater inflater;
	
	private Gbe gbe;
	
	
	public MessageAdapter(Context context, List<GuestMessage> data, Gbe gbe)
	{
		// Forma 1
		this.inflater = LayoutInflater.from(context);
		// Forma 2
		//this.inflater = (LayoutInflater)
		//		context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
		this.gbe = gbe;
	}*/
	

	@Override
	public int getCount()
	{
		return 0;//this.data.size();
	}

	@Override
	public Object getItem(int position)
	{
		return null;//this.data.get(position);
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
		/*GuestMessage message = data.get(n);
		
		if (view == null)
		{
			view = inflater.inflate(R.layout.message_row, null);
		}
		
		TextView date = (TextView) view.findViewById(R.id.text_view_date);
		TextView msg = (TextView) view.findViewById(R.id.text_view_message);
		Button button = (Button) view.findViewById(R.id.button_delete);
		
		date.setText(message.getDate().toString());
		msg.setText(message.getMessage());
		button.setTag(n);
		button.setOnClickListener(this);
		
		return view;*/
		return null;
	}


	@Override
	public void onClick(View v)
	{
		/*int n = (int) v.getTag();
		GuestMessage guestMessage = (GuestMessage) data.get(n);
		DeleteMessage deleteMessage = new DeleteMessage(guestMessage.getId());
		deleteMessage.execute();
		data.remove(n);
		
		notifyDataSetChanged();*/
	}
	
	private class DeleteMessage extends AsyncTask<Void, Void, Void>
    {

        private Long id;
        
        public DeleteMessage(Long id)
        {
            this.id = id;
        }
        
        @Override
        protected Void doInBackground(Void ... unused)
        {
        	Void void1 = null;
        	
            /*try
            {
                Delete delete = gbe.delete(id);
                void1 = delete.execute();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }*/
            
            return void1;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            if (result == null)
            {
                Log.v("Resultados de create", "No creado, result == null");
                return;
            }
            
            Log.v("Propiedades", "¿Delete?: " + result.toString());
        }
    }

	/*public void add(GuestMessage result)
	{
		data.add(result);
		notifyDataSetChanged();
	}*/

}
