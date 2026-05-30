/**
 * Prototype Design Pattern
 * ========================
 *
 * Definition:
 * -----------
 * The Prototype Design Pattern creates new objects by copying (cloning)
 * an existing object instead of creating them from scratch.
 *
 * Why do we need it?
 * -----------------
 * Imagine that creating an object requires a lot of configuration.
 *
 * Example:
 *
 * - A contract has predefined pricing.
 * - A contract has predefined services.
 * - A contract has predefined legal terms.
 *
 * If every contract is mostly the same, repeatedly creating and
 * configuring new objects becomes unnecessary work.
 *
 * Instead:
 *
 * 1. Create one fully configured "prototype" object.
 * 2. Clone it whenever a new object is needed.
 * 3. Modify only the fields that are different.
 *
 * Real World Analogy:
 * ------------------
 * Think of a company contract template.
 *
 * The company always provides:
 * - Software Development
 * - For $100,000
 *
 * The only thing that changes is the client name.
 *
 * Instead of creating a new contract every time:
 *
 *     Contract -> Mohit
 *     Contract -> Rohit
 *     Contract -> Amit
 *
 * We create one template contract and clone it.
 *
 * Benefits:
 * ---------
 * - Avoid repeated object setup.
 * - Useful when object creation is expensive.
 * - Acts as a template for future objects.
 * - Reduces initialization code.
 *
 * Interview Notes:
 * ----------------
 * Q: Why not simply use 'new'?
 *
 * A:
 * If most fields are identical between objects,
 * cloning an existing configured object is easier
 * than repeatedly creating and configuring new ones.
 *
 * Q: What is the most important Prototype concept?
 *
 * A:
 * Shallow Copy vs Deep Copy.
 *
 * In this example:
 * - All fields are Strings.
 * - Strings are immutable.
 * - Therefore a shallow copy is sufficient.
 *
 * If this class contained mutable objects such as:
 *
 *     Address address;
 *
 * Then deep cloning would likely be required.
 */
class CompanyContract implements Cloneable {

    private String price;
    private String service;
    private String client;

    public CompanyContract(String client, String service, String price) {
        this.client = client;
        this.price = price;
        this.service = service;
    }

    /**
     * Generates the final contract document.
     */
    public String generateContract() {
        String template = """
                AGREEMENT: Tech Startups LLC agrees to provide "%s" for %s.
                CLIENT:    %s agrees to pay the full amount upon completion.
                SIGNED:    ___________________ (Tech Startups)  ___________________ (%s)
                """;

        return String.format(
                template,
                this.service,
                this.price,
                this.client,
                this.client
        );
    }

    /**
     * Prototype operation.
     *
     * Creates a copy of the current object.
     *
     * Since this class only contains immutable Strings,
     * the default shallow copy provided by Object.clone()
     * is sufficient.
     */
    @Override
    public CompanyContract clone() {
        try {
            return (CompanyContract) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning should always be supported");
        }
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}

/**
 * Client Code
 * ===========
 *
 * Demonstrates how Prototype Pattern works.
 *
 * We first create a fully configured contract template:
 *
 *     Service = Software Development
 *     Price   = $100,000
 *
 * Then clone it multiple times and customize only
 * the client name.
 *
 * This avoids repeatedly creating and configuring
 * identical contract objects.
 */
public class CompanyContractPrototypePattern {

    public static void main(String[] args) {

        // Prototype object (template)
        CompanyContract standardContract =
                new CompanyContract(
                        "",
                        "Software Development",
                        "$100,000"
                );

        // Clone #1
        CompanyContract c1 = standardContract.clone();
        c1.setClient("Mohit");

        // Clone #2
        CompanyContract c2 = standardContract.clone();
        c2.setClient("Rohit");

        // Clone #3
        CompanyContract c3 = standardContract.clone();
        c3.setClient("Amit");

        System.out.println("===== CONTRACT 1 =====");
        System.out.println(c1.generateContract());

        System.out.println("===== CONTRACT 2 =====");
        System.out.println(c2.generateContract());

        System.out.println("===== CONTRACT 3 =====");
        System.out.println(c3.generateContract());
    }
}