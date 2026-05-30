/**
 * Facade Pattern
 * --------------
 * The Facade Pattern is a structural design pattern that provides a simplified
 * interface to a complex subsystem.
 *
 * Problem:
 * --------
 * Suppose a user wants to watch a movie in a home theatre setup.
 *
 * Without a Facade, the client would need to know about and interact with
 * multiple subsystem classes directly:
 *
 * Projector projector = new Projector();
 * SoundSystem soundSystem = new SoundSystem();
 * StreamingDevice streamingDevice = new StreamingDevice();
 * Lights lights = new Lights();
 *
 * projector.on();
 * lights.dim();
 * streamingDevice.on();
 * soundSystem.on();
 *
 * As the system grows, the client becomes tightly coupled to many classes and
 * must understand the correct sequence of operations.
 *
 * Solution:
 * ---------
 * Introduce a Facade that encapsulates the interaction between subsystem
 * components and exposes a simple, high-level operation.
 *
 * Example:
 *
 * HomeTheatreFacade facade = new HomeTheatreFacade();
 * facade.watchMovie();
 *
 * The client now interacts with a single class and does not need to know
 * how the individual components work together.
 *
 * Benefits:
 * ---------
 * - Reduces complexity for the client.
 * - Hides subsystem interaction details.
 * - Reduces coupling between client and subsystem classes.
 * - Provides a clean and easy-to-use API.
 * - Centralizes workflow orchestration logic.
 *
 * What it avoids:
 * ---------------
 * - Clients directly coordinating multiple subsystem objects.
 * - Duplicating orchestration logic across different clients.
 * - Tight coupling between clients and subsystem implementations.
 *
 * How this implementation follows the pattern:
 * --------------------------------------------
 * - Projector, SoundSystem, Lights, and StreamingDevice are subsystem classes.
 * - HomeTheatreFacade acts as the Facade.
 * - The Facade coordinates the subsystem interactions.
 * - The client invokes a single method (watchMovie()) instead of managing
 *   each subsystem individually.
 *
 * Note:
 * -----
 * In production applications, subsystem dependencies are typically injected
 * through the constructor for better testability and flexibility.
 *
 * Example:
 *
 * public HomeTheatreFacade(
 *         Projector projector,
 *         SoundSystem soundSystem,
 *         StreamingDevice streamingDevice,
 *         Lights lights) {
 *     ...
 * }
 *
 * In interview examples, the Facade often creates and manages the subsystem
 * objects internally to demonstrate the pattern more clearly.
 */
class Projector{
    public void on(){
        System.out.println("Turning projector on");
    }
}

class SoundSystem{
    public void on(){
        System.out.println("Turning sound on");
    }
}

class Lights{
    public void dim(){
        System.out.println("Turning lights dim");
    }
}

class StreamingDevice{
    public void on(){
        System.out.println("Turning streaming device on");
    }
}

public class HomeTheatreFacadePattern {

    private final Projector projector;
    private final SoundSystem soundSystem;
    private final StreamingDevice streamingDevice;
    private final Lights lights;

    public HomeTheatreFacadePattern(Projector projector, SoundSystem soundSystem, StreamingDevice streamingDevice, Lights lights) {
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.streamingDevice = streamingDevice;
        this.lights = lights;
    }

    public void watchMovie(){
        this.projector.on();
        this.lights.dim();
        this.streamingDevice.on();
        this.soundSystem.on();
    }
}

class HomeTheatreFacadeClient{
    public static void main(String[] args) {
        HomeTheatreFacadePattern pattern = new HomeTheatreFacadePattern(
                new Projector(),
                new SoundSystem(),
                new StreamingDevice(),
                new Lights()
        );

        pattern.watchMovie();
    }
}