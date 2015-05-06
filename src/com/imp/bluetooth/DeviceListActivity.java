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
import com.imp.Main.MainActivity;

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
		Log.v("MY","data:"+data);
		adapter = new ArrayAdapter<String>(
				DeviceListActivity.this, android.R.layout.simple_list_item_1,data);
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
				Toast.makeText(DeviceListActivity.this, "蓝牙已连接", Toast.LENGTH_SHORT).show();
				MessageProcessing.getBluetoothSocket(); //获取蓝牙socket，并设置为Tools类变量。
				new MessageProcessing(Tools.transferSocket);//开始xxxxx
				
				Intent intent1 = new Intent(DeviceListActivity.this,MainActivity.class);
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
