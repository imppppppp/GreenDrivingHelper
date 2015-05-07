package com.imp.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

public class mBluetoothSocket {
	
	private BluetoothAdapter bluetooth;
	private boolean listening = false;
	public static BluetoothSocket transferSocket;
	//private BluetoothSocket transferSocket_client;
    private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String name = "OBDDTC";
	public mBluetoothSocket(BluetoothAdapter bluetooth,BluetoothDevice device,Handler handler) {
		// TODO Auto-generated constructor stub
		startServerSocket(bluetooth,handler);
		Log.v("MY","UUID:"+uuid.toString());
		connectToServerSocket(device, uuid,handler);
	}
	
	private void startServerSocket(final BluetoothAdapter bluetooth,final Handler handler) {

	      try {
	    	  Log.v("MY","startServerSocket1");
	        final BluetoothServerSocket btserver = 
	          bluetooth.listenUsingRfcommWithServiceRecord(name, uuid);
	        //transferSocket=btserver;
	        Log.v("MY","startServerSocket2");
	        
	        new Thread(new Runnable() {
	          public void run() {
	            try {
	              // Block until client connection established.
	              Log.v("MY","开始监听客户端请求");
	              BluetoothSocket serverSocket = btserver.accept();
	              Log.v("MY", "Service is opening");
	              // Start listening for messages.
//	              StringBuilder incoming = new StringBuilder();
	              listenForMessages(serverSocket,handler);
	              // Add a reference to the socket used to send messages.
//	              transferSocket = serverSocket;
	            } catch (IOException e) {
	              Log.e("MY", "Server connection IO Exception", e);
	            }
	          }
	        }).start();
	     
	      } catch (IOException e) {
	        Log.e("MY", "Socket listener IO Exception", e);
	      }
	    }

	private void connectToServerSocket(final BluetoothDevice device,final UUID uuid,final Handler handler) {
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
			    	  Log.v("MY","connectServerSocket1");
			    	  int sdk = Build.VERSION.SDK_INT;
		          	Log.v("MY","SDK"+String.valueOf(sdk));
		          	BluetoothSocket clientSocket;
		          	if(sdk >= 10){
		          		 clientSocket 
		          		  = device.createInsecureRfcommSocketToServiceRecord(uuid);
	            	}else {
	            		 clientSocket 
				          = device.createRfcommSocketToServiceRecord(uuid);
					}     
			        
			        Log.v("MY","clientSocket"+clientSocket.toString());
			        Log.v("MY","connectServerSocket2");
			        // Block until server connection accepted.
			        
			        transferSocket = clientSocket;
			        clientSocket.connect();
			        Log.v("MY", "Client is asking for connection");
			        // Start listening for messages.
			      
//			        	StringBuilder incoming = new StringBuilder();
			        	listenForMessages(clientSocket,handler);
//			        	Log.v("MY","receving:"+incoming.toString());
			        // Add a reference to the socket used to send messages.
//			        transferSocket_client = clientSocket;

			      } catch (IOException e) {
			        Log.e("MY", "Blueooth client I/O Exception", e);
			      }
			}
		}).start(); 
		
	    }
	

	private void listenForMessages(final BluetoothSocket socket,final Handler handler) {
		listening = true;
		String result = "";
		new MyServerThread(socket,listening,handler).start();
	}

}


