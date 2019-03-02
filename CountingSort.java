/*
 * Name: Allison Smith 
 * Homework4 
 * Project: CountingSort.java 
 */
 
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class CountingSort {

   public static void main(String[] args) {
   
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Enter array size: ");
      int n = keyboard.nextInt();
      
      int[] A = new int[n];
      
      //generate random values for all index positions 0 through n-1
      Random r = new Random();
      for (int i = 0; i < n; i++){
         A[i] = r.nextInt(501);
      }
      
      System.out.println("unsorted array: " + Arrays.toString(A));
      sort(A);
      System.out.println("sorted array: " + Arrays.toString(A));
      
   }
   
   public static void sort(int[] A) {
      //determine range of input array
      int k = find_k(A);
      
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
              
   }
   
   public static int find_k(int[] A) {
   
      int max = A[0];
      for (int i = 1; i < A.length; i++) {
         if (A[i] > max) 
            max = A[i];
      }
      System.out.println("k = " + max);
      return max; 
  }

}

/* Sample output: 

Enter array size: 
 50
 unsorted array: [96, 48, 35, 450, 349, 79, 270, 242, 328, 463, 407, 219, 321, 205, 79, 338, 97, 270, 56, 361, 488, 130, 312, 171, 461, 82, 58, 259, 145, 388, 44, 74, 471, 64, 270, 362, 67, 410, 262, 330, 240, 489, 30, 101, 422, 123, 299, 478, 113, 330]
 k = 489
 sorted array: [30, 35, 44, 48, 56, 58, 64, 67, 74, 79, 79, 82, 96, 97, 101, 113, 123, 130, 145, 171, 205, 219, 240, 242, 259, 262, 270, 270, 270, 299, 312, 321, 328, 330, 330, 338, 349, 361, 362, 388, 407, 410, 422, 450, 461, 463, 471, 478, 488, 489]
 
*/
          
            
   
