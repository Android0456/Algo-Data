package Dlinked;
import java.util.Random;

import org.w3c.dom.Node;

public class LinkedListBenchmark {
    public static void main(String[] args) {
        int n = 10000; // Adjust the size of the list
        int k = 1000; // Number of cells to randomly select

        // Create doubly linked list and initialize cell array
        Dlink doublyList = new Dlink();
        Node[] cellArray = new Node[n];
        for (int i = 0; i < n; i++) {
            doublyList.add(i); // Adding cells to the list
            cellArray[i] = doublyList.getFirst(); // Maintaining references
        }

        // Generate array of k random indices
        Random rand = new Random();
        int[] randomIndices = new int[k];
        for (int i = 0; i < k; i++) {
            randomIndices[i] = rand.nextInt(n);
        }

        // Benchmark for doubly linked list
        long startTime = System.nanoTime();
        for (int index : randomIndices) {
            Node cell = cellArray[index];
            doublyList.unlink(cell);
            doublyList.insertFirst(cell);
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken for Doubly Linked List: " + elapsedTime + " nanoseconds");

        // Reset list for Single Linked List benchmark
        doublyList = new DoublyLinkedList();
        cellArray = new Node[n];
        for (int i = 0; i < n; i++) {
            doublyList.add(i);
            cellArray[i] = doublyList.getFirst();
        }

        // Benchmark for single linked list
        startTime = System.nanoTime();
        for (int index : randomIndices) {
            Node cell = cellArray[index];
            doublyList.unlink(cell);
            doublyList.insertFirst(cell);
        }
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;

        System.out.println("Time taken for Single Linked List: " + elapsedTime + " nanoseconds");
    }
}


