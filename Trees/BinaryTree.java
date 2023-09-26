package Trees;

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
        // root = new Node((size * 2) + rnd.nextInt(size / 2) - rnd.nextInt(size / 2),
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

    public static void benchmarkLookup(int treeSize, int runs) {
        Random rnd = new Random();
        long tMin = Long.MAX_VALUE;
        long time = 0;
        BinaryTree bTree = new BinaryTree(treeSize);

        int tempKey = rnd.nextInt(treeSize);
        bTree.lookup(tempKey);

        for (int i = 0; i < runs; i++) {
            int key = rnd.nextInt(treeSize);
            // System.out.println("key " + key);
            long timeStart = System.nanoTime();
            bTree.lookup(key);
            long timeStop = System.nanoTime();
            time += timeStop - timeStart;
            if ((timeStop - timeStart) < tMin) {
                tMin = timeStop - timeStart;
            }
        }
        System.out.printf("%d\t %d\t %d%n", treeSize, tMin, time / runs);
        // System.out.println("Min: " + tMin);
        // System.out.println("Average time: " + time / runs);

    }

    public static void main(String[] args) {
        // for (int size = 1_000_000; size <= 100_000_000; size = 2 * size) {

        // benchmarkLookup(size, 100);
        // }
        BinaryTree tree = new BinaryTree();
        tree.add(5, 105);
        tree.add(2, 102);
        tree.add(7, 107);
        tree.add(1, 101);
        tree.add(8, 108);
        tree.add(6, 106);
        tree.add(3, 103);

        Iterator<Integer> itr = tree.iterator();
        // tree.print();

        // for (int i : tree)
        // System.out.println("Next value " + i);
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println("add key 4 to tree. shouldn't show up before the for each loop");
        tree.add(4, 104);
        System.out.println("add key 9 to tree. might show up before the for each loop");
        tree.add(9, 109);
        System.out.println(itr.next());
        System.out.println(itr.next());
        for (int i : tree)
            System.out.println("Next value " + i);
        System.out.println(itr.next());
        System.out.println(itr.next());
            
    }

    @Override
    public Iterator<Integer> iterator() {
        return new TreeIterator(root);
    }

    public class TreeIterator implements Iterator<Integer> {
        private Node next;
        private Stack<Node> stack;

        public TreeIterator(Node current) {
            stack = new Stack<Node>();
            moveLeft(current);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();

        }
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node curr = stack.pop();
            if (curr.right != null) {
                moveLeft(curr.right);
            }
            next = curr;
            return curr.value;
        }
        private void moveLeft(Node current) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
