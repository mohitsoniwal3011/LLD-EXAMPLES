/**
 * Decorator Pattern
 * -----------------
 * The Decorator Pattern is a structural design pattern that allows us to add
 * new behavior or responsibilities to an object dynamically without modifying
 * its existing implementation.
 *
 * Problem:
 * --------
 * Suppose we have multiple bike models:
 * - CB350RS
 * - CB350 Highness
 * - CB350
 *
 * And multiple optional accessories:
 * - Fog Lights
 * - Custom Exhaust
 * - Crash Guard
 * - Saddle Stay
 * - Touring Windscreen
 *
 * Without the Decorator Pattern, we may end up creating classes for every
 * possible combination:
 *
 * CB350RSWithFogLights
 * CB350RSWithCustomExhaust
 * CB350RSWithFogLightsAndCustomExhaust
 * CB350HighnessWithFogLights
 * CB350HighnessWithCustomExhaust
 * ...
 *
 * This leads to a class explosion problem where the number of classes grows
 * rapidly as more accessories are introduced.
 *
 * Solution:
 * ---------
 * Decorators wrap an existing bike object and add extra behavior while still
 * exposing the same interface (BasicBike).
 *
 * Example:
 *
 * BasicBike bike =
 *     new CustomExhaustDecorator(
 *         new FogLightDecorator(
 *             new CB350RS()
 *         )
 *     );
 *
 * Here:
 * - CB350RS is the base component.
 * - FogLightDecorator adds fog light related behavior.
 * - CustomExhaustDecorator adds custom exhaust related behavior.
 *
 * Benefits:
 * ---------
 * - Avoids creating classes for every feature combination.
 * - Supports adding features at runtime.
 * - Follows Composition over Inheritance.
 * - Keeps individual features independent and reusable.
 * - Follows the Open/Closed Principle:
 *     Open for extension, closed for modification.
 *
 * How this implementation follows the pattern:
 * --------------------------------------------
 * - BasicBike defines the common abstraction.
 * - CB350RS, CB350Highness, and CB350 are concrete components.
 * - Decorators extend BasicBike and also contain a BasicBike reference.
 * - Each decorator delegates to the wrapped bike and adds its own behavior.
 *
 * This allows multiple decorators to be stacked together dynamically.
 */
abstract class BasicBike {

    public abstract void look();

    public abstract void useCase();
}

// ================= Concrete Components =================

class CB350RS extends BasicBike {

    @Override
    public void look() {
        System.out.println("Looks sporty");
    }

    @Override
    public void useCase() {
        System.out.println("City + highway + occasional long tours");
    }
}

class CB350Highness extends BasicBike {

    @Override
    public void look() {
        System.out.println("Looks modern classic");
    }

    @Override
    public void useCase() {
        System.out.println("Ease + comfort + long tours");
    }
}

class CB350 extends BasicBike {

    @Override
    public void look() {
        System.out.println("Looks like an absolute tank with a classic design");
    }

    @Override
    public void useCase() {
        System.out.println("Built for people who are in no hurry");
    }
}

// ================= Abstract Decorator =================

abstract class BikeDecorator extends BasicBike {

    protected final BasicBike bike;

    public BikeDecorator(BasicBike bike) {
        this.bike = bike;
    }
}

// ================= Concrete Decorators =================

class FogLightDecorator extends BikeDecorator {

    public FogLightDecorator(BasicBike bike) {
        super(bike);
    }

    @Override
    public void look() {
        bike.look();
        System.out.println("Front look changes slightly with fog lights");
    }

    @Override
    public void useCase() {
        bike.useCase();
        System.out.println("Better visibility during night rides");
    }
}

class CustomExhaustDecorator extends BikeDecorator {

    public CustomExhaustDecorator(BasicBike bike) {
        super(bike);
    }

    @Override
    public void look() {
        bike.look();
        System.out.println("Not much visual difference");
    }

    @Override
    public void useCase() {
        bike.useCase();
        System.out.println("Louder dug-dug to wake the neighbours");
    }
}

// ================= Client =================

public class BikeDecoratorPattern {

    public static void main(String[] args) {

        BasicBike bike1 =
                new CustomExhaustDecorator(
                        new FogLightDecorator(
                                new CB350RS()));

        BasicBike bike2 =
                new FogLightDecorator(
                        new CB350Highness());

        System.out.println("========== Bike 1 ==========");
        bike1.look();
        bike1.useCase();

        System.out.println("\n========== Bike 2 ==========");
        bike2.look();
        bike2.useCase();
    }
}