package it.skinjobs.main;

//Questo signor Generatore diventa il task da incodare nella blockingQueue del Threadpool

public class GeneratorThread implements Runnable{     
    
    private Generator generator;
    //private ThreadDelegate delegate;

    // public void setDelegate(ThreadDelegate delegate) {
    //     this.delegate = delegate;
    // }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public void run() {
        generator.generate();
        // try {
        //     this.delegate.complete(this);
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }
       
    }
}
