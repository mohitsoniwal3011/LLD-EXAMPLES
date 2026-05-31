package state;

/**
 * ============================================================================
 * MEDIA PLAYER USING STATE DESIGN PATTERN
 * ============================================================================
 *
 * Definition:
 * The State Pattern is a behavioral design pattern that allows an object
 * to alter its behavior when its internal state changes.
 *
 * Instead of using large if-else or switch statements, behavior is
 * encapsulated inside separate state classes.
 *
 * The Context object delegates requests to the currently active state.
 *
 * ============================================================================
 * PROBLEM
 * ============================================================================
 *
 * Without State Pattern:
 *
 * class VideoPlayer {
 *
 *     String state;
 *
 *     void play() {
 *         if(state.equals("PLAYING")) {
 *             ...
 *         }
 *         else if(state.equals("PAUSED")) {
 *             ...
 *         }
 *         else if(state.equals("STOPPED")) {
 *             ...
 *         }
 *     }
 *
 *     void pause() {
 *         ...
 *     }
 *
 *     void stop() {
 *         ...
 *     }
 * }
 *
 * As more states are added, these conditionals become harder to maintain.
 *
 * ============================================================================
 * SOLUTION
 * ============================================================================
 *
 * Create separate classes for each state and delegate behavior to them.
 *
 *                            VideoState
 *                                 ^
 *                                 |
 *       ----------------------------------------------------
 *       |                 |               |                |
 * PlayingState      PausedState     StoppedState    FastForwardState
 *
 * Context:
 *      VideoPlayer
 *
 * ============================================================================
 * STATE TRANSITIONS
 * ============================================================================
 *
 * StoppedState
 *      play()          -> PlayingState
 *      pause()         -> Invalid
 *      stop()          -> Invalid
 *      fastForward()   -> Invalid
 *
 * PlayingState
 *      play()          -> Already Playing
 *      pause()         -> PausedState
 *      stop()          -> StoppedState
 *      fastForward()   -> FastForwardState
 *
 * PausedState
 *      play()          -> PlayingState
 *      pause()         -> Already Paused
 *      stop()          -> StoppedState
 *      fastForward()   -> Invalid
 *
 * FastForwardState
 *      play()          -> PlayingState
 *      pause()         -> PausedState
 *      stop()          -> StoppedState
 *      fastForward()   -> Already Fast Forwarding
 *
 * ============================================================================
 * BENEFITS
 * ============================================================================
 *
 * 1. Eliminates large if-else blocks.
 * 2. Encapsulates state-specific behavior.
 * 3. Follows Open/Closed Principle.
 * 4. Makes state transitions easy to manage.
 * 5. Improves readability and maintainability.
 *
 * ============================================================================
 * PARTICIPANTS
 * ============================================================================
 *
 * State:
 *      VideoState
 *
 * Concrete States:
 *      PlayingState
 *      PausedState
 *      StoppedState
 *      FastForwardState
 *
 * Context:
 *      VideoPlayer
 *
 * ============================================================================
 */

/**
 * State interface.
 *
 * Every state must define how it reacts
 * to media player actions.
 */
interface VideoState {

    void play(VideoPlayer player);

    void pause(VideoPlayer player);

    void stop(VideoPlayer player);

    void fastForward(VideoPlayer player);
}

/**
 * Represents the Playing state.
 */
class PlayingState implements VideoState {

    @Override
    public void play(VideoPlayer player) {
        System.out.println("Already Playing...");
    }

    @Override
    public void pause(VideoPlayer player) {
        System.out.println("Video Paused...");
        player.setState(VideoPlayer.PAUSED_STATE);
    }

    @Override
    public void stop(VideoPlayer player) {
        System.out.println("Video Stopped...");
        player.setState(VideoPlayer.STOPPED_STATE);
    }

    @Override
    public void fastForward(VideoPlayer player) {
        System.out.println("Fast Forwarding video at 2x speed...");
        player.setState(VideoPlayer.FAST_FORWARD_STATE);
    }
}

/**
 * Represents the Paused state.
 */
class PausedState implements VideoState {

    @Override
    public void play(VideoPlayer player) {
        System.out.println("Resuming video...");
        player.setState(VideoPlayer.PLAYING_STATE);
    }

    @Override
    public void pause(VideoPlayer player) {
        System.out.println("Already Paused...");
    }

    @Override
    public void stop(VideoPlayer player) {
        System.out.println("Video Stopped...");
        player.setState(VideoPlayer.STOPPED_STATE);
    }

    @Override
    public void fastForward(VideoPlayer player) {
        System.out.println("Cannot fast forward while paused.");
    }
}

/**
 * Represents the Stopped state.
 */
class StoppedState implements VideoState {

    @Override
    public void play(VideoPlayer player) {
        System.out.println("Starting video...");
        player.setState(VideoPlayer.PLAYING_STATE);
    }

    @Override
    public void pause(VideoPlayer player) {
        System.out.println("Cannot pause. Video is stopped.");
    }

    @Override
    public void stop(VideoPlayer player) {
        System.out.println("Already Stopped...");
    }

    @Override
    public void fastForward(VideoPlayer player) {
        System.out.println("Cannot fast forward. Video is stopped.");
    }
}

/**
 * Represents the Fast Forward state.
 */
class FastForwardState implements VideoState {

    @Override
    public void play(VideoPlayer player) {
        System.out.println("Returning to normal playback speed...");
        player.setState(VideoPlayer.PLAYING_STATE);
    }

    @Override
    public void pause(VideoPlayer player) {
        System.out.println("Video Paused...");
        player.setState(VideoPlayer.PAUSED_STATE);
    }

    @Override
    public void stop(VideoPlayer player) {
        System.out.println("Video Stopped...");
        player.setState(VideoPlayer.STOPPED_STATE);
    }

    @Override
    public void fastForward(VideoPlayer player) {
        System.out.println("Already Fast Forwarding...");
    }
}

/**
 * Context class.
 *
 * Maintains the current state and delegates
 * actions to the active state object.
 */
class VideoPlayer {

    /**
     * Reusable singleton state instances.
     *
     * Since states do not hold any mutable data,
     * we can reuse the same objects instead of
     * creating new ones repeatedly.
     */
    public static final VideoState PLAYING_STATE = new PlayingState();
    public static final VideoState PAUSED_STATE = new PausedState();
    public static final VideoState STOPPED_STATE = new StoppedState();
    public static final VideoState FAST_FORWARD_STATE = new FastForwardState();

    private VideoState currentState;

    public VideoPlayer(VideoState initialState) {
        this.currentState = initialState;
    }

    public void setState(VideoState state) {
        this.currentState = state;
    }

    public void play() {
        currentState.play(this);
    }

    public void pause() {
        currentState.pause(this);
    }

    public void stop() {
        currentState.stop(this);
    }

    public void fastForward() {
        currentState.fastForward(this);
    }
}

/**
 * Driver class.
 */
public class MediaPlayerStatePatternDemo {

    public static void main(String[] args) {

        VideoPlayer player =
                new VideoPlayer(VideoPlayer.STOPPED_STATE);

        player.play();
        player.play();

        player.fastForward();
        player.fastForward();

        player.play();

        player.pause();
        player.pause();

        player.fastForward();

        player.play();

        player.stop();
        player.stop();

        player.pause();
    }
}
