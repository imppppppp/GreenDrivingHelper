package com.imp.bluetooth;

import java.io.IOException;
import java.io.InputStream;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MyServerThread extends Thread{
//	private StringBuilder incoming;
//	private String receiveBuffer = "";
	private static boolean firstTime = true;
	final int bufferSize = 1024;
	private BluetoothSocket socket = null;
	private boolean listening = false;
	private InputStream instream;
	private final Handler mHandler;

	
	public MyServerThread(BluetoothSocket socket,boolean listening,Handler handler){
//		this.incoming = incoming;
		this.socket = socket;
		this.listening = listening;
		this.mHandler = handler;
		if(firstTime){
			new mBluetoothSend(socket,"ATSOFF\r\n").start();
			Log.v("MY","关闭AMT！");
			//new mBluetoothSend(socket,"ATHBT\r\n").start();
		//	new mBluetoothSend(socket).sendCommond("ATHBT\r\n");
			firstTime = false;
		}else if(getDataThread.getcmdUser().equals("")){
			new mBluetoothSend(socket,"ATSOFF\r\n").start();
			//new mBluetoothSend(socket,getDataThread.getCommData()).start();
		}else{
			new mBluetoothSend(socket,getDataThread.getcmdUser()).start();
		}
		
	}
	
	public void run(){
		
		try {
			instream = socket.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int bytesRead = -1;
		byte[] buffer_temp = new byte[bufferSize];
		String result_temp = "";
		while(listening){
		try {
			while (listening) {
				bytesRead = instream.read(buffer_temp);
				Log.v("MY", "收到串口数据: " + (new String(buffer_temp,0,bytesRead)) + "(" + String.valueOf(bytesRead) + ")");
				String tempString = new String(buffer_temp,0,bytesRead);
        		result_temp += tempString;
        		if (result_temp.endsWith("\r\n")){
        			String strArray[] = result_temp.split("\r\n");
        			for(String stemp:strArray){			        				
        				//发送显示
        				Log.v("MY","接收数据为："+stemp);
        				if(mHandler != null){
        					
//        				mHandler.obtainMessage(BluetoothSet.MESSAGE_READ, stemp.length(), -1, stemp.getBytes())
//    	        		.sendToTarget();
        					
//        				Message msg = mHandler.obtainMessage(BluetoothSet.MESSAGE_READ);
//        				Log.i("MY","msg_read");
//        				Bundle bundle = new Bundle();
//        				Log.i("MY","创建Bundle");
//        				bundle.putString("RPS_RT", stemp);
//        				Log.i("MY","3333333333");
//        				msg.setData(bundle);
//        				Log.i("MY","4444444444");
//        				msg.sendToTarget();
//        				Log.i("MY","5555555555");
        					
        					getDataThread.setCommData(stemp);
        				}
	    				sleep(10L);//这个方法存在于Thread类中。。所以，你如果
	    				//要用的话，可以在这里创建一个线程。
        			}
        			//初始化接收缓存数据
//        			receiveBuffer = "";
        			result_temp = "";
        		}
        		if (instream.available() == 0) break;	

				}	
				//socket.close();
//			Log.v("MY","receive:"+incoming.toString());
			} catch (IOException e) {
//				Log.e("MY", "disconnected.Message received failed.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	}
}