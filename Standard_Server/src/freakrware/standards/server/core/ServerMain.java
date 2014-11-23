package freakrware.standards.server.core;

import freakrware.privat.standards.android.resources.Standards_interface;


public class ServerMain implements Standards_interface{

	private static final int PORT = 15000;
//	public Standards standard = new Standards();

	public static void main(String[] args) {
				final ThreadPooledServer tpserver = new ThreadPooledServer(PORT);
				final SysTray st = new SysTray(tpserver, PORT);
				tpserver.tray = st;
				st.start();
				new Thread(tpserver).start();
				
				
		}
}