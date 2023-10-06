public class Quicklist {
    Node head;
    Node rear;

    public class Node {
        int value;
        Node next;

        public Node(int input_value, Node list) {
            value = input_value;
            next = list;
        }

        public void printout() {
            Node current = this;
            System.out.print(" " + current.value + " ");
            if (current.next != null) {
                current = current.next;
                current.printout();
            }
        }
    }

    public Quicklist(int fih) {
        head = new Node(fih, null);
    }

    public void add(int item) {
        if (head.next == null) {
            rear = new Node(item, head);
            head.next = rear;
        } else {
            Node temp = rear;
            rear = new Node(item, null);
            temp.next = rear;
        }
    }

    public Node partition(Node first, Node last) {
        Node current = first, low = null, high = null, temp;
        Integer pivot = first.value;
        while (current != last.next && current != null) {
            temp = current;
            current = current.next;
            temp.next = null;
            // System.out.println("This is temp V");
            // temp.printout();
            // System.out.println();

            if (low == null && pivot > temp.value) {
                low = temp;
                first = low;
            } else if (high == null && pivot == temp.value) {
                high = temp;
                last = high;
            } else if (pivot > temp.value) {
                low.next = temp;
                low = low.next;
            } else if (pivot < temp.value) {
                high.next = temp;
                high = high.next;
            }
        }
        high = last;
        low.next = last;
        low = first;

        System.out.println("uwu_wos_dis");
        low.printout();
        System.out.println();
        high.printout();
        return high;
    }

    public void sort(Node first, Node last) {
        if (first == last || first == null || last == null)
            return;
        Node pivot = partition(first, last);
        sort(first, pivot);
        sort(pivot.next, last);
    }

}