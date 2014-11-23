package freakrware.standards.server.core;

import freakrware.privat.standards.android.resources.Standards_interface;


public class ServerMain implements Standards_interface,Config_interfaces{

	
	public static void main(String[] args) {
				
		mTPServer.mSysTray = mSysTray;
		mSysTray.start();
		new Thread(mTPServer).start();
	}
}