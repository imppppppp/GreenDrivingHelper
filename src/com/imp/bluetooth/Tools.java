package com.imp.bluetooth;

import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;

/**
 * 该类用于获取单例对象，以及存储常量值。
 * 
 * **/
public class Tools {
	public static BluetoothSet btSet = null;
	public static BluetoothAdapter btAdapter = null;
	public static BluetoothDevice btDevice = null;
	public static UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	public static BluetoothSocket transferSocket = null;
	private BluetoothSocket transferSocket_client;
	
	public static String name = "OBDDTC";
	public String getName(){
		return name;
	}
	public UUID getUUID(){
		return uuid;
	}
	
	public static void setBluetoothSocket(BluetoothDevice device,UUID uuid){
		BluetoothSocket clientSocket = null;
		Log.v("MY","connectServerSocket1");
  	  	int sdk = Build.VERSION.SDK_INT;
    	Log.v("MY","SDK"+String.valueOf(sdk));
    	try{
			if(sdk >= 10){
	     		 clientSocket 
	     		  = device.createInsecureRfcommSocketToServiceRecord(uuid);
			}else {
	   		 clientSocket 
		          = device.createRfcommSocketToServiceRecord(uuid);
			} 
    	}catch(Exception e){}
    	transferSocket = clientSocket;
	}
	
}
