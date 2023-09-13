import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //benchmark of the duplicate search
        System.out.printf("%d\t %f%n", 100, benchmarkDuplicateSearch(100, 100));
        System.out.printf("%d\t %f%n", 200, benchmarkDuplicateSearch(200, 200));
        System.out.printf("%d\t %f%n", 400, benchmarkDuplicateSearch(400, 400));
        System.out.printf("%d\t %f%n", 800, benchmarkDuplicateSearch(800, 800));
        System.out.printf("%d\t %f%n", 1600, benchmarkDuplicateSearch(1600, 1600));
        System.out.printf("%d\t %f%n", 3200, benchmarkDuplicateSearch(3200, 3200));
        System.out.printf("%d\t %f%n", 6400, benchmarkDuplicateSearch(6400, 6400));
        System.out.printf("%d\t %f%n", 12800, benchmarkDuplicateSearch(12800, 12800));
        System.out.printf("%d\t %f%n", 25600, benchmarkDuplicateSearch(25600, 25600));

    }



    public static void duplicateSearch(int[] sortedArray1, int[] sortedArray2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < sortedArray1.length && index2 < sortedArray2.length) {
            int comparisonResult = Integer.compare(sortedArray2[index2], sortedArray1[index1]);
            switch (comparisonResult) {
                case -1:
                    index2++;
                    break;
                case 0:
                    index1++;
                    break;
                case 1:
                    index1++;
                    break;
                default:
                    // Handle unexpected case (optional)
                    break;
            }
        }
    }

    public static double benchmarkDuplicateSearch(int arraySize1, int arraySize2) {
        int[] sortedArray1 = SortedArray(arraySize1);
        int[] sortedArray2 = SortedArray(arraySize2);
        double sum = 0;
        for (int i = 0; i < 100_000; i++) {
//             betterDuplicateSearch(sortedArray1,sortedArray2);
            long timeStart = System.nanoTime();
            duplicateSearch(sortedArray1, sortedArray2);
            sum += (double) (System.nanoTime() - timeStart);
        }
        return sum / 100_000;
    }

    public static boolean binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = ((low + high) / 2);
            if (array[mid] == key) {
                return true;
            } 
            else if (key > array[mid]) { //key is on the right side
                low = mid + 1;
            } 
            else
                high = mid - 1; //key is on the left side.
        }
        return false;
    }

    public static double benchmarkBinarySearch(int maxArraySize) {
        Random rnd = new Random();
        int[] arrayToSearch = SortedArray(maxArraySize);
        double sum = 0;
    
        int[] randomKeys = new int[100_000];
        for (int i = 0; i < 100_000; i++) {
            randomKeys[i] = rnd.nextInt(arrayToSearch.length);
        }
    
        for (int key : randomKeys) {
            binarySearch(arrayToSearch, key);
            long timeStart = System.nanoTime();
            binarySearch(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart);
        }
        return sum / 100_000;
    }

    public static boolean unsortedSearch(int[] arrayToSearch, int key) {
        for (int element : arrayToSearch) {
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean sortedSearch(int[] arrayToSearch, int key) {
        for (int element : arrayToSearch) {
            if (element == key) {
                return true;
            }
            if (key < element) {
                return false;
            }
        }
        return false;
    }

    /**
     * creates an array and measure the average time it takes to find a random element in the array when doing an unsorted
     * search
     *
     * @param maxArraySize the arrays size.
     * @return the average time measured over 1,000,000 loops
     */
    public static double unsortedBenchmark(int maxArraySize) {
        Random rnd = new Random();
        int[] arrayToSearch = RandomArray(maxArraySize);
        double sum = 0;
    
        int[] randomKeys = new int[100_000];
        for (int i = 0; i < 100_000; i++) {
            randomKeys[i] = rnd.nextInt(arrayToSearch.length - 1);
        }
    
        for (int key : randomKeys) {
            long timeStart = System.nanoTime();
            unsortedSearch(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart);
        }
    
        return sum / 100_000;
    }

    public static double sortedBenchmark(int maxArraySize) {
    Random rnd = new Random();
    int[] arrayToSearch = SortedArray(maxArraySize);
    double sum = 0;

    int[] randomKeys = new int[100_000];
    for (int i = 0; i < 100_000; i++) {
        randomKeys[i] = rnd.nextInt(arrayToSearch.length);
    }

    try {
        // Create a FileWriter and BufferedWriter to write to a text file
        FileWriter fileWriter = new FileWriter("search_results.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int key : randomKeys) {
            long timeStart = System.nanoTime();
            boolean result = sortedSearch(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart);

            // Write the key and search time to the text file
            bufferedWriter.write("(" + key + ", " + (System.nanoTime() - timeStart) + ")");
            bufferedWriter.newLine();
        }

        // Close the BufferedWriter
        bufferedWriter.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

    return sum / 100_000;
}
    

    public static int[] SortedArray(int arraySize) {
        Random rnd = new Random();
        int[] array = new int[arraySize];
        int next = 0;
        for (int i = 0; i < array.length; i++) {
            next += (rnd.nextInt(10) + 1);
            array[i] = next;
        }
        return array;
    }

    public static int[] RandomArray(int maxArraySize) {
        Random rnd = new Random();
        int[] rndArray = new int[maxArraySize];
        for (int i = 0; i < rndArray.length; i++) {
            rndArray[i] = rnd.nextInt(100_000);
        }
        return rndArray;
    }


}
