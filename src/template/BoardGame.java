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
 * - Allow subclasses to implement variable steps.
 * - Ensure the sequence of execution remains unchanged.
 * - Promote code reuse for common logic.
 *
 * Real World Example:
 * Board Game Engine
 *
 * Every board game follows the same high-level flow:
 *
 * 1. Roll Dice
 * 2. Move Player
 * 3. Decide Winner
 * 4. Reward Winner
 *
 * However, different games implement the movement and winner
 * calculation differently.
 *
 * Snake & Ladder:
 * - Players move according to snakes and ladders.
 * - First player to reach the last cell wins.
 *
 * Ludo:
 * - Players move according to ludo rules.
 * - First player to bring all tokens home wins.
 *
 * Example Walkthrough:
 *
 * SnakeAndLadder game = new SnakeAndLadder();
 * game.play();
 *
 * Execution:
 *
 * play()
 *   -> rollDice()
 *   -> movePlayer()    (Snake & Ladder logic)
 *   -> decideWinner()  (Snake & Ladder logic)
 *   -> rewardWinner()
 *
 * Output:
 * Dice rolled
 * Moving player according to Snake & Ladder rules
 * Checking winner according to Snake & Ladder rules
 * Winner won my heart
 *
 * ------------------------------------------------------------
 *
 * Ludo game = new Ludo();
 * game.play();
 *
 * Execution:
 *
 * play()
 *   -> rollDice()
 *   -> movePlayer() (Ludo logic)
 *   -> decideWinner() (Ludo logic)
 *   -> rewardWinner()
 *
 * Output:
 * Dice rolled
 * Moving player according to Ludo rules
 * Checking winner according to Ludo rules
 * Winner won my heart
 *
 * Benefits:
 * - Eliminates duplicated workflow logic.
 * - Enforces a fixed algorithm structure.
 * - Allows subclasses to customize only required steps.
 * - Follows Open/Closed Principle.
 *
 * Common Real World Examples:
 * - Spring JdbcTemplate
 * - Servlet lifecycle methods
 * - JUnit test execution flow
 * - Build pipelines
 * - Data processing workflows
 */
abstract class BasicGame {

    /**
     * Template Method.
     *
     * Defines the fixed workflow for all board games.
     * Subclasses can customize certain steps but cannot
     * change the execution order.
     */
    public final void play() {
        rollDice();
        movePlayer();
        decideWinner();
        rewardWinner();
    }

    /**
     * Variable step implemented by subclasses.
     */
    protected abstract void movePlayer();

    /**
     * Variable step implemented by subclasses.
     */
    protected abstract void decideWinner();

    /**
     * Common step shared by all games.
     */
    private void rollDice() {
        System.out.println("Dice rolled");
    }

    /**
     * Common step shared by all games.
     */
    private void rewardWinner() {
        System.out.println("Winner won my heart");
    }
}

/**
 * Concrete implementation for Snake & Ladder.
 */
class SnakeAndLadder extends BasicGame {

    @Override
    protected void movePlayer() {
        System.out.println(
                "Moving player according to Snake & Ladder rules"
        );
    }

    @Override
    protected void decideWinner() {
        System.out.println(
                "Checking winner according to Snake & Ladder rules"
        );
    }
}

/**
 * Concrete implementation for Ludo.
 */
class Ludo extends BasicGame {

    @Override
    protected void movePlayer() {
        System.out.println(
                "Moving player according to Ludo rules"
        );
    }

    @Override
    protected void decideWinner() {
        System.out.println(
                "Checking winner according to Ludo rules"
        );
    }
}

/**
 * Client code.
 */
public class BoardGame {

    public static void main(String[] args) {

        System.out.println("===== Snake & Ladder =====");

        BasicGame snakeAndLadder = new SnakeAndLadder();
        snakeAndLadder.play();

        System.out.println("\n===== Ludo =====");

        BasicGame ludo = new Ludo();
        ludo.play();
    }
}
