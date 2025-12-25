import java.util.*;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {

        /* =====================================================
           ANONYMOUS CLASS
           -----------------------------------------------------
           - A class without a name.
           - Used to give implementation of an interface
             directly inside the method.
           - Old style before Java 8 Lambda.
        ===================================================== */
        Walkable anonymousWalker = new Walkable() {
            @Override
            public int walk(int steps, boolean enabled) {
                return enabled ? steps * 2 : 0;
            }
        };

        System.out.println("Anonymous class result: " +
                anonymousWalker.walk(5, true));


        /* =====================================================
           LAMBDA EXPRESSION
           -----------------------------------------------------
           - Short form of anonymous class.
           - Works only with Functional Interfaces.
        ===================================================== */
        Walkable lambdaWalker = (steps, enabled) ->
                enabled ? steps * 2 : 0;

        System.out.println("Lambda result: " +
                lambdaWalker.walk(10, true));


        /* ===================================
           STREAM API EXAMPLES
           =================================== */
        List<String> fruits = List.of(
                "Apple", "Banana", "Kiwi", "Mango",
                "Orange", "Papaya", "Kiwi", "Apple"
        );

        // Filter + Sorted
        System.out.println("\nFruits with length > 5 (Sorted):");
        fruits.stream()
                .filter(f -> f.length() > 5)
                .sorted()
                .forEach(System.out::println);

        // Distinct
        System.out.println("\nUnique Fruits:");
        fruits.stream()
                .distinct()
                .forEach(System.out::println);

        // Map + Collect to List
        List<Integer> fruitLengths = fruits.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("\nFruit Lengths: " + fruitLengths);

        // Collect to Map
        Map<String, Integer> fruitLengthMap =
                fruits.stream().distinct()
                        .collect(Collectors.toMap(f -> f, String::length));
        System.out.println("\nFruit Length Map: " + fruitLengthMap);

        // Reduce
        int totalLength = fruits.stream()
                .map(String::length)
                .reduce(0, Integer::sum);
        System.out.println("\nTotal Length of All Fruits: " + totalLength);

        // Limit & Skip
        System.out.println("\nFirst 3 Fruits:");
        fruits.stream().limit(3).forEach(System.out::println);

        System.out.println("\nSkip First 2 Fruits:");
        fruits.stream().skip(2).forEach(System.out::println);

        // Grouping By
        Map<Integer, List<String>> groupedByLength =
                fruits.stream().distinct()
                        .collect(Collectors.groupingBy(String::length));
        System.out.println("\nGrouped By Length:");
        System.out.println(groupedByLength);

        // Partitioning By
        Map<Boolean, List<String>> partitioned =
                fruits.stream().distinct()
                        .collect(Collectors.partitioningBy(f -> f.length() > 5));
        System.out.println("\nPartitioned By Length > 5:");
        System.out.println(partitioned);

        // flatMap
        List<List<String>> basket = List.of(
                List.of("Apple", "Banana"),
                List.of("Kiwi", "Mango")
        );

        List<String> flatList =
                basket.stream()
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
        System.out.println("\nFlattened Fruit Basket:");
        System.out.println(flatList);

        // Optional
        Optional<String> longestFruit =
                fruits.stream()
                        .max(Comparator.comparingInt(String::length));
        longestFruit.ifPresent(f -> System.out.println("\nLongest Fruit: " + f));

        // Sorting using Comparator
        System.out.println("\nSorted By Length Descending:");
        fruits.stream()
                .distinct()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .forEach(System.out::println);

        // Parallel Stream
        System.out.println("\nParallel Stream Example:");
        fruits.parallelStream().forEach(System.out::println);
    }
}


/* =====================================================
   FUNCTIONAL INTERFACE
   -----------------------------------------------------
   - Interface with only ONE abstract method.
   - Required for Lambda expressions.
===================================================== */
@FunctionalInterface
interface Walkable {
    int walk(int steps, boolean enabled);
}
