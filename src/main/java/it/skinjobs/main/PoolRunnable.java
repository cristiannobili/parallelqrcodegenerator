package it.skinjobs.main;

import java.util.concurrent.BlockingQueue;

public class PoolRunnable implements Runnable{
   
    private BlockingQueue<Runnable> queue;
    private Thread thread; 
    private boolean isStopped;

    public PoolRunnable (BlockingQueue<Runnable> queue) {
        this.queue = queue;
        this.isStopped = false;
    }

    @Override
    public void run(){
        this.thread = Thread.currentThread();
        while(!this.isStopped()){
            try{
                GeneratorThread generatorThread = (GeneratorThread) queue.take();
                generatorThread.run();
            } catch(Exception e){
                
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.thread.interrupt();
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }

}
