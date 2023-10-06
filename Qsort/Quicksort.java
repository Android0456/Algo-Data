public class Quicksort {
    public static void sort(int[] array, int i, int j) {
        if (i < j) {
            int index = partition(array, i, j);
            // Array.printout(array);
            sort(array, i, index - 1);
            sort(array, index + 1, j);
        }
    }

    public static int partition(int[] array, int i, int j) {
        int low = i, high = j, holder;
        while (low < high) {
            while (array[low] <= array[i])
                low++;
            while (array[high] > array[i])
                high--;
            if (low < high) {
                holder = array[low];
                array[low] = array[high];
                array[high] = holder;
            }
        }
        holder = array[high];
        array[high] = array[i];
        array[i] = holder;

        return high;
    }
}