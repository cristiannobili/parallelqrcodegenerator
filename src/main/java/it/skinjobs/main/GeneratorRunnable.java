package it.skinjobs.main;
public class GeneratorRunnable implements Runnable {     
    
    private Generator generator;
    private CurrentState state;

    public void setState(CurrentState state) {
        this.state = state;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public void run() {
        generator.generate();
        try {
            this.state.incrementTaskDone();
            this.state.decrementActiveThreads();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }
}
