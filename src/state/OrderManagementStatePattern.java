package state;

/**
 * ============================================================================
 * ORDER MANAGEMENT USING STATE DESIGN PATTERN
 * ============================================================================
 *
 * States:
 *
 * WaitingForPayment
 *        ↓ pay()
 *
 * Placed
 *        ↓ startPreparing()
 *
 * Preparing
 *        ↓ dispatch()
 *
 * OnTheWay
 *        ↓ deliver()
 *
 * Delivered
 *
 * Cancellation Flow:
 *
 * WaitingForPayment -> Cancelled
 * Placed            -> Cancelled
 *
 * Once preparation starts, cancellation is not allowed.
 *
 * ============================================================================
 */

interface OrderState {

    void pay(Order order);

    void startPreparing(Order order);

    void dispatch(Order order);

    void deliver(Order order);

    void cancel(Order order);
}

/**
 * Initial state.
 */
class WaitingForPaymentState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("Payment completed successfully.");
        order.setCurrentState(Order.PLACED_STATE);
    }

    @Override
    public void startPreparing(Order order) {
        System.out.println("Cannot start preparation before payment.");
    }

    @Override
    public void dispatch(Order order) {
        System.out.println("Cannot dispatch before payment.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order has not been dispatched.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order cancelled successfully.");
        order.setCurrentState(Order.CANCELLED_STATE);
    }
}

/**
 * Payment completed.
 * Restaurant has accepted the order.
 */
class PlacedState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("Payment is already completed.");
    }

    @Override
    public void startPreparing(Order order) {
        System.out.println("Preparation started.");
        order.setCurrentState(Order.PREPARING_STATE);
    }

    @Override
    public void dispatch(Order order) {
        System.out.println("Preparation has not started yet.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order has not been dispatched.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order cancelled successfully.");
        order.setCurrentState(Order.CANCELLED_STATE);
    }
}

/**
 * Food is being prepared.
 */
class PreparingState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("Payment is already completed.");
    }

    @Override
    public void startPreparing(Order order) {
        System.out.println("Preparation is already in progress.");
    }

    @Override
    public void dispatch(Order order) {
        System.out.println("Order dispatched successfully.");
        order.setCurrentState(Order.ON_THE_WAY_STATE);
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order must be dispatched first.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order cannot be cancelled after preparation has started.");
    }
}

/**
 * Order is with delivery partner.
 */
class OnTheWayState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("Payment is already completed.");
    }

    @Override
    public void startPreparing(Order order) {
        System.out.println("Order has already been prepared.");
    }

    @Override
    public void dispatch(Order order) {
        System.out.println("Order is already on the way.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order delivered successfully.");
        order.setCurrentState(Order.DELIVERED_STATE);
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order cannot be cancelled after dispatch.");
    }
}

/**
 * Terminal State.
 */
class DeliveredState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void startPreparing(Order order) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void dispatch(Order order) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Delivered orders cannot be cancelled.");
    }
}

/**
 * Terminal State.
 */
class CancelledState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("Order is cancelled.");
    }

    @Override
    public void startPreparing(Order order) {
        System.out.println("Order is cancelled.");
    }

    @Override
    public void dispatch(Order order) {
        System.out.println("Order is cancelled.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order is cancelled.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order is already cancelled.");
    }
}

/**
 * Context Class.
 *
 * Maintains current state and delegates behavior
 * to the active state object.
 */
class Order {

    public static final OrderState WAITING_FOR_PAYMENT_STATE =
            new WaitingForPaymentState();

    public static final OrderState PLACED_STATE =
            new PlacedState();

    public static final OrderState PREPARING_STATE =
            new PreparingState();

    public static final OrderState ON_THE_WAY_STATE =
            new OnTheWayState();

    public static final OrderState DELIVERED_STATE =
            new DeliveredState();

    public static final OrderState CANCELLED_STATE =
            new CancelledState();

    private OrderState currentState;

    public Order() {
        System.out.println("Order created.");
        this.currentState = WAITING_FOR_PAYMENT_STATE;
    }

    public void setCurrentState(OrderState currentState) {
        this.currentState = currentState;
    }

    public void pay() {
        currentState.pay(this);
    }

    public void startPreparing() {
        currentState.startPreparing(this);
    }

    public void dispatch() {
        currentState.dispatch(this);
    }

    public void deliver() {
        currentState.deliver(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }
}

/**
 * Driver Class.
 */
public class OrderManagementStatePattern {

    public static void main(String[] args) {

        Order order = new Order();

        order.dispatch();

        order.pay();

        order.startPreparing();

        order.dispatch();

        order.deliver();

        order.cancel();

        System.out.println("\n------------------\n");

        Order secondOrder = new Order();

        secondOrder.pay();

        secondOrder.cancel();

        secondOrder.startPreparing();
    }
}