package freakrware.standards.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import freakrware.privat.standards.android.resources.Standards_interface;

public class ServerRunnable implements Runnable,Standards_interface{

    protected Socket clientSocket = null;
    private SysTray tray;
	 

    public ServerRunnable(Socket clientSocket, SysTray tray) {
        this.clientSocket = clientSocket;
        this.tray   = tray;
        this.tray.clientip = clientSocket.getLocalSocketAddress();
       
    }

    public void run() {
        try {
        	BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
        	String line; 
        	while( (line = input.readLine())!=null){
            	System.out.println(line);
//            	String serverstatus = setup.get_Parameter(SERVERSTATUS);
//            	if (serverstatus.equals(SERVERSTATUS_OFF)){
//            		line = CONNECTION_CLOSE;
//            	}
            	new SFCP(line,input,output);
            	
            }  
        } catch (IOException e) {
//        	tray.update(setup.get_Parameter(SERVERSTATUS));
        	standard.exception_catch(e);
        	//e.printStackTrace();
        }
    }
}