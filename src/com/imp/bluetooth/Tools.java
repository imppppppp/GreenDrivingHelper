package com.imp.bluetooth;

import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

/**
 * 该类用于获取单例对象，以及存储常量值。
 * 
 * **/
public class Tools {
	public static BluetoothSet btSet = null;
	public static BluetoothAdapter btAdapter = null;
	public static BluetoothDevice btDevice = null;
	public UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	public BluetoothSocket transferSocket;
	private BluetoothSocket transferSocket_client;
	
	private String name = "OBDDTC";
	public String getName(){
		return name;
	}
	public UUID getUUID(){
		return uuid;
	}
	
}
