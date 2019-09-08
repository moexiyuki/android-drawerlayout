package com.drawableMenu;
import android.graphics.*;
import android.os.*;
import android.widget.*;
import java.io.*;
public class doInBackGround_getUrlAndData extends AsyncTask
<String,Void,Bitmap[]>
{

	private Bitmap[] bitmaps;
	
	ListView listview;

	doInBackGround_getUrlAndData(ListView list){
		this.listview=list;
	}
	@Override
	protected Bitmap[] doInBackground(String[] urls){
		// TODO: Implement this method
		try{
			bitmaps=loadBitmap.getBitmap(urls);
		}
		catch(IOException e){}
		return bitmaps;
	}

	@Override
	protected void onPostExecute(Bitmap[] result){
		// TODO: Implement this method
		ArrayAdapter<Bitmap> adp
		= new main_img_list_apapter(
		listview.getContext(),result);
		listview.setAdapter(adp);
		super.onPostExecute(result);
	}
	
	
}
