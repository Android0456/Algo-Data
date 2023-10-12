public class Array {
    static void sort(int[] array, int low, int high){
        if (low < high){
            int pivot = partition(array, low, high);
            sort(array, low, pivot - 1);
            sort(array, pivot + 1, high);
        }
    }

    static int partition(int[] array, int low, int high)
    {
        int pivot = array[low];     //Pivot to move partition around
        int i = low;
        int j = high;

        while(i < j){
            while(array[i] <= pivot){
                i++;
                //Extra condition to prevent index out of bounds
                if(i == array.length || i > j){
                    i--;
                    break;
                }
            }

            while(array[j] > pivot) {
                j--;
            }

            //If i and j are stuck.
            //"<=" instad of "<". Incase if "i" got decremented since it was out of bounds and j = high
            if(i <= j){ 
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        //Place pivot at appropriate index and return it.
        array[low] = array[j];
        array[j] = pivot;
        return j;
    }

    static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println("Index " + i + ": " + arr[i]);
        }
        System.out.println();
    }
}