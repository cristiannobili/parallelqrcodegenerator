package it.skinjobs.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadPoolExecutor {
    
    @Autowired
    ThreadPool threadPool;

    private int maxNumberOfTasks;

    public void runner () throws Exception {
        this.maxNumberOfTasks = this.threadPool.getMaxNumberOfTasks();
        for(int i=0; i<maxNumberOfTasks; i++) {

            int taskNumber = i;
            threadPool.execute( () -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNumber ;
                System.out.println(message);
            });
        }
    
        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();
    }
    
    
     
}


