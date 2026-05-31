package ChainOfResponsibility;

/**
 * ============================================================================
 * CHAIN OF RESPONSIBILITY DESIGN PATTERN
 * ============================================================================
 *
 * Definition:
 * Chain of Responsibility is a behavioral design pattern that allows a request
 * to pass through a chain of handlers. Each handler decides whether it can
 * process the request. If it cannot, it forwards the request to the next
 * handler in the chain.
 *
 * Intent:
 * - Decouple the sender of a request from its receiver.
 * - Allow multiple objects to get a chance to handle the request.
 * - Avoid large if-else or switch-case blocks.
 *
 * Real World Example:
 * Leave Approval System
 *
 * Manager  -> Can approve up to 5 days
 * Director -> Can approve up to 10 days
 * CEO      -> Can approve up to 14 days
 *
 * Example Walkthrough:
 *
 * Request: 3 days leave
 * Employee -> Manager
 * Manager approves.
 *
 * Request: 8 days leave
 * Employee -> Manager
 * Manager cannot approve.
 * Request forwarded to Director.
 * Director approves.
 *
 * Request: 12 days leave
 * Employee -> Manager
 * Manager cannot approve.
 * Director cannot approve.
 * Request forwarded to CEO.
 * CEO approves.
 *
 * Request: 20 days leave
 * Employee -> Manager
 * Manager cannot approve.
 * Director cannot approve.
 * CEO rejects because company policy allows only up to 14 days.
 *
 * Benefits:
 * - Loose coupling between sender and receiver.
 * - Easy to add new handlers.
 * - Follows Open/Closed Principle.
 * - Cleaner business logic.
 */
interface Approver {
    void approve(int days);
}

/**
 * First level approver.
 */
class Manager implements Approver {

    private final Approver nextHandler;

    public Manager(Approver nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void approve(int days) {

        if (days <= 5) {
            System.out.println(
                    "Manager approved leave request for "
                            + days + " days."
            );
            return;
        }

        System.out.println(
                "Manager cannot approve "
                        + days
                        + " days. Forwarding to Director..."
        );

        if (nextHandler != null) {
            nextHandler.approve(days);
        }
    }
}

/**
 * Second level approver.
 */
class Director implements Approver {

    private final Approver nextHandler;

    public Director(Approver nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void approve(int days) {

        if (days <= 10) {
            System.out.println(
                    "Director approved leave request for "
                            + days + " days."
            );
            return;
        }

        System.out.println(
                "Director cannot approve "
                        + days
                        + " days. Forwarding to CEO..."
        );

        if (nextHandler != null) {
            nextHandler.approve(days);
        }
    }
}

/**
 * Final approver in the chain.
 */
class CEO implements Approver {

    private final Approver nextHandler;

    public CEO(Approver nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void approve(int days) {

        if (days <= 14) {
            System.out.println(
                    "CEO approved leave request for "
                            + days + " days."
            );
            return;
        }

        System.out.println(
                "Leave request rejected. Leaves greater than "
                        + "14 days are not allowed as per company policy."
        );

        if (nextHandler != null) {
            nextHandler.approve(days);
        }
    }
}

/**
 * Client code.
 */
public class LeaveApprovalSystem {

    public static void main(String[] args) {

        // Create chain
        Approver ceo = new CEO(null);
        Approver director = new Director(ceo);
        Approver manager = new Manager(director);

        System.out.println("\n========== Request : 2 Days ==========");
        manager.approve(2);

        System.out.println("\n========== Request : 8 Days ==========");
        manager.approve(8);

        System.out.println("\n========== Request : 11 Days ==========");
        manager.approve(11);

        System.out.println("\n========== Request : 15 Days ==========");
        manager.approve(15);
    }
}

