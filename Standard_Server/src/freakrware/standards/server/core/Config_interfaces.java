package freakrware.standards.server.core;

public interface Config_interfaces {
	
	static final int PORT = 15000;
	static final int THREAD_POOL_COUNT = 20;
	final ThreadPooledServer mTPServer = new ThreadPooledServer();
	final SysTray mSysTray = new SysTray(mTPServer);
	
	

}
