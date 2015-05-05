package com.imp.bluetooth;

import android.bluetooth.BluetoothSocket;

import com.imp.bluetooth.BluetoothServerThread;

public class MessageProcessing {
	private static boolean listening = false;
	
	public static void listenForMessages(final BluetoothSocket socket) {
		listening = true;
		String result = "";
		new BluetoothServerThread(socket,listening).start();
	}
	
	
}
