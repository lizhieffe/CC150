package Finished;

import java.util.ArrayList;

public class Q0908 {

	ArrayList<int[]> result = new ArrayList<int[]>();
	
	ArrayList<int[]> findCombination(int sum) {
		if (sum < 1)
			return result;
		
		int[] solution = {0, 0, 0, 0};
		findSolution(0, sum, solution);
		findSolution(1, sum, solution);
		findSolution(2, sum, solution);
		findSolution(3, sum, solution);

		return result;
	}
	
	void findSolution(int nicklePriceId, int sum, int[] solution) {
		if (sum <= 0)
			return;
		
		for (int i = nicklePriceId; i < 4; i ++) {
			int[] tmpSolution = new int[4];
			System.arraycopy(solution, 0, tmpSolution, 0, solution.length);
			tmpSolution[nicklePriceId] ++;
			
			int tmp;
			if (nicklePriceId == 0)
				tmp = 25;
			else if (nicklePriceId == 1)
				tmp = 10;
			else if (nicklePriceId == 2)
				tmp = 5;
			else
				tmp = 1;
			
			if (sum - tmp == 0) {
				result.add(tmpSolution);
				return;
			}
			else if (sum - tmp < 0)
				return;
			
			findSolution(i, sum - tmp, tmpSolution);
		}
	}
	
	public static void main(String[] args) {
		int sum = 25;
		
		Q0908 service = new Q0908();
		service.findCombination(sum);
	}
}
