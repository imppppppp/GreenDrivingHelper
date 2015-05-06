package com.imp.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class mBluetootServer {
	public mBluetootServer(){
		startServerSocket(Tools.btAdapter);
	}
	
	private void startServerSocket(final BluetoothAdapter btAdapter){
		try{
			final BluetoothServerSocket btServer 
				= btAdapter.listenUsingRfcommWithServiceRecord(Tools.name, Tools.uuid);
			 Log.v("MY","startServerSocket");
			 new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Log.v("MY","开始监听客户端请求");
			              BluetoothSocket serverSocket = btServer.accept();
			              Log.v("MY", "Service is opening");
			              
					} catch (Exception e) {
						// TODO: handle exception
						Log.e("MY", "Blueooth client I/O Exception", e);
					}
				}
			}).start();
		}catch(Exception e){}
	}
	
}
