import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Reflector {

    private final Class<?> clazz;

    public Reflector(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getField(Object instance, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }

    public void inspect() {
        System.out.println("Class: " + clazz.getName());
        System.out.println("Superclass: " + clazz.getSuperclass().getSimpleName());

        System.out.println("Fields:");
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println("  [" + Modifier.toString(f.getModifiers()) + "] " + f.getType().getSimpleName() + " " + f.getName());
        }

        System.out.println("Methods:");
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println("  [" + Modifier.toString(m.getModifiers()) + "] " + m.getReturnType().getSimpleName() + " " + m.getName() + "()");
        }

        System.out.println("Constructors:");
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            System.out.println("  " + c.getName() + "(" + Arrays.toString(c.getParameterTypes()) + ")");
        }
    }

    public Object invokeMethod(Object instance, String methodName, Class<?>[] paramTypes, Object... args) throws Exception {
        Method method = clazz.getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    public Object invokeMethod(Object instance, String methodName) throws Exception {
        return invokeMethod(instance, methodName, new Class<?>[]{});
    }

    public Object newInstance(Class<?>[] paramTypes, Object... args) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor(paramTypes);
        constructor.setAccessible(true);
        return constructor.newInstance(args);
    }

    public void setField(Object instance, String fieldName, Object value) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }
}