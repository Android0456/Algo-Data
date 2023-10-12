public class Quicklist {
    
    public class Node {
        public int value;
        public Node next;

        public Node(int value, Node next){
            this.next = next;
            this.value = value;
        }
    }

    Node first;
    Node last;

    public Quicklist(){
        first = null;
        last = null;
    }

    public void add(Integer item) {
        if(first == null){
            first = new Node(item, null);
            last = first;
        }
        else{
            last.next = new Node(item, null);
            last = last.next;
        }
    }
    
    void sort(Node start, Node end){
        if (start == null || start == end)
        {
            return;
        } 
        Quicklist small = new Quicklist();
        Quicklist big = new Quicklist(); 

        //Splits list
        Node pivot = partitionFirst(start, end, small, big);

        //Partition the smaller list
        small.sort(small.first, small.last);
        
        //Partition the bigger list
        big.sort(big.first, big.last);

        //Resets the current list then appends 
        //the big list, the pivot and the small list
        this.first = null;
        this.last = null;
        this.append(big.first, big.last);
        this.append(pivot, pivot);
        this.append(small.first, small.last);
            // System.out.println("Final list");
            // printList();
    }       

    private Node partitionFirst(Node start, Node end, Quicklist small, Quicklist big){

        Node pivot = start;
        //Start from the node after the pivot node
        start = start.next;
        Node nxt = start;

        while(start != null){
            nxt = nxt.next;
            //If new small node, put it at the top of the small list
            if(start.value < pivot.value){
                start.next = small.first;
                small.first = start;

                //First small node is last node
                if(small.last == null){
                    small.last = start;
                }
            }
            //If new big node, put it at the top of the big list
            else{
                start.next = big.first;
                big.first = start;

                //First big node is last node
                if(big.last == null){
                    big.last = start;
                }
            }
            //Move start to the next node to reiterate
            start = nxt;
        }
        //Dereference and return pivot
        pivot.next = null;
        return pivot;
    }

    public void append(Node head, Node tail){
        //If there isn't anything to append
        if(head == null){
            return;
        }

        //If our list is empty, the new list will just be the appendé
        if(this.first == null){
            this.first = head;
        }
        else{
            this.last.next = head;
        }
        this.last = tail;  
    }

    public void printList(){
        if(first == null){
            System.out.println("empty list");
            return;  
        }
        Node p = first;
        int i = 0;

        System.out.println();
        while(p.next != null){
            i++;
            System.out.println("Node " + i + ": " + p.value);
            p = p.next;
        }
        i++;
        System.out.println("Node " + i + ": " + p.value);
    }
}