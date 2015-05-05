package com.imp.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.bluetooth.BluetoothSocket;
import android.util.Log;


public class BluetoothServerThread extends Thread{
	private static boolean firstTime = true;
	final int bufferSize = 1024;
	private BluetoothSocket socket = null;
	private boolean listening = false;//------------------------是否
	private InputStream instream;
	String atCommond;
	
	public BluetoothServerThread(BluetoothSocket socket,boolean listening){
//		this.incoming = incoming;
		this.socket = socket;
		this.listening = listening;
		if(firstTime){
			sendCommond("ATSOFF\r\n");
			sendCommond("ATHBT\r\n");
			firstTime = false;
		}
	}
	
	public void sendCommond(String atCommond){
		try {
			OutputStream outStream = socket.getOutputStream();
			byte[] byteArray = atCommond.getBytes();
			outStream.write(byteArray);
			outStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
