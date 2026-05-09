/*
 * ============================================================
 *                  STRATEGY PATTERN
 * ============================================================
 *
 * Definition:
 * -----------
 * Strategy Pattern is a behavioral design pattern
 * that encapsulates interchangeable algorithms/behaviors
 * separately and allows them to be switched dynamically
 * at runtime.
 *
 *
 * Simple Meaning:
 * ---------------
 * Instead of writing large if-else conditions
 * for different behaviors,
 * we separate each behavior into its own class.
 *
 *
 * Example:
 * --------
 * Payment System:
 *
 * Different payment methods:
 * - UPI
 * - Wallet
 * - Card
 *
 * Each payment method has its own payment logic.
 *
 * Instead of:
 *
 * if(type == "upi")
 * else if(type == "wallet")
 * else if(type == "card")
 *
 * we create separate strategy classes.
 *
 *
 * Main Goal:
 * ----------
 * Make behaviors interchangeable dynamically.
 *
 *
 * Advantages:
 * -----------
 * 1. Removes large if-else chains
 * 2. Follows Open/Closed Principle
 * 3. Easy to add new strategies
 * 4. Runtime behavior switching
 * 5. Better maintainability
 * 6. Encourages composition over inheritance
 *
 *
 * Real-world Examples:
 * --------------------
 * - Payment systems
 * - Sorting algorithms
 * - Compression algorithms
 * - Route finding systems
 * - Authentication providers
 *
 *
 * IMPORTANT INTERVIEW LINE:
 * -------------------------
 * "Strategy Pattern encapsulates interchangeable
 * algorithms separately and allows behavior
 * to change dynamically at runtime."
 *
 * ============================================================
 */



/*
 * ============================================================
 *                  STRATEGY INTERFACE
 * ============================================================
 *
 * Common interface for all payment strategies.
 *
 * Every payment method must implement this.
 */
interface Payment {

    void pay();
}



/*
 * ============================================================
 *              CONCRETE STRATEGIES
 * ============================================================
 *
 * Each class contains its own payment logic.
 */


/*
 * UPI Payment Strategy
 */
class UPIPayment implements Payment {

    @Override
    public void pay() {

        System.out.println(
                "Processing payment using UPI"
        );
    }
}


/*
 * Wallet Payment Strategy
 */
class WalletPayment implements Payment {

    @Override
    public void pay() {

        System.out.println(
                "Processing payment using Wallet"
        );
    }
}


/*
 * Card Payment Strategy
 */
class CardPayment implements Payment {

    @Override
    public void pay() {

        System.out.println(
                "Processing payment using Card"
        );
    }
}



/*
 * ============================================================
 *                  FACTORY CLASS
 * ============================================================
 *
 * Factory Pattern is combined here
 * to centralize strategy object creation.
 *
 * Client does not directly create
 * concrete strategy objects.
 */
class PaymentProviderFactory {

    /*
     * Returns the appropriate payment strategy
     * based on payment method.
     */
    static Payment getPaymentMethod(String method) {

        if ("upi".equalsIgnoreCase(method)) {

            return new UPIPayment();
        }

        else if ("wallet".equalsIgnoreCase(method)) {

            return new WalletPayment();
        }

        else if ("card".equalsIgnoreCase(method)) {

            return new CardPayment();
        }

        else {

            throw new IllegalArgumentException(
                    "Invalid payment method"
            );
        }
    }
}



/*
 * ============================================================
 *                  CONTEXT CLASS
 * ============================================================
 *
 * This is the most important class
 * in Strategy Pattern.
 *
 * Context does NOT know:
 * - UPI logic
 * - Wallet logic
 * - Card logic
 *
 * It only works with the strategy interface.
 */
class PaymentService {

    /*
     * Reference to current strategy.
     */
    private Payment payment;


    /*
     * Strategy injected through constructor.
     *
     * This demonstrates:
     * "Composition over inheritance"
     */
    PaymentService(Payment payment) {

        this.payment = payment;
    }


    /*
     * Executes payment using current strategy.
     *
     * If payment fails,
     * switches to backup strategy dynamically.
     */
    public void executePayment(
            Payment backupPaymentMethod
    ) {

        try {

            /*
             * Execute current strategy
             */
            this.payment.pay();
        }

        catch (Exception e) {

            System.out.println(
                    "Main payment failed"
            );

            /*
             * Runtime strategy switching
             */
            this.changePaymentMethod(
                    backupPaymentMethod
            );

            System.out.println(
                    "Switching to backup payment method"
            );

            /*
             * Retry payment using backup strategy
             */
            this.payment.pay();
        }
    }


    /*
     * Dynamically changes payment strategy.
     *
     * Core feature of Strategy Pattern.
     */
    public void changePaymentMethod(
            Payment payment
    ) {

        this.payment = payment;
    }
}



/*
 * ============================================================
 *                      CLIENT CLASS
 * ============================================================
 *
 * Demonstrates Strategy Pattern usage.
 */
public class PaymentStrategyPattern {

    public static void main(String[] args) {

        /*
         * Creating the main payment strategy
         * using Factory Pattern.
         */
        Payment mainPaymentStrategy =
                PaymentProviderFactory
                        .getPaymentMethod("upi");


        /*
         * Creating backup strategy
         */
        Payment backupPaymentMethod =
                PaymentProviderFactory
                        .getPaymentMethod("wallet");


        /*
         * Injecting strategy into context class.
         */
        PaymentService paymentService =
                new PaymentService(
                        mainPaymentStrategy
                );


        /*
         * Executing payment.
         *
         * Service uses strategy internally.
         */
        paymentService.executePayment(
                backupPaymentMethod
        );
    }
}