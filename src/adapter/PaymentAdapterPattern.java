package adapter;/*
 * ============================================================
 *                  ADAPTER PATTERN
 * ============================================================
 *
 * Definition:
 * -----------
 * Adapter Pattern is a structural design pattern
 * that converts one interface into another compatible
 * interface so that incompatible classes can work together.
 *
 *
 * Simple Meaning:
 * ---------------
 * Sometimes external systems or third-party libraries
 * expose APIs that do not match our application's
 * expected structure.
 *
 * Adapter Pattern acts like a bridge/converter
 * between them.
 *
 *
 * Example:
 * --------
 * Our application expects:
 *
 *      pay()
 *
 * But different payment gateways provide:
 *
 * adapter.RazorPay  -> makePayment()
 * adapter.PhonePay  -> executePayment()
 * adapter.GooglePay -> transferMoney()
 *
 * Since method names and interfaces differ,
 * they are incompatible with our system.
 *
 *
 * Adapter Pattern solves this by converting
 * all gateway APIs into one common interface:
 *
 *      pay()
 *
 *
 * Advantages:
 * -----------
 * 1. Allows incompatible systems to work together
 * 2. Avoids modifying third-party code
 * 3. Improves maintainability
 * 4. Makes integrations cleaner
 * 5. Reduces tight coupling
 *
 *
 * Real-world Usage:
 * -----------------
 * - strategy.Payment gateway integrations
 * - Third-party APIs
 * - Legacy system integration
 * - DTO transformations
 * - External SDK integration
 *
 *
 * IMPORTANT INTERVIEW LINE:
 * -------------------------
 * "Adapter Pattern converts one interface into another
 * compatible interface so that incompatible classes
 * can work together."
 *
 * ============================================================
 */



/*
 * ============================================================
 *                  ADAPTEE CLASSES
 * ============================================================
 *
 * These are third-party/external payment systems.
 *
 * Problem:
 * --------
 * All expose different method names.
 *
 * Therefore they are incompatible with our system.
 */


/*
 * adapter.RazorPay external SDK
 */
class RazorPay {

    public void makePayment() {

        System.out.println(
                "Paying via adapter.RazorPay"
        );
    }
}


/*
 * adapter.PhonePay external SDK
 */
class PhonePay {

    public void executePayment() {

        System.out.println(
                "Paying via adapter.PhonePay"
        );
    }
}


/*
 * adapter.GooglePay external SDK
 */
class GooglePay {

    public void transferMoney() {

        System.out.println(
                "Paying via adapter.GooglePay"
        );
    }
}



/*
 * ============================================================
 *                  TARGET INTERFACE
 * ============================================================
 *
 * This is the interface expected by our system.
 *
 * All payment gateways should eventually behave
 * like this interface.
 */
interface PaymentAdapter {

    void pay();
}



/*
 * ============================================================
 *                  ADAPTER CLASSES
 * ============================================================
 *
 * Adapters convert incompatible gateway APIs
 * into our application's expected structure.
 */


/*
 * Adapter for adapter.RazorPay
 */
class RazorPayAdapter
        implements PaymentAdapter {

    /*
     * Wrapped adaptee object
     */
    private final RazorPay razorPay;


    /*
     * Constructor receives external object
     */
    RazorPayAdapter(RazorPay razorPay) {

        this.razorPay = razorPay;
    }


    /*
     * Converting makePayment()
     * into pay()
     */
    @Override
    public void pay() {

        this.razorPay.makePayment();
    }
}



/*
 * Adapter for adapter.PhonePay
 */
class PhonePayAdapter
        implements PaymentAdapter {

    private final PhonePay phonePay;


    PhonePayAdapter(PhonePay phonePay) {

        this.phonePay = phonePay;
    }


    /*
     * Converting executePayment()
     * into pay()
     */
    @Override
    public void pay() {

        this.phonePay.executePayment();
    }
}



/*
 * Adapter for adapter.GooglePay
 */
class GooglePayAdapter
        implements PaymentAdapter {

    private final GooglePay googlePay;


    GooglePayAdapter(GooglePay googlePay) {

        this.googlePay = googlePay;
    }


    /*
     * Converting transferMoney()
     * into pay()
     */
    @Override
    public void pay() {

        this.googlePay.transferMoney();
    }
}



/*
 * ============================================================
 *                  CLIENT/SERVICE CLASS
 * ============================================================
 *
 * This class works ONLY with adapter.PaymentAdapter.
 *
 * It does NOT care:
 * - Which payment gateway is used
 * - What internal API method exists
 *
 * Thanks to Adapter Pattern,
 * all gateways now expose:
 *
 *      pay()
 */
class PaymentProcessorService {

    /*
     * Adapter reference
     */
    private final PaymentAdapter paymentAdapter;


    /*
     * Adapter injected through constructor
     */
    PaymentProcessorService(
            PaymentAdapter adapter
    ) {

        this.paymentAdapter = adapter;
    }


    /*
     * Common payment execution method
     */
    public void makePayment() {

        paymentAdapter.pay();
    }
}



/*
 * ============================================================
 *                      CLIENT CLASS
 * ============================================================
 *
 * Demonstrates Adapter Pattern usage.
 */
public class PaymentAdapterPattern {

    public static void main(String[] args) {

        /*
         * External payment systems
         */
        RazorPay razorPay =
                new RazorPay();

        PhonePay phonePay =
                new PhonePay();

        GooglePay googlePay =
                new GooglePay();


        /*
         * Adapting external systems
         * into compatible structure
         */
        PaymentAdapter razorAdapter =
                new RazorPayAdapter(
                        razorPay
                );

        PaymentAdapter phonePayAdapter =
                new PhonePayAdapter(
                        phonePay
                );

        PaymentAdapter googleAdapter =
                new GooglePayAdapter(
                        googlePay
                );


        /*
         * Service works uniformly
         * with all payment gateways
         */
        PaymentProcessorService service1 =
                new PaymentProcessorService(
                        razorAdapter
                );

        PaymentProcessorService service2 =
                new PaymentProcessorService(
                        phonePayAdapter
                );

        PaymentProcessorService service3 =
                new PaymentProcessorService(
                        googleAdapter
                );


        /*
         * Executing payments
         */
        service1.makePayment();

        service2.makePayment();

        service3.makePayment();
    }
}