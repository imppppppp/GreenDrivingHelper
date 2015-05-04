package com.imp.bluetooth;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.greendrivinghelper.R;

public class DeviceListActivity extends Activity{
	private static String MacAddress;
//	private static String[] data;
	private static List<String> data = new ArrayList<String>();
	private static ListView listView;
	private static ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("MY","�����豸�б�Activity");
		super.onCreate(savedInstanceState);
		Log.v("MY","super���");
		setContentView(R.layout.device_list);
		Log.v("MY","��layout�������");
//		listView.setAdapter(null);
//		data.clear();
	
		Log.v("MY","data:"+data);
	
		adapter = new ArrayAdapter<String>(
				DeviceListActivity.this, android.R.layout.simple_list_item_1,data);
//		adapter.clear();
		Log.v("MY","����AdapterList���");
		listView = (ListView)findViewById(R.id.new_devices);
		Log.v("MY","����listView���");
		listView.setAdapter(adapter);
		Log.v("MY","��adapter��ӵ�list���");
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				setMacAddress(data.get(position).split(",")[1]);
				finish();
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
