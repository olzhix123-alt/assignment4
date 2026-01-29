package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static void inspectEntity(Object entity) {
        Class<?> clazz = entity.getClass();
        System.out.println("--- Reflection Inspection ---");
        System.out.println("Class Name: " + clazz.getSimpleName());

        System.out.println("Fields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("- " + field.getName() + " (Type: " + field.getType().getSimpleName() + ")");
        }

        System.out.println("Methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("- " + method.getName());
        }
        System.out.println("----------------------------");
    }
}
