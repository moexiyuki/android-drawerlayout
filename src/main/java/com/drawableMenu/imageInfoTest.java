package com.drawableMenu;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.widget.*;

public class imageInfoTest
{

	private Resources res;

	private Context c;
	
	private float isize;

	private int res_Draw;

	private Float t高;

	private Float t宽;

	private Float s;
	imageInfoTest(Context c,Resources res,int res_Draw){
		this.c=c;
		this.res=res;
		this.res_Draw=res_Draw;
		testImageSize();
	}
	public float getImageSize(){
		//Toast.makeText(c,isize.toString(),Toast.LENGTH_SHORT).show();
		return isize;
	}
	public void printImageSize(){
		Toast.makeText(c,
					   "高" +
					   t高.toString()+
					   "宽"+
					   t宽.toString()+
					   "宽高比"+s.toString()
					   ,Toast.LENGTH_SHORT).show();
	}
		public void testImageSize(){
			BitmapFactory.Options options=new BitmapFactory.Options();
			BitmapFactory.decodeResource(res,res_Draw,options);
			float 高=options.outHeight;
			float 宽=options.outWidth;
			t高=new Float(高);
			t宽 = new Float(宽);
			float size=高 /宽;
			s=new Float(size);
			this.isize=size;

		}
}
