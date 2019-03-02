/*
 * Name: Allison Smith 
 * Homework4 
 * Project: BucketSort.java 
 */

import java.util.Scanner; 
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BucketSort {

   public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Enter array size: ");
      int n = keyboard.nextInt();
      
      double[] A = new double[n];
      
      //generate random values for all index positions 0 through n-1
      Random r = new Random();
      for (int i = 0; i < n; i++){
         A[i] = r.nextDouble();
      }
      
      System.out.println("unsorted array: " + Arrays.toString(A));
      sort(A);
      System.out.println("sorted array: " + Arrays.toString(A));
     
   }
   
   public static void sort(double[] A) {
      int n = A.length; 
      
      //find range of A[] 
      double min = A[0];
      double max = A[0]; 
    
      for (int i = 1; i < n; i++) {
	      if (A[i] > max) {
		      max = A[i];
		   }
		   if (A[i] < min) {
		      min = A[i]; 
		   }
      }
      //create buckets
      @SuppressWarnings("unchecked")
      List<Double> buckets[] = new ArrayList[n];
      for (int i = 0; i < n; i++) {
         buckets[i] = new ArrayList<Double>();
      }
       
      for (int i = 0; i < n; i++) {
         buckets[(int) A[i] *n].add((double) A[i]);
      }
      
     
     int count = 0; 
     for (int i = 0; i < buckets.length; i++) { 
			insertionSort(buckets[i]); 
			for(int j = 0; j < buckets[i].size(); j++) {
				A[count] = buckets[i].get(j); 
            count++;
			}
		}   
             
   }
   
   //insertionSort method for List type array 
   public static void insertionSort (List<Double> A) {
		double key;
		int j;
		for  (int i = 1; i < A.size(); i++) {
			key = A.get(i);
			j = i - 1;
			
			while ((j >= 0) && (A.get(j) > key)) {
				A.set(j+1, A.get(j));
				j--; 
				A.set(j+1, key);
			}	
		}
   }
  
} 

