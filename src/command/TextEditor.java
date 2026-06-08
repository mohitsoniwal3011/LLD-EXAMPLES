import java.util.Stack;

/**
 * THE COMMAND DESIGN PATTERN (Core Notes)
 * <p>
 * <b>1. Definition:</b>
 * A behavioral design pattern that converts a request or action into a standalone
 * object. This object encapsulates all information needed to perform, delay,
 * queue, or undo the action at a later time.
 * <p>
 * <b>2. Core Architecture Checklist:</b>
 * - Parameterless execute(): The command interface takes no arguments. State is locked in via the constructor.
 * - Separation of Concerns: The Invoker handles history, the Receiver handles logic, the Command bridges them.
 * - Stateful Undo: Commands act as time-capsules, storing runtime data needed to reverse themselves.
 * <p>
 * <b>3. Lifecycle Flow:</b>
 * Client Creates Receiver -> Wraps it in a Command -> Hands it to Invoker -> Invoker triggers execute().
 */
public class TextEditor {

    /**
     * The Client. Assembles and wires the design pattern components together.
     */
    public static void main(String[] args) {
        Document myDoc = new Document();
        EditorInvoker editor = new EditorInvoker();

        // 1. Write "Hello "
        TextEditorCommand writeHello = new WriteCommand(myDoc, "Hello ");
        editor.executeCommand(writeHello);
        myDoc.printDocument(); // Output: "Hello "

        // 2. Write "World!"
        TextEditorCommand writeWorld = new WriteCommand(myDoc, "World!");
        editor.executeCommand(writeWorld);
        myDoc.printDocument(); // Output: "Hello World!"

        // 3. Undo "World!"
        System.out.println("\n--- Hitting Undo ---");
        editor.undo();
        myDoc.printDocument(); // Output: "Hello "

        // 4. Undo "Hello "
        System.out.println("\n--- Hitting Undo Again ---");
        editor.undo();
        myDoc.printDocument(); // Output: ""
    }
}


/**
 * ROLE: The Command Interface
 * <p>
 * Establishes a uniform boundary. The invoker only talks to this interface,
 * completely decoupled from how individual concrete commands work.
 */
interface TextEditorCommand {
    /**
     * Triggers the action. Must be parameterless.
     */
    void execute();

    /**
     * Reverses the exact mutation caused by this specific command instance.
     */
    void undo();
}


/**
 * ROLE: The Receiver
 * <p>
 * The actual worker that owns the core state and text data. It knows how to
 * execute low-level buffer manipulations but has no concept of an "undo history".
 */
class Document {
    private final StringBuilder text = new StringBuilder();

    public void insert(int position, String textToInsert) {
        text.insert(position, textToInsert);
    }

    public void delete(int position, int length) {
        text.delete(position, position + length);
    }

    public int getLength() {
        return text.length();
    }

    public void printDocument() {
        System.out.println("Current Text: \"" + text.toString() + "\"");
    }
}


/**
 * ROLE: Concrete Command
 * <p>
 * Binds a specialized action (writing) to the receiver (Document).
 * <p>
 * Notice how it tracks 'executionPosition'. This is internal state calculated
 * dynamically during execute() so that undo() knows exactly where to look later.
 */
class WriteCommand implements TextEditorCommand {
    private final Document document;
    private final String textToWrite;

    // Internal historical metadata captured at runtime
    private int executionPosition;

    /**
     * @param document The target Receiver.
     * @param textToWrite The contextual data bound to this specific operation.
     */
    public WriteCommand(Document document, String textToWrite) {
        this.document = document;
        this.textToWrite = textToWrite;
    }

    @Override
    public void execute() {
        // Capture state before making the mutation
        this.executionPosition = document.getLength();
        document.insert(executionPosition, textToWrite);
    }

    @Override
    public void undo() {
        // Use the captured state to perfectly reverse the mutation
        document.delete(executionPosition, textToWrite.length());
    }
}


/**
 * ROLE: The Invoker
 * <p>
 * The controller middleware. It orchestrates execution timing and manages
 * the transaction log (history stack). It has no dependency on the Document class.
 */
class EditorInvoker {
    private final Stack<TextEditorCommand> history = new Stack<>();

    /**
     * Executes a command blindly and saves it to the tracking stack.
     */
    public void executeCommand(TextEditorCommand command) {
        command.execute();
        history.push(command);
    }

    /**
     * Pops the absolute latest command and requests its direct self-reversal.
     */
    public void undo() {
        if (!history.isEmpty()) {
            TextEditorCommand lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Nothing to undo!");
        }
    }
}