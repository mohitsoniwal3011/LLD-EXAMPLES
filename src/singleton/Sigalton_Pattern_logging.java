public class Sigalton_Pattern_logging {

    // Static variable to hold the single instance of the class
    // Shared across the entire application
    private static Sigalton_Pattern_logging logger;

    // Private constructor prevents object creation from outside
    // This is the core idea behind Singleton Pattern
    private Sigalton_Pattern_logging(){}

    // Public static method to provide global access to the single object
    public static synchronized  Sigalton_Pattern_logging getInstance(){

        // Lazy initialization:
        // Object is created only when it is needed for the first time
        if (logger == null ){
            logger = new Sigalton_Pattern_logging();
        }

        // Return the same object every time
        return logger;
    }

    // Normal logging method
    public synchronized void info(String msg){
        System.out.println("MSG: " + msg);
    }
}