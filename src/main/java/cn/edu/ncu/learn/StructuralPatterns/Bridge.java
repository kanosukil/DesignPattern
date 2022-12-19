package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 某日志记录器（Logger）既可以支持不同的操作系统，还可以支持多种编程语言，并且可以使用不同的输出方式。
 * <p>
 * 使用桥接模式设计该系统。
 */
enum ImplementationLanguage {
    C, CPP, JAVA, PYTHON, CS, SHELL, OC, SWIFT
}

interface LoggerAPI {
    void info(String msg);

    void debug(String msg);

    void warn(String msg);

    void error(String msg);
}

abstract class WindowLogger implements LoggerAPI {
    protected final String os = "Windows";
    protected final ImplementationLanguage language;

    protected WindowLogger(ImplementationLanguage language) {
        this.language = language;
    }

    @Override
    public void info(String msg) {
        System.out.format("\33[36;4mInfo(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void debug(String msg) {
        System.out.format("\33[34;4mDebug(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void warn(String msg) {
        System.out.format("\33[33;4mWarn(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void error(String msg) {
        System.out.format("\33[31;4mError(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }
}

abstract class LinuxLogger implements LoggerAPI {
    protected final String os = "Linux";
    protected final ImplementationLanguage language;

    protected LinuxLogger(ImplementationLanguage language) {
        this.language = language;
    }

    @Override
    public void info(String msg) {
        System.out.format("\33[36;4mInfo(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void debug(String msg) {
        System.out.format("\33[34;4mDebug(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void warn(String msg) {
        System.out.format("\33[33;4mWarn(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void error(String msg) {
        System.out.format("\33[31;4mError(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }
}

abstract class MacOSLogger implements LoggerAPI {
    protected final String os = "MacOS";
    protected final ImplementationLanguage language;

    protected MacOSLogger(ImplementationLanguage language) {
        this.language = language;
    }

    @Override
    public void info(String msg) {
        System.out.format("\33[36;4mInfo(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void debug(String msg) {
        System.out.format("\33[34;4mDebug(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void warn(String msg) {
        System.out.format("\33[33;4mWarn(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }

    @Override
    public void error(String msg) {
        System.out.format("\33[31;4mError(OS=%s, APL=%s)=%s%n\n", os, language.toString(), msg);
    }
}

class WinCPPLogger extends WindowLogger {
    public WinCPPLogger() {
        super(ImplementationLanguage.CPP);
    }
}

class WinJavaLogger extends WindowLogger {
    public WinJavaLogger() {
        super(ImplementationLanguage.JAVA);
    }
}

class WinCSLogger extends WindowLogger {
    public WinCSLogger() {
        super(ImplementationLanguage.CS);
    }
}

class WinPythonLogger extends WindowLogger {
    public WinPythonLogger() {
        super(ImplementationLanguage.PYTHON);
    }
}

class LinuxCLogger extends LinuxLogger {
    public LinuxCLogger() {
        super(ImplementationLanguage.C);
    }
}

class LinuxCPPLogger extends LinuxLogger {

    public LinuxCPPLogger() {
        super(ImplementationLanguage.CPP);
    }
}

class LinuxShellLogger extends LinuxLogger {
    public LinuxShellLogger() {
        super(ImplementationLanguage.SHELL);
    }
}

class LinuxJavaLogger extends LinuxLogger {
    public LinuxJavaLogger() {
        super(ImplementationLanguage.JAVA);
    }
}

class MacCLogger extends MacOSLogger {
    public MacCLogger() {
        super(ImplementationLanguage.C);
    }
}

class MacOCLogger extends MacOSLogger {
    public MacOCLogger() {
        super(ImplementationLanguage.OC);
    }
}

class MacSwiftLogger extends MacOSLogger {
    public MacSwiftLogger() {
        super(ImplementationLanguage.SWIFT);
    }
}

class MacCppLogger extends MacOSLogger {
    public MacCppLogger() {
        super(ImplementationLanguage.CPP);
    }
}


abstract class Logger {
    protected LoggerAPI log;

    protected Logger(LoggerAPI log) {
        this.log = log;
    }

    public abstract void info(String msg);

    public abstract void debug(String msg);

    public abstract void warn(String msg);

    public abstract void error(String msg);
}

class LoggerImpl extends Logger {

    public LoggerImpl(String os, ImplementationLanguage il) {
        super(
                switch (os.toLowerCase()) {
                    case "win", "w", "window", "windows" -> switch (il) {
                        case CPP -> new WinCPPLogger();
                        case JAVA -> new WinJavaLogger();
                        case CS -> new WinCSLogger();
                        case PYTHON -> new WinPythonLogger();
                        default -> null;
                    };
                    case "mac", "macos", "m" -> switch (il) {
                        case C -> new MacCLogger();
                        case SWIFT -> new MacSwiftLogger();
                        case OC -> new MacOCLogger();
                        case CPP -> new MacCppLogger();
                        default -> null;
                    };
                    case "linux", "l" -> switch (il) {
                        case C -> new LinuxCLogger();
                        case CPP -> new LinuxCPPLogger();
                        case SHELL -> new LinuxShellLogger();
                        case JAVA -> new LinuxJavaLogger();
                        default -> null;
                    };
                    default -> null;
                }
        );
    }

    @Override
    public void info(String msg) {
        log.info(msg);
    }

    @Override
    public void debug(String msg) {
        log.debug(msg);
    }

    @Override
    public void warn(String msg) {
        log.warn(msg);
    }

    @Override
    public void error(String msg) {
        log.error(msg);
    }
}

public class Bridge implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 2: ");
        LoggerImpl wcpp = new LoggerImpl("w", ImplementationLanguage.CPP);
        LoggerImpl wcs = new LoggerImpl("w", ImplementationLanguage.CS);
        LoggerImpl wjava = new LoggerImpl("w", ImplementationLanguage.JAVA);
        LoggerImpl wpython = new LoggerImpl("w", ImplementationLanguage.PYTHON);
        LoggerImpl mc = new LoggerImpl("m", ImplementationLanguage.C);
        LoggerImpl mswift = new LoggerImpl("m", ImplementationLanguage.SWIFT);
        LoggerImpl moc = new LoggerImpl("m", ImplementationLanguage.OC);
        LoggerImpl mcpp = new LoggerImpl("m", ImplementationLanguage.CPP);
        LoggerImpl lc = new LoggerImpl("l", ImplementationLanguage.C);
        LoggerImpl lcpp = new LoggerImpl("l", ImplementationLanguage.CPP);
        LoggerImpl lshell = new LoggerImpl("l", ImplementationLanguage.SHELL);
        LoggerImpl ljava = new LoggerImpl("l", ImplementationLanguage.JAVA);

        String msg = "This is a message.";

        wcpp.info(msg);
        wcs.debug(msg);
        wjava.warn(msg);
        wpython.error(msg);

        mc.info(msg);
        mswift.debug(msg);
        moc.warn(msg);
        mcpp.error(msg);

        lc.info(msg);
        lcpp.debug(msg);
        lshell.warn(msg);
        ljava.error(msg);
    }
}
