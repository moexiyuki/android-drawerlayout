package com.drawableMenu;
import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;

public class main_img_list_apapter extends ArrayAdapter<Bitmap>
{
	private Bitmap[] bitmap;
	public main_img_list_apapter(
	//上下文
	Context context,
	//资源
	Bitmap[] bitmap){
		super(context,R.layout.img_list_item,bitmap);
		this.bitmap=bitmap;
}

@Override
public View getView(int position, View convertView, ViewGroup parent)
{
	// TODO: Implement this method
	View view=LayoutInflater.from(getContext())
	.inflate(R.layout.img_list_item,parent,false);
	ImageView img=(ImageView) view.findViewById(R.id.image);
	img.setImageBitmap(bitmap[position]);
	return view;
}
	
}
