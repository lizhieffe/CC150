package Chap11;

import java.util.Arrays;

public class Q01 {
	int[] mergeTwoArrays(int[] A, int[] B) {
		if (A.length == 0) {
			Arrays.sort(B);
			return B;
		}
		
		if (B.length == 0) {
			Arrays.sort(A);
			return A;
		}
		
		int[] result = new int[A.length + B.length];
		int i = A.length - 1;
		int j = B.length - 1;
		Arrays.sort(A);
		Arrays.sort(B);
		
		while (i >= 0 || j >= 0) {
			if (i < 0) {
				result[i + j + 1] = B[j --];
				continue;
			}
			
			if (j < 0) {
				result[i + j + 1] = A[i --];
				continue;
			}
			
			result[i + j + 1] = A[i] < B[j] ? B [j --] : A[i --];
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] A = {1, 3, 9, 7, 5};
		int[] B = {6, 4};
		
		Q01 service = new Q01();
		service.mergeTwoArrays(A, B);
	}
}
