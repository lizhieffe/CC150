package Finished;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Q1102 {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortArray(String[] array) {
		if (array == null || array.length <= 1)
			return;
		
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		
		for (int i = 0; i < array.length; i ++) {
			String sorted = sortString(array[i]);
			if (!result.containsKey(sorted))
				result.put(sorted, new ArrayList<String>());
			result.get(sorted).add(array[i]);
		}
		
		int i = 0;
		Iterator<?> it = result.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        for (String s : (ArrayList<String>)pairs.getValue()) {
	        	array[i ++] = s;
	        }
		}
		
		System.out.println("end");
	}
	
	private String sortString(String input) {
		if (input == null || input.length() <= 1)
			return input;
		
		int[] count = new int[256];
		for (int i = 0; i < 256; i ++)
			count[i] = 0;
		
		for (int i = 0; i < input.length(); i ++) {
			count[(int)input.charAt(i)] ++;
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 256; i ++) {
			if (count[i] > 0) {
				for (int j = 0; j < count[i]; j ++) {
					builder.append((char)i);
				}
			}
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		String[] strings = {"abc", "cba", "bbb", "efr", "ref"};
		
		Q1102 service = new Q1102();
		service.sortArray(strings);
	}
}
