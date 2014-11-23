package freakrware.standards.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import freakrware.privat.standards.android.resources.Standards_interface;

public class ThreadPooledServer implements Runnable,Standards_interface,Config_interfaces{

    
	protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_COUNT);
	public SysTray mSysTray;
	

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
                  return;
                }
                standard.exception_catch(e);
                throw new RuntimeException("Error accepting client connection", e);
            }
            mSysTray.clientip = clientSocket.getLocalSocketAddress();
            mSysTray.update("C");
            this.threadPool.execute(new ServerRunnable(clientSocket,mSysTray));
        }
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
        } catch (IOException e) {
        	standard.exception_catch(e);
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
        	standard.exception_catch(e);
            throw new RuntimeException("Cannot open port "+PORT, e);
        }
    }
}
