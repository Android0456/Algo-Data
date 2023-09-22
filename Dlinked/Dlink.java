package Dlinked;

import java.util.LinkedList;

public class Dlink {
    private Node first;
    private Node[] holder = new Node[25600];
    private int size = 0;

    private class Node {
        int head;
        Node next;
        Node prev;

        Node(int val, Node tl, Node prevNode) {
            head = val;
            next = tl;
            prev = prevNode;
        }
    }

    public int getSize() {
        return size;
    }

    // Add item as the first cell in the sequence
    public void add(int item) {
        Node newNode = new Node(item, first, null);
        holder[size] = newNode;
        size++;

        if (first != null) {
            first.prev = newNode;
        }

        first = newNode;
    }

    // Return the length of the sequence
    public int length() {
        int count = 0;
        Node current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Return true or false depending on if the item can be found in the sequence
    public boolean find(int item) {
        Node current = first;
        while (current != null) {
            if (current.head == item) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Remove the item if it exists in the sequence
    public void remove(int item) {
        Node current = first;

        while (current != null && current.head != item) {
            current = current.next;
        }

        if (current != null) {
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                first = current.next;
            }

            if (current.next != null) {
                current.next.prev = current.prev;
            }
        }
    }

    // Unlink a specific cell from the doubly linked list
    public void unlink(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }

    // Insert a cell as the first cell in the list
    public void insertFirst(Node node) {
        node.next = first;
        node.prev = null;

        if (first != null) {
            first.prev = node;
        }

        first = node;
    }

    public void append(Dlink b) {
        if (this.first == null) {
            this.first = b.first;
            b.first = null;
            return;
        }

        Node current = this.first;
        while (current.next != null) {
            current = current.next;
        }

        current.next = b.first;
        b.first = null;
    }
}
