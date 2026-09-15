package com.code.main.dsa;

import java.util.HashMap;
import java.util.Map;

public class Dsa {

	
	public void frequence(int[]input) {
		
		Map<Integer,Integer>freq = new HashMap<>();
		for(int index = 0; index < input.length; index++) {
			
			if(freq.containsKey(input[index])) {
				
//				System.out.println(input[index]);
				freq.put(input[index],freq.getOrDefault(input[index], 0)+1);
//				System.out.println(freq.getOrDefault(input[index], 0));
			}
			else {
//				System.out.println(input[index]);
	     		freq.put(input[index], 1);
			}

		}
		
		for(Map.Entry entry: freq.entrySet()) {
			
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
	public static void main(String[]args) {
		
		
		Dsa dsa = new Dsa();
		int[] input = {10, 10, 10, 25, 30, 30};
		dsa.frequence(input);
	}
}
