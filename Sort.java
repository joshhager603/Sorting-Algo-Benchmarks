import java.util.Arrays;
import java.util.Random;

public class Sort {
    
    /**
     * Sorts an array using insertion sort.
     * LOGIC:
     * 1. Take first unsorted element in array, meaning the subarray to its left is sorted
     * 2. Compare to each element in the sorted subarray to its left
     * 3. If the selected element is < the sorted element, slide the sorted element one space to the right
     * 4. If the selected element is >= the sorted element, insert in front of the sorted element (found the right spot)
     * 5. Continue until the end of the array is reached, performing this operation on each unsorted element 
     * @param arr the array to be sorted
     */
    public void insertionSort(int[] arr){
        if(arr != null){

            /* Start at index 1, since the array from index 0 to index 0 contains 1 element and therefore is sorted.
             * Going into this loop, we assume each element to the left of arr[i] is sorted.  */
            for(int i = 1; i < arr.length; i++){

                /* if the element is out of place */
                if(arr[i] < arr[i - 1]){
                    int toInsert = arr[i];    // "selected element"
                    int j = i;
                    
                    /* slide each element in the sorted subarray to the right
                     * until we've reached the correct spot */
                    while(j > 0 && toInsert < arr[j - 1]){      // arr[j - 1] is the "sorted element"
                        arr[j] = arr[j - 1];
                        j--;
                    }
    
                    /* insert the element in the correct spot */
                    arr[j] = toInsert;
                }
    
            }
        }
    }

    /**
     * Sorts an array using quicksort.
     * LOGIC:
     * 1. Pick a pivot value
     * 2. Partition the array so that all elements in the left partition are
     *    less than the pivot and all elements in the right partition are
     *    greater than the pivot
     * 3. Repeat this process on both the left and right partitions until we 
     *    encounter an array of size 1; base case
     * @param arr the array to be sorted
     */
    public void quickSort(int[] arr){
        if(arr != null){
            qSort(arr, 0, arr.length - 1);
        }
    }

    /**
     * A helper method to recursively quicksort a subarray.
     * @param arr the array to sort
     * @param first the index to start the sort from (beginning of subarray)
     * @param last the index to end the sort at (end of subarray)
     */
    private void qSort(int[] arr, int first, int last){

        /* base case; subarray is either size 0 or size 1 */
        if(first >= last){
            return;
        }

        /* the index of the partition; assume each value to
         * the left of this index is less than the pivot used in the partition
         * method, and each value to the right of this index is greater than
         * the pivot used in the partition method */
        int split = partition(arr, first, last);

        /* sort the left and right subarrays as divided by the partition,
         * these methods will return once we have reached a subarray of size 1,
         * meaning we have completed the sort */
        qSort(arr, first, split);
        qSort(arr, split + 1, last);
    }

    /**
     * A helper method to partition a subarray. 
     * @param arr the array to sort
     * @param first the index to start the partition from (beginning of subarray)
     * @param last the index to end the partition from (end of subarray)
     * @return the index of the partition
     */
    private int partition(int arr[], int first, int last){
        int pivot = arr[(first + last) / 2];    // pivot value chosen as median
        int i = first - 1;                      // left to right index, start outside of subarray
        int j = last + 1;                       // right to left index, start outside of subarray

        /* repeat until we return, meaning the array is fully partitioned */
        while(true){

            /* increment i until we've found a value >= pivot */
            do{
                i++;
            } while(arr[i] < pivot);

            /* decrement j until we've found a value <= pivot */
            do{
                j--;
            } while(arr[j] > pivot);

            /* if i and j have not crossed yet, meaning the subarray is not fully partitioned,
             * swap the elements at i and j */
            if(i < j){
                swap(arr, i, j);
            }
            /* i and j have crossed; subarray is fully partitioned with j being the index of the partition */
            else{
                return j;
            } 
        }
    }

    /**
     * A helper method to swap two elements in an array.
     * @param arr the array containing the elements to swap
     * @param i the index of one of the elements to swap
     * @param j the index of the other element to swap
     */
    private void swap(int[] arr, int i, int j){
        int temp  = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Sorts an array using mergesort.
     * LOGIC:
     * 1. Divide the array in half.
     * 2. Mergesort each subarray until we have subarrays size 1, which are inherently sorted.
     * 3. Merge the subarrays, adding their elements in sorted order to a temp array and then copying back over.
     * @param arr the array to be sorted
     */
    public void mergeSort(int[] arr){
        if(arr != null){
            int[] temp = new int[arr.length];           // the temporary array
            mSort(arr, temp, 0, arr.length - 1);
        }
    }

    /**
     * A helper method to recursively mergesort a subarray.
     * @param arr the array to sort
     * @param temp the temporary array to sort the elements into
     * @param start the starting index of the sort (beginning of subarray)
     * @param end the end index of the sort (end of subarray)
     */
    private void mSort(int[] arr, int[] temp, int start, int end){

        /* base case; reached an array of size 0 or size 1 */
        if(start >= end){
            return;
        } 

        int middle = (start + end) / 2;         // the index to represent the halfway divide point

        mSort(arr, temp, start, middle);        // mergesort the left subarray
        mSort(arr, temp, middle + 1, end);      // mergesort the right subarray

        /* merge the sorted subarrays, assume that each subarray is sorted */
        merge(arr, temp, start, middle, middle + 1, end);
    }

    /**
     * A helper method to merge two sorted subarrays.
     * @param arr the array that is being sorted in mergesort
     * @param temp the temporary array to copy elements into
     * @param leftStart the starting index of the sorted subarray on the "left"
     * @param leftEnd the ending index of the sorted subarray on the "left"
     * @param rightStart the starting index of the sorted subarray on the "right"
     * @param rightEnd the ending index of the sorted subarray on the "right"
     */
    private void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd){
        int i = leftStart;                  // start one index at the beginning of the left subarray
        int j = rightStart;                 // start another index at the beginning of the right subarray
        int k = leftStart;                  // start an index in the location in the temporary array to start adding elements

        /* while there's still elements in both subarrays */
        while(i <= leftEnd && j <= rightEnd){

            /* add the smaller element to the temporary array, and increase the index of the subarray
             * that element was pulled from as well as the index for the temp array */
            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }

        /* if there's still elements in the left subarray, add them to the end of the temp subarray */
        while(i <= leftEnd){
            temp[k++] = arr[i++];
        }

        /* if there's still elements in the right subarray, add them to the end of the temp subarray */
        while(j <= rightEnd){
            temp[k++] = arr[j++];
        }

        /* copy the elements in the temp subarray back to their correct locations in the actual array being sorted */
        for(i = leftStart; i <= rightEnd; i++){
            arr[i] = temp[i];
        }
    }

    /**
     * Generates a random int array of length n with values between a and b, inclusive.
     * @param n the length of the random array
     * @param a the lower bound of the array's values, inclusive
     * @param b the upper bound of the array's values, inclusive
     * @return the random array
     */
    public int[] randomArray(int n, int a, int b){
        int[] randomArray = new int[n];

        if(a == b){
            for(int i = 0; i < randomArray.length; i++){
                randomArray[i] = a;
            }
            return randomArray;
        }
                                            
        Random rng = new Random();
        
        for(int i = 0; i < randomArray.length; i++){
            randomArray[i] = rng.nextInt( ((b - a) + 1)) + a;
        }

        return randomArray;
    }

    /**
     * Runs a demonstration of the Sort class.
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Sort sort = new Sort();
        int[] arr = new int[]{4, 2, 5, 1, 3};

        System.out.print("A demonstration of the Sort class.\n\n"+
                         ">Sort sort = new Sort()\n" +
                         ">int[] arr = new int[]{4, 2, 5, 1, 3};\n" +
                         ">sort.insertionSort(arr)\n");
        sort.insertionSort(arr);
        System.out.print(">Arrays.toString(arr)\n" +
                         ">" + Arrays.toString(arr) + "\n" +
                         "insertionSort(int[] arr) sorts an int[] using insertion sort.\n\n");
        arr = new int[]{4, 2, 5, 1, 3};
        System.out.print(">arr = new int[]{4, 2, 5, 1, 3};\n" +
                         ">sort.quickSort(arr)\n");
        sort.quickSort(arr);
        System.out.print(">Arrays.toString(arr)\n" +
                         ">" + Arrays.toString(arr) + "\n" +
                         "quickSort(int[] arr) sorts an int[] using quicksort.\n\n");
        arr = new int[]{4, 2, 5, 1, 3};
        System.out.print(">arr = new int[]{4, 2, 5, 1, 3};\n" +
                         ">sort.mergeSort(arr)\n");
        sort.mergeSort(arr);
        System.out.print(">Arrays.toString(arr)\n" +
                         ">" + Arrays.toString(arr) + "\n" +
                         "mergeSort(int[] arr) sorts an int[] using mergesort.\n\n");
        arr = sort.randomArray(5, 4, 10);
        System.out.print(">arr = sort.randomArray(5, 4, 10)\n" +
                         ">Arrays.toString(arr)\n" + 
                         ">" + Arrays.toString(arr) + "\n");
        arr = sort.randomArray(10, 12, 100);
        System.out.print(">arr = sort.randomArray(10, 12, 100)\n" +
                         ">Arrays.toString(arr)\n" +
                         ">" + Arrays.toString(arr) + "\n" +
                         "randomArray(int n, int a, int b) returns an int array of length n, containing random numbers within the interval [a,b].\n\n");
    }
}
