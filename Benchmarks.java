import java.util.Arrays;

/**
 * A class to run benchmark test on the various sorting methods in the Sort class.
 * @author Josh Hager
 */
public class Benchmarks {                           
    
    /* stores the various array sizes to test each sort method with */
    public static int[] arraySizes = new int[]{10, 20, 50, 100, 200, 500, 1000, 2000, 5000};

    /* the instance of the Sort class to use in each benchmark method */
    public static Sort sort = new Sort();

    /**
     * Measures the time it takes the insertionSort method from the Sort class to sort
     * a random array of a given size.
     * @param arraySize the desired size 
     * @return the time it took for insertionSort to sort a random array of arraySize, in ns
     */
    public static long benchmarkInsertionSort(int arraySize){
        int[] arr = sort.randomArray(arraySize, 1, arraySize);

        long startTime = System.nanoTime();

        sort.insertionSort(arr);

        return System.nanoTime() - startTime;
    }

    /**
     * Measures the time it takes the quickSort method from the Sort class to sort
     * a random array of a given size.
     * @param arraySize the desired size 
     * @return the time it took for quickSort to sort a random array of arraySize, in ns
     */
    public static long benchmarkQuickSort(int arraySize){

        int[] arr = sort.randomArray(arraySize, 1, arraySize);

        long startTime = System.nanoTime();

        sort.quickSort(arr);

        return System.nanoTime() - startTime;
    }

    /**
     * Measures the time it takes the mergeSort method from the Sort class to sort
     * a random array of a given size.
     * @param arraySize the desired size 
     * @return the time it took for mergeSort to sort a random array of arraySize, in ns
     */
    public static long benchmarkMergeSort(int arraySize){

        int[] arr = sort.randomArray(arraySize, 1, arraySize);

        long startTime = System.nanoTime();

        sort.mergeSort(arr);

        return System.nanoTime() - startTime;
    }

    /**
     * Measures the time it takes the sort method in Java's Arrays class to sort
     * a random array of a given size.
     * @param arraySize the desired size
     * @return the time it took for Arrays.sort to sort a random array of arraySize, in ns
     */
    public static long benchmarkArraysSort(int arraySize){

        int[] arr = sort.randomArray(arraySize, 1, arraySize);

        long startTime = System.nanoTime();

        Arrays.sort(arr);

        return System.nanoTime() - startTime;
    }

    /**
     * Prints the results of each benchmark method for the array sizes specified in arraySizes.
     * @param args the command line arguments
     */
    public static void main(String[] args){
        System.out.print("A demonstration of the Benchmarks class.\n\n" + 
                         "Benchmarks measures the time, in ns, that each sorting method in the Sort class takes to sort a random int array of size 10, 20, 50,\n" +
                         "100, 200, 500, 1000, 2000, and 5000, as seen below.\n\n");

        System.out.println("insertionSort:");
        for(int arraySize : arraySizes){
            System.out.println("    For size " + arraySize + ": " + Benchmarks.benchmarkInsertionSort(arraySize) + " ns");
        }

        System.out.println("\nquickSort:");
        for(int arraySize : arraySizes){
            System.out.println("    For size " + arraySize + ": " + Benchmarks.benchmarkQuickSort(arraySize) + " ns");
        }

        System.out.println("\nmergeSort:");
        for(int arraySize : arraySizes){
            System.out.println("    For size " + arraySize + ": " + Benchmarks.benchmarkMergeSort(arraySize) + " ns");
        }

        System.out.println("\nArrays.sort:");
        for(int arraySize : arraySizes){
            System.out.println("    For size " + arraySize + ": " + Benchmarks.benchmarkArraysSort(arraySize) + " ns");
        }

    }
}
