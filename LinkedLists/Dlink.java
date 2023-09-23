public class Dlink {
    Node first;
    Node last;

    public static class Node {
        int value;
        Node next;
        Node prev;

        Node(int val, Node nxt) {
            value = val;
            next = nxt;
            if (next != null) {
                next.prev = this;    
            }
        }
    }

    public void add(int item) {
        this.first = (this.first == null) ? 
        new Node(item, this.first) : new Node(item, this.first);
    }

    public int length() {
        int count = 0;
        Node current = this.first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean find(int item) {
        Node current = this.first;
        while (current != null) {
            if (current.value == item) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void append(Dlink b){
        Node tail = this.first;
        while (tail.next != null) {
            tail = tail.next;
        }
        b.first.prev = tail;
        tail.next = b.first;
        b.first = null; 
    }

    public void remove(int item) {
        Node current = this.first;
        while (current != null && current.value != item) {
            current = current.next;
        }
        if (current == null) {
            return;
        }
        switch (current.prev != null ? 1 : 2) {
            case 1:
                current.prev.next = current.next;
                break;
            case 2:
                first = current.next;
                break;
        }
        
        switch (current.next != null ? 1 : 2) {
            case 1:
                current.next.prev = current.prev;
                break;
            case 2:
                last = current.prev;
                break;
        }        
    }

    //Return Node at specified position
    public Node getCell(int position){
        Node inline = this.first;
        int counter = position;
        if (length() < position || position < 0) {
            return null;
        }
        for (int i = 0; i < counter; i++) {
            inline = inline.next;
        }
        return inline;
    }

    public void unlink(Node remove){
        remove.prev = (remove.prev == null) ? remove.next.prev : remove.prev;
        remove.next = (remove.next == null) ? remove.prev.next : remove.next;
    }

    public void insert(Node insert) {
        this.first.prev = insert;
        insert.next = this.first;
        this.first = insert;
    }
}
