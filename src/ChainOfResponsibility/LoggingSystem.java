package ChainOfResponsibility;

enum LogLevel {
    INFO(10),
    WARN(20),
    ERROR(30);

    private final int value;

    LogLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

interface Logger {
    void log(LogLevel level, String message);
}

abstract class AbstractLogger implements Logger {

    protected final LogLevel baseLevel;
    protected final Logger nextLogger;

    protected AbstractLogger(LogLevel baseLevel, Logger nextLogger) {
        this.baseLevel = baseLevel;
        this.nextLogger = nextLogger;
    }

    @Override
    public void log(LogLevel level, String message) {

        if (level.getValue() >= baseLevel.getValue()) {
            write(message);
        }

        if (nextLogger != null) {
            nextLogger.log(level, message);
        }
    }

    protected abstract void write(String message);
}

class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(LogLevel baseLevel, Logger nextLogger) {
        super(baseLevel, nextLogger);
    }

    @Override
    protected void write(String message) {
        System.out.println("Console Logger : " + message);
    }
}

class FileLogger extends AbstractLogger {

    public FileLogger(LogLevel baseLevel, Logger nextLogger) {
        super(baseLevel, nextLogger);
    }

    @Override
    protected void write(String message) {
        System.out.println("File Logger : " + message);
    }
}

class EmailLogger extends AbstractLogger {

    public EmailLogger(LogLevel baseLevel, Logger nextLogger) {
        super(baseLevel, nextLogger);
    }

    @Override
    protected void write(String message) {
        System.out.println("Email Logger : " + message);
    }
}

public class LoggingSystem {

    public static void main(String[] args) {

        Logger emailLogger = new EmailLogger(LogLevel.ERROR, null);
        Logger fileLogger = new FileLogger(LogLevel.WARN, emailLogger);
        Logger consoleLogger = new ConsoleLogger(LogLevel.INFO, fileLogger);

        System.out.println("===== INFO =====");
        consoleLogger.log(LogLevel.INFO, "Application started");

        System.out.println("\n===== WARN =====");
        consoleLogger.log(LogLevel.WARN, "Disk space running low");

        System.out.println("\n===== ERROR =====");
        consoleLogger.log(LogLevel.ERROR, "Database connection failed");
    }
}