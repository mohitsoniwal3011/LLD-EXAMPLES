package state;

/**
 * ============================================================================
 * STATE DESIGN PATTERN
 * ============================================================================
 *
 * Definition:
 * The State Pattern is a behavioral design pattern that allows an object
 * to change its behavior when its internal state changes.
 *
 * Instead of maintaining large if-else or switch statements, behavior is
 * delegated to separate state objects.
 *
 * The Context object delegates work to the currently active State object.
 *
 * ============================================================================
 * PROBLEM
 * ============================================================================
 *
 * Without State Pattern:
 *
 * class TrafficLight {
 *
 *     String state;
 *
 *     void handle() {
 *         if(state.equals("RED")) {
 *             ...
 *         }
 *         else if(state.equals("GREEN")) {
 *             ...
 *         }
 *         else if(state.equals("YELLOW")) {
 *             ...
 *         }
 *     }
 * }
 *
 * As more states are added, the conditional logic becomes difficult
 * to maintain.
 *
 * ============================================================================
 * SOLUTION
 * ============================================================================
 *
 * Move state-specific behavior into dedicated classes.
 *
 *                     TrafficState
 *                           ^
 *                           |
 *        -----------------------------------------
 *        |                  |                     |
 *     RedLight         GreenLight          YellowLight
 *
 * Context:
 *     TrafficManager
 *
 * ============================================================================
 * WALKTHROUGH
 * ============================================================================
 *
 * Initial State:
 *
 * TrafficManager -> RedLight
 *
 * Iteration 1:
 *     RedLight.handleState()
 *     Prints: "Red Light"
 *     Changes state to GreenLight
 *
 * Iteration 2:
 *     GreenLight.handleState()
 *     Prints: "Green Light"
 *     Changes state to YellowLight
 *
 * Iteration 3:
 *     YellowLight.handleState()
 *     Prints: "Yellow Light"
 *     Changes state to RedLight
 *
 * Cycle:
 *
 * Red -> Green -> Yellow -> Red -> ...
 *
 * ============================================================================
 * REAL WORLD EXAMPLES
 * ============================================================================
 *
 * 1. Traffic Signal
 * 2. ATM Machine
 * 3. Vending Machine
 * 4. Order Processing System
 * 5. Media Player
 *
 * ============================================================================
 * BENEFITS
 * ============================================================================
 *
 * 1. Removes large if-else blocks.
 * 2. Encapsulates state-specific behavior.
 * 3. Follows Open/Closed Principle.
 * 4. Makes transitions easier to manage.
 *
 * ============================================================================
 * PARTICIPANTS
 * ============================================================================
 *
 * State:
 *     TrafficState
 *
 * Concrete States:
 *     RedLight
 *     GreenLight
 *     YellowLight
 *
 * Context:
 *     TrafficManager
 *
 * ============================================================================
 */

/**
 * State interface.
 *
 * Every state must implement this behavior.
 */
interface TrafficState {

    void handleState(TrafficManager manager);
}

/**
 * Concrete RED state.
 */
class RedLight implements TrafficState {

    private TrafficState nextState;

    public void setNextState(TrafficState nextState) {
        this.nextState = nextState;
    }

    @Override
    public void handleState(TrafficManager manager) {

        System.out.println("Red Light : Stop");

        manager.setState(nextState);
    }
}

/**
 * Concrete GREEN state.
 */
class GreenLight implements TrafficState {

    private TrafficState nextState;

    public void setNextState(TrafficState nextState) {
        this.nextState = nextState;
    }

    @Override
    public void handleState(TrafficManager manager) {

        System.out.println("Green Light : Please Proceed");

        manager.setState(nextState);
    }
}

/**
 * Concrete YELLOW state.
 */
class YellowLight implements TrafficState {

    private TrafficState nextState;

    public void setNextState(TrafficState nextState) {
        this.nextState = nextState;
    }

    @Override
    public void handleState(TrafficManager manager) {

        System.out.println("Yellow Light : Move Cautiously");

        manager.setState(nextState);
    }
}

/**
 * Context class.
 *
 * Maintains the current state and delegates
 * behavior to the active state object.
 */
class TrafficManager {

    private TrafficState currentState;

    public TrafficManager(TrafficState initialState) {
        this.currentState = initialState;
    }

    public void setState(TrafficState state) {
        this.currentState = state;
    }

    public void changeState() {
        currentState.handleState(this);
    }
}

/**
 * Demo class.
 */
public class TrafficLightStateDesignPattern {

    public static void main(String[] args) throws InterruptedException {

        RedLight redLight = new RedLight();
        GreenLight greenLight = new GreenLight();
        YellowLight yellowLight = new YellowLight();

        redLight.setNextState(greenLight);
        greenLight.setNextState(yellowLight);
        yellowLight.setNextState(redLight);

        TrafficManager manager = new TrafficManager(redLight);

        for (int i = 0; i < 10; i++) {

            manager.changeState();

            Thread.sleep(1000);
        }
    }
}