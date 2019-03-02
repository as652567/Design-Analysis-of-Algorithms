//----------------------------------------------------------------------
/*
   Name: Allison Smith
   Program: Sorts.java
*/
 
 import java.util.Scanner;
 import java.util.Random;
 import java.util.Arrays;
 import java.lang.management.ManagementFactory;
 import java.lang.management.ThreadMXBean;
 
 public class Sorts {
 
 
   public static void main ( String[] args ) {
   
    
      //compare times of sorted array
      //int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      
      //compare times of reverse sorted array 
      //int[] reverse_sorted = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
      
      //create array of length n
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Enter array size: ");
      int n = keyboard.nextInt();
      
      int[] array = new int[n];
      
      //generate random values for all index positions 0 through n-1
      Random r = new Random();
      for (int i = 0; i < n; i++){
         array[i] = r.nextInt();
      }
      
      long total_elapsedTime = 0, total_elapsedCPUTime = 0;

      for (int i = 0; i < 99; i++) {

      //calculate elapsed and cpu times 
      double sum = 0;
      long count; 
      ThreadMXBean tmb = ManagementFactory.getThreadMXBean();
      long startTime = System.nanoTime(); //get starting times
      long startCPUTime = tmb.getCurrentThreadCpuTime(); //also in nanoseconds
      
      //sort arrays of length n with randomly selected integers 
      //selectionSort(array, n);
      //insertionSort(array, n);
      mergeSort(array, n);
      
      //sort already sorted array of length 10 
      //selectionSort(sorted, 10);
      //insertionSort(sorted, 10);
      //mergeSort(sorted, 10);
      
      //sort reverse sorted array of length 10 
      //selectionSort(reverse_sorted, 10);
      //insertionSort(reverse_sorted, 10);
      //mergeSort(reverse_sorted, 10); 
    
      
      long endCPUTime = tmb.getCurrentThreadCpuTime(); //get ending times
      long endTime = System.nanoTime();
      long elapsedTime = endTime - startTime; //calculate delta times
      long elapsedCPUTime = endCPUTime - startCPUTime;
      total_elapsedTime += elapsedTime;
      total_elapsedCPUTime += elapsedCPUTime;
    
     }//end for loop 
     
     //report results for 100 test iterations
     System.out.println( "total elapsed time for 100 iterations = " + total_elapsedTime + " ns" ); 
     System.out.println( "total cpu time for 100 iterations = " + total_elapsedCPUTime + " ns" );
      
   }
 
 //----------------------------------------------------------------------
   public static void selectionSort ( int A[], int n ) {
   
      int index, smallest;
      
      for (int j = 0; j < n - 1; j++) {
            
            index = j;
            
            //compare A[j] with A[j+1] to A[n-1] to find next smallest number in array  
            for (int i = j + 1; i < n; i++) {
               if (A[i] < A[index])
                  index = i;
            }
            //if applicable swap A[j] with next smallest number in unsorted portion of array
            smallest = A[index];
            A[index] = A[j];
            A[j] = smallest; 
            
      }
                   
   }
 //----------------------------------------------------------------------
 
   public static void insertionSort ( int A[], int n ) {
   
      int key, i;
      
      for (int j = 1; j < n; j++) {
         key = A[j];
         i = j - 1;
         
         //compare key to all index positions from A[0] to A[key-1]
         while (i > -1 && A[i] > key){
            A[i+1] = A[i];
            i = i - 1;
         }
         
         A[i+1] = key; //reposition key after comparing with A[0] to A[key-1]
      }      
 
   }
 //-----------------------------------------------------------------------
   public static void mergeSort ( int A[], int n) {
         
         if (n <= 1)
            return;
         else {
            //divide array at midpoint and create two new subarrays for left and right halves
            int[] left = new int[n/2];
            int[] right = new int[n-n/2];
            
            //copy segments of A[] into new subarrays 
            System.arraycopy(A, 0, left, 0, n/2);
            System.arraycopy(A, n/2, right, 0, (n-n/2));
            
            //recursive method calls for subarrays 
            mergeSort(left, left.length);
            mergeSort(right, right.length);
            
            //merge subarrays into original array A[]  
          int key1 = 0, key2 = 0, A_index = 0;
            
          while (key1 < left.length && key2 < right.length) {
               
            if (left[key1] < right[key2]) 
               A[A_index++] = left[key1++];
            else 
               A[A_index++] = right[key2++];
          }
          
          while (key1 < left.length)
            A[A_index++] = left[key1++];
          
          while (key2 < right.length)
            A[A_index++] = right[key2++];
            
       } 

    }
 } 