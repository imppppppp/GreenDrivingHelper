package com.imp.bluetooth;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.greendrivinghelper.R;
import com.imp.data.DataActivity;

public class DeviceListActivity extends Activity{
	private static String MacAddress;
//	private static String[] data;
	private static List<String> data = new ArrayList<String>();
	private static ListView listView;
	private static ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("MY","进入设备列表Activity");
		super.onCreate(savedInstanceState);
		Log.v("MY","super完成");
		setContentView(R.layout.device_list);
		Log.v("MY","与layout连接完成");
//		listView.setAdapter(null);
//		data.clear();
	
		Log.v("MY","data:"+data);
	
		adapter = new ArrayAdapter<String>(
				DeviceListActivity.this, android.R.layout.simple_list_item_1,data);
//		adapter.clear();
		Log.v("MY","创建AdapterList完成");
		listView = (ListView)findViewById(R.id.new_devices);
		Log.v("MY","设置listView完成");
		listView.setAdapter(adapter);
		Log.v("MY","将adapter添加到list完成");
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				setMacAddress(data.get(position).split(",")[1]);
				Log.i("MY","输出mac:"+MacAddress);
				boolean flag = Tools.btSet.bindBluetooth();
				Log.i("MY","是否绑定成功。flag:"+flag);
				//这个地方我也没整明白。。。就是你绑定蓝牙的时候，它会留在DeviceListActivity这个活动页面。
				//可是失败的时候，却会跳转到你想要的地方。另外，建议，还是不要跳转到数据区了。
				//你会丢失掉下面的TAB切换。所以，最好还是跳转到Main.....
				if(flag){
					Toast.makeText(DeviceListActivity.this, "蓝牙已连接", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(DeviceListActivity.this, "蓝牙连接失败！", Toast.LENGTH_SHORT).show();
				}
				Intent intent1 = new Intent(DeviceListActivity.this,DataActivity.class);
				startActivity(intent1);
			}
		});
	}
	public static ListView getListView(){
		return listView;
	}
	public static String getMacAddress(){
		return MacAddress;
	}
	public static void setMacAddress(String address){
		MacAddress = address;
	}
	public static void setdata(String[]arg){
		data.clear();
		for(int i=0;i<arg.length;i++){
			data.add(arg[i]);
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		try{
			listView.setAdapter(null);
			listView.removeAllViews();
			adapter.clear();
			data.clear();
			finish();
		}catch(Exception e){}
		super.onBackPressed();
	}
	
}
