package com.imp.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.imp.bluetooth.BluetoothServerThread;

public class MessageProcessing {
	private static boolean listening = false;
	
	public static void getBluetoothSocket(){
		Tools.setBluetoothSocket(Tools.btDevice, Tools.uuid);
		//return Tools.transferSocket;
	}
	public MessageProcessing(final BluetoothSocket socket) {
		listening = true;
		String result = "";
		new BluetoothServerThread(socket,listening).start();
		(new BluetoothClientSocket(listening)).connectToServerSocket(Tools.btDevice, Tools.uuid);
		
		
	}
	
	
}
