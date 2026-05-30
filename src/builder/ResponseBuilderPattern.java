package builder;/*
 * ============================================================
 *                  BUILDER PATTERN
 * ============================================================
 *
 * Definition:
 * -----------
 * Builder Pattern is a creational design pattern used to
 * construct complex objects step-by-step.
 *
 * It separates object construction logic from the actual object.
 *
 *
 * Why do we need it?
 * ------------------
 * Imagine creating objects like this:
 *
 * new builder.Response(200, "Success", "Payload", true, false, ...);
 *
 * Problems:
 * - Constructor becomes huge and unreadable
 * - Difficult to remember parameter order
 * - Optional fields become messy
 * - Hard to maintain
 *
 *
 * Builder Pattern solves this by allowing:
 *
 * new builder.Response.ResponseBuilder()
 *      .setStatus(200)
 *      .setMsg("Success")
 *      .setPayload("Data")
 *      .build();
 *
 *
 * Advantages:
 * -----------
 * 1. Improves readability
 * 2. Handles optional fields cleanly
 * 3. Helps create immutable objects
 * 4. Avoids telescoping constructors
 * 5. Makes object creation more controlled
 *
 *
 * Common Real-World Usage:
 * ------------------------
 * - Lombok @Builder
 * - HTTP Request Builders
 * - Spring Configurations
 * - Query Builders
 * - Complex DTO creation
 *
 * ============================================================
 */


/*
 * Product Class
 *
 * This is the actual object we want to create
 * using the Builder Pattern.
 */
class Response {

    /*
     * final fields make the object immutable.
     *
     * Once object is created,
     * values cannot be changed.
     */
    private final int status;
    private final String msg;
    private final String payload;


    /*
     * Private constructor
     *
     * Object creation is restricted.
     * Only Builder can create this object.
     */
    private Response(ResponseBuilder rb) {

        this.status = rb.status;
        this.msg = rb.msg;
        this.payload = rb.payload;
    }


    /*
     * Static Nested Builder Class
     *
     * Responsible for step-by-step object creation.
     */
    static class ResponseBuilder {

        /*
         * Temporary fields stored inside builder.
         *
         * Builder collects all values first,
         * then creates final object using build().
         */
        private int status;
        private String msg;
        private String payload;


        /*
         * Setter-style methods
         *
         * Returning "this" enables method chaining.
         */

        public ResponseBuilder setStatus(int status) {

            this.status = status;
            return this;
        }

        public ResponseBuilder setMsg(String msg) {

            this.msg = msg;
            return this;
        }

        public ResponseBuilder setPayload(String payload) {

            this.payload = payload;
            return this;
        }


        /*
         * Final object creation method.
         *
         * This officially creates and returns
         * the immutable builder.Response object.
         */
        public Response build() {

            /*
             * Optional:
             * Validation logic can also be added here.
             *
             * Example:
             *
             * if(status <= 0){
             *     throw new IllegalArgumentException();
             * }
             */

            return new Response(this);
        }
    }


    /*
     * toString() method
     *
     * Helps print object contents properly.
     */
    @Override
    public String toString() {

        return "builder.Response{"
                + "status=" + status
                + ", msg='" + msg + '\''
                + ", payload='" + payload + '\''
                + '}';
    }
}



/*
 * Client Class
 *
 * Demonstrates Builder Pattern usage.
 */
public class ResponseBuilderPattern {

    public static void main(String[] args) {

        /*
         * Step-by-step object construction.
         *
         * Cleaner and more readable compared
         * to large constructors.
         */
        Response response = new Response.ResponseBuilder()

                .setStatus(200)
                .setMsg("Success")
                .setPayload("builder.User Data")

                .build();


        /*
         * Printing a final object
         */
        System.out.println(response);
    }
}