/*
 * ============================================================
 *                  OBSERVER PATTERN
 * ============================================================
 *
 * Definition:
 * -----------
 * Observer Pattern is a behavioral design pattern
 * that defines a one-to-many dependency between objects,
 * so that when one object changes state or triggers an event,
 * all its dependent objects are automatically notified.
 *
 *
 * Simple Meaning:
 * ---------------
 * One object acts as a publisher (subject),
 * while multiple other objects act as subscribers (observers).
 *
 * Whenever an event occurs,
 * all subscribers automatically receive updates.
 *
 *
 * Example:
 * --------
 * YouTube Notification System:
 *
 * - YouTuber uploads a video
 * - All subscribers receive notification
 *
 *
 * Advantages:
 * -----------
 * 1. Supports event-driven systems
 * 2. Reduces tight coupling
 * 3. Dynamic subscription/unsubscription
 * 4. Easy to add new observer types
 * 5. Follows Open/Closed Principle
 *
 *
 * Real-world Examples:
 * --------------------
 * - YouTube subscriptions
 * - Instagram followers
 * - Kafka consumers
 * - RabbitMQ subscribers
 * - WebSocket listeners
 * - Spring EventListeners
 *
 *
 * IMPORTANT INTERVIEW LINE:
 * -------------------------
 * "Observer Pattern defines a one-to-many dependency
 * where multiple observers automatically receive updates
 * when the subject changes state."
 *
 * ============================================================
 */



import java.util.ArrayList;
import java.util.List;



/*
 * ============================================================
 *                  SUBJECT INTERFACE
 * ============================================================
 *
 * Represents publisher/event producer.
 *
 * Responsible only for:
 * - managing subscribers
 * - triggering events
 *
 * IMPORTANT:
 * notifySubscribers() is intentionally NOT exposed
 * in the interface because it is an internal behavior
 * of concrete implementation.
 */
interface Channel {

    /*
     * Event method
     *
     * Example:
     * Uploading a new YouTube video.
     */
    void upload();


    /*
     * Register new subscriber
     */
    void subscribe(
            Subscriber subscriber
    );


    /*
     * Remove existing subscriber
     */
    void unsubscribe(
            Subscriber subscriber
    );
}



/*
 * ============================================================
 *                  OBSERVER INTERFACE
 * ============================================================
 *
 * Every subscriber/observer must implement this.
 */
interface Subscriber {

    /*
     * Called automatically whenever
     * subject sends notification.
     */
    void updateNotification(
            String msg
    );
}



/*
 * ============================================================
 *                  CONCRETE SUBJECT
 * ============================================================
 *
 * Concrete implementation of publisher.
 *
 * Acts as:
 * - YouTube channel
 * - Event producer
 */
class Youtuber implements Channel {

    /*
     * List of all subscribers/observers.
     *
     * Core part of Observer Pattern.
     */
    private final List<Subscriber>
            subscribers;


    /*
     * Channel name
     */
    private final String name;


    /*
     * Constructor
     */
    Youtuber(String name) {

        this.subscribers =
                new ArrayList<>();

        this.name = name;
    }


    /*
     * Event trigger method.
     *
     * Uploading video automatically
     * notifies all subscribers.
     */
    @Override
    public void upload() {

        System.out.println(
                "Video Uploaded"
        );

        /*
         * Internal notification trigger
         */
        notifySubscribers(
                this.name
                        + " uploaded a new video"
        );
    }


    /*
     * Add new subscriber
     */
    @Override
    public void subscribe(
            Subscriber subscriber
    ) {

        this.subscribers.add(
                subscriber
        );
    }


    /*
     * Remove existing subscriber
     */
    @Override
    public void unsubscribe(
            Subscriber subscriber
    ) {

        this.subscribers.remove(
                subscriber
        );
    }


    /*
     * Internal helper method.
     *
     * Not exposed publicly through interface
     * because clients should NOT manually
     * trigger notifications.
     *
     * Only subject itself should decide
     * when observers must be notified.
     */
    private void notifySubscribers(
            String msg
    ) {

        for (Subscriber s
                : this.subscribers) {

            s.updateNotification(msg);
        }
    }
}



/*
 * ============================================================
 *              CONCRETE OBSERVER
 * ============================================================
 *
 * Represents actual subscriber/viewer.
 *
 * Receives updates from subject.
 */
class Viewer implements Subscriber {

    /*
     * Viewer name
     */
    private final String name;


    /*
     * Constructor
     */
    Viewer(String name) {

        this.name = name;
    }


    /*
     * Called automatically whenever
     * channel uploads video.
     */
    @Override
    public void updateNotification(
            String msg
    ) {

        System.out.println(
                "Hey "
                        + this.name
                        + ", "
                        + msg
        );
    }
}



/*
 * ============================================================
 *                  SERVICE CLASS
 * ============================================================
 *
 * Simulates YouTube platform behavior.
 */
class YoutubeService {

    /*
     * Upload video through channel
     */
    void uploadNewVideo(
            Channel ch
    ) {

        System.out.println(
                "Uploading video..."
        );

        ch.upload();
    }
}



/*
 * ============================================================
 *                      CLIENT CLASS
 * ============================================================
 *
 * Demonstrates Observer Pattern usage.
 */
public class ObserverPatternExample {

    public static void main(String[] args) {

        /*
         * Creating platform service
         */
        YoutubeService service =
                new YoutubeService();


        /*
         * Creating publisher/channel
         */
        Channel channel =
                new Youtuber("Satish");


        /*
         * Creating subscribers
         */
        Viewer viewer1 =
                new Viewer("Hitesh");

        Viewer viewer2 =
                new Viewer("Harsh");


        /*
         * Registering subscribers
         */
        channel.subscribe(viewer1);

        channel.subscribe(viewer2);


        /*
         * Uploading video.
         *
         * All subscribers receive notification.
         */
        service.uploadNewVideo(channel);


        System.out.println();


        /*
         * Removing one subscriber
         */
        channel.unsubscribe(viewer1);


        /*
         * Uploading again.
         *
         * Only remaining subscribers
         * receive notification.
         */
        service.uploadNewVideo(channel);
    }
}