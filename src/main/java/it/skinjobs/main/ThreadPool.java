package it.skinjobs.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadPool {
    private BlockingQueue<Runnable> queue;
    private List<PoolRunnable> runnables = new ArrayList<>();
    private boolean isStopped = false;
    int numberOfThreads; 
    int maxNumberOfTasks;

    
    public ThreadPool(){
        queue = new ArrayBlockingQueue<Runnable>(maxNumberOfTasks);

        for(int i=0; i<numberOfThreads; i++){
            PoolRunnable poolRunnable =
                    new PoolRunnable(queue);

            runnables.add(poolRunnable);
        }
        for(PoolRunnable runnable : runnables){
            new Thread(runnable).start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) {
        	throw
                new IllegalStateException("ThreadPool is stopped");
        }
        this.queue.offer(task);  //è come add, ma mentre add
        					     //non gestisce l'eccezione di capacità max
    }                            //max superata, offer lo fa internamente.

    public synchronized void stop(){
        this.isStopped = true;
        for(PoolRunnable runnable : runnables){
            runnable.doStop();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while(this.queue.size() > 0) {  //l'idea è fare in modo che i thread
            try {                       //che hanno finito aspettino finchè
                Thread.sleep(1);        //la coda non è vuota e che anche
            } catch (InterruptedException e) {  //gli altri finiscano.
                e.printStackTrace();
            }
        }
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = 3;
    }

    public int getMaxNumberOfTasks() {
        return this.maxNumberOfTasks;
    }

    public void setMaxNumberOfTasks(int maxNumberOfTasks) {
        this.maxNumberOfTasks = 51;
    }
    

}
