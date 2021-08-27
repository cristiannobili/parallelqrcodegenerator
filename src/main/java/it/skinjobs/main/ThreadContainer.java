package it.skinjobs.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadContainer implements ThreadDelegate{

    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int TOTAL = 200;
    
    @Autowired
	Generator generator;

    private GeneratorRunnable runnable;

    synchronized private void createThread() {
      
        //runnable.run();
        Thread thread = new Thread(this.runnable);
        thread.start();
    }


    public void runner() throws Exception{
        CurrentState state = new CurrentState();
        state.setActiveThreads(0);
        state.setCountTaskDone(0);
        this.runnable = new GeneratorRunnable();
        runnable.setGenerator(generator);
        runnable.setState(state);
        while (state.getCountTaskDone() < TOTAL) {
            if (state.getActiveThreads() < NUM_THREADS && 
                TOTAL - state.getCountTaskDone() > state.getActiveThreads()) {
                this.createThread();
                state.incrementActiveThreads();
            }
            else {
                Thread.sleep(1);
            }
        }
    }

    @Override
    public void complete(GeneratorRunnable thread) throws Exception{
        synchronized(this) {
            // System.out.println(Thread.currentThread().getId() + " " + this.activeThreads);
            // if (this.countTaskDone < TOTAL && this.activeThreads < NUM_THREADS) {
            //     this.createThread();
            // }
            //System.out.println("Thread ended, actual: " + this.countTaskDone + " - numThread: " + this.activeThreads);
        }
       
    }

}
