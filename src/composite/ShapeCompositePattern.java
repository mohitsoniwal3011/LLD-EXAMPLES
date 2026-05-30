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
 * A single object and a collection of objects should behave in the same way.
 *
 * Real World Examples:
 * -------------------------------------------------------------------------
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
 * We create:
 *
 * 1. Component
 *    -> Common interface for both leaf and composite objects.
 *
 * 2. Leaf
 *    -> Actual individual object.
 *
 * 3. Composite
 *    -> Container object that can contain leaf objects as well as other
 *       composite objects.
 *
 *
 * =========================================================================
 * THIS EXAMPLE
 * =========================================================================
 *
 * Problem Statement:
 * -------------------------------------------------------------------------
 * Design a graphical drawing application.
 *
 * Requirements:
 * -------------------------------------------------------------------------
 * 1. A shape can be:
 *      - Circle
 *      - Rectangle
 *      - Triangle
 *      - OR a group of shapes.
 *
 * 2. A shape group can contain:
 *      - Individual shapes
 *      - Other shape groups
 *
 * 3. Every shape should support:
 *      - displayShapeName()
 *      - move(x, y)
 *
 * 4. Calling operations on a group should recursively apply operations to
 *    all child shapes/groups.
 *
 *
 * =========================================================================
 * HOW THIS IMPLEMENTATION WORKS
 * =========================================================================
 *
 * composite.Shape (Component)
 * -------------------------------------------------------------------------
 * Common interface for:
 *    - composite.ShapeItem (Leaf)
 *    - composite.ShapeContainer (Composite)
 *
 *
 * composite.ShapeItem (Leaf)
 * -------------------------------------------------------------------------
 * Represents actual individual shapes like:
 *    - Circle
 *    - Rectangle
 *    - Triangle
 *
 * These are the end objects of the tree.
 *
 *
 * composite.ShapeContainer (Composite)
 * -------------------------------------------------------------------------
 * Represents a group/container of shapes.
 *
 * This can contain:
 *    - composite.ShapeItem objects
 *    - Other composite.ShapeContainer objects
 *
 * This creates a recursive tree structure.
 *
 *
 * =========================================================================
 * RECURSION MAGIC OF COMPOSITE
 * =========================================================================
 *
 * The most important line:
 *
 *      shape.move(x, y);
 *
 * OR
 *
 *      item.displayShapeName();
 *
 * Since every object implements composite.Shape:
 * -------------------------------------------------------------------------
 * 1. If object is composite.ShapeItem:
 *      -> Directly performs operation.
 *
 * 2. If object is composite.ShapeContainer:
 *      -> Recursively performs operation on all children.
 *
 *
 * =========================================================================
 * TREE STRUCTURE OF THIS EXAMPLE
 * =========================================================================
 *
 * Main Container
 * ├── Circle
 * ├── Square
 * ├── Rectangle
 * └── Small Group
 *      ├── Triangle
 *      └── Pyramid
 *
 *
 * =========================================================================
 * BENEFITS OF COMPOSITE PATTERN
 * =========================================================================
 *
 * 1. Recursive tree structures become easy to manage.
 *
 * 2. Client code treats single object and group uniformly.
 *
 * 3. Reduces complex conditional logic.
 *
 * 4. Makes hierarchical systems easier to extend.
 *
 *
 * =========================================================================
 * WHEN TO USE COMPOSITE PATTERN
 * =========================================================================
 *
 * Use Composite Pattern when:
 *
 * 1. Your system has tree-like hierarchy.
 *
 * 2. Objects can contain similar objects recursively.
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
 * - Tree Structure
 * - Recursive Composition
 * - Uniform Treatment
 * - Recursive Delegation
 * - Hierarchical Objects
 *
 * =========================================================================
 */

interface Shape {

    /**
     * Displays the shape/group information.
     */
    void displayShapeName();

    /**
     * Moves the shape/group by x and y coordinates.
     *
     * @param x movement in x-axis
     * @param y movement in y-axis
     */
    void move(int x, int y);
}

/**
 * =========================================================================
 * COMPOSITE CLASS
 * =========================================================================
 *
 * composite.ShapeContainer acts as a group/container of shapes.
 *
 * This class can contain:
 * 1. Individual shapes
 * 2. Other shape containers
 *
 * This creates recursive tree hierarchy.
 * =========================================================================
 */
class ShapeContainer implements Shape {

    /**
     * Name of the container/group.
     */
    private final String name;

    /**
     * Stores child shapes/groups.
     */
    private final List<Shape> shapeItemList;

    /**
     * Constructor to initialize container name.
     *
     * @param name name of the container
     */
    ShapeContainer(String name) {
        this.name = name;
        this.shapeItemList = new ArrayList<>();
    }

    /**
     * Recursively displays all shapes/groups.
     */
    @Override
    public void displayShapeName() {

        System.out.println("Container Name: " + this.name);

        for (Shape item : shapeItemList) {
            item.displayShapeName();
        }
    }

    /**
     * Recursively moves all shapes/groups.
     *
     * @param x movement in x-axis
     * @param y movement in y-axis
     */
    @Override
    public void move(int x, int y) {

        System.out.println(this.name +
                " container moved by (" + x + "," + y + ")");

        for (Shape shape : shapeItemList) {
            shape.move(x, y);
        }
    }

    /**
     * Adds shape/group into current container.
     *
     * @param item shape/group to add
     */
    public void addShape(Shape item) {
        this.shapeItemList.add(item);
    }
}

/**
 * =========================================================================
 * LEAF CLASS
 * =========================================================================
 *
 * composite.ShapeItem represents actual individual shapes.
 *
 * Examples:
 * - Circle
 * - Rectangle
 * - Triangle
 *
 * These are terminal/end objects in the hierarchy.
 * =========================================================================
 */
class ShapeItem implements Shape {

    /**
     * Name of the shape.
     */
    private final String name;

    /**
     * Constructor to initialize shape name.
     *
     * @param name name of shape
     */
    ShapeItem(String name) {
        this.name = name;
    }

    /**
     * Displays shape information.
     */
    @Override
    public void displayShapeName() {
        System.out.println("Item Name: " + this.name);
    }

    /**
     * Moves the individual shape.
     *
     * @param x movement in x-axis
     * @param y movement in y-axis
     */
    @Override
    public void move(int x, int y) {

        System.out.println(this.name +
                " moved by (" + x + "," + y + ")");
    }
}

/**
 * =========================================================================
 * DRIVER CLASS
 * =========================================================================
 *
 * Creates shape hierarchy and demonstrates:
 * 1. Recursive display
 * 2. Recursive movement
 *
 * =========================================================================
 */
public class ShapeCompositePattern {

    public static void main(String[] args) {

        /**
         * Creating main container.
         */
        ShapeContainer mainContainer =
                new ShapeContainer("Main Container");

        /**
         * Creating nested group.
         */
        ShapeContainer smallGroup =
                new ShapeContainer("Small Group");

        /**
         * Adding shapes to main container.
         */
        mainContainer.addShape(new ShapeItem("Circle"));
        mainContainer.addShape(new ShapeItem("Square"));
        mainContainer.addShape(new ShapeItem("Rectangle"));

        /**
         * Adding nested group into main container.
         */
        mainContainer.addShape(smallGroup);

        /**
         * Adding shapes inside nested group.
         */
        smallGroup.addShape(new ShapeItem("Triangle"));
        smallGroup.addShape(new ShapeItem("Pyramid"));

        /**
         * Recursively displays all shapes/groups.
         */
        mainContainer.displayShapeName();

        System.out.println();

        /**
         * Recursively moves all shapes/groups.
         */
        mainContainer.move(1, 2);
    }
}