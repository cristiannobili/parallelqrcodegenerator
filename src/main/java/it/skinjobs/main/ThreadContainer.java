package it.skinjobs.main;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadContainer implements ThreadDelegate{

    // private static final int NUM_THREAD = 10;
    // private static final int TOTAL = 1000;
    // private int actual;
    // private  int activeThreads;

    // @Autowired
	// Generator generator;

    // public void runner() throws Exception{
    //     this.actual = 0;
    //     this.activeThreads = 0;
    //     for(int i = 0; i < NUM_THREAD; i++) {
    //             GeneratorThread thread = new GeneratorThread();
    //             this.activeThreads++;
    //             thread.setGenerator(generator);
    //             thread.setDelegate(this);
    //             thread.start();
    //             System.out.println("Thread started, actual: " + this.actual + " - numThread: " + this.activeThreads);
    //     }
    // }

    // @Override
    public void complete(GeneratorThread thread) throws Exception{
    //     this.actual++;
    //     if (this.actual < TOTAL) {
    //         thread.join();
    //         thread.start();
    //     }
    //     System.out.println("Thread ended, actual: " + this.actual + " - numThread: " + this.activeThreads);
    }
}
