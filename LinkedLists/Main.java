

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        benchmarkAppendVaryingASize();
        benchmarkAppendVaryingBSize();
        benchmarkAppendLinkedList();
        benchmarkAppendArray();
        benchmarkBuildingList();

        
    }
    public static void benchmarkAppendVaryingASize() {
        int bSize = 1000; // Size of the fixed-size list b
        int[] aSizes = { 100, 750, 1500, 2250, 3000, 4000, 5000 }; // Varying sizes of list a

        for (int aSize : aSizes) {
            LinkedList a = createLinkedList(aSize);
            LinkedList b = createLinkedList(bSize);

            long startTime = System.nanoTime();
            b.append(a);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            System.out.println("Size of a: " + aSize + ", Time (ns): " + elapsedTime);
        }
    }
    
    private static Dlink genDoubleList(int n) {
		Dlink list = new Dlink();
		int[] randomValues = new int[n];
        for (int i = 0; i < n; i++) {
        	randomValues[i] = (randomValue(13, false));
			list.add(randomValues[i]);
        }
		return list;
	}

    public static void benchmarkAppendVaryingBSize() {
        int aSize = 1000; // Size of list a
        int[] bSizes = { 100, 750, 1500, 2250, 3000, 4000, 5000 }; // Varying sizes of list b

        LinkedList a = createLinkedList(aSize);

        for (int bSize : bSizes) {
            LinkedList b = createLinkedList(bSize);

            long startTime = System.nanoTime();
            b.append(a);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            System.out.println("Size of b: " + bSize + ", Time (ns): " + elapsedTime);
        }
    }

    public static void benchmarkAppendLinkedList() {
        int bSize = 1000; // Size of the fixed-size list b
        int[] aSizes = { 100, 750, 1500, 2250, 3000, 4000, 5000 }; // Varying sizes of list a

        for (int aSize : aSizes) {
            LinkedList a = createLinkedList(aSize);
            LinkedList b = createLinkedList(bSize);

            long startTime = System.nanoTime();
            b.append(a);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            System.out.println("LinkedList: Size of a: " + aSize + ", Time (ns): " + elapsedTime);
        }
    }

    public static void benchmarkAppendArray() {
        int bSize = 1000; // Size of the fixed-size array b
        int[] aSizes = { 100, 750, 1500, 2250, 3000, 4000, 5000 }; // Varying sizes of array a

        for (int aSize : aSizes) {
            int[] a = new int[aSize];
            int[] b = new int[bSize];

            long startTime = System.nanoTime();
            int[] result = appendArrays(a, b);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            System.out.println("Array: Size of a: " + aSize + ", Time (ns): " + elapsedTime);
        }
    }

    public static int[] appendArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static void benchmarkBuildingList() {
        int[] listSizes = { 100, 750, 1500, 2250, 3000, 4000, 5000 }; // Varying sizes of the list

        for (int size : listSizes) {
            long startTime = System.nanoTime();
            LinkedList list = createLinkedList(size);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            System.out.println("LinkedList Build: Size: " + size + ", Time (ns): " + elapsedTime);
        }
    }

    public static LinkedList createLinkedList(int size) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }
    private static int randomValue(int limit, boolean includeZero){

        Random rnd = new Random();
        if (includeZero) {
            return rnd.nextInt(limit);
        } else {
            return (rnd.nextInt(limit - 1) + 1);
        }
    }
}