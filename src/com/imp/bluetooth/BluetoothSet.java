package com.imp.bluetooth;

import java.util.ArrayList;

import com.imp.home.HomeActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class BluetoothSet {
	//private BluetoothAdapter mBtAdapter = null;
	//change
	public static BluetoothAdapter mBtAdapter = null;
	public static int mState;//蓝牙连接状态
	private Boolean isBusy;//串口是否忙碌
	private ProgressDialog impDialog = null;//进度条
	private Context impContext;//上下文
	private boolean is_bonded;
//	private static final int ENABLE_BLUETOOTH = 1;
	private String dStarted = BluetoothAdapter.ACTION_DISCOVERY_STARTED;
	private String dFinished = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
	private String[]s_temp = null;
	private ArrayList<BluetoothDevice> deviceList = new ArrayList<BluetoothDevice>();
	public static BluetoothDevice device = null;
	public BluetoothSet(Context impContext,BluetoothAdapter bluetooth) {
		// TODO Auto-generated constructor stub
		this.impContext = impContext;
		BluetoothSet.mBtAdapter = bluetooth;
	}
	
	/*判断是否支持本地蓝牙设备*/
	public Boolean isSupported(){
		if (mBtAdapter == null)
			return false;		
		else		
			return true;
	}
	
	/*判断蓝牙是否已连接----------------------------------------*/
//	public Boolean isConnected(){
//		if(mBtService.getState() == BluetoothService.STATE_CONNECTED){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
	
	/*设置蓝牙连接状态*/
	private synchronized void setState(int state) {
        mState = state;
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
			//iimpDialog = ProgressDialog.show(impContext, "提示", "打开蓝牙中...",false);
		    Log.v("MY","蓝牙未开启");
		    //iimpDialog.cancel();
		    //impContext.startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
		    mBtAdapter.enable();
			Log.v("MY","蓝牙开启成功");
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
	
	
    //搜索周围蓝牙设备。
	public void findBluetooth(){
//		if (!mBtAdapter.isEnabled()){
//			mBtAdapter.enable();
//		}
		//开始查询
		mBtAdapter.startDiscovery();
		impDialog = new ProgressDialog(impContext);  
        impDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条  
        impDialog.setTitle("提示");//设置标题  
        impDialog.setMessage("正在搜索周边蓝牙设备...");  
        impDialog.setIndeterminate(false);//设置进度条是否为不明确  
        impDialog.setCancelable(true);//设置进度条是否可以按退回键取消  
        impDialog.show();     
		impContext.registerReceiver(discoveryMonitor, new IntentFilter(dStarted));
		impContext.registerReceiver(discoveryMonitor, new IntentFilter(dFinished));
		impContext.registerReceiver(discoveryResult, 
				new IntentFilter(BluetoothDevice.ACTION_FOUND));
	}
	
    //绑定蓝牙
	public boolean bindBluetooth(){
		Log.i("MY","toGetRemote");
		device = mBtAdapter.getRemoteDevice(DeviceListActivity.getMacAddress());
		Log.i("MY","GetRemote");
		try{

			//若未配对
			if(device.getBondState()!=BluetoothDevice.BOND_BONDED){
				is_bonded = ClsUtils.createBond(device.getClass(), device);
				Log.i("MY","蓝牙是否已绑定:"+mBtAdapter.getState()+is_bonded);
			}
			//已配对
		    else{
				//ClsUtils.removeBond(device.getClass(), device);
		    	is_bonded = ClsUtils.createBond(device.getClass(), device);
		    	is_bonded = ClsUtils.createBond(device.getClass(), device);
		    	Log.i("MY","蓝牙是否已绑定:"+mBtAdapter.getState()+!is_bonded);
		    	is_bonded = !is_bonded;
			}
			
		}catch(Exception e){
			Log.i("MY","getRemoteException");
			e.printStackTrace();
		}
		return is_bonded;
	}
	
	
	
	
	/////////////////////////////
	BroadcastReceiver discoveryMonitor = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(dStarted.equals(intent.getAction()) ){
				Log.v("MY","Discovery Started");
			}
			else if(dFinished.equals(intent.getAction())){
				deviceList.clear();
				Log.v("MY","Discovery Completed");
				DeviceListActivity.setdata(s_temp);
				Log.v("MY","s.toString == "+s_temp.toString());
				impDialog.cancel();
				Intent intent1 = new Intent(impContext,DeviceListActivity.class);
				impContext.startActivity(intent1);
			}
		}
	};
	
BroadcastReceiver discoveryResult = new BroadcastReceiver(){
		
		public void onReceive(Context context, Intent intent) {
			
			String remoteDeviceName = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
			BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			if(!deviceList.contains(remoteDevice)){
				deviceList.add(remoteDevice);
			}
			StringBuilder str = new StringBuilder();
			try{
			for (BluetoothDevice reD : deviceList) {
				str.append(reD.getName()+","+reD.getAddress()+"\r\n");
			}
			s_temp = str.toString().split("\r\n");
			}catch(Exception e){}
			
		}
		
	};
	
	//////////////////
	
}
