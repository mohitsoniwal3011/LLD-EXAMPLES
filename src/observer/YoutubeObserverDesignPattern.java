package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Design Pattern
 *
 * Definition:
 * The Observer Pattern defines a one-to-many dependency between objects
 * so that when one object changes its state, all dependent objects are
 * notified and updated automatically.
 *
 * Intent:
 * - Establish communication between a publisher and multiple subscribers.
 * - Decouple the publisher from the subscribers.
 * - Allow subscribers to register and unregister dynamically.
 *
 * Components in this Example:
 *
 * Subject (Publisher):
 * - YoutubeChannel
 * - Maintains a list of subscribers (observers).
 * - Notifies all subscribers whenever a new video is uploaded.
 *
 * Observer:
 * - Observer interface
 * - Defines the contract for receiving updates.
 *
 * Concrete Observer:
 * - Subscriber
 * - Implements the Observer interface and reacts to notifications.
 *
 * Flow:
 * 1. Subscribers subscribe to a YouTube channel.
 * 2. The channel uploads a new video.
 * 3. The channel notifies all registered subscribers.
 * 4. Each subscriber receives the update.
 *
 * Advantages:
 * - Loose coupling between publisher and subscribers.
 * - Dynamic subscription and unsubscription.
 * - Easy to add new subscriber types.
 * - Supports the Open/Closed Principle.
 *
 * Disadvantages:
 * - Large numbers of observers may impact performance.
 * - Notification chains can be difficult to debug.
 * - Observers may receive updates they are not interested in.
 *
 * Real-World Examples:
 * - YouTube subscriptions
 * - Instagram followers
 * - Twitter/X followers
 * - Newsletter subscriptions
 * - Event listeners in Java Swing/JavaFX
 * - Spring Application Events
 *
 * Pattern Mapping:
 * - Subject           -> YoutubeChannel
 * - Observer          -> Observer
 * - Concrete Observer -> Subscriber
 *
 * This implementation uses the Push Model, where the Subject pushes
 * the update message directly to all observers.
 */
public class YoutubeObserverDesignPattern {

    public static void main(String[] args) {
        YoutubeChannel channel = new YoutubeChannel("PewDePie");

        Observer sub1 = new Subscriber("Mohit");
        Observer sub2 = new Subscriber("Rohit");

        channel.addSubscriber(sub1);
        channel.addSubscriber(sub2);

        channel.postVideo("Youtube ka keeda");

        channel.removeSubscriber(sub1);

        channel.postVideo("Another video");
    }
}

/**
 * Observer contract.
 *
 * Any subscriber interested in receiving updates from a publisher
 * must implement this interface.
 */
interface Observer {

    /**
     * Called by the Subject whenever a state change occurs.
     *
     * @param msg notification message sent by the publisher
     */
    void update(String msg);
}

/**
 * Concrete Observer.
 *
 * Represents a YouTube subscriber who receives notifications
 * whenever a subscribed channel uploads a new video.
 */
class Subscriber implements Observer {

    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    /**
     * Handles notifications sent by the YouTube channel.
     *
     * @param msg notification message
     */
    @Override
    public void update(String msg) {
        System.out.println(
                "Hey " + this.name + ", Update received: " + msg
        );
    }
}

/**
 * Subject (Publisher).
 *
 * Maintains a list of subscribers and notifies them whenever
 * a new video is uploaded.
 */
class YoutubeChannel {

    private final String channelName;
    private final List<Observer> subscribers = new ArrayList<>();

    public YoutubeChannel(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Registers a new subscriber.
     *
     * @param observer observer to be registered
     */
    void addSubscriber(Observer observer) {
        subscribers.add(observer);
    }

    /**
     * Removes an existing subscriber.
     *
     * @param observer observer to be removed
     */
    void removeSubscriber(Observer observer) {
        subscribers.remove(observer);
    }

    /**
     * Uploads a new video and notifies all subscribers.
     *
     * @param videoName name of the uploaded video
     */
    void postVideo(String videoName) {
        notifySubscribers(videoName);
    }

    /**
     * Notifies all registered subscribers about the new upload.
     *
     * @param videoName name of the uploaded video
     */
    private void notifySubscribers(String videoName) {
        for (Observer observer : subscribers) {
            observer.update(
                    channelName + " just uploaded a new video: " + videoName
            );
        }
    }
}