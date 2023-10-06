public class ArrayQ<Item> {
    private Item[] queue;
    private int head = 0;
    private int tail = 0;
    private int size;
    private int itemsInQueue = 0;

    public ArrayQ(int size) {
        this.size = size;
        this.queue = (Item[]) new Object[this.size];
    }

    public boolean isEmpty() {
        return this.itemsInQueue == 0;
    }

    private boolean isFull() {
        return this.itemsInQueue == this.size;
    }

    public void enqueue(Item item) {
        if (!isEmpty() && isFull()) {
            resize();
        }
    
        if (isEmpty()) {
            this.queue[this.head] = item;
            this.tail++;
        } else {
            this.queue[this.tail] = item;
            this.tail = (this.tail + 1) % this.size;
        }
    
        this.itemsInQueue++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            System.out.println("dequeue(): queue is empty");
            return null;
        }
        Item returnedItem = this.queue[this.head];
        this.queue[this.head] = null;
        this.head++;
        this.itemsInQueue--;
        if (this.itemsInQueue <= (this.size / 4)) {
            resize();
        }
        return returnedItem;
    }

    public void print() {
        System.out.println("Start of array");
        for (int i = 0; i < this.size; i++) {
            System.out.println(queue[i]);
        }
    }

    public void resize() {
        int newSize = isFull() ? this.size * 2 : this.size / 2;
        Item[] newQueue = (Item[]) new Object[newSize];
        int j = 0;
    
        if (this.head < this.tail) {
            System.arraycopy(this.queue, this.head, newQueue, 0, this.tail - this.head);
        } else {
            System.arraycopy(this.queue, this.head, newQueue, 0, this.size - this.head);
            System.arraycopy(this.queue, 0, newQueue, this.size - this.head, this.tail);
        }
    
        this.queue = newQueue;
        this.size = newSize;
        this.head = 0;
        this.tail = this.itemsInQueue;
    }
}
