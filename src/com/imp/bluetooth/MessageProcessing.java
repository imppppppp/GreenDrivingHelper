package com.imp.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.content.IntentSender.SendIntentException;
import android.os.Handler;

public class MessageProcessing {
	private static boolean listening = false;
	public static String cmd = "ATSOFF\r\n";
	public static Handler handler = new Handler();
	public static void getBluetoothSocket(){
		Tools.setBluetoothSocket(Tools.btDevice, Tools.uuid);
		//return Tools.transferSocket;
	}
	public MessageProcessing(final BluetoothSocket socket) {
		listening = true;
		String result = "";
		//new BluetoothServerThread(socket,listening).start();
		Tools.btSet.setConnect();
		(new BluetoothClientSocket(listening)).connectToServerSocket(Tools.btDevice, Tools.uuid,cmd);	
	}
	
	static Runnable sendMessage = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			BluetoothClientSocket.listenForMessages(Tools.transferSocket, cmd);
			handler.postDelayed(sendMessage,1000);
		}
	};
	
	public static void setHandler(String command){
		handler.removeCallbacks(sendMessage);
		cmd = command;
		handler.postDelayed(sendMessage,1000);
	}
	
	
}
