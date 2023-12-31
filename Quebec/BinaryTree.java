import java.util.*;

public class BinaryTree implements Iterable<Integer> {
    Node root;

    public class Node {
        public Integer key;
        public Integer value;
        public Node left;
        public Node right;

        private Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }

        private void add(Integer k, Integer v) {
            switch (Integer.compare(this.key, k)) {
                case 0:
                    this.value = v;
                    break;
                case 1:
                    if (this.left == null) {
                        this.left = new Node(k, v);
                    } else {
                        this.left.add(k, v);
                    }
                    break;
                default:
                    if (this.right == null) {
                        this.right = new Node(k, v);
                    } else {
                        this.right.add(k, v);
                    }
            }
        }

        private Integer lookup(Integer k) {
            Node current = this;
            while (current != null) {
                switch (Integer.compare(current.key, k)) {
                    case 0:
                        return current.value;
                    case 1:
                        current = current.left;
                        break;
                    default:
                        current = current.right;
                }
            }
            return null;
        }
        

        public void print() {
            if (left != null)
                left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if (right != null)
                right.print();
        }
    }

    public BinaryTree() {
        root = null;
    }

    /*
     * Creates a binary tree of specified size and populates it with key values
     */
   public BinaryTree(int size) {
        Random rnd = new Random();
        // root = new Node((size * 2) + rnd.nextInt(size / 2) - rnd.nextInt(size /2),
        // rnd.nextInt(size * 10));
        // for (int i = 0; i < size; i++) {
        // add(rnd.nextInt(size * 4) + 1, rnd.nextInt(size * 10));
        // }
        root = new Node(size / 2, size / 2);
        for (int i = 0; i < size; i++) {
            add(rnd.nextInt(size), rnd.nextInt(size));
        }
    }

    public void add(Integer k, Integer v) {
        if (root == null) {
            root = new Node(k, v);
        } else {
            root.add(k, v);
        }
    }

    public Integer lookup(Integer key) {
        return root.lookup(key);
    }

    public void print() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        root.print();
    }

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.add(20, 20);
        tree.add(8, 8);
        tree.add(22, 22);
        tree.add(4, 4);
        tree.add(12, 12);
        tree.add(10, 10);
        tree.add(14, 14);

        // tree.print();

        for (int i : tree)
            System.out.println("Next value " + i);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new TreeIterator(root);
    }

    public class TreeIterator implements Iterator<Integer> {
        private Que<Node> queue;

        public TreeIterator(Node root) {
            queue = new Que<Node>();
            queue.enqueue(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();

        }

        @Override
        public Integer next() {
            if (!hasNext() || queue.isEmpty()) {
                throw new NoSuchElementException("No more elements in the binary tree.");
            }
        
            Node current = queue.dequeue();
        
            for (Node child : new Node[]{current.left, current.right}) {
                if (child != null) {
                    queue.enqueue(child);
                }
            }
        
            return current.value;
        }
        

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}