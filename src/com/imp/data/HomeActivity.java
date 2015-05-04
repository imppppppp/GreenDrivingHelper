package com.imp.data;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.greendrivinghelper.R;
import com.imp.bluetooth.BluetoothSet;
import com.imp.bluetooth.DeviceListActivity;
import com.imp.bluetooth.Tools;

public class HomeActivity extends Activity{
	private ImageView IV1;
	private BluetoothSet btSet = null;
	private BluetoothAdapter bluetooth = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		bluetooth = BluetoothAdapter.getDefaultAdapter();
		Tools.btAdapter = bluetooth;
		btSet = new BluetoothSet(HomeActivity.this,bluetooth);
		Tools.btSet = btSet;
		//获取图片
		IV1=(ImageView)this.findViewById(R.id.blue);
		IV1.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//点击蓝牙图片实现蓝牙连接
				btSet.openBluetooth();
				
				btSet.findBluetooth();
			}
	    });
		
	
	}
	
	

}
