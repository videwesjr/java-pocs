package com.behavioral.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public final class InterpreterDemo {

    private InterpreterDemo() {
    }

    public static void run() {
        System.out.println("=== Interpreter ===");

        Context context = new Context();
        context.assign("x", 10);
        context.assign("y", 5);

        String sentence = "x y + 2 -";
        Expression expression = parse(sentence);

        System.out.printf("%s => %d%n", sentence, expression.interpret(context));
    }

    private static Expression parse(String postfix) {
        Deque<Expression> stack = new ArrayDeque<>();

        for (String token : postfix.split(" ")) {
            switch (token) {
                case "+" -> {
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new AddExpression(left, right));
                }
                case "-" -> {
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new SubtractExpression(left, right));
                }
                default -> {
                    if (Character.isDigit(token.charAt(0))) {
                        stack.push(new NumberExpression(Integer.parseInt(token)));
                    } else {
                        stack.push(new VariableExpression(token));
                    }
                }
            }
        }

        return stack.pop();
    }
}
