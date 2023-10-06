public class Tree {
    public static void main(String[] args) {
        int treeSize = 1000;
        BinaryTree bTree1 = new BinaryTree();
        BinaryTree bTree = new BinaryTree(treeSize);
        bTree1.add(12, 112);
        bTree1.add(6, 106);
        bTree1.add(14, 114);
        bTree1.add(8, 108);
        bTree1.add(2, 102);
        bTree1.print();
        System.out.println(bTree.lookup(100));
        System.out.println(bTree1.lookup(8));
        bTree.benchmarkLookup(1000000, 100);
        for (int size = 1_000; size <= 10_000; size += 1_000) {
            bTree.benchmarkLookup(size, 10000);
        }
        BinaryTree.benchmarkLookup(100, 100);
        BinaryTree.benchmarkLookup(1000, 100);
        BinaryTree.benchmarkLookup(2000, 100);
        BinaryTree.benchmarkLookup(3000, 100);
        BinaryTree.benchmarkLookup(4000, 100);
        BinaryTree.benchmarkLookup(5000, 100);
        BinaryTree.benchmarkLookup(6000, 100);
        BinaryTree.benchmarkLookup(7000, 100);
        BinaryTree.benchmarkLookup(8000, 100);
        BinaryTree.benchmarkLookup(9000, 100);
        BinaryTree.benchmarkLookup(10_000, 100);
        BinaryTree.benchmarkLookup(20_000, 100);
        BinaryTree.benchmarkLookup(30_000, 100);
        BinaryTree.benchmarkLookup(40_000, 100);
        BinaryTree.benchmarkLookup(50_000, 100);
        BinaryTree.benchmarkLookup(60_000, 100);
        BinaryTree.benchmarkLookup(70_000, 100);
        BinaryTree.benchmarkLookup(80_000, 100);
        BinaryTree.benchmarkLookup(90_000, 100);
        BinaryTree.benchmarkLookup(100_000, 100);
        BinaryTree.benchmarkLookup(200_000, 100);
        BinaryTree.benchmarkLookup(300_000, 100);
        BinaryTree.benchmarkLookup(400_000, 100);

    }
}