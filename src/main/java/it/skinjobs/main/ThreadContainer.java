package it.skinjobs.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadContainer implements ThreadDelegate{

    private static final int NUM_THREADS = 10;
    private static final int TOTAL = 1000;
    private int countTaskDone;
    private  int activeThreads;

    @Autowired
	Generator generator;

    private void createThread() {
        GeneratorThread runnable = new GeneratorThread();
        this.activeThreads++;
        runnable.setGenerator(generator);
        runnable.setDelegate(this);
        System.out.println("Thread started, actual: " + this.countTaskDone + " - numThread: " + this.activeThreads);
        //runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
    }


    public void runner() throws Exception{
        this.countTaskDone = 0;
        this.activeThreads = 0;
        while ( this.activeThreads < NUM_THREADS) {
            this.createThread();
        }
    }

    @Override
    synchronized public void complete(GeneratorThread thread) throws Exception{
        this.countTaskDone++;
        this.activeThreads--;
        if (this.countTaskDone < TOTAL && this.activeThreads < NUM_THREADS) {
            this.createThread();
        }
        System.out.println("Thread ended, actual: " + this.countTaskDone + " - numThread: " + this.activeThreads);
    }
}
