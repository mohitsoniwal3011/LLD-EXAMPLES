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
 * A single object and a collection/group of objects should behave in the
 * same way from the client perspective.
 *
 *
 * =========================================================================
 * PROBLEM WITHOUT COMPOSITE PATTERN
 * =========================================================================
 *
 * Imagine a composite.File System containing:
 * 1. Files
 * 2. Folders
 *
 * Without Composite Pattern, client code would look like:
 *
 *      if(type == FILE) {
 *          perform file operations
 *      }
 *      else if(type == FOLDER) {
 *          perform folder operations
 *      }
 *
 * Problems:
 * -------------------------------------------------------------------------
 * 1. Too many if-else conditions.
 *
 * 2. Tight coupling between client and object types.
 *
 * 3. Difficult to extend system.
 *
 * 4. Client must know internal implementation details.
 *
 *
 * =========================================================================
 * SOLUTION USING COMPOSITE PATTERN
 * =========================================================================
 *
 * Instead of checking object types manually,
 * we create a common interface:
 *
 *                      composite.FileSystem
 *                     /          \
 *                    /            \
 *                 composite.File          composite.Folder
 *                                   \
 *                                    \
 *                              contains composite.FileSystem
 *
 *
 * IMPORTANT IDEA:
 * -------------------------------------------------------------------------
 * composite.Folder stores List<composite.FileSystem>.
 *
 * This means:
 * 1. composite.Folder can contain files
 * 2. composite.Folder can contain other folders
 *
 * This creates recursive tree hierarchy.
 *
 *
 * =========================================================================
 * REAL WORLD EXAMPLES OF COMPOSITE
 * =========================================================================
 *
 * 1. composite.File System
 *    - composite.File
 *    - composite.Folder
 *
 * 2. Restaurant Menu
 *    - Menu Item
 *    - Menu
 *
 * 3. Company Hierarchy
 *    - Employee
 *    - Manager
 *
 * 4. UI Components
 *    - Button
 *    - Panel
 *
 *
 * =========================================================================
 * THIS EXAMPLE
 * =========================================================================
 *
 * Problem Statement:
 * -------------------------------------------------------------------------
 * Design a composite.File System where:
 *
 * 1. composite.File supports:
 *      - show()
 *      - getName()
 *
 * 2. composite.Folder supports:
 *      - show()
 *      - getName()
 *      - add()
 *
 * 3. composite.Folder can contain:
 *      - Files
 *      - Other folders
 *
 * 4. Calling show() on parent folder should recursively display:
 *      - all files
 *      - all nested folders
 *
 *
 * =========================================================================
 * TREE STRUCTURE OF THIS EXAMPLE
 * =========================================================================
 *
 * General Info
 * ├── Bikes
 * │      ├── honda.txt
 * │      └── triumph.txt
 * │
 * └── People
 *        ├── Mohit.txt
 *        └── Rohit.txt
 *
 *
 * =========================================================================
 * HOW THIS IMPLEMENTATION WORKS
 * =========================================================================
 *
 * composite.FileSystem (Component)
 * -------------------------------------------------------------------------
 * Common interface implemented by:
 *    - composite.File (Leaf)
 *    - composite.Folder (Composite)
 *
 *
 * composite.File (Leaf)
 * -------------------------------------------------------------------------
 * Represents actual individual files.
 *
 * Examples:
 *    - Mohit.txt
 *    - honda.txt
 *
 * These are terminal/end objects.
 *
 *
 * composite.Folder (Composite)
 * -------------------------------------------------------------------------
 * Represents folder/container.
 *
 * composite.Folder can contain:
 *    - Files
 *    - Other folders
 *
 * This creates recursive tree structure.
 *
 *
 * =========================================================================
 * RECURSION MAGIC OF COMPOSITE
 * =========================================================================
 *
 * Most important line:
 *
 *      file.show();
 *
 * Since every object implements composite.FileSystem:
 *
 * 1. If object is composite.File:
 *      -> Displays file details.
 *
 * 2. If object is composite.Folder:
 *      -> Recursively displays all child files/folders.
 *
 *
 * =========================================================================
 * BENEFITS OF COMPOSITE PATTERN
 * =========================================================================
 *
 * 1. Removes large if-else chains.
 *
 * 2. Simplifies recursive tree handling.
 *
 * 3. Client treats:
 *      - single object
 *      - group of objects
 *    uniformly.
 *
 * 4. Easily extensible.
 *
 * 5. Cleaner and scalable architecture.
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
 * 3. You want uniform treatment of:
 *      - individual objects
 *      - collection of objects
 *
 *
 * =========================================================================
 * INTERVIEW KEYWORDS
 * =========================================================================
 *
 * - Structural Design Pattern
 * - Recursive Composition
 * - Tree Structure
 * - Uniform Treatment
 * - Recursive Delegation
 * - Parent Child Hierarchy
 *
 * =========================================================================
 */

/**
 * =========================================================================
 * COMPONENT INTERFACE
 * =========================================================================
 *
 * Common interface for:
 * 1. composite.File
 * 2. composite.Folder
 *
 * =========================================================================
 */
interface FileSystem {

    /**
     * Displays file/folder information.
     */
    void show();

    /**
     * Returns name of file/folder.
     *
     * @return name
     */
    String getName();
}

/**
 * =========================================================================
 * LEAF CLASS
 * =========================================================================
 *
 * composite.File represents actual individual files.
 *
 * Examples:
 * - Mohit.txt
 * - honda.txt
 *
 * These are terminal/end objects in hierarchy.
 *
 * =========================================================================
 */
class File implements FileSystem {

    /**
     * Name of file.
     */
    private final String name;

    /**
     * Constructor to initialize file name.
     *
     * @param name file name
     */
    public File(String name) {
        this.name = name;
    }

    /**
     * Displays file information.
     */
    @Override
    public void show() {
        System.out.println("composite.File Name : " + this.name);
    }

    /**
     * Returns file name.
     *
     * @return file name
     */
    @Override
    public String getName() {
        return name;
    }
}

/**
 * =========================================================================
 * COMPOSITE CLASS
 * =========================================================================
 *
 * composite.Folder represents folder/container.
 *
 * composite.Folder can contain:
 * 1. Files
 * 2. Other folders
 *
 * This creates recursive tree hierarchy.
 *
 * =========================================================================
 */
class Folder implements FileSystem {

    /**
     * Stores child files/folders.
     */
    private final List<FileSystem> files;

    /**
     * Name of folder.
     */
    private final String name;

    /**
     * Constructor to initialize folder.
     *
     * @param name folder name
     */
    public Folder(String name) {
        this.name = name;
        this.files = new ArrayList<>();
    }

    /**
     * Adds file/folder into current folder.
     *
     * @param file file/folder to add
     */
    public void add(FileSystem file) {

        System.out.println(
                "Successfully added file: "
                        + file.getName()
                        + " to the folder: "
                        + this.name
        );

        this.files.add(file);
    }

    /**
     * Recursively displays all child files/folders.
     */
    @Override
    public void show() {

        System.out.println("composite.Folder Name: " + this.name);

        for (FileSystem file : files) {
            file.show();
        }
    }

    /**
     * Returns folder name.
     *
     * @return folder name
     */
    @Override
    public String getName() {
        return this.name;
    }
}

/**
 * =========================================================================
 * DRIVER CLASS
 * =========================================================================
 *
 * Creates composite.File System hierarchy and demonstrates recursive traversal
 * using Composite Pattern.
 *
 * =========================================================================
 */
public class FileCompositeDesignPatterns {

    public static void main(String[] args) {

        /**
         * Creating files.
         */
        File file1 = new File("Mohit.txt");
        File file2 = new File("Rohit.txt");

        /**
         * Creating People folder.
         */
        Folder folder1 = new Folder("People");

        folder1.add(file1);
        folder1.add(file2);

        /**
         * Creating bike-related files.
         */
        File file3 = new File("honda.txt");
        File file4 = new File("triumph.txt");

        /**
         * Creating Bikes folder.
         */
        Folder folder2 = new Folder("Bikes");

        folder2.add(file3);
        folder2.add(file4);

        /**
         * Creating parent folder.
         */
        Folder folder3 = new Folder("General Info");

        /**
         * Adding nested folders.
         */
        folder3.add(folder2);
        folder3.add(folder1);

        /**
         * Recursively displays complete file system hierarchy.
         */
        folder3.show();
    }
}