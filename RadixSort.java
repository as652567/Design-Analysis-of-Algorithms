/*
 * Name: Allison Smith 
 * Homework4 
 * Project: BucketSort.java 
 */
 
import java.util.Random; 
import java.util.Arrays; 

public class RadixSort {

   public static void main(String[] args) {
      
      //test on input size n = 20
      int n = 20;
      int range = n * n; 
      int[] A = new int[n];
      
      //generate random values for all index positions 0 through n-1
      Random r = new Random();
      for (int i = 0; i < n; i++){
         A[i] = r.nextInt(range) + 1;
      }
      
      System.out.println("unsorted array: " + Arrays.toString(A));
      //subtract 1 from each number to create range of 0 to n^2 -1 
      subtract1(A); //takes O(n) time 
      //radixSort makes 3 calls to countingSort for total time of O(3 * (n + k)) = O(3 * (n + n)
      radixSort(A);
      //add 1 back to each number to recreate range of 1 to n^2
      add1(A);      //takes O(n) time
      
      //Overall time complexity O(n) + O(3n + 3n) + O(n) for total of T(n) = O(n)
      
      System.out.println("sorted array: " + Arrays.toString(A));
   }
   
      public static void subtract1(int[] A) {
         for (int i = 0; i < A.length; i++) {
            A[i] = A[i] - 1;
         }
         
      }
      
      public static void add1(int[] A) {
         for (int i = 0; i < A.length; i++) {
            A[i] = A[i] + 1;
         }
         
      }
      
      public static void countingSort(int[] A, int k) {
      //create count array C[1...k] for auxillary storage and initialize each value to 0
      int[] C = new int[k+1];  
      for (int i = 0; i < C.length; i++) {
         C[i] = 0; 
      }
      
      //store count of each integer from A[] in count array C[] 
      for (int i = 0; i < A.length; i++) {
         C[A[i]] += 1;
      } 
      
      //change C[i] to now contain the number of elements <= i       
      for (int i = 1; i < C.length; i++) {
         C[i] = C[i] + C[i - 1];
      } 
      
      //build sorted output array B[] 
      int[] B = new int[A.length];
      for (int i = 0; i < A.length; i++) {
         B[C[A[i]]-1] = A[i];
         --C[A[i]];
      } 
      
      //copy output array back onto input array[] A 
      for (int i = 0; i < A.length; i++) {
         A[i] = B[i];
      } 
     System.out.println("countingSort done");         
   }
   
   public static int find_max(int[] A) {
   
      int max = A[0];
      for (int i = 1; i < A.length; i++) {
         if (A[i] > max) 
            max = A[i];
      }
      System.out.println("k = " + max);
      return max; 
   }
   
   public static void radixSort(int[] A) {
   
      int max = find_max(A);
      
      //perform counting sort on every digit, 10^i passed for every digit number
      for (int i = 1; max/i > 0; i *= 10) {
         countingSort(A, max);
      }
      
  }
} 
      
   
