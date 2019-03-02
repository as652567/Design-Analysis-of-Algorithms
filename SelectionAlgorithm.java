/*
 * Name: Allison Smith 
 * Homework3 due 3/8/18
 * Project: SelectionAlgorithm.java
 */

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class SelectionAlgorithm {

   public static int min;
   public static int max;
   public static int num_comparisons;
   public static int num_swaps; 
   
   public static void main (String[] args) {
   
   //Step 1: create small size array (n=16) to test w/ output checks at each stage 
   //int[] A = {7, 35, -2, 4, 16, 1, 0, 9, -10, 3, 45, 11, 10, 100, -5, 2};
   
   /*getMinMax(A);
   
   //prompt user to provide int i (ith smallest element in array)
   Scanner keyboard = new Scanner(System.in);
   System.out.println("indicate value of i for ith smallest element: ");
   int i = keyboard.nextInt();
   if (i <= 0 || i > A.length) 
      throw new IllegalArgumentException("i must be greater than 0 and less than array length A.length");
   System.out.println("\ni = " + i + "\ni'th smallest element is " + randomizedSelect(A, 0, A.length - 1, i));
   */
   /*System.out.println("The medianOfMedians is " + median(A));
   int medianOfMedians = median(A);
   //swap median of medians with last element to serve as the pivot 
   int index = findIndex(A, medianOfMedians);
   int temp = A[A.length-1];
   A[A.length-1] = medianOfMedians;
   A[index] = temp;
   num_swaps++; 
   median_quickSort(A, 0, A.length-1);
   System.out.println("The sorted array partitioned using the medianOfMedians is " + Arrays.toString(A)); */
   
  
      
   //Step 2: create array of length n
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
        
       //re-initialize num_swaps and num_comparisons to 0 for each iteration of for loop   
       num_swaps = 0;
       num_comparisons = 0;

       //getMinMax(A);
       //randomizedSelect(A, 0, A.length-1, 5); //test ranomized select with i = 5
       //median(A);
         
         
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
   
   //Version 1: Simulatenous Min & Max Select
   public static void getMinMax(int[] A) {
	   int i;
      
      //check to see if array is even/odd
      //case even: compare first two elements and set min and max accordingly 
		num_comparisons++;
      if (A.length % 2 == 0) { 
         num_comparisons++; 
			if (A[0] >= A[1]) {   //check to find min/max of first two elements 
				max = A[0];
				min = A[1];
			} else {    
				min = A[0];
				max = A[1];
			}
			i = 2;  //case even: starting index set at 2
		}
		//case odd: set first element as both min and max
		else {
			min = A[0];
			max = A[0];
			i = 1; //case odd: starting index set at 1
		}
		
      //process remaining elements in pairs 
      //find larger and smaller of pair; compare larger with current max and smaller with current min
		while (i < A.length) { 
         num_comparisons++; 
			if (A[i] > A[i + 1]) {
            num_comparisons +=2;
				if (A[i] > max) {
					max = A[i];
               num_swaps++; }
				if (A[i + 1] < min) {
					min = A[i + 1];
               num_swaps++; }
			} else {
            num_comparisons +=2;
				if (A[i + 1] > max) {
					max = A[i + 1];
               num_swaps++; }
				if (A[i] < min) {
					min = A[i];
               num_swaps++; }
			}
			i += 2; 
		}
		System.out.println("Minimum of input is: " + min);
		System.out.println("Maximum of input is: " + max);
	}
   
   //Version 2: Randomized Select algorithm 
   public static int randomizedSelect(int[] A, int low, int high, int i) {

		//if array contains only one element
		if (low == high) {
			return A[low];
		}
      num_comparisons++;


		int q = randomizedPartition(A, low, high);
		int k = q - low + 1;  // number of elements in the low side of partition + pivot
      
		if (i == k) { // the pivot value is the answer
            num_comparisons++;
				return A[q];
		} else if (i < k) {
         num_comparisons +=2; 
		   return randomizedSelect(A, low, q - 1, i);
      } else {
         num_comparisons +=2;
         return randomizedSelect(A, q + 1, high, i - k);
      }
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
			
         //System.out.println("Partition done"); //output print check 
			return i+1;
		
	}
   
   //Version 3: Median of Medians Selection Algorithm   
   public static int median(int[] A) {
      int numSubArrays = A.length/5;
      if (A.length%5 != 0) 
         numSubArrays++;
      num_comparisons++;
      int[] medians_A = new int[numSubArrays];
      int count = 0;  //initialize variable to fill index positions of medians_A array     
      
      //find median of mod5 group if applicable
      if (A.length%5 != 0) {
     
         int[] mod5_median = Arrays.copyOfRange(A, A.length-(A.length%5), A.length);
         insertionSort(mod5_median);
         if (mod5_median.length == 1 || mod5_median.length == 2) {
            medians_A[0] = mod5_median[0];
         } else { 
            medians_A[0] = mod5_median[1];
         }
         num_comparisons++;
         count++;
      //test code: System.out.println(medians_A[0] + Arrays.toString(mod5_median));
      }
   
      //find medians of all other A.length/5 subsequences
      for (int j = 0; j <(A.length-A.length%5); j +=5) {
         int[] temp1 = Arrays.copyOfRange(A, j, j+5);
         insertionSort(temp1);
         medians_A[count++] = temp1[2];
      }
 
      insertionSort(medians_A);
      //test code: System.out.println(Arrays.toString(medians_A));
      int medianOfMedians;
   
      if (numSubArrays % 2 == 0)
         medianOfMedians = medians_A[medians_A.length/2 - 1];
      else 
         medianOfMedians = medians_A[medians_A.length/2];
      num_comparisons++;
      
      return medianOfMedians;
   }
      
 
   public static int findIndex(int[] A, int value) {
      int index;
      for(index = 0; index < A.length; index++) { //find index position of medianOfMedians in A
         num_comparisons++;
         if(A[index] == value)
             break;
      }
      return index;
   }
      
   public static void median_quickSort(int A[], int low, int high) {
			
      if (low < high) {
            //num_comparisons++; 
		      int q = partition(A, low, high);
		      median_quickSort(A, low, q - 1);     // before pivot q 
		      median_quickSort(A, q + 1, high);    // after pivot q 		
		   }
         //num_comparisons++; 
   }
      
   public static void insertionSort ( int A[]) {
			   
      int key, i;    
		for (int j = 1; j < A.length; j++) {
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
              
		//test code: System.out.println("InsertionSort Done");
   }
  

}//end SelectionAlgorithm class 
 
 