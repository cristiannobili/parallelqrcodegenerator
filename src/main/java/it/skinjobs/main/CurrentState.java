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

    public void setCountTaskDone(int countTaskDone) {
        int countTaskDoneLocal;
        int activeThreadsLocal;
        synchronized(this) { //blocco cosi l'applicazione per 1000 volte in meno
            this.countTaskDone = countTaskDone; //assegnazione di variabile usa solo la memoria centrale
            countTaskDoneLocal = countTaskDone;
            activeThreadsLocal = this.activeThreads; // queste operazioni hanno un costo macchina nell'ordine di milionesimi di secondo
        }
        //la stampa è un'operazione di I/O che non usa soltanto la memoria centrale
        System.out.println("Init: tasksDone: " + countTaskDoneLocal + " - numActiveThreads: " + activeThreadsLocal); // operazione nell'ordine del millisecondo
    }

    public void incrementTaskDone() {
        synchronized(this) {
            this.countTaskDone++;
        }
    }

    public void incrementActiveThreads() {
        int countTaskDoneLocal;
        int activeThreadsLocal;
        synchronized(this) {
            this.activeThreads++;
            activeThreadsLocal = this.activeThreads;
            countTaskDoneLocal = this.countTaskDone;
        }
        System.out.println("Start: tasksDone: " + countTaskDoneLocal + " - numActiveThreads: " + activeThreadsLocal);
    }

    public void decrementActiveThreads() {
        int countTaskDoneLocal;
        int activeThreadsLocal;
        synchronized(this) {
            this.activeThreads--;
            activeThreadsLocal = this.activeThreads;
            countTaskDoneLocal = this.countTaskDone;
        }
        System.out.println("End: tasksDone: " + countTaskDoneLocal + " - numActiveThreads: " + activeThreadsLocal);
    }

    
}
