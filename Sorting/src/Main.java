import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.printf("%s\t %6s\t %14s\t %12s\t %6s\t %12s\t %n", "n", "select", "insert", "ratio", "merge", "ratio");

        for (int i = 100; i <= 25600; i *= 2) {
            int[] array = createRandomArray(i);
            double selectionTime = benchmarkSelection(array, 10, 1000);
            double insertionTime = benchmarkInsertion(array, 10, 1000);
            double mergeTime = benchmarkMerge(array, 10, 1000);
            double ratioSI = selectionTime / insertionTime;
            double ratioIM = insertionTime / mergeTime;

            System.out.printf("%d\t %.2f\t %.2f\t %.2f\t %.2f\t %.2f\t %n", i, selectionTime, insertionTime, ratioSI, mergeTime, ratioIM);
        }
    }

    public static double benchmarkSelection(int[] array, int tries, int loop) {
        double minSelectionTime = Double.POSITIVE_INFINITY;

        for (int k = 0; k < tries; k++) {
            double startTime = System.nanoTime();

            for (int j = 0; j < loop; j++) {
                int[] copy = array.clone();
                Sort.selection(copy);
            }

            double totalTime = System.nanoTime() - startTime;

            if (totalTime < minSelectionTime) {
                minSelectionTime = totalTime;
            }
        }

        return minSelectionTime / loop;
    }

    public static double benchmarkInsertion(int[] array, int tries, int loop) {
        double minInsertionTime = Double.POSITIVE_INFINITY;

        for (int k = 0; k < tries; k++) {
            double startTime = System.nanoTime();

            for (int j = 0; j < loop; j++) {
                int[] copy = array.clone();
                Sort.insertion(copy);
            }

            double totalTime = System.nanoTime() - startTime;

            if (totalTime < minInsertionTime) {
                minInsertionTime = totalTime;
            }
        }

        return minInsertionTime / loop;
    }

    public static double benchmarkMerge(int[] array, int tries, int loop) {
        double minMergeTime = Double.POSITIVE_INFINITY;

        for (int k = 0; k < tries; k++) {
            double startTime = System.nanoTime();

            for (int j = 0; j < loop; j++) {
                int[] copy = array.clone();
                Sort.sort(copy);
            }

            double totalTime = System.nanoTime() - startTime;

            if (totalTime < minMergeTime) {
                minMergeTime = totalTime;
            }
        }

        return minMergeTime / loop;
    }

    private static int[] createRandomArray(int arraySize) {
        Random rnd = new Random();
        int[] rndArray = new int[arraySize];
        
        for (int i = 0; i < rndArray.length; i++) {
            rndArray[i] = rnd.nextInt(arraySize);
        }

        return rndArray;
    }
}
