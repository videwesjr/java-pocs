package com.java15.hiddenclasses;

import java.lang.classfile.ClassFile;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup.ClassOption;

import static java.lang.constant.ConstantDescs.CD_Object;
import static java.lang.constant.ConstantDescs.CD_String;
import static java.lang.constant.ConstantDescs.INIT_NAME;
import static java.lang.constant.ConstantDescs.MTD_void;

/**
 * JEP 371: Hidden Classes (standard in Java 15).
 *
 * A hidden class is defined at runtime from a byte array and is deliberately
 * not discoverable: it has no entry in any class loader's table, so
 * Class.forName and reflection over the enclosing package cannot reach it.
 * By default it is also weakly referenced by its defining loader, so it can
 * be unloaded once nothing holds it.
 *
 * This is the mechanism behind machinery that fabricates one throwaway class
 * per call site -- lambdas, method handle implementations, proxies -- which
 * previously abused anonymous classes via the unsupported
 * sun.misc.Unsafe::defineAnonymousClass (removed in Java 17).
 *
 * The bytes below are assembled with the Class-File API (java.lang.classfile,
 * standard since Java 24) purely to keep the example self-contained; any
 * source of valid class bytes works.
 */
public class HiddenClassesDemo {

    public interface Greeter {
        String greet(String name);
    }

    public static void main(String[] args) throws Throwable {
        byte[] bytes = greeterBytes();

        Class<?> hidden = MethodHandles.lookup()
                .defineHiddenClass(bytes, true)
                .lookupClass();

        System.out.println("isHidden:     " + hidden.isHidden());
        System.out.println("getName:      " + hidden.getName());
        System.out.println("package:      " + hidden.getPackageName());
        System.out.println("interfaces:   " + hidden.getInterfaces()[0].getSimpleName());

        Greeter greeter = (Greeter) hidden.getDeclaredConstructor().newInstance();
        System.out.println("invocation:   " + greeter.greet("Duke"));

        // The name is not a binary name -- it carries a '/' -- so it is not
        // something a class loader could ever be asked to resolve.
        try {
            Class.forName(hidden.getName());
            System.out.println("lookup:       found (unexpected)");
        } catch (ClassNotFoundException e) {
            System.out.println("Class.forName: " + e.getClass().getSimpleName() + " (as expected)");
        }

        // Each definition yields a distinct class, even from identical bytes.
        Class<?> again = MethodHandles.lookup()
                .defineHiddenClass(bytes, true, ClassOption.NESTMATE)
                .lookupClass();
        System.out.println();
        System.out.println("second definition is a different class: " + (hidden != again));
        System.out.println("  first:  " + hidden.getName());
        System.out.println("  second: " + again.getName());

        // ClassOption.STRONG ties the class to its defining loader's lifetime;
        // without it the class is weakly reachable and may be unloaded.
        Class<?> strong = MethodHandles.lookup()
                .defineHiddenClass(bytes, true, ClassOption.STRONG)
                .lookupClass();
        System.out.println("strongly referenced definition: " + strong.getName());
    }

    /**
     * The equivalent of:
     *
     * {@snippet :
     * public final class HiddenGreeter implements Greeter {
     *     public String greet(String name) {
     *         return "Hello from a hidden class, ".concat(name);
     *     }
     * }
     * }
     */
    private static byte[] greeterBytes() {
        ClassDesc greeter = ClassDesc.of(Greeter.class.getName());
        MethodTypeDesc greetType = MethodTypeDesc.of(CD_String, CD_String);

        return ClassFile.of().build(ClassDesc.of("com.java15.hiddenclasses.HiddenGreeter"), clb -> clb
                .withFlags(ClassFile.ACC_PUBLIC | ClassFile.ACC_FINAL | ClassFile.ACC_SUPER)
                .withInterfaceSymbols(greeter)
                .withMethodBody(INIT_NAME, MTD_void, ClassFile.ACC_PUBLIC, cob -> cob
                        .aload(0)
                        .invokespecial(CD_Object, INIT_NAME, MTD_void)
                        .return_())
                .withMethodBody("greet", greetType, ClassFile.ACC_PUBLIC, cob -> cob
                        .ldc("Hello from a hidden class, ")
                        .aload(1)
                        .invokevirtual(CD_String, "concat", greetType)
                        .areturn()));
    }
}
