public class Sort {

    public Sort() {
    }

    /**
     * selection sort algorithm. Has time complexity O(n^2)
     * 
     * @param array the array to sort
     * @return the same array but sorted
     */
    public static void selection(int[] array) {
        int Index;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            Index = i;
            // find minimum element in an unsorted array
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[Index]) {
                    Index = j;
                }
            }
            // swap the smallest found element with the element we started at
            temp = array[Index];
            array[Index] = array[i];
            array[i] = temp;
        }
    }

    /**
     * Insertion sort algorithm. Average and worst case time complexity of O(n^2).
     * Best case of O(n).
     * This algorithm is good if the array is already partly sorted.
     * 
     * @param array
     */
    
     public static void insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Merge sort algorithm using recursive callback. Time complexity of O(n*log(n))
     * 
     * @param original the array we want sorted
     */
    public static void sort(int[] original) {
        if (original.length == 0) {
            return;
        }
        int[] auxiliaryArray = new int[original.length];
        sort(original, auxiliaryArray, 0, original.length - 1);
    }
    
    private static void sort(int[] original, int[] auxiliaryArray, int low, int high) {
        if (low != high) {
            int middle = low + (high - low) / 2;
            sort(original, auxiliaryArray, low, middle);
            sort(original, auxiliaryArray, middle + 1, high);
            merge(original, auxiliaryArray, low, middle, high);
        }
    }

    private static void merge(int[] original, int[] auxiliaryArray, int low, int middle, int high) {
        // copy all items from lo to hi from org to aux
        for (int i = low; i <= high; i++) {
            auxiliaryArray[i] = original[i];
        }
        // let's do the merging
        int i = low; // the index in the first part
        int j = middle + 1; // the index in the second part
        // for all indices from low to high
        for (int k = low; k <= high; k++) {
            // corner case
            // if i (low) is greater than mid, move the j item to the org array, update j
            if (i > middle) {
                original[k] = auxiliaryArray[j++];

                // corner case
                // else if j is greater than hi, move the i item to the org array, update i
            } else if (j > high) {
                original[k] = auxiliaryArray[i++];

                // else if the i item is smaller than the j item,
                // move it to the org array, update i
            } else if (auxiliaryArray[i] < auxiliaryArray[j]) {
                original[k] = auxiliaryArray[i++];

                // else you can move the j item to the org array, update j
            } else {
                original[k] = auxiliaryArray[j++];
            }
        }
    }

    public static int[] copyArray(int[] array) {
        int[] tempArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        return tempArray;
    }
    

    public static void sortBetter(int[] original) {
        if (original.length == 0) {
            return;
        }
        int[] auxiliaryArray = copyArray(original);
        sort2Better(original, auxiliaryArray, 0, original.length - 1);
    }
    
    private static void sort2Better(int[] original, int[] auxiliaryArray, int low, int high) {
        if (low != high) {
            int middle = low + (high - low) / 2;
            sort2Better(auxiliaryArray, original, low, middle);
            sort2Better(auxiliaryArray, original, middle + 1, high);
            mergeBetter(original, auxiliaryArray, low, middle, high);
        }
    }

    private static void mergeBetter(int[] original, int[] auxiliaryArray, int low, int middle, int high) {
        // let's do the merging
        int i = low; // the index in the first part
        int j = middle + 1; // the index in the second part
        // for all indices from low to high
        for (int k = low; k <= high; k++) {
            // corner case
            // if i (low) is greater than mid, move the j item to the org array, update j
            if (i > middle) {
                original[k] = auxiliaryArray[j++];

                // corner case
                // else if j is greater than hi, move the i item to the org array, update i
            } else if (j > high) {
                original[k] = auxiliaryArray[i++];

                // else if the i item is smaller than the j item,
                // move it to the org array, update i
            } else if (auxiliaryArray[i] < auxiliaryArray[j]) {
                original[k] = auxiliaryArray[i++];

                // else you can move the j item to the org array, update j
            } else {
                original[k] = auxiliaryArray[j++];
            }
        }
    }
    


    /**
     * Quick sort algorithm. Time complexity of O(n*log(n)) 
     * in the average and best case and O(n^2) in worst case
     * 
     * @param original the array we want sorted
     */
    public static void quick(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
    
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
    
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
    
        return i + 1;
    }
}
