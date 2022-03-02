package guru.qa;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JUnitCore {

    public static void main(String[] args) throws Exception{

        Class clazz = SimpleTest.class;
        for (Method method : clazz.getDeclaredMethods()) {
            Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotation != null) {

                try {
                    method.invoke(clazz.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test failed " + method.getName());
                        continue;
                    } else {
                        System.out.println("Test broken " + method.getName());
                        continue;
                    }

                }
                System.out.println("Test passed " + method.getName());
            }
        }
    }
}
