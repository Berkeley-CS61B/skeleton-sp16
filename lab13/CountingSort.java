/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 * 
 * @author 	Akhil Batra
 * @version	1.1 - April 16, 2016
 * 
**/
public class CountingSort
{
    /**
     * Array that will cause naiveCountingSort to fail
     **/
    public static int[] naiveArr = {};

    /**
     * Array that will cause naiveCountingSort to fail and betterCountingSort to work
     **/
    public static int[] betterArr = {};
    
    /**
     * Array that will cause naiveCountingSort and betterCountingSort to fail
     **/
    public static int[] naiveBetterArr = {};
    
    /**
     * Counting sort on the given int array and returns it
     *  does not touch original array (non-destructive method)
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
    **/
    public static int[] naiveCountingSort(int[] arr)
    {
        // find max
        int max = Integer.MIN_VALUE;
        for(int i : arr)
        {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for(int i : arr)
        {
            counts[i]++;
        }

        // put the value count times into a new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for(int i = 0; i < counts.length; i++)
        {
            for(int j = 0; j < counts[i]; j++, k++)
            {
                sorted[k] = i;
            }
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work where naiveCountingSort fails
     *  original array is changed to be sorted (destructive method)
     * Find a case where naiveCountingSort fails and this one succeeds,
     *  then find a case where this method will fail
     * 
     * @param arr int array that will be sorted
    **/
    public static void betterCountingSort(int[] toSort) {
        //TODO make it work with arrays that naiveCountingSort does not
    }
}
