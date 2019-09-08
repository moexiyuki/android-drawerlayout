package com.drawableMenu;

import android.app.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.net.*;
import java.net.*;
import java.io.*;
import android.util.*;
import android.graphics.*;
import android.content.*;
public class MainActivity 
extends Activity implements ListView.OnItemClickListener
{

	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4){
		TextView textview =	(TextView) p2.findViewById(R.id.layout_list_item_TextView);
		Toast.makeText(MainActivity.this, "你点击了:" + textview.getText().toString(),
					   Toast.LENGTH_SHORT).show();
		// TODO: Implement this method
	}
	private ListView list_menu,list_img;

	private LinearLayout image;

	private DrawerLayout drawer;

	private  int 侧滑开关图标;

	private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = null;
       // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://q1.qlogo.cn/g?b=qq&nk=2022146043&s=640"));
       Intent intent=new Intent(Intent.ACTION_SEND);
	   intent.setType("image");
	   PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

 
        //   Intent hangIntent = new Intent();
		//hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       //    hangIntent.setClass(this, MainActivity.class);
        //    PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            notification = builder.setContentTitle("悬挂通知Title")
				.setContentText("悬挂通知Text")
				.setSmallIcon(R.mipmap.about)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
				.setAutoCancel(true)
				//.setContentIntent(pendingIntent)
				//悬挂式通知  悬挂在手机屏上方
				.setFullScreenIntent(pendingIntent, true)
				.build();
                notificationManager.notify(3, notification);
				
		//获取控件
		this.getViewObject();
		this.侧滑开关图标 = R.mipmap.ic_drawer;
		//加载侧滑组件
		this.loadDrawer();
		String[] Array_string=
			getResources().getStringArray(R.array.array_menu);
		ListAdapter list_adapter=
			//新建一个适配器
			new main_menu_list_adapter(
			this,
			R.layout.layout_list_item,
			R.id.layout_list_item_TextView,
			Array_string,
			R.id.layout_list_item_ImageView,
			resData.MENU_IMAGE_DATA);
		//设置适配器
		list_menu.setAdapter(list_adapter);
		//绑定item监听
		list_menu.setOnItemClickListener(this);
		this.loadDrawerImageSize();
		this.checkInternet();
    }
	//选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inf=getMenuInflater();
		inf.inflate(R.menu.optionsmenu_activity_main, menu);
        return true;
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		new itemSelected(drawer).set(this, item);
		return super.onOptionsItemSelected(item);
	}
//初始化数据
    void getViewObject(){
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		drawer = (DrawerLayout) findViewById(R.id.activitymainDrawerLayout1);
		image = (LinearLayout) findViewById(R.id.menu_icon);
		list_menu = (ListView) findViewById(R.id.list_menu);
		list_img = (ListView) findViewById(R.id.img_list);
	}
	void loadDrawer(){
		setActionBar(toolbar);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ActionBarDrawerToggle to=new ActionBarDrawerToggle(this, drawer, 侧滑开关图标, R.string.about_menu, R.string.app_name);
		to.syncState();
		drawer.setDrawerListener(to);
	}
	void loadDrawerImageSize(){
		//获取图片w,h
		imageInfoTest ss=new imageInfoTest(this, getResources(), R.mipmap.image_1);
		ss.printImageSize();
		ViewGroup.LayoutParams size=image.getLayoutParams();
		String 宽=getResources().getString(R.string.height_image);
		float 宽2=Float.parseFloat(宽);
		//计算图片的高
		float 高2=宽2 * ss.getImageSize();
		size.height = (int)高2;
		image.setLayoutParams(size);
	}
	void loadImageList(){
		new	 doInBackGround_getUrlAndData(list_img)
			.execute(resData.imageURL);
	}
	void checkInternet(){
		ConnectivityManager netsy= (ConnectivityManager)
		getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo 
		gprs=netsy.getNetworkInfo
		(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo 
		wifi=netsy.getNetworkInfo
		(ConnectivityManager.TYPE_WIFI);
		if(gprs.isConnected()|wifi.isConnected()){
			//Log.i("INTERNET", "HAVE");
			//加载图片列表
			loadImageList();
		}else{
			Toast.makeText(MainActivity.this,
			"please check your internet",
			Toast.LENGTH_SHORT).show();
		}
	}
}
