package com.code.main.java_8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class StreamAPI {

	
	//find all the even number;
	public List<Integer>findEvenNumber(List<Integer> numbers){
		
		/**
		 * 1:source:numbers
		 * 2:what i want:even number only
		 * 3:what final result: list of even number
		 */
		
		return numbers.stream().filter((n)->n%2==0).collect(Collectors.toList());
		
		/*
		 *numbers: source
		 *stream: create stream
		 *filter(n->n%2==0): intermediate operation
		 *collect(...): terminal operation
		 */
		
	}
	
	//how many even numbers are present
	
	public long countTheEvenNumber(List<Integer>numbers) {
		
		/*
		 * 1. Source          → ?
		   2. What I want     → ?
	       3. Final result    → ?
		 */
		
		return numbers.stream().filter((n)->n%2==0).count();
		
		
//		numbers
//		   ↓
//		stream()
//		   ↓
//		filter()     ← Intermediate
//		   ↓
//		count()      ← Terminal
//		   ↓
//		   3
	}
	
	
	//find the first even number
	
	public Integer findFirstEvenNumber(List<Integer>nums) {
		
		/**
		 * 1:source: nums
		 * 2:what we want: filter the even number
		 * 3:Final result: first even number
		 */
		
		return nums.stream().filter((n)->n%2==0).findFirst().orElse(-1);
		
	}
	
	//now we will move the second part, transform the data.
	
	public List<Integer>sqaure(List<Integer>numbers){
		
		/**
		 * source: numbers
		 * what i want: square of every number
		 * final result: return list of square number
		 */
		
		return numbers.stream().map(n->n*n).collect(Collectors.toList());
	}
	
	
	
	public Map<Character,List<String>> groupNamesBasedOnFirstChar(List<String>names){
		
		
		return names.stream().collect(Collectors.groupingBy(name->name.charAt(0)));
		
	}
	
	
	
	
	public static void main(String[]args) {
		
		StreamAPI api = new StreamAPI();
		
		List<Integer>nums = Arrays.asList(10,15,20,25,30);
		List<Integer> result = api.findEvenNumber(nums);
		System.out.println(result);
		System.out.println(api.countTheEvenNumber(nums));
		System.out.println(api.findFirstEvenNumber(nums));
		
		result = api.sqaure(nums);
		System.out.println(result);
		
		
		
		
	//find the all even number and square them
		
		Function<List<Integer>,List<Integer>>numbers = (input)->{
			
			/*
			 * 
			 * input
				  ↓
				stream()
				  ↓
				filter(n -> n % 2 == 0)   ← Intermediate
				  ↓
				map(n -> n * n)            ← Intermediate
				  ↓
				collect(...)                ← Terminal
			 * 
			 * 
			 */
			return input.stream().filter(n->n%2==0).map(n->n*n).collect(Collectors.toList());
		};
		
		System.out.println(numbers.apply(nums));
		
	
		//find all names starting with "A".
	
		Function<List<String>,List<String>>strings = (input)->{
		
			return input.stream().filter(s->s.startsWith("A")).collect(Collectors.toList());
		};
		
		List<String> names = Arrays.asList("Amit", "Shahrukh", "Ankit", "Rahul", "Aman", "Rohit");


		System.out.println(strings.apply(names));
		
		/**
		 * 
		 *  1. Source       → names
			2. Select       → starts with "A" → ?
			3. Transform    → uppercase       → ?
			4. Final result → List<String>    → ?
		 * 
		 * 
		 */
		
		// Find all names starting with "A" and convert those names to uppercase.
		
		Function<List<String>,List<String>>str = (input)->{
			
			/**
			 * source: input
			 * select: start with A
			 * transform: uppercase
			 * final result: list<String>
			 * 
			 */
			
			return input.stream().filter(s->s.startsWith("A")).map(s->s.toUpperCase()).collect(Collectors.toList());

		};
		System.out.println(str.apply(names));
	
	
	//sort the ascending order
	List<Integer> numbers1 = Arrays.asList(50, 10, 40, 20, 30);
	
	Function<List<Integer>,List<Integer>>nums1 = (input)->{
		
		/**
		 * source: input
		 * transform:sort the element
		 * final result: to list
		 */
		
		return input.stream().sorted().collect(Collectors.toList());
	};
	System.out.println(nums1.apply(numbers1));
	
	
	//decending order
	
	nums1 = (input)->{
		
		return input.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	};
	
	System.out.println(nums1.apply(numbers1));
	
	
	//removed the duplicate 
	
	List<Integer> numb = Arrays.asList(10, 20, 10, 30, 20, 40, 30);
	
	Function<List<Integer>,List<Integer>> unique = (input)->{
		
		return input.stream().distinct().collect(Collectors.toList());
	};
	System.out.println(unique.apply(numb));
	
	//only first 3 element
	
	Function<List<Integer>,List<Integer>>limit = (input)->{
		
		 return input.stream().limit(3).collect(Collectors.toList());
	};
	System.out.println(limit.apply(numb));
	
	
	//skip the first 3 element
	
	Function<List<Integer>,List<Integer>> skip = (input)->{
		
		return input.stream().skip(3).collect(Collectors.toList());
	};
	System.out.println(skip.apply(numb));
	
	
	//any number is greater than 40.
	numb = Arrays.asList(10,0, 20, 30, 40, 50);
	
	Predicate<List<Integer>>isExist40 = (input)->{
		
		return input.stream().anyMatch(n->n>40);
	};
	System.out.println(isExist40.test(numb));

	//find all the number are greater then 5
	
	Predicate<List<Integer>>allNumbs = (input)->{
		
		return input.stream().allMatch(n-> n>5);
	};
	System.out.println(allNumbs.test(numb));
	
	
	//find the maximum number from list
	 List<Integer>num = Arrays.asList(10, 50, 20, 80, 30, 60);
	
	Function<List<Integer>,Integer>maxnum = (input)->{
		
		/**
		 * source: input
		 * want we want: find the maximum number from list
		 * final result: return the maxim number(single number)
		 */
		
//		return input.stream().max(Integer::compareTo).orElse(-1);
		return input.stream().max(Comparator.naturalOrder()).orElse(-1);
	};
	System.out.println(maxnum.apply(num));
	
	
	//find the minimum number
	Function<List<Integer>,Integer>minnum = (input)->{
		
		return input.stream().min(Comparator.naturalOrder()).orElse(-1);
	};
	
	System.out.println(minnum.apply(num));
	
	
	
	List<Integer>nmb= Arrays.asList(10,20,30,40,50);

	Function<List<Integer>,Integer>total = (input)->{
		
		
		return input.stream().reduce(0, (a,b)->a+b);

	};
	
	System.out.println(total.apply(nmb));
	
	
	//product all the number
	
	Function<List<Integer>,Integer> product = (input)->{
		
		return input.stream().reduce(1, (a,b)->a*b).intValue();
	};
	
	System.out.println(product.apply(nmb));
	
	
	
	
	
	Function<List<Integer>,Integer>sums = (input)->{
		
		
		return input.stream().reduce(0, (a,b)->a+b);

	};
	
	
	//square first then sum the number
	List<Integer>gunja = Arrays.asList(1,2,3,4,5,6);
	
	Function<List<Integer>,Integer>sumofsquare = (input)->{
		
		
		return input.stream().map(n->n*n).reduce(0,(a,b)->a+b);
	};
	System.out.println(sumofsquare.apply(gunja));
	
	
	
	
	//Find the sum of squares of only the even numbers.
	
	Function<List<Integer>,Integer>sum_of_square = (input)->{
		
		
		return input.stream().filter(n->n%2==0).map(n->n*n).reduce(Integer::sum).orElse(-1);
		
		
	};
	System.out.println(sum_of_square.apply(gunja));
	
	
	Map<Character,List<String>> map = api.groupNamesBasedOnFirstChar(names);
	
		for(Entry entry: map.entrySet()) {
			
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	
		
		//how many names start with each character
		
		List<Integer> inpu = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4);
		Function<List<String>,Map<Character,Long>> counting = (input)->{
			
			/**
			 * GroupingBy(what to group by, * what to do with each group)
			 */
			
			return input.stream().collect(Collectors.groupingBy(ch->ch.charAt(0),Collectors.counting()));

		};
		
		
		for(Entry entry: counting.apply(names).entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		
		//find the duplicate element and return the list.
		
		
		Function<List<Integer>,List<Integer>> duplicate = (input)->{
			
			return input.stream().collect(Collectors.groupingBy(n->n,Collectors.counting())).entrySet().stream().filter(n->n.getValue()>1).map(n->n.getKey()).collect(Collectors.toList());
		};
	
	
	}
	
}
	
	
	
	
	
	