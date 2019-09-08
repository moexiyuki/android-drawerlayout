package com.drawableMenu;
import android.graphics.*;
import java.io.*;
import java.net.*;

public class loadBitmap
{
   /***
	*loadBitmap.getBitmap(String[] urls)
	*/
	public static Bitmap[] getBitmap(String[] urls) throws IOException{
		int size=urls.length;
		
		Bitmap[] bitmaps=new Bitmap[size];
		
		for(int index=0;index < size;index++){
			
			URL url = new URL(urls[index]);
			InputStream in=url.openStream();
			BufferedInputStream buff=
				new BufferedInputStream(in);
			Bitmap bitmap=BitmapFactory.decodeStream(buff);
			bitmaps[index] = bitmap;
			buff.close();
			in.close();
			
		}
		return bitmaps;
	}
	
}
