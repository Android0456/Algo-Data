import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        benchmarkRegularS(1000);
        benchmarkDS(1000);


            DCalculator calculator = new DCalculator(new Item[]{
                new Item(1),
                new Item(2),
                new Item(3),
                new Item(4),
                new Item(5),
                new Item(6),
                new Item(7),
                new Item(8),
                new Item(9),
                new Item(10),
                new Item(11),
                new Item(12),
                new Item(13),
                new Item(14),
                new Item(15),
                new Item(16),
                new Item(ItemType.ADD),
                new Item(ItemType.MUL),
                new Item(ItemType.ADD),
                new Item(ItemType.MUL),
                new Item(ItemType.ADD),
                new Item(ItemType.MUL),
                new Item(ItemType.ADD),
                new Item(ItemType.MUL),
                new Item(ItemType.ADD),
                new Item(ItemType.MUL),
                new Item(ItemType.ADD),
                new Item(ItemType.MUL),
                new Item(ItemType.ADD),
                new Item(ItemType.MUL),
                new Item(ItemType.ADD)
            });
            int res = calculator.run();
            System.out.println("Result: " + res);
            
    }
    //Random Number Generator, the jesus part is just because I'm experiencing a crisis of faith (dont laugh >:C)
    public static Item[] RNGesus() {
        Random rnd = new Random();
        Item[] expression = new Item[1000];

        // Generate random numbers and populate the first 501 elements
        IntStream.range(0, 501)
        .forEach(i -> expression[i] = new Item(rnd.nextInt(-10000, 10000)));
        // Fill the remaining elements with ADD
        Arrays.fill(expression, 501, 1000, new Item(ItemType.ADD));
        return expression;
    }

    public static void benchmarkRegularS(int loops) {
        double min1 = IntStream.range(0, loops)
            .mapToObj(i -> {
                Calculator cal = new Calculator(RNGesus());
                long startTime = System.nanoTime();
                cal.runStatic();
                long endTime = System.nanoTime();
                return (double) (endTime - startTime);
            })
            .min(Double::compare)
            .orElse(Double.MAX_VALUE);
        System.out.println("Static stack: " + min1 + " nanoseconds.");
    }

    public static void benchmarkDS(int loops) {
        double min2 = IntStream.range(0, loops)
            .mapToObj(i -> {
                DCalculator cal = new DCalculator(RNGesus());
                long startT = System.nanoTime();
                cal.run();
                long endT = System.nanoTime();
                return (double) (endT - startT);
            })
            .min(Double::compare)
            .orElse(Double.MAX_VALUE);

        System.out.println("Dyn Stack: " + min2 + " nanoseconds.");
    }

}
