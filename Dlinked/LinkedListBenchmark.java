package Dlinked;

import java.util.Random;

import org.w3c.dom.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class LinkedListBenchmark {

    public static void main(String[] args) throws IOException {
        // Print with . instead of , for .dat file usage later
        Locale.setDefault(new Locale("en", "US"));

        int[] sizes = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144,
                524288, 1048576 };
        int k = 1000;
        Random random = new Random();

        BufferedWriter writer = new BufferedWriter(new FileWriter("benchmark.dat"));
        writer.write(String.format("%-8s \t\t %s \t\t %s\n", "Size", "DLL", "SLL"));
        System.out.printf("%-8s \t\t %s \t\t %s\n", "Size", "DLL", "SLL");
            
        for (int n : sizes) {
            // Initialize Doubly Linked List
            Dlink doublyList = new Dlink();
            Node[] doublyNodes = new Node[n];
            Node current = doublyList.head;
            for (int i = 0; i < n && current != null; i++) {
                doublyNodes[i] = current;
                current = current.next;
            }

            // Initialize Singly Linked List
            LinkedList singlyList = new SinglyLinkedList(n);
            Node[] singlyNodes = new Node[n];
            current = singlyList.head;
            for (int i = 0; i < n && current != null; i++) {
                singlyNodes[i] = current;
                current = current.next;
            }

            // Generate targets
            int[] indices = new int[k];
            for (int i = 0; i < k; i++) {
                indices[i] = random.nextInt(n);
            }
            // Benchmark for doubly-linked list
            double start = System.nanoTime();
            for (int index : indices) {
                Node target = doublyNodes[index];
                // Unlink
                doublyList.unlink(target);
                // Insert at the beginning
                doublyList.add(target.data);
            }
            double dllTime = System.nanoTime() - start;

            // Benchmark for singly-linked list
            start = System.nanoTime();
            for (int index : indices) {
                Node target = singlyNodes[index];
                // Unlink
                singlyList.remove(target.data);
                // Insert at the beginning
                singlyList.add(target.data);
            }
            double sllTime = System.nanoTime() - start;
            System.out.printf("%-8d \t\t %.2f \t\t %.2f\n", n, dllTime / 1000000, sllTime / 1000000);
            writer.write(String.format("%-8d \t\t %.2f \t\t %.2f\n", n, dllTime / 1000000, sllTime / 1000000));
        }
        writer.close();
    }
}