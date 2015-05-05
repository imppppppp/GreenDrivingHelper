package com.imp.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;

public class BluetoothClientSocket {
	
	private boolean listening = false;
	public BluetoothClientSocket(boolean listening) {
		// TODO Auto-generated constructor stub
		this.listening = listening;
	}
	public void connectToServerSocket(final BluetoothDevice device,final UUID uuid) {
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
//			    	  Log.v("MY","connectServerSocket1");
//			    	  int sdk = Build.VERSION.SDK_INT;
//		          	Log.v("MY","SDK"+String.valueOf(sdk));
//		          	BluetoothSocket clientSocket;
//		          	if(sdk >= 10){
//		          		 clientSocket 
//		          		  = device.createInsecureRfcommSocketToServiceRecord(uuid);
//	            	}else {
//	            		 clientSocket 
//				          = device.createRfcommSocketToServiceRecord(uuid);
//					}  
					BluetoothSocket clientSocket = Tools.transferSocket;
			        Log.v("MY","clientSocket"+clientSocket.toString());
			        Log.v("MY","connectServerSocket2");
			        // Block until server connection accepted.
			        clientSocket.connect();
			        Log.v("MY", "Client is asking for connection");
			        // Start listening for messages.
			      
//			        	StringBuilder incoming = new StringBuilder();
			        	listenForMessages(clientSocket);
//			        	Log.v("MY","receving:"+incoming.toString());
			        // Add a reference to the socket used to send messages.
//			        transferSocket_client = clientSocket;

			      } catch (IOException e) {
			        Log.e("MY", "Blueooth client I/O Exception", e);
			      }
			}
		}).start(); 
		
	    }
	
	private void listenForMessages(final BluetoothSocket socket) {
		listening = true;
//		String result = "";
		new BluetoothServerThread(socket,listening).start();
	}
	
}
