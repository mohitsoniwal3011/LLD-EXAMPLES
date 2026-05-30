package observer;


import java.util.ArrayList;
import java.util.List;

/**
 * Observer Design Pattern
 *
 * Definition:
 * The Observer Pattern defines a one-to-many dependency between objects
 * so that when one object (Subject) changes its state, all of its dependents
 * (Observers) are notified and updated automatically.
 *
 * Intent:
 * - Establish communication between a publisher and multiple subscribers.
 * - Allow objects to be notified about changes without tight coupling.
 * - Enable dynamic registration and removal of interested observers.
 *
 * Components in this Example:
 *
 * Subject (Publisher):
 * - Stock
 * - Maintains a list of observers.
 * - Notifies all registered observers whenever the stock price changes.
 *
 * Observer:
 * - StockObserver
 * - Defines the contract for receiving updates.
 *
 * Concrete Observer:
 * - StockSubscriber
 * - Implements the Observer interface and reacts to stock price updates.
 *
 * Flow:
 * 1. Observers subscribe to a Stock.
 * 2. Stock price changes through updatePrice().
 * 3. Stock notifies all registered observers.
 * 4. Each observer receives the updated Stock object and reacts accordingly.
 *
 * Advantages:
 * - Promotes loose coupling between Subject and Observers.
 * - Supports runtime subscription and unsubscription.
 * - Easy to extend with new observer types without modifying existing code.
 * - Follows the Open/Closed Principle.
 *
 * Disadvantages:
 * - Notification chains can become difficult to debug.
 * - Large numbers of observers may impact performance.
 * - Observers may receive updates they are not interested in.
 *
 * Real-World Examples:
 * - YouTube channel subscriptions
 * - Instagram/Twitter followers
 * - Stock market alerts
 * - Java Event Listeners
 * - Spring Application Events
 * - Kafka consumers receiving messages
 * - WebSocket notification systems
 *
 * Pattern Mapping:
 * - Subject -> Stock
 * - Observer -> StockObserver
 * - Concrete Observer -> StockSubscriber
 *
 * In this implementation, the Subject passes itself to the Observer
 * (Pull Model), allowing observers to access any required state from
 * the Stock object without changing the Observer interface in the future.
 */



/**
 * Observer interface.
 * All observers interested in stock updates must implement this contract.
 */
interface StockObserver{
    void update(Stock stock);
}

class StockSubscriber implements StockObserver{

    private final String name;

    StockSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update( Stock stock) {
        System.out.printf("Hey %s, %s's price just" +
                " got updated to %d \n", this.name, stock.getName(), stock.getPrice());
    }
}

/**
 * Subject (Publisher).
 * Maintains a list of observers and notifies them whenever
 * its internal state (stock price) changes.
 */
class Stock {
    private final String name;

    public int getPrice() {
        return price;
    }

    private int price;
    private final List<StockObserver> observers;


    Stock(String name,int price) {
        this.name = name;
        this.price = price;
        observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void updatePrice(int price){
        this.price = price;
        notifySubscribers();
    }

    void notifySubscribers() {
        for(StockObserver ob: observers){
            ob.update(this);
        }
        System.out.println("==========Update Complete==========");
    }

    void addSubs(StockObserver sub){
        this.observers.add(sub);
    }
    void removeSub(StockObserver sub){
        this.observers.remove(sub);
    }
}

public class StockPriceObserverDesignPattern {

    public static void main(String[] args) {
        Stock apple = new Stock("Apple", 300);
        StockSubscriber s1 = new StockSubscriber("Mohit");
        StockSubscriber s2 = new StockSubscriber("Rohit");
        StockSubscriber s3 = new StockSubscriber("Rahul");


        apple.addSubs(s1);
        apple.addSubs(s2);
        apple.addSubs(s3);

        apple.updatePrice(340);
        apple.removeSub(s1);

        apple.updatePrice(351);






    }

}
