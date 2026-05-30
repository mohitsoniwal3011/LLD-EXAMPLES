/*
 * ============================================================
 *                  STRATEGY PATTERN
 * ============================================================
 *
 * Definition:
 * -----------
 * Strategy Pattern is a behavioral design pattern
 * that encapsulates interchangeable algorithms separately
 * and allows them to be switched dynamically at runtime.
 *
 *
 * Simple Meaning:
 * ---------------
 * Different algorithms/behaviors are placed
 * inside separate classes.
 *
 * The main service/context class uses these
 * algorithms through a common interface.
 *
 *
 * Example:
 * --------
 * Google Maps can calculate:
 * - Shortest Route
 * - Fastest Route
 * - Scenic Route
 *
 * Each route-finding logic is different.
 *
 * Instead of writing:
 *
 * if(type == "shortest")
 * else if(type == "fastest")
 *
 * we create separate strategy classes.
 *
 *
 * Advantages:
 * -----------
 * 1. Removes large if-else chains
 * 2. Makes algorithms interchangeable
 * 3. Supports runtime behavior switching
 * 4. Follows Open/Closed Principle
 * 5. Easier to maintain and extend
 *
 *
 * IMPORTANT INTERVIEW LINE:
 * -------------------------
 * "Strategy Pattern encapsulates interchangeable
 * algorithms separately and allows behavior
 * to change dynamically at runtime."
 *
 * ============================================================
 */



/*
 * ============================================================
 *                  STRATEGY INTERFACE
 * ============================================================
 *
 * Common interface for all path finding algorithms.
 */
interface PathFinder {

    String findPath();
}



/*
 * ============================================================
 *              CONCRETE STRATEGIES
 * ============================================================
 *
 * Each class implements its own
 * path finding algorithm.
 */


/*
 * Strategy -> Shortest Path Algorithm
 */
class ShortestPathFinder implements PathFinder {

    @Override
    public String findPath() {

        return "Returning shortest path";
    }
}


/*
 * Strategy -> Fastest Path Algorithm
 */
class FastestPathFinder implements PathFinder {

    @Override
    public String findPath() {

        return "Returning fastest path";
    }
}


/*
 * Strategy -> Most Relevant Path Algorithm
 */
class RelevantPathFinder implements PathFinder {

    @Override
    public String findPath() {

        return "Returning most relevant path";
    }
}



/*
 * ============================================================
 *                  CONTEXT CLASS
 * ============================================================
 *
 * This class uses strategies dynamically.
 *
 * IMPORTANT:
 * Context does NOT know:
 * - shortest path logic
 * - fastest path logic
 * - relevant path logic
 *
 * It only works with the strategy interface.
 */
class PathFindingService {

    /*
     * Current strategy reference
     */
    private PathFinder pathFinder;


    /*
     * Strategy injected through constructor.
     */
    PathFindingService(PathFinder pathFinder) {

        this.pathFinder = pathFinder;
    }


    /*
     * Allows strategy switching dynamically
     * at runtime.
     */
    public void changeStrategy(
            PathFinder pathFinder
    ) {

        this.pathFinder = pathFinder;
    }


    /*
     * Executes currently selected strategy.
     */
    public String findPath() {

        return this.pathFinder.findPath();
    }
}



/*
 * ============================================================
 *                      CLIENT CLASS
 * ============================================================
 *
 * Demonstrates Strategy Pattern usage.
 */
public class MapStrategyPattern {

    public static void main(String[] args) {

        /*
         * Injecting Shortest Path Strategy
         */
        PathFinder shortestPathStrategy =
                new ShortestPathFinder();


        /*
         * Context class using strategy
         */
        PathFindingService service =
                new PathFindingService(
                        shortestPathStrategy
                );


        /*
         * Executing current strategy
         */
        System.out.println(
                service.findPath()
        );


        /*
         * Dynamically changing strategy
         * at runtime.
         */
        service.changeStrategy(
                new FastestPathFinder()
        );


        /*
         * Executing new strategy
         */
        System.out.println(
                service.findPath()
        );


        /*
         * Switching strategy again
         */
        service.changeStrategy(
                new RelevantPathFinder()
        );


        /*
         * Executing another strategy
         */
        System.out.println(
                service.findPath()
        );
    }
}