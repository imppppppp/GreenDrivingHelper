package com.imp.bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BluetoothSet {
	private BluetoothAdapter mBtAdapter = null;
	private Boolean isBusy;//串口是否忙碌
	private ProgressDialog impDialog = null;//进度条
	private Context impContext;//上下文
	private static final int ENABLE_BLUETOOTH = 1;
	public BluetoothSet(Context impContext,BluetoothAdapter bluetooth) {
		// TODO Auto-generated constructor stub
		this.impContext = impContext;
		this.mBtAdapter = bluetooth;
	}
	
	/*判断是否支持本地蓝牙设备*/
	public Boolean isSupported(){
		if (mBtAdapter == null)
			return false;		
		else		
			return true;
	}
	
	/*判断蓝牙是否已连接----------------------------------------*/
	public Boolean isConnected(){
        return false;
	}
	
	/*蓝牙服务是否注册------------------------------------------*/
	
	/* 获取串口数据状态，true为忙碌*/
	public synchronized boolean getIsBusy(){
		return isBusy;
	}
	
	/*设置串口数据状态，true为忙碌*/
	public synchronized void setIsBusy(boolean status){
		this.isBusy = status;
	}
	
	/*打开本地蓝牙*/
	public void openBluetooth(){
		//若没打开
		if (!mBtAdapter.isEnabled()){	
			//impDialog = ProgressDialog.show(impContext, "提示", "打开蓝牙中...",false);
			//Log.v("MY","启动Progress");
		    Log.v("MY","启动bluetooth");
		    //impDialog.cancel();
		    impContext.startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
		    mBtAdapter.enable();
		    Toast.makeText(impContext, "开启蓝牙中", Toast.LENGTH_SHORT).show();
//		    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), ENABLE_BLUETOOTH);
//			new OpenBluetoothThread().start();
		}
		//若已打开
		else
		{
			//显示打开本地蓝牙失败。注册服务
//			registerService();
			Toast.makeText(impContext, "蓝牙已开启", Toast.LENGTH_SHORT).show();
		}
	}
	
}
