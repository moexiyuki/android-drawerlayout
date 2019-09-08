package com.drawableMenu;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.support.v4.widget.*;
import android.view.*;
import android.widget.*;
import com.google.zxing.*;
import com.zxing.encoding.*;

public class itemSelected implements DialogInterface.OnClickListener
{

	private static AlertDialog alert_edit_obj;

	@Override
	public void onClick(DialogInterface p1, int p2){
		// TODO: Implement this method
		EditText text=(EditText) alert_edit_obj.findViewById(R.id.alertdialogedittextEditText1);
		
		next(
		text.getText().toString());
	}
	private static Bitmap bitmap;
	private static DrawerLayout drawer;
	itemSelected(DrawerLayout drawer){
		this.drawer=drawer;
	}
	private static void next(String str){
		Dialog Dialog=
			new Dialog(drawer.getContext());
		Dialog.setContentView(R.layout.alertdialog_qrcode);
		ImageView Img=(ImageView) Dialog.findViewById(R.id.alertdialog_ImageView);
		try{
			bitmap = EncodingHandler.createQRCode(str, 400,0xF50057,0xff000000);
			Img.setImageBitmap(bitmap);
		}
		catch(WriterException e){}
		Dialog.show();
	}
	
	public static void set(Context context,MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.more:break;
			case R.id.qrcode:
				AlertDialog.Builder alert_edit=new AlertDialog.Builder(drawer.getContext())
				.setView(R.layout.alertdialog_edittext)
				.setTitle("请输入需要转换的字符串")
				.setPositiveButton("ok",new itemSelected(drawer));
				alert_edit_obj=alert_edit.show();
				break;
			case android.R.id.home:
				if(drawer.isDrawerOpen(Gravity.START)){
					drawer.closeDrawer(Gravity.START);
				}else{
					drawer.openDrawer(Gravity.START);
				}
				break;
			case R.id.exit:System.exit(0);break;
			case R.id.about:
				new AlertDialog.Builder(context)
					.setMessage("应用包名:"+drawer.getContext()
					.getPackageName()+"\n应用dataDir:"+drawer.getContext()
					.getApplicationInfo().dataDir)
					.setTitle(R.string.about_menu)
					.setPositiveButton("确定",
					new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							// TODO: Implement this method

						}
					})
					.show();break;
			default:Toast.makeText(drawer.getContext(),"别点了，代码没写",Toast.LENGTH_SHORT).show();

		}
	}
}
