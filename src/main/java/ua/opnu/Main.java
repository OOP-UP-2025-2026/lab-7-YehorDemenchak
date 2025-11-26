package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static String stringify(int[] arr, Function<Integer, String> converter) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(converter.apply(num)).append(" ");
        }
        return sb.toString().trim();
    }

    public static <T> Predicate<T> andFilter(Predicate<T> p1, Predicate<T> p2) {
        return p1.and(p2);
    }

    public static <T> void applyIf(T value, Predicate<T> predicate, Consumer<T> consumer) {
        if (predicate.test(value)) {
            consumer.accept(value);
        }
    }

    static void main() {
        // Завдання 1
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };

        int[] n1 = {-34, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 238587};

        System.out.println("Завдання 1:");
        for (int num : n1) {
            System.out.println("Чи просте число " + num + ": " + isPrime.test(num));
        }

        //Завдання 2
        Student[] studentList = {
                new Student("Денис Семеняко", "A1", new int[]{90, 80, 75}),
                new Student("Максим Маєр", "A1", new int[]{40, 55, 20}),
                new Student("Уляна Дегтяренко", "B2", new int[]{60, 60, 60}),
                new Student("Артур Синявський", "B2", new int[]{40, 95, 88})
        };

        Predicate<Student> noDebts =
                s -> Arrays.stream(s.getMarks()).allMatch(m -> m >= 60);

        Student[] result = Student.filter(studentList, noDebts);

        System.out.println("\nЗавдання 2:");
        for (Student s : result) {
            System.out.println(s.getName());
        }

        //Завдання 3
        Predicate<Integer> divisibleBySeven = x -> (x % 7) == 0;
        Predicate<Integer> even = x -> x % 2 == 0;
        Predicate<Integer> divisibleBySevenAndEven = andFilter(divisibleBySeven, even);

        System.out.println("\nЗавдання 3:");
        System.out.println(divisibleBySevenAndEven.test(14));


        //Завдання 4
        Consumer<StudentFirstAndLastName> printFullName = s ->
                System.out.println(s.getLastName() + " " + s.getFirstName());

        StudentFirstAndLastName[] students = {
                new StudentFirstAndLastName("Семеняко", "Денис"),
                new StudentFirstAndLastName("Маєр", "Максим"),
                new StudentFirstAndLastName("Дегтяренко", "Уляна")
        };

        System.out.println("\nЗавдання 4:");
        Arrays.stream(students).forEach(printFullName);

        //Завдання 5
        int[] n2 = {1, 68, 2, 4, 6, 74, 7, 10, 13};

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<Integer> printEvenNumber = n -> System.out.println("Число парне: " + n);

        Predicate<Integer> greaterThanFive = n -> n > 5;
        Consumer<Integer> printGreaterThanFive = n -> System.out.println("Більше 5: " + n);

        System.out.println("\nЗавдання 5:");
        for (int num : n2) {
            applyIf(num, isEven, printEvenNumber);
            applyIf(num, greaterThanFive, printGreaterThanFive);
        }

        //Завдання 6
        Function<Integer, Integer> pow2 = n -> (int) Math.pow(2, n);

        int[] n3 = {23, 9, 14, 21, 12, 13, 6, 8, 15, 5};
        System.out.println("\nЗавдання 6:");
        for (int num : n3) {
            System.out.println("2^" + num + ": " + pow2.apply(num));
        }

        //Завдання 7
        Function<Integer, String> numToWord = n -> switch (n) {
            case 0 -> "нуль";
            case 1 -> "один";
            case 2 -> "два";
            case 3 -> "три";
            case 4 -> "чотири";
            case 5 -> "п’ять";
            case 6 -> "шість";
            case 7 -> "сім";
            case 8 -> "вісім";
            case 9 -> "дев’ять";
            default -> throw new IllegalArgumentException("Число має бути від 0 до 9");
        };

        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("\nЗавдання 7:");
        System.out.println(stringify(digits, numToWord));
    }
}
