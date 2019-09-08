package com.drawableMenu;
import android.content.*;
import android.view.*;
import android.widget.*;

public class main_menu_list_adapter extends ArrayAdapter<String>
{

	private int itemview;

	private int imageviewid;

	private int[] data;

	private int textviewid;
	
	private String[] text;
	protected
	main_menu_list_adapter(
	Context context,
	int itemview,
	int textviewid,
	String[] text,
	int imageviewid,
	int[] data){
		super(context,itemview,text);
		this.text=text;
		this.itemview=itemview;
		this.imageviewid=imageviewid;
		this.textviewid=textviewid;
		this.data=data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO: Implement this method
		View view= LayoutInflater.from(getContext()).
			inflate(itemview, parent, false);
			ImageView imageview=(ImageView)
			view.findViewById(imageviewid);
			TextView textview=(TextView)
			view.findViewById(textviewid);
			textview.setText(text[position]);
			imageview.setImageResource(data[position]);
		return view;
		//super.getView(position, convertView, parent);
	}
	
}
