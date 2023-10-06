import java.util.Random;

public class Array {

    public static int[] random_array(int size) {
        Random rnd = new Random();
        int[] tbr = new int[size];
        for (int i = 0; i < size - 1; i++) {
            tbr[i] = rnd.nextInt(100);
        }
        tbr[size - 1] = 101;
        return tbr;
    }

    public static void printout(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}