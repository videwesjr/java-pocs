package com.java16.sealed;

import java.util.Arrays;

/**
 * JEP 397: Sealed Classes, 2nd preview (Java 16; standard in Java 17 via
 * JEP 409).
 *
 * The syntax of JEP 360 was kept; the second preview refined the rules:
 *  - sealed, non-sealed and permits became contextual keywords, so they are
 *    still usable as ordinary identifiers;
 *  - local classes may no longer be declared as subtypes of a sealed type,
 *    since they cannot be named in a permits clause;
 *  - anonymous classes may not implement a sealed interface either, for the
 *    same reason;
 *  - narrowed casts to a sealed type that no permitted subtype can satisfy
 *    are rejected at compile time.
 *
 * Paired with records, a sealed interface describes a closed set of shapes --
 * the Java form of an algebraic data type.
 */
public class SealedClassesSecondPreviewDemo {

    // The permits clause may be omitted when every permitted subtype is
    // declared in the same compilation unit: the compiler infers it from the
    // declarations below.
    sealed interface Expr {
    }

    record Literal(int value) implements Expr {
    }

    record Add(Expr left, Expr right) implements Expr {
    }

    record Multiply(Expr left, Expr right) implements Expr {
    }

    record Negate(Expr operand) implements Expr {
    }

    public static void main(String[] args) {
        Expr expression = new Add(
                new Literal(3),
                new Multiply(
                        new Negate(new Literal(2)),
                        new Literal(5)));

        System.out.println("expression: " + format(expression));
        System.out.println("value:      " + eval(expression));

        System.out.println();
        System.out.println("inferred permitted subtypes of Expr: "
                + Arrays.stream(Expr.class.getPermittedSubclasses())
                        .map(Class::getSimpleName)
                        .toList());

        System.out.println("contextual keywords as identifiers: " + contextualKeywords());
    }

    private static int eval(Expr expr) {
        if (expr instanceof Literal literal) {
            return literal.value();
        }
        if (expr instanceof Add add) {
            return eval(add.left()) + eval(add.right());
        }
        if (expr instanceof Multiply multiply) {
            return eval(multiply.left()) * eval(multiply.right());
        }
        if (expr instanceof Negate negate) {
            return -eval(negate.operand());
        }
        throw new IllegalStateException("unreachable: Expr permits nothing else");
    }

    private static String format(Expr expr) {
        if (expr instanceof Literal literal) {
            return String.valueOf(literal.value());
        }
        if (expr instanceof Add add) {
            return "(" + format(add.left()) + " + " + format(add.right()) + ")";
        }
        if (expr instanceof Multiply multiply) {
            return "(" + format(multiply.left()) + " * " + format(multiply.right()) + ")";
        }
        if (expr instanceof Negate negate) {
            return "-" + format(negate.operand());
        }
        throw new IllegalStateException("unreachable: Expr permits nothing else");
    }

    /**
     * sealed and permits are contextual keywords: they only mean something in
     * a type declaration, so existing code that uses them as names still
     * compiles. (non-sealed is hyphenated and so was never a valid
     * identifier.)
     */
    private static int contextualKeywords() {
        int sealed = 1;
        int permits = 2;
        return sealed + permits;
    }
}
