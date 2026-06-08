package command;

import java.util.Objects;
import java.util.Stack;

/**
 * THE COMMAND DESIGN PATTERN (Smart Remote Example)
 * <p>
 * <b>1. Definition:</b>
 * A behavioral design pattern that encapsulates a request as an object, thereby
 * letting you parameterize clients with different requests, queue or log requests,
 * and support undoable operations.
 * <p>
 * <b>2. Key Pattern Features in this Code:</b>
 * - Simple State Undo: Reversal is trivial here (On turns to Off, Off turns to On) because the target state is binary.
 * - Idempotency Guard: The Invoker prevents identical consecutive actions by peeking at the history stack history.
 * <p>
 * <b>3. Lifecycle Flow:</b>
 * Client Instantiates Light (Receiver) -> Binds Light to Commands -> Commands are passed to Remote (Invoker) to fire.
 */
public class RemoteControlCommandPattern {

    /**
     * The Client. Wires the components and runs execution/undo lifecycles.
     */
    public static void main(String[] args) {
        Light light = new Light();

        Command turnOnCommand = new TurnOnCommand(light);
        Command turnOffCommand = new TurnOffCommand(light);

        RemoteControl remoteControl = new RemoteControl();

        // 1. Attempt to undo an empty stack -> Prints "Nothing to undo!"
        remoteControl.undo();

        // 2. Turn light ON -> Prints "Lights got turned ON"
        remoteControl.execute(turnOnCommand);

        // 3. Duplicate Call -> Blocked by state guard. Prints "Action is already going on"
        remoteControl.execute(turnOnCommand);

        // 4. Undo -> Pops turnOnCommand, triggers undo(). Prints "Lights got turned OFF"
        remoteControl.undo();

        // 5. Turn light OFF -> Prints "Lights got turned OFF"
        remoteControl.execute(turnOffCommand);
    }
}


/**
 * ROLE: The Command Interface
 * <p>
 * Standardizes the structural contract. Any device command added in the future
 * (Stereo, AC, Fan) must implement this to remain compatible with the RemoteControl.
 */
interface Command {
    void execute();
    void undo();
}


/**
 * ROLE: The Receiver
 * <p>
 * The hardware layer component. Knows how to perform the actual work of changing
 * physical state, completely independent of the Command software abstraction layer.
 */
class Light {

    void turnOn() {
        System.out.println("Lights got turned ON");
    }

    void turnOff() {
        System.out.println("Lights got turned OFF");
    }
}


/**
 * ROLE: Concrete Command (Turn On)
 * <p>
 * Encapsulates the explicit intention to illuminate the target {@link Light} receiver.
 */
class TurnOnCommand implements Command {
    private final Light light;

    public TurnOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.turnOn();
    }

    @Override
    public void undo() {
        this.light.turnOff();
    }
}


/**
 * ROLE: Concrete Command (Turn Off)
 * <p>
 * Encapsulates the explicit intention to darken the target {@link Light} receiver.
 */
class TurnOffCommand implements Command {
    private final Light light;

    public TurnOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.turnOff();
    }

    @Override
    public void undo() {
        this.light.turnOn();
    }
}


/**
 * ROLE: The Invoker
 * <p>
 * Acts as the UI or physical controller. It executes operations dynamically, tracks
 * historical execution state, and filters out redundant user inputs using a stack check.
 */
class RemoteControl {
    private final Stack<Command> history = new Stack<>();

    /**
     * Executes incoming commands. Features a built-in state-guard check.
     * <p>
     * <b>Note:</b> Objects.equals relies on instance identity here. It checks if the exact
     * same object instance is being submitted twice consecutively.
     * * @param cmd The transaction to execute.
     */
    void execute(Command cmd) {
        // Look at the top of the history stack without popping it
        if (!history.isEmpty() && Objects.equals(cmd, history.peek())) {
            System.out.println("Action is already going on");
            return;
        }

        cmd.execute();
        this.history.push(cmd);
    }

    /**
     * Directly unrolls the top transaction in the command stack.
     */
    void undo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Nothing to undo!");
        }
    }
}