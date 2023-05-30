import java.util.*;
import java.io.*;

public class selection_sort{

	public static void sel_sort(int[] arr){
		for(int i=0; i<arr.length-1; i++){
			int min=i;
			for(int j=i+1; j<arr.length; j++){
				if(arr[j]<arr[min])
					min=j;
			}
			if(min!=i){
				int temp= arr[min];
				arr[min]=arr[i];
				arr[i]=temp;
			}
		}
	}
	public static void main(String[] args){
		
		System.out.println("Enter the size of array: ");
		Scanner sc= new Scanner(System.in);
		int n=sc.nextInt();
		int[] arr1=new int[n];
		System.out.println("Enter the elements of array: ");
		for(int i=0; i<n; i++){
			arr1[i]=sc.nextInt();
		}
		System.out.println("Given array is: ");
		for(int i=0; i<n; i++){
			System.out.print(arr1[i]+ " " );
		}
		System.out.println("\nSorted array is: ");
		sel_sort(arr1);

		for(int i=0; i<n; i++){
			System.out.print(arr1[i]+ " " );
		}

	}
}