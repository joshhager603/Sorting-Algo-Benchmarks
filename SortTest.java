import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * A class to test Sort. Conditions for each test can be found on the right.
 * @author Josh Hager
 */
public class SortTest { 
    
    /**
     * A helper method to ensure that each element in an int array falls within [a,b].
     * @param arr the array to check
     * @param a the lower bound of the range to compare to, inclusive
     * @param b the upper bound of the range to compare to, inclusive
     * @return true if each element in arr falls within [a,b], false if not
     */
    public static boolean testArrBounds(int[] arr, int a, int b){

        if(arr.length == 0){
            return false;
        }

        for(int element : arr){
            if(!(element >= a && element <= b)){
                return false;
            }
        }

        return true;
    }
                                                                                      //CONDITION:
    @Test
    public void testTestArrBounds(){
        int[] arr = new int[0];

        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size 0

        arr = new int[]{3};
        assertEquals(true, SortTest.testArrBounds(arr, 1, 5));                  //size 1, value falls within bounds
        assertEquals(false, SortTest.testArrBounds(arr, 4, 9));                 //size 1, value falls below lower bound
        assertEquals(false, SortTest.testArrBounds(arr, 0, 2));                 //size 1, value fall above upper bound

        arr = new int[]{5, 1, 3};
        assertEquals(true, SortTest.testArrBounds(arr, 1, 5));                  //size >1, all values fall within bounds

        arr = new int[]{6, 5, 1, 3};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, first value falls above upper bound

        arr = new int[]{5, 1, 6, 3};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, middle value falls above upper bound

        arr = new int[]{5, 1, 3, 6};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, last value falls above upper bound

        arr = new int[]{6, 5, 6, 1, 3, 6};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, >1 value fall above upper bound

        arr = new int[]{0, 5, 1, 3};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, first value falls below lower bound

        arr = new int[]{5, 0, 1, 3};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, middle value falls below lower bound

        arr = new int[]{5, 1, 3, 0};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, last value falls below lower bound

        arr = new int[]{0, 5, 1, 0, 3, 0};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, >1 value fall below lower bound

        arr = new int[]{5, 6, 1, 0, 3};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, 1 value falls above upper bound, 1 value falls below lower bound

        arr = new int[]{6, 5, 1, 6, 0, 3, 0};
        assertEquals(false, SortTest.testArrBounds(arr, 1, 5));                 //size >1, >1 value falls above upper bound, >1 value falls below lower bound
    }

    @Test
    public void testInsertionSort(){
        Sort test = new Sort();
        int[] arr = new int[0];

        test.insertionSort(arr);
        assertEquals("[]", Arrays.toString(arr));                               //size 0

        arr = new int[]{5};
        test.insertionSort(arr);
        assertEquals("[5]", Arrays.toString(arr));                              //size 1
        
        arr = new int[]{4, 1, 5, 2, 3};
        test.insertionSort(arr);
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(arr));                  //size >1

        arr = new int[]{4, 4, 1, 5, 2, 3};
        test.insertionSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in first possible position (index 1)

        arr = new int[]{4, 1, 5, 4, 2, 3};
        test.insertionSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in a middle position

        arr = new int[]{4, 1, 5, 2, 3, 4};
        test.insertionSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in last position

        arr = new int[]{4, 4, 1, 5, 2, 4, 3, 4};
        test.insertionSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 4, 4, 5]", Arrays.toString(arr));         //>1 duplicates of one number

        arr = new int[]{4, 4, 1, 5, 1, 2, 3};
        test.insertionSort(arr);
        assertEquals("[1, 1, 2, 3, 4, 4, 5]", Arrays.toString(arr));            //>1 number has a duplicate

        arr = new int[]{4, 4, 1, 5, 4, 1, 1, 2, 3};
        test.insertionSort(arr);
        assertEquals("[1, 1, 1, 2, 3, 4, 4, 4, 5]", Arrays.toString(arr));      //>1 number has >1 duplicates

        for(int i = 0; i < 100; i++){                                           //test on 100 random arrays between size 0 and 100 

            int size = (int)Math.floor(Math.random() * 100);
            arr = test.randomArray(size, 0, 99);        
            int[] copyArr = Arrays.copyOf(arr, size);

            test.insertionSort(arr);
            Arrays.sort(copyArr);

            assertEquals(true, Arrays.equals(arr, copyArr)); 

            if(!Arrays.equals(arr, copyArr)){
                System.out.println(Arrays.toString(arr));                       //print the two arrays if not equal
                System.out.println(Arrays.toString(copyArr));  
                break;                 
            }
        }
        
        arr = null;
        test.insertionSort(arr);                                                //null input, just make sure null input doesn't cause a crash
    }

    @Test
    public void testQuickSort(){
        Sort test = new Sort();
        int[] arr = new int[0];

        test.quickSort(arr);
        assertEquals("[]", Arrays.toString(arr));                               //size 0

        arr = new int[]{5};
        test.quickSort(arr);
        assertEquals("[5]", Arrays.toString(arr));                              //size 1
        
        arr = new int[]{4, 1, 5, 2, 3};
        test.quickSort(arr);
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(arr));                  //size >1

        arr = new int[]{4, 4, 1, 5, 2, 3};
        test.quickSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in first possible position (index 1)

        arr = new int[]{4, 1, 5, 4, 2, 3};
        test.quickSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in a middle position

        arr = new int[]{4, 1, 5, 2, 3, 4};
        test.quickSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in last position

        arr = new int[]{4, 4, 1, 5, 2, 4, 3, 4};
        test.quickSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 4, 4, 5]", Arrays.toString(arr));         //>1 duplicates of one number

        arr = new int[]{4, 4, 1, 5, 1, 2, 3};
        test.quickSort(arr);
        assertEquals("[1, 1, 2, 3, 4, 4, 5]", Arrays.toString(arr));            //>1 number has a duplicate

        arr = new int[]{4, 4, 1, 5, 4, 1, 1, 2, 3};
        test.quickSort(arr);
        assertEquals("[1, 1, 1, 2, 3, 4, 4, 4, 5]", Arrays.toString(arr));      //>1 number has >1 duplicates

        for(int i = 0; i < 100; i++){                                           //test on 100 random arrays between size 0 and 100 

            int size = (int)Math.floor(Math.random() * 100);
            arr = test.randomArray(size, 0, 99);        
            int[] copyArr = Arrays.copyOf(arr, size);

            test.quickSort(arr);
            Arrays.sort(copyArr);

            assertEquals(true, Arrays.equals(arr, copyArr)); 

            if(!Arrays.equals(arr, copyArr)){
                System.out.println(Arrays.toString(arr));                       //print the two arrays if not equal
                System.out.println(Arrays.toString(copyArr));  
                break;                 
            }
        }                        

        arr = null;
        test.quickSort(arr);                                                    //null input, just make sure null input doesn't cause a crash
    }

    @Test
    public void testMergeSort(){
        Sort test = new Sort();
        int[] arr = new int[0];

        test.mergeSort(arr);
        assertEquals("[]", Arrays.toString(arr));                               //size 0

        arr = new int[]{5};
        test.mergeSort(arr);
        assertEquals("[5]", Arrays.toString(arr));                              //size 1
        
        arr = new int[]{4, 1, 5, 2, 3};
        test.mergeSort(arr);
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(arr));                  //size >1

        arr = new int[]{4, 4, 1, 5, 2, 3};
        test.mergeSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in first possible position (index 1)

        arr = new int[]{4, 1, 5, 4, 2, 3};
        test.mergeSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in a middle position

        arr = new int[]{4, 1, 5, 2, 3, 4};
        test.mergeSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 5]", Arrays.toString(arr));               //1 duplicate in last position

        arr = new int[]{4, 4, 1, 5, 2, 4, 3, 4};
        test.mergeSort(arr);
        assertEquals("[1, 2, 3, 4, 4, 4, 4, 5]", Arrays.toString(arr));         //>1 duplicates of one number

        arr = new int[]{4, 4, 1, 5, 1, 2, 3};
        test.mergeSort(arr);
        assertEquals("[1, 1, 2, 3, 4, 4, 5]", Arrays.toString(arr));            //>1 number has a duplicate

        arr = new int[]{4, 4, 1, 5, 4, 1, 1, 2, 3};
        test.mergeSort(arr);
        assertEquals("[1, 1, 1, 2, 3, 4, 4, 4, 5]", Arrays.toString(arr));      //>1 number has >1 duplicates

        for(int i = 0; i < 100; i++){                                           //test on 100 random arrays between size 0 and 100 

            int size = (int)Math.floor(Math.random() * 100);
            arr = test.randomArray(size, 0, 99);        
            int[] copyArr = Arrays.copyOf(arr, size);

            test.mergeSort(arr);
            Arrays.sort(copyArr);

            assertEquals(true, Arrays.equals(arr, copyArr)); 

            if(!Arrays.equals(arr, copyArr)){
                System.out.println(Arrays.toString(arr));                       //print the two arrays if not equal
                System.out.println(Arrays.toString(copyArr));  
                break;                 
            }
        }

        arr = null;
        test.mergeSort(arr);                                                    //null input, just make sure null input doesn't cause a crash
    }

    @Test
    public void testRandomArray(){
        Sort test = new Sort();
        int[] arr;

        arr = test.randomArray(0, 0, 9);
        assertEquals(0, arr.length);
        assertEquals("[]", Arrays.toString(arr));                               //n = 0

        arr = test.randomArray(1, 3, 3);
        assertEquals(1, arr.length);
        assertEquals(true, SortTest.testArrBounds(arr, 3, 3));                  //n = 1, range = 1

        arr = test.randomArray(1, 1, 5);
        assertEquals(1, arr.length);
        assertEquals(true, SortTest.testArrBounds(arr, 1, 5));                  //n = 1, range >1

        arr = test.randomArray(10, 0, 0);                                       
        assertEquals(10, arr.length);
        assertEquals(true, SortTest.testArrBounds(arr, 0, 0));                  //n >1, range = 1

        arr = test.randomArray(10, 0, 99);
        assertEquals(10, arr.length);
        assertEquals(true, SortTest.testArrBounds(arr, 0, 99));                 //n >1, range >1
    }
}
