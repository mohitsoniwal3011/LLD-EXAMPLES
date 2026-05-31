package template;

/**
 * ============================================================================
 * TEMPLATE METHOD DESIGN PATTERN
 * ============================================================================
 *
 * Definition:
 * Template Method is a behavioral design pattern that defines the skeleton of
 * an algorithm in a parent class while allowing subclasses to customize
 * specific steps of that algorithm without changing its overall structure.
 *
 * Intent:
 * - Define a fixed workflow in a base class.
 * - Allow subclasses to provide custom implementations for certain steps.
 * - Ensure the sequence of steps remains unchanged.
 *
 * Structure:
 *
 * Parent Class
 *     |
 *     +--> templateMethod()      <-- defines the algorithm
 *     +--> commonStep1()
 *     +--> commonStep2()
 *     +--> abstractStep1()
 *     +--> abstractStep2()
 *
 * Child Classes
 *     |
 *     +--> implement abstractStep1()
 *     +--> implement abstractStep2()
 *
 * Real World Example:
 * Preparing a hot drink.
 *
 * Every drink follows the same process:
 *
 * 1. Boil water
 * 2. Add sugar
 * 3. Add milk
 * 4. Serve
 *
 * However, Tea and Coffee add sugar and milk differently.
 *
 * Example Walkthrough:
 *
 * Tea tea = new Tea();
 * tea.prepare();
 *
 * Execution Flow:
 *
 * prepare()
 *   -> boil()
 *   -> addSugar()  (Tea implementation)
 *   -> addMilk()   (Tea implementation)
 *   -> serve()
 *
 * Output:
 * Boiling the water
 * Adding sugar in tea
 * Adding milk in tea
 * Serving Drink
 *
 * Coffee coffee = new Coffee();
 * coffee.prepare();
 *
 * Execution Flow:
 *
 * prepare()
 *   -> boil()
 *   -> addSugar() (Coffee implementation)
 *   -> addMilk() (Coffee implementation)
 *   -> serve()
 *
 * Output:
 * Boiling the water
 * Adding sugar in coffee
 * Adding milk in coffee
 * Serving Drink
 *
 * Benefits:
 * - Reuses common logic.
 * - Enforces a fixed algorithm structure.
 * - Reduces code duplication.
 * - Follows Open/Closed Principle.
 *
 * Common Examples:
 * - Spring's JdbcTemplate
 * - Servlet HttpServlet (doGet, doPost)
 * - JUnit test lifecycle methods
 * - Build pipelines
 * - Data processing workflows
 */

abstract class HotDrink{

    final void prepare(){
        boil();
        addSugar();
        addMilk();
        serve();
    }

    final void boil(){
        System.out.println("Boiling the water");
    }

    abstract void addSugar();
    abstract void addMilk();

    final void serve(){
        System.out.println("Serving Drink");
    }
}

class Coffee extends HotDrink{


    @Override
    public void addSugar() {
        System.out.println("Adding sugar in coffee");
    }

    @Override
    public void addMilk() {
        System.out.println("Adding milk in coffee");
    }

}

class Tea extends HotDrink{

    @Override
    public void addSugar() {
        System.out.println("Adding sugar in tea");
    }

    @Override
    public void addMilk() {
        System.out.println("Adding milk in tea");
    }
}

public class HotDrinkMaker {

    public static void main(String[] args) {
        HotDrink tea = new Tea();
        tea.prepare();

        HotDrink coffee = new Coffee();
        coffee.prepare();
    }
}
