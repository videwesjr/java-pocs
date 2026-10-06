package com.java13.switchyield;

/**
 * JEP 354: Switch Expressions, second preview (Java 13).
 *
 * Java 13 replaced the "break value" form from the first preview with the
 * yield statement, used to produce a value from a block inside a switch
 * expression. yield works with both arrow cases and old-style colon cases.
 */
public class SwitchYieldDemo {

    public static void main(String[] args) {
        for (String command : new String[]{"start", "stop", "restart", "status"}) {
            System.out.println(command + " -> exit code " + exitCode(command) + ", " + describe(command));
        }
    }

    private static int exitCode(String command) {
        return switch (command) {
            case "start", "stop" -> 0;
            case "restart" -> {
                System.out.println("  (restart = stop + start)");
                yield exitCode("stop") + exitCode("start");
            }
            default -> {
                System.out.println("  (unknown command: " + command + ")");
                yield 1;
            }
        };
    }

    private static String describe(String command) {
        return switch (command) {
            case "start":
            case "restart":
                yield "brings the service up";
            case "stop":
                yield "shuts the service down";
            default:
                yield "does nothing";
        };
    }
}
