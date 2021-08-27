package it.skinjobs.main;

public class CurrentState {
    private int activeThreads;
    private int countTaskDone;
    
    public int getActiveThreads() {
        return activeThreads;
    }
    public void setActiveThreads(int activeThreads) {
        synchronized(this) {
            this.activeThreads = activeThreads;
        }
        
    }
    public int getCountTaskDone() {
        return countTaskDone;
    }
    public void setCountTaskDone(int totalThreads) {
        synchronized(this) {
            this.countTaskDone = totalThreads;
            System.out.println("Init: actual: " + this.countTaskDone + " - numThread: " + this.activeThreads);
        }
    }

    public void incrementTaskDone() {
        synchronized(this) {
            this.countTaskDone++;
        }
    }

    public void incrementActiveThreads() {
        synchronized(this) {
            this.activeThreads++;
            System.out.println("Start: actual: " + this.countTaskDone + " - numThread: " + this.activeThreads);
        }
    }

    public void decrementActiveThreads() {
        synchronized(this) {
            this.activeThreads--;
            System.out.println("End: actual: " + this.countTaskDone + " - numThread: " + this.activeThreads);
        }
    }

    
}
