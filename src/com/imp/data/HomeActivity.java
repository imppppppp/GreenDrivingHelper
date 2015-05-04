package com.imp.data;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.greendrivinghelper.R;
import com.imp.bluetooth.BluetoothSet;

public class HomeActivity extends Activity{
	private ImageView IV1;
	private BluetoothSet btSet;
	private BluetoothAdapter bluetooth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		bluetooth = BluetoothAdapter.getDefaultAdapter();
		btSet = new BluetoothSet(HomeActivity.this,bluetooth);
		//��ȡͼƬ
		IV1=(ImageView)this.findViewById(R.id.blue);
		IV1.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//�������ͼƬʵ����������
				btSet.openBluetooth();
				//��ת���豸�б�
				Intent localIntent = new Intent();
				localIntent.setClass(HomeActivity.this,RealTimeDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
	
	}
	
	

}
