import java.util.*;

public class Main {
    public static void main(String[] args) {
        int loop = 2000;
        int size = 1000;
        Random rnd = new Random();

        for(int incrSize = 0; incrSize < 5; incrSize++){
            System.out.println("---------------");
            long maxTArray = 0;
            long averageTArr = 0;
            long maxTList = 0;
            long averageTList = 0;

            long t0;
            long time = 0;

            //Create an array of LinkedLists and Arrays, so that every sequance of the loop, 
            //the Array and LinkedList has the same random elements
            //(BAD, TAKES UP TO MUCH MEMORY)
            int[][] megaArray = new int[loop][size];
            Quicklist[] megaList = new Quicklist[loop];
            for(int j = 0; j < loop; j++){
                megaList[j] = new Quicklist();
                for(int i = 0; i < size; i++){
                    megaArray[j][i] = rnd.nextInt(size*10);
                    megaList[j].add(megaArray[j][i]);
                }
            }

            //Benchmark SortArray
            for(int j = 0; j < loop; j++){
                t0 = System.nanoTime();
                //SortArray
                Array.sort(megaArray[j], 0, size-1);
                    // System.out.println("----Final array----");
                    // SortArray.printArray(megaArray[j]);
                time = (System.nanoTime() - t0);

                //Minimum time
                if(time > maxTArray){
                    maxTArray = time;
                }
                //Average time
                averageTArr += (time);
            }
            long arrayTime = averageTArr/loop;
            
            //Benchmark SortList
            for(int j = 0; j < loop; j++){
                t0 = System.nanoTime();
                //SortList
                megaList[j].sort(megaList[j].first, megaList[j].last);
                    // System.out.println("----Final list----");
                    // megaList[j].printList();
                time = (System.nanoTime() - t0);

                //Maximum time
                if(time > maxTList){
                    maxTList = time;
                }
                //Average time
                averageTList += (time);
            }
            long listTime = averageTList/loop;


            System.out.println("Runtime for " + size + " element array: " + arrayTime + " ns. " + "(Max: " + maxTArray + " ns)");
            System.out.println("Runtime for " + size + " element list: " + listTime + " ns. " + "(Max: " + maxTList + " ns)");

            //Double the size
            System.out.println();
            size = size*2;
        } 
    }
}