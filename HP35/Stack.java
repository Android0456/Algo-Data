import java.util.EmptyStackException;

public class Stack {
    private int[] arr;
    private int index;

    public Stack(int size) {
        arr = new int[size];
        index = -1;
    }

    public void push(int element) {
        if (index + 1 == arr.length) {
            throw new StackOverflowError("Stack is full.");
        }
        else{
        arr[++index] = element;
        }
    }
    public int pop() {

        if (index == -1) {
            throw new EmptyStackException();
        }
        else{
        return arr[index--];
        }
    }
}
