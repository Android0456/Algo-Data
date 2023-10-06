public class LinkedList {
     Node first;

    public static class Node {
        int head;
        Node tail;

        Node(int val, Node tl) {
            head = val;
            tail = tl;
        }
    }

    public void add(int item) {
        Node newNode = new Node(item, first);
        first = newNode;
    }

    public int length() {
        int count = 0;
        Node current = first;
        while (current != null) {
            count++;
            current = current.tail;
        }
        return count;
    }

    public boolean find(int item) {
        Node current = first;
        while (current != null) {
            if (current.head == item) {
                return true;
            }
            current = current.tail;
        }
        return false;
    }

    public void remove(int item) {
        if (first != null && first.head == item) {
            first = first.tail;
            return;
        }

        Node current = first;
        Node previous = null;

        for (; current != null && current.head != item; previous = current, current = current.tail);

        if (current != null) {
            previous.tail = current.tail;
        }
    }

    public void append(LinkedList b) {
        if (this.first == null) {
            this.first = b.first;
            b.first = null;
            return;
        }

        Node current = this.first;
        while (current.tail != null) {
            current = current.tail;
        }
        

        current.tail = b.first;
        b.first = null;
    }

    class DynamicStack {
        private LinkedList list;
    
        public DynamicStack() {
            list = new LinkedList();
        }
    
        public void push(int item) {
            list.add(item);
        }
    
        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
    
            int top = list.first.head;
            list.first = list.first.tail;
            return top;
        }
    
        public boolean isEmpty() {
            return list.first == null;
        }
    }

    void unlink(Node cut){ 
        if (this.first == cut) { 
            this.first = cut.tail;
            return;
        }
        Node temp = cut;
        Node next = this.first;
        Node prev = null;     
        for (temp = this.first; temp != null; temp = temp.tail) {
            prev = next;
            next = next.tail;
            if (temp.tail == null) {
                break;
            }
            if (next == temp) {
                prev.tail = next.tail;
                return;
            }
        }
    }

    public void insert(Node insert) {
        insert.tail = this.first;
        this.first = insert;
    }

    Node getCell(int position){
        Node inline = this.first;
        int counter = position;
        if (length() < position || position < 0) {
            return null;
        }
        for (int i = 0; i < counter; i++) {
            inline = inline.tail;
        }
        return inline;
    }
    
}
