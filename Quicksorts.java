/*
 * Name: Allison Smith 
 * Homework3 due 2/27/18
 * Project: Quicksorts.java
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Quicksorts {

   static int num_swaps = 0;
   static int num_comparisons = 0;

	public static void main(String[] args) {
		
      //Step 1: create small size array (n=16) to test w/ output checks at each stage 
      //int[] A = {7, 35, -2, 4, 16, 1, 0, 9, -10, 3, 45, 11, 10, 100, -5, 2};
      
      //quickSort(A, 0, (A.length-1));
      //quick_Insertion(A, 0, A.length-1);
      //insertionSort(A, A.length);
		//randomizedQuickSort(A, 0, (A.length-1));
      //medianQuickSort(A, 0, A.length-1);
		//System.out.println(Arrays.toString(A));
      
      //create array of length n
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Enter array size: ");
      int n = keyboard.nextInt();
      
      int[] A = new int[n];
    
      int total_comparisons = 0;
      int total_swaps = 0; 
      int max_swaps = 0;
      int max_comparisons = 0;  
      for (int i = 0; i < 10; i++) {
      
      
      /*//Step 2: generate random values for all index positions 0 through n-1
      Random r = new Random();
      for (int j = 0; j < n; j++){
         A[j] = r.nextInt();
      }*/
      
      //Step 3: repeat step 2 on "almost sorted" arrays 
      for (int k = 0; k < A.length; k++) {
         A[k] = k;
      }
      //swap random indices i,j to create almost sorted array
      Random r = new Random();
      for (int count = 0; count < (A.length/50); count++){
         // Pick a random index from 0 to n
			int m = r.nextInt(n);
			int j = r.nextInt(n);

			// Swap A[i] with the element at A[j]
			int temp = A[m];
			A[m] = A[j];
			A[j] = temp;
		 }
        
         
         num_swaps = 0;
         num_comparisons = 0;
         //int num_swaps = 0;
         //int num_comparisons = 0;
		   //quickSort(A, 0, (A.length-1));
         //quick_Insertion(A, 0, A.length-1);
		   //randomizedQuickSort(A, 0, (A.length-1));
         medianQuickSort(A, 0, A.length-1);
         
         if (num_swaps > max_swaps) 
            max_swaps = num_swaps;
         if (num_comparisons > max_comparisons)
            max_comparisons = num_comparisons; 

         total_comparisons += num_comparisons;
         total_swaps += num_swaps;
     }
     
     int av_swaps = (total_swaps/10);
     int av_comparisons = (total_comparisons/10);
     System.out.println("The mean number of comparisons was: " + av_comparisons + "\nThe max number of comparisons was: " + max_comparisons);
     System.out.println("The mean number of swaps was: " + av_swaps + "\nThe max number of swaps was: " + max_swaps);
     
	   
      
		
	}
		
		public static int partition(int A[], int low, int high) {
			int pivot = A[high]; 
			int i = (low-1); // index of smaller element
			for (int j=low; j<high; j++) {
			// If current element is smaller than or equal to pivot 
			   if (A[j] <= pivot) {
               i++;
			      // swap A[i] and A[j]
			      int temp = A[i];
			      A[i] = A[j];
			      A[j] = temp;
               num_swaps++;
			   }
            num_comparisons++;
			}
			 
		   // swap A[i+1] and A[high] (or pivot)
		   int temp = A[i+1];
			A[i+1] = A[high];
			A[high] = temp;
         num_swaps++;        
			
         System.out.println("Partition done"); //output print check 
			return i+1;
		
		}
		
		public static int randomizedPartition(int A[], int low, int high) {
			//swap last element w/ randomly chosen element from subarray making pivot equally likely to be any of the r-p+1 elements
         Random rand = new Random();
			int pivot = low + rand.nextInt(high - low + 1);
			int temp = A[high];
			A[high] = A[pivot];
			A[pivot] = temp;
         num_swaps++;
			
			return partition(A,low, high);
		
		}
      
      public static int medianPartition(int A[], int low, int high) {

         int mid = (high-1)/2;
         int[] medianArray = {A[low], A[mid], A[high]};
         Arrays.sort(medianArray);
         int medianOfThree = medianArray[1];
         
         //swap with last element to serve as the pivot 
         int temp = A[high];
			A[high] = medianOfThree;
			if (medianOfThree == A[low]){
            A[low] = temp;
         } else if (medianOfThree == A[mid]){
            A[mid] = temp;
         } 
         num_swaps++;
			return partition(A,low, high);
         
      }
      
      //Version1 QuickSort method 
      public static void quickSort(int A[], int low, int high) {
			
		   if (low < high) {
            num_comparisons++; 
		      int q = partition(A, low, high);
		      quickSort(A, low, q - 1);     // before pivot q 
		      quickSort(A, q + 1, high);    // after pivot q 		
		   }
         num_comparisons++; 
		}
      
      //Version2 QuickSort method w/ InsertionSort on small arrays (n <= 5, 10, 20)
		public static void quick_Insertion(int A[], int low, int high) {
			
		   if (low < high) {
		      int q = partition(A, low, high);
            
            //check to see if a subarray is <= n = 5, 10, 20 
            //if so, perform InsertionSort on subarray
		    	if (q - low <= 5 || high - q <= 5) {
               insertionSort(A, A.length);
		    	} else {
		        quick_Insertion(A, low, q - 1); 
              quick_Insertion(A, q+1, high);
            } 
            num_comparisons++;  
		   }
         num_comparisons++;
		}
      
      //InsertionSort method for Version2 of QuickSort
      public static void insertionSort ( int A[], int n ) {
			   
		   int key, i;    
		   for (int j = 1; j < n; j++) {
		      key = A[j];
		      i = j - 1;
		         
		      //compare key to all index positions from A[0] to A[key-1]
            num_comparisons++;  
		      while (i > -1 && A[i] > key){
               num_comparisons++;  
		         A[i+1] = A[i];
		         i = i - 1;
               num_swaps++;
		      } 
     
		      A[i+1] = key;  //reposition key after comparing with A[0] to A[key-1]  
            num_swaps++; 
		   }//end for statement  
              
		System.out.println("InsertionSort Done");
	   }
		
      //Version3 Randomized QuickSort method
		public static void randomizedQuickSort(int A[], int low, int high) {
			
		   if (low < high) {
            num_comparisons++; 
		      int q = randomizedPartition(A, low, high);
		      randomizedQuickSort(A, low, q - 1);       
		      randomizedQuickSort(A, q + 1, high);      
		    }
         num_comparisons++;  	   
		}
		
      //Version4 Median of 3 QuickSort method
		public static void medianQuickSort(int A[], int low, int high) {
			
		   if (low < high) {
            num_comparisons++; 
		      int q = medianPartition(A, low, high);
		      medianQuickSort(A, low, q - 1);  
		      medianQuickSort(A, q + 1, high); 
         }
         num_comparisons++;
		}
		
} //end QuickSort class 
