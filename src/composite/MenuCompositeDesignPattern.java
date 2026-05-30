package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * COMPOSITE DESIGN PATTERN
 * =========================================================================
 *
 * Definition:
 * -------------------------------------------------------------------------
 * Composite Pattern is a Structural Design Pattern which allows us to treat
 * individual objects and groups of objects uniformly.
 *
 * In simple words:
 * -------------------------------------------------------------------------
 * We should be able to treat:
 *    - single object
 *    - collection/group of objects
 *
 * in the exact same way.
 *
 *
 * =========================================================================
 * REAL WORLD EXAMPLES
 * =========================================================================
 *
 * 1. composite.File System
 *    - composite.File
 *    - composite.Folder (contains files/folders)
 *
 * 2. Restaurant Menu
 *    - Menu Item
 *    - Menu (contains items/submenus)
 *
 * 3. Company Hierarchy
 *    - Employee
 *    - Manager (contains employees/managers)
 *
 * 4. UI Components
 *    - Button
 *    - Panel (contains buttons/panels)
 *
 *
 * =========================================================================
 * MAIN IDEA OF COMPOSITE PATTERN
 * =========================================================================
 *
 * Composite Pattern mainly consists of:
 *
 * 1. Component
 *    -> Common interface for all objects.
 *
 * 2. Leaf
 *    -> Actual individual object.
 *
 * 3. Composite
 *    -> Container object which can contain:
 *          - leaf objects
 *          - other composite objects
 *
 *
 * =========================================================================
 * THIS EXAMPLE
 * =========================================================================
 *
 * Problem Statement:
 * -------------------------------------------------------------------------
 * Design a restaurant menu system.
 *
 *
 * Requirements:
 * -------------------------------------------------------------------------
 * 1. A menu can contain:
 *      - Menu items
 *      - Other menus
 *
 * 2. Every menu/menu-item should support:
 *      - display()
 *
 * 3. Calling display() on parent menu should recursively display:
 *      - all submenus
 *      - all menu items
 *
 *
 * =========================================================================
 * MENU HIERARCHY
 * =========================================================================
 *
 * Main Menu
 * ├── Veg Menu
 * │    ├── Paneer
 * │    └── Rice
 * └── Drinks Menu
 *      ├── Cold Drinks
 *      │      └── Coke
 *      └── Hot Drinks
 *             └── Coffee
 *
 *
 * =========================================================================
 * HOW THIS IMPLEMENTATION WORKS
 * =========================================================================
 *
 * composite.MenuItem (Component)
 * -------------------------------------------------------------------------
 * Common interface implemented by:
 *    - composite.MenuItemImpl (Leaf)
 *    - composite.MenuItemContainer (Composite)
 *
 *
 * composite.MenuItemImpl (Leaf)
 * -------------------------------------------------------------------------
 * Represents actual menu items like:
 *    - Paneer
 *    - Rice
 *    - Coke
 *    - Coffee
 *
 * These are terminal/end objects.
 *
 *
 * composite.MenuItemContainer (Composite)
 * -------------------------------------------------------------------------
 * Represents menus/submenus.
 *
 * These can contain:
 *    - individual menu items
 *    - other submenus
 *
 * This creates recursive tree hierarchy.
 *
 *
 * =========================================================================
 * RECURSION MAGIC OF COMPOSITE
 * =========================================================================
 *
 * Most important line:
 *
 *      item.display();
 *
 * Since every object implements composite.MenuItem:
 *
 * 1. If object is composite.MenuItemImpl:
 *      -> Displays actual menu item.
 *
 * 2. If object is composite.MenuItemContainer:
 *      -> Recursively displays all child items/submenus.
 *
 *
 * =========================================================================
 * BENEFITS OF COMPOSITE PATTERN
 * =========================================================================
 *
 * 1. Makes recursive tree structures easy to manage.
 *
 * 2. Treats individual objects and groups uniformly.
 *
 * 3. Simplifies client-side logic.
 *
 * 4. Easily extensible hierarchy.
 *
 *
 * =========================================================================
 * WHEN TO USE COMPOSITE PATTERN
 * =========================================================================
 *
 * Use Composite Pattern when:
 *
 * 1. Your system forms tree hierarchy.
 *
 * 2. Objects recursively contain similar objects.
 *
 * 3. You want client code to treat:
 *      - single object
 *      - group of objects
 *    in the same way.
 *
 *
 * =========================================================================
 * INTERVIEW KEYWORDS
 * =========================================================================
 *
 * - Structural Design Pattern
 * - Recursive Composition
 * - Tree Hierarchy
 * - Uniform Treatment
 * - Recursive Delegation
 * - Parent Child Relationship
 *
 * =========================================================================
 */

/**
 * =========================================================================
 * COMPONENT INTERFACE
 * =========================================================================
 *
 * Common interface for:
 * 1. Menu Items
 * 2. Menu Containers
 *
 * =========================================================================
 */
interface MenuItem {

    /**
     * Displays menu/menu-item information.
     */
    void display();
}

/**
 * =========================================================================
 * COMPOSITE CLASS
 * =========================================================================
 *
 * composite.MenuItemContainer represents menus/submenus.
 *
 * This class can contain:
 * 1. Menu items
 * 2. Other submenus
 *
 * This creates recursive tree hierarchy.
 *
 * =========================================================================
 */
class MenuItemContainer implements MenuItem {

    /**
     * Name of the menu/submenu.
     */
    private final String name;

    /**
     * Stores child menu items/submenus.
     */
    private final List<MenuItem> items;

    /**
     * Constructor to initialize menu name.
     *
     * @param name name of menu
     */
    MenuItemContainer(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    /**
     * Recursively displays all child menu items/submenus.
     */
    @Override
    public void display() {

        System.out.println("Menu Container Name: " + this.name);

        for (MenuItem item : items) {
            item.display();
        }
    }

    /**
     * Adds menu item/submenu into current menu.
     *
     * @param item menu item/submenu
     */
    public void addItem(MenuItem item) {
        this.items.add(item);
    }
}

/**
 * =========================================================================
 * LEAF CLASS
 * =========================================================================
 *
 * composite.MenuItemImpl represents actual menu items.
 *
 * Examples:
 * - Paneer
 * - Rice
 * - Coke
 * - Coffee
 *
 * These are terminal/end objects in hierarchy.
 *
 * =========================================================================
 */
class MenuItemImpl implements MenuItem {

    /**
     * Name of menu item.
     */
    private final String itemName;

    /**
     * Constructor to initialize menu item name.
     *
     * @param itemName name of item
     */
    MenuItemImpl(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Displays actual menu item.
     */
    @Override
    public void display() {
        System.out.println("Item Name: " + this.itemName);
    }
}

/**
 * =========================================================================
 * DRIVER CLASS
 * =========================================================================
 *
 * Creates restaurant menu hierarchy and demonstrates recursive display
 * operation using Composite Pattern.
 *
 * =========================================================================
 */
public class MenuCompositeDesignPattern {

    public static void main(String[] args) {

        /**
         * Creating main menu.
         */
        MenuItemContainer mainMenu =
                new MenuItemContainer("Main Menu");

        /**
         * Creating veg menu.
         */
        MenuItemContainer vegMenu =
                new MenuItemContainer("Veg Menu");

        /**
         * Creating drinks menu.
         */
        MenuItemContainer drinks =
                new MenuItemContainer("Drinks Menu");

        /**
         * Creating drink categories.
         */
        MenuItemContainer coldDrinks =
                new MenuItemContainer("Cold Drinks");

        MenuItemContainer hotDrinks =
                new MenuItemContainer("Hot Drinks");

        /**
         * Adding submenus into main menu.
         */
        mainMenu.addItem(vegMenu);
        mainMenu.addItem(drinks);

        /**
         * Adding drink categories into drinks menu.
         */
        drinks.addItem(coldDrinks);
        drinks.addItem(hotDrinks);

        /**
         * Adding actual drink items.
         */
        coldDrinks.addItem(new MenuItemImpl("Coke"));
        hotDrinks.addItem(new MenuItemImpl("Coffee"));

        /**
         * Adding veg items.
         */
        vegMenu.addItem(new MenuItemImpl("Paneer"));
        vegMenu.addItem(new MenuItemImpl("Rice"));

        /**
         * Recursively displays entire menu hierarchy.
         */
        mainMenu.display();
    }
}