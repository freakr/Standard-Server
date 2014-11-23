package freakrware.standards.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledServer implements Runnable{

    private static final int THREAD_POOL_COUNT = 20;
	protected int          serverPort;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_COUNT);
	public SysTray tray;
	

    public ThreadPooledServer(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                	this.threadPool.shutdown();
                    System.out.println("Server Stopped.") ;
//                    tray.update(setup.get_Parameter(SERVERSTATUS));
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            tray.clientip = clientSocket.getLocalSocketAddress();
            tray.update("C");
            this.threadPool.execute(
                new ServerRunnable(clientSocket,tray));
        }
//        tray.update(setup.get_Parameter(SERVERSTATUS));
        this.threadPool.shutdownNow();
        System.out.println("Server Stopped.") ;
    }


    public synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
        	this.serverSocket.close();
//            setup.set_Parameter(SERVERSTATUS, SERVERSTATUS_OFF);
//            System.out.println(setup.get_Parameter(SERVERSTATUS));
//            tray.update(setup.get_Parameter(SERVERSTATUS));
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
//            setup.set_Parameter(SERVERSTATUS, SERVERSTATUS_ON);
//            System.out.println(setup.get_Parameter(SERVERSTATUS));
//            tray.update(setup.get_Parameter(SERVERSTATUS));
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port "+serverPort, e);
        }
    }
}
