package com.imp.bluetooth;

import java.io.IOException;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;

/*实现向EST发送数据*/
public class mBluetoothSend extends Thread{
	private static BluetoothSocket btSocket;
	String atCommond;
	public mBluetoothSend(BluetoothSocket bsSocket,String atCommond) {
		// TODO Auto-generated constructor stub
		this.btSocket = bsSocket;
		this.atCommond = atCommond;
	}
	public void sendCommond(String atCommond){
		try {
			OutputStream outStream = btSocket.getOutputStream();
			byte[] byteArray = atCommond.getBytes();
			outStream.write(byteArray);
			outStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		OutputStream outStream;
//	      try {
//	        outStream = bsSocket.getOutputStream();
//	        
//
//	        // Add a stop character.
//	        byte[] byteArray = (message + " ").getBytes();
//	        byteArray[byteArray.length - 1] = 0;
//
//	        outStream.write(byteArray);
//	      } catch (IOException e) { 
//	        Log.e(TAG, "Message send failed.", e);
//	      }
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			OutputStream outStream = btSocket.getOutputStream();
			byte[] byteArray = atCommond.getBytes();
			outStream.write(byteArray);
			outStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
