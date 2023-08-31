import java.util.EmptyStackException;

public class DStack {
    private int index;
    private int init_size;
    private int[] array;
    private static final int new_size = 4;

    public DStack(int cap) {
        init_size = cap;
        array = new int[new_size];
        index = -1;
    }
    //incr the capacity incase of full stack
    public void push(int element) {
        if (init_size == index + 1) {
            expandArray();
        }
        //push the data
        array[++index] = element;    
    }

    public int pop() {
        if (index == -1) {
            throw new EmptyStackException();
        } else {
            reduceSize();                 //function to check if size can be reduced
            return array[index--];
        }
    }

    public void expandArray() {
        int curr_size = index + 1;
        int[] new_array = new int[curr_size * 2];
        for (int i = 0; i < curr_size; i++) {
            new_array[i] = array[i];
        }
        array = new_array;              //refer to the new array
        init_size = new_array.length;
    }

    public void reduceSize() {
        int curr_length = index + 1;
        if (curr_length < init_size / 2) {
            int[] new_array = new int[init_size / 2];
            System.arraycopy(array, 0, new_array, 0, new_array.length);
            array = new_array;
            init_size = new_array.length;
        }
    }
}