package com.imp.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;

public class BluetoothClientSocket {
	
	private static boolean listening = false;
	public BluetoothClientSocket(boolean listening) {
		// TODO Auto-generated constructor stub
		BluetoothClientSocket.listening = listening;
	}
	public void connectToServerSocket(final BluetoothDevice device,final UUID uuid,final String cmd) {
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					BluetoothSocket clientSocket = Tools.transferSocket;
			        Log.v("MY","clientSocket"+clientSocket.toString());
			        Log.v("MY","connectServerSocket2");
			        // Block until server connection accepted.
			        clientSocket.connect();
			        Log.v("MY", "Client is asking for connection");
			        // Start listening for messages.
			      
//			        	StringBuilder incoming = new StringBuilder();
			        	listenForMessages(clientSocket,cmd);
//			        	Log.v("MY","receving:"+incoming.toString());
			        // Add a reference to the socket used to send messages.
//			        transferSocket_client = clientSocket;

			      } catch (IOException e) {
			        Log.e("MY", "Blueooth client I/O Exception", e);
			      }
			}
		}).start(); 
		
	    }
	
	public static synchronized void listenForMessages(final BluetoothSocket socket,final String cmd) {
		listening = true;
//		String result = "";
		new BluetoothServerThread(socket,listening,cmd).start();
	}
	
}
