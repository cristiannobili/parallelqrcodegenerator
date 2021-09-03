package it.skinjobs.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadContainer {

    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private int numTasks;
    
    @Autowired
	Generator generator;

    private GeneratorRunnable runnable;

    private void createThread() {
        Thread thread = new Thread(this.runnable);
        thread.start();
    }

    public void runner(int numTask) throws Exception{
        this.numTasks = numTask;
        CurrentState state = new CurrentState();
        state.setActiveThreads(0);
        state.setCountTaskDone(0);
        this.runnable = new GeneratorRunnable();
        runnable.setGenerator(generator);
        runnable.setState(state);
        while (state.getCountTaskDone() < numTasks) {
            if (state.getActiveThreads() < NUM_THREADS && 
                numTasks - state.getCountTaskDone() > state.getActiveThreads()) {
                this.createThread();
                state.incrementActiveThreads();
            }
            else {
                Thread.sleep(1); // only for test purposes
            }
        }
    }
}
