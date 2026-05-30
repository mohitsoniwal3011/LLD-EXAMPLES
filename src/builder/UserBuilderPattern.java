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
 * Instead of using large constructors with many parameters,
 * Builder Pattern provides a cleaner and more readable way
 * to create objects.
 *
 *
 * Example of problematic constructor:
 * -----------------------------------
 *
 * new builder.User("Mohit", 24, "Delhi", "India");
 *
 * Problems:
 * - Hard to remember parameter order
 * - Difficult to read
 * - Becomes messy when fields increase
 * - Optional parameters create constructor overload chaos
 *
 *
 * Builder Pattern solves this problem:
 * ------------------------------------
 *
 * new builder.User.UserBuilder()
 *      .setName("Mohit")
 *      .setAge(24)
 *      .setCity("Delhi")
 *      .setCountry("India")
 *      .build();
 *
 *
 * Advantages:
 * -----------
 * 1. Improves readability
 * 2. Cleaner object creation
 * 3. Supports optional fields easily
 * 4. Helps create immutable objects
 * 5. Avoids huge constructors
 * 6. More maintainable and scalable
 *
 *
 * Real-world usage:
 * -----------------
 * - Lombok @Builder
 * - Spring Boot configurations
 * - HTTP request builders
 * - Query builders
 * - DTO creation
 *
 * ============================================================
 */


/*
 * Product Class
 *
 * This is the actual object we want to create
 * using Builder Pattern.
 */
class User {

    /*
     * final fields make object immutable.
     *
     * Once object is created,
     * values cannot be modified.
     */
    private final String name;
    private final int age;
    private final String city;
    private final String country;


    /*
     * Private constructor
     *
     * Object creation is restricted.
     * Only Builder can create builder.User object.
     */
    private User(UserBuilder builder) {

        this.name = builder.name;
        this.age = builder.age;
        this.city = builder.city;
        this.country = builder.country;
    }


    /*
     * Static Nested Builder Class
     *
     * Responsible for collecting values
     * and constructing final builder.User object.
     */
    static class UserBuilder {

        /*
         * Temporary fields stored inside builder.
         */
        private String name;
        private int age;
        private String city;
        private String country;


        /*
         * Setter-style builder methods
         *
         * Returning "this" enables method chaining.
         */

        public UserBuilder setName(String name) {

            this.name = name;
            return this;
        }

        public UserBuilder setAge(int age) {

            this.age = age;
            return this;
        }

        public UserBuilder setCity(String city) {

            this.city = city;
            return this;
        }

        public UserBuilder setCountry(String country) {

            this.country = country;
            return this;
        }


        /*
         * Final object creation method.
         *
         * Creates immutable builder.User object
         * using collected builder values.
         */
        public User build() {

            /*
             * Validation logic can also be added here.
             *
             * Example:
             *
             * if(name == null){
             *     throw new IllegalArgumentException(
             *          "Name cannot be null"
             *     );
             * }
             */

            return new User(this);
        }
    }


    /*
     * toString() method
     *
     * Helps print object contents properly.
     */
    @Override
    public String toString() {

        return "builder.User{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", city='" + city + '\''
                + ", country='" + country + '\''
                + '}';
    }
}



/*
 * Client Class
 *
 * Demonstrates Builder Pattern usage.
 */
public class UserBuilderPattern {

    public static void main(String[] args) {

        /*
         * Step-by-step object creation
         * using Builder Pattern.
         */
        User user = new User.UserBuilder()

                .setName("Mohit")
                .setAge(24)
                .setCity("Delhi")
                .setCountry("India")

                .build();


        /*
         * Printing a final object
         */
        System.out.println(user);
    }
}