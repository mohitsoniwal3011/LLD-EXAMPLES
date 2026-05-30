/*
 * ============================================================
 *              ABSTRACT FACTORY PATTERN
 * ============================================================
 *
 * Definition:
 * -----------
 * Abstract Factory Pattern is a creational design pattern
 * that provides an interface for creating families of
 * related objects without specifying their concrete classes.
 *
 *
 * Simple Meaning:
 * ---------------
 * Instead of creating objects directly using "new",
 * we use a factory that creates related groups of objects.
 *
 *
 * Example:
 * --------
 * Light Theme:
 * - Light Button
 * - Light Checkbox
 *
 * Dark Theme:
 * - Dark Button
 * - Dark Checkbox
 *
 * The factory ensures that matching UI components
 * are created together.
 *
 *
 * Problem Without Abstract Factory:
 * ---------------------------------
 * Client may accidentally mix unrelated objects:
 *
 * DarkButton + LightCheckbox
 *
 * This can create inconsistent UI behavior.
 *
 *
 * Abstract Factory solves this problem by creating
 * complete related object families together.
 *
 *
 * Advantages:
 * -----------
 * 1. Ensures consistency among related objects
 * 2. Hides object creation logic
 * 3. Reduces tight coupling
 * 4. Easy to add new product families
 * 5. Client works only with interfaces
 *
 *
 * Real-world Examples:
 * --------------------
 * - UI Theme Systems
 * - Windows / Mac / Linux UI creation
 * - Database driver factories
 * - Cloud provider SDKs
 * - Game environment generators
 *
 *
 * Difference Between Factory and Abstract Factory:
 * ------------------------------------------------
 *
 * Factory Pattern:
 * -> Creates ONE type of object
 *
 * Example:
 * factory.NotificationFactory -> SMS / Email
 *
 *
 * Abstract Factory Pattern:
 * -> Creates FAMILIES of related objects
 *
 * Example:
 * UIFactory ->
 *      Button + Checkbox + TextField
 *
 * ============================================================
 */



/*
 * ============================================================
 *                  ABSTRACT PRODUCTS
 * ============================================================
 *
 * These interfaces define common behavior
 * for product families.
 */


/*
 * Abstract Product -> Button
 */
interface UIButton {

    void color();
}


/*
 * Abstract Product -> Checkbox
 */
interface UICheckBox {

    void check();
}



/*
 * ============================================================
 *                  ABSTRACT FACTORY
 * ============================================================
 *
 * Factory responsible for creating
 * related UI components together.
 *
 * Every concrete factory must implement this.
 */
interface UIFactory {

    UIButton createButton();

    UICheckBox createCheckBox();
}



/*
 * ============================================================
 *              LIGHT THEME CONCRETE PRODUCTS
 * ============================================================
 */


/*
 * Light Theme Button
 */
class LightUIButton implements UIButton {

    @Override
    public void color() {

        System.out.println("This is a Light Theme Button");
    }
}


/*
 * Light Theme Checkbox
 */
class LightUICheckBox implements UICheckBox {

    @Override
    public void check() {

        System.out.println("Checking Light Theme Checkbox");
    }
}



/*
 * ============================================================
 *              DARK THEME CONCRETE PRODUCTS
 * ============================================================
 */


/*
 * Dark Theme Button
 */
class DarkUIButton implements UIButton {

    @Override
    public void color() {

        System.out.println("This is a Dark Theme Button");
    }
}


/*
 * Dark Theme Checkbox
 */
class DarkUICheckBox implements UICheckBox {

    @Override
    public void check() {

        System.out.println("Checking Dark Theme Checkbox");
    }
}



/*
 * ============================================================
 *              CONCRETE FACTORIES
 * ============================================================
 *
 * Each factory creates a complete family
 * of matching UI components.
 */


/*
 * Factory for creating Light Theme components
 */
class LightUIFactory implements UIFactory {

    @Override
    public UIButton createButton() {

        return new LightUIButton();
    }

    @Override
    public UICheckBox createCheckBox() {

        return new LightUICheckBox();
    }
}


/*
 * Factory for creating Dark Theme components
 */
class DarkUIFactory implements UIFactory {

    @Override
    public UIButton createButton() {

        return new DarkUIButton();
    }

    @Override
    public UICheckBox createCheckBox() {

        return new DarkUICheckBox();
    }
}



/*
 * ============================================================
 *              FACTORY PROVIDER CLASS
 * ============================================================
 *
 * This class decides which concrete factory
 * should be returned.
 *
 * Think of this as:
 * "Factory of Factories"
 */
class UIFactoryProvider {

    /*
     * Private constructor prevents object creation.
     *
     * Utility class should not be instantiated.
     */
    private UIFactoryProvider() {
    }


    /*
     * Returns the appropriate factory
     * based on requested theme.
     */
    public static UIFactory getFactory(String theme) {

        /*
         * equalsIgnoreCase() makes comparison
         * case-insensitive.
         */
        if ("light".equalsIgnoreCase(theme)) {

            return new LightUIFactory();
        }

        if ("dark".equalsIgnoreCase(theme)) {

            return new DarkUIFactory();
        }


        /*
         * Invalid theme handling
         */
        throw new IllegalArgumentException(
                "Invalid Theme : " + theme
        );
    }
}



/*
 * ============================================================
 *                      CLIENT CLASS
 * ============================================================
 *
 * Client works only with interfaces.
 *
 * Client does NOT know:
 * - Which concrete classes are used
 * - How objects are created
 */
public class UIThemeAbstractFactory {


    public static void main(String[] args) {

        /*
         * Requesting Dark Theme Factory
         */
        UIFactory factory =
                UIFactoryProvider.getFactory("dark");


        /*
         * Creating related UI components
         * from the same family.
         */
        UIButton button =
                factory.createButton();

        UICheckBox checkBox =
                factory.createCheckBox();


        /*
         * Using created objects
         */
        button.color();

        checkBox.check();
    }
}