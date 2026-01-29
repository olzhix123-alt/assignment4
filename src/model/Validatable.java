package model;

public interface Validatable<T> {
    boolean validate(T entity);


    default void printStatus(String name, boolean isValid) {
        System.out.println("Нәтиже: " + name + (isValid ? " талапқа сай." : " қате толтырылған!"));
    }


    static boolean isPositive(double value) {
        return value > 0;
    }
}
