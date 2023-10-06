public class Main {
    public static void main(String[] args) {
        // int[] test = Array.random_array(10);
        // int[] rtest = {5,2,1,6,8,3,9,7,4};
        // Array.printout(test);
        // System.out.println();
        // Quicksort.sort(test,0,9);
        // System.out.println();
        // Array.printout(test);

        Quicklist test = new Quicklist(4);
        test.add(2);
        test.add(5);
        test.add(1);
        test.add(3);
        test.add(7);
        test.add(6);
        test.head.printout();
        System.out.println();
        test.partition(test.head, test.rear);

        System.out.println("\n in main");
        test.head.printout();

        // System.out.println("Before Sorting");
        // test.head.printout();
        // System.out.println("After Sorting");
        // test.sort(test.head ,test.rear);
        // test.head.printout();

    }
}