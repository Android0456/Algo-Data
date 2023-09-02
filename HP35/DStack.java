import java.util.EmptyStackException;

public class DStack {
    private int index;
    private int initsize;
    private int[] array;

    public DStack(int cap) {
        initsize = cap;
        array = new int[cap];
        index = -1;
    }
    //incr the capacity incase of full stack
    public void push(int element) {
        if (initsize == index + 1) {
            expandArray();
        }
        //push the data
        array[++index] = element;    
    }

    public int pop() {
        if (index == -1) {
            throw new EmptyStackException();
        } else {
            reduceSizeofArray();
            return array[index--];
        }
    }

    public void expandArray() {
        int currents = index +1;
        int[] new_array = new int[currents * 2];
        for (int i = 0; i < currents; i++) {
            new_array[i] = array[i];
        }
        array = new_array;              
        initsize = new_array.length;
    }

    public void reduceSizeofArray() {
        int currentLength = index + 1;
        
        if (currentLength < initsize / 2) {
            int[] newArray = new int[initsize / 2];
            System.arraycopy(array, 0, newArray, 0, newArray.length);
            array = newArray;
            initsize = newArray.length;
        }
    }
}