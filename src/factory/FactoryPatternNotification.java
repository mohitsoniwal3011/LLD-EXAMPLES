package factory;

/*
Factory Pattern provides a way to create objects without exposing the object creation logic to the client.
 */

/*
 * factory.Notification interface
 *
 * This acts as the common contract for all notification types.
 * Any new notification type must implement this interface.
 */
interface Notification {

    /*
     * Sends the notification message.
     *
     * @param msg -> message/content to send
     */
    void send(String msg);
}


/*
 * Concrete implementation for Email notifications.
 */
class EmailNotifications implements Notification {

    @Override
    public void send(String msg) {
        System.out.println("Sending Email factory.Notification : " + msg);
    }
}


/*
 * Concrete implementation for OTP notifications.
 */
class OTPNotifications implements Notification {

    @Override
    public void send(String msg) {
        System.out.println("Sending OTP factory.Notification : " + msg);
    }
}


/*
 * Concrete implementation for SMS notifications.
 */
class SMSNotifications implements Notification {

    @Override
    public void send(String msg) {
        System.out.println("Sending SMS factory.Notification : " + msg);
    }
}


/*
 * BAD APPROACH (Without Factory Pattern)
 *
 * Problems with this approach:
 *
 * 1. Tight coupling
 *    -> Client directly depends on concrete classes.
 *
 * 2. Violates Open/Closed Principle
 *    -> Every time a new notification type is added,
 *       this method must be modified.
 *
 * 3. Object creation logic is scattered inside business logic.
 *
 * 4. Difficult to maintain when conditions grow.
 */

// class FactoryPatternNotificationBadCode {
//
//     private factory.Notification getNotification(String type) {
//
//         if ("sms".equals(type)) {
//             return new factory.SMSNotifications();
//         }
//
//         if ("otp".equals(type)) {
//             return new factory.OTPNotifications();
//         }
//
//         if ("email".equals(type)) {
//             return new factory.EmailNotifications();
//         }
//
//         return null;
//     }
// }


/*
 * Factory Class
 *
 * Responsibility:
 * -> Centralize object creation logic.
 *
 * Client does not need to know:
 * - Which concrete class is being created
 * - How object creation happens
 *
 * Client only requests the required type.
 */
class NotificationFactory {

    /*
     * Factory Method
     *
     * Returns the appropriate factory.Notification object
     * based on the provided type.
     */
    public static Notification getInstance(String type) {

        /*
         * Using equalsIgnoreCase() to make
         * comparison case-insensitive.
         */
        if ("sms".equalsIgnoreCase(type)) {
            return new SMSNotifications();
        }

        if ("otp".equalsIgnoreCase(type)) {
            return new OTPNotifications();
        }

        if ("email".equalsIgnoreCase(type)) {
            return new EmailNotifications();
        }

        /*
         * Throwing exception instead of returning null
         * helps avoid NullPointerException later.
         */
        throw new IllegalArgumentException(
                "Invalid notification type : " + type
        );
    }
}


/*
 * Client Class
 *
 * This class uses the factory to obtain
 * notification objects.
 *
 * It does NOT directly create concrete objects.
 */
public class FactoryPatternNotification {

    /*
     * Common interface reference.
     *
     * This demonstrates abstraction and polymorphism.
     */
    private final Notification notification;


    /*
     * Constructor
     *
     * Factory handles object creation internally.
     */
    FactoryPatternNotification(String type) {

        this.notification =
                NotificationFactory.getInstance(type);
    }


    /*
     * Sends the notification message.
     */
    public void sendNotification(String msg) {

        notification.send(msg);
    }


    /*
     * Main method for testing/demo.
     */
    public static void main(String[] args) {

        FactoryPatternNotification service1 =
                new FactoryPatternNotification("email");

        service1.sendNotification("Welcome to the platform");


        FactoryPatternNotification service2 =
                new FactoryPatternNotification("sms");

        service2.sendNotification("Your order has been shipped");


        FactoryPatternNotification service3 =
                new FactoryPatternNotification("otp");

        service3.sendNotification("Your OTP is 4582");
    }
}