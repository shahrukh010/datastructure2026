package com.code.main.java_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


//class Employee {
//	
//	private Long id;
//	private String firstName;
//	private Double salary;
//	
//	public Employee(Long id, String firstName, Double salary) {
//		
//		this.id = id;
//		this.firstName= firstName;
//		this.salary = salary;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public Double getSalary() {
//		return salary;
//	}
//
//	public void setSalary(Double salary) {
//		this.salary = salary;
//	}
//
//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", firstName=" + firstName + ", salary=" + salary + "]";
//	}
//	
//	
//}
//public class Main {
//
//	
//	//Remove duplicate and reverse in decending order
//	
//	public List<Integer> removeDuplicate(List<Integer> list){
//
//		List<Integer> result = list.stream().distinct().collect(Collectors.toList());
//		return result;
//	}
//	//Rmove duplicate and sort in reverse order
//	public List<Integer> removeDuplicateAndSortReverse(List<Integer>input){
//		
//		return input.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList()); }
//	
//	
//	//find all the odd number 	
//	public List<Integer> findAllOddNumber(List<Integer>input){
//		
//													//collect is terminal operator of stream api,
//		   										    //means collect final stream element into list
//		return input.stream().filter(ele->ele%2!=0).collect(Collectors.toList());
//	}
//	
//	//find all the odd number and return the its square
//	public List<Integer>findAllTheOddNumberAndItSqaure(List<Integer>input){
//		
//		return input.stream().filter(ele->ele % 2!=0).map(ele->ele*ele).collect(Collectors.toList());
//	}
//
//	//Get the 2nd and 3rd element
//	public List<Integer> GetTheSpecifyPositionElement(List<Integer>input){
//		
//		return input.stream().skip(1).limit(2).collect(Collectors.toList());
//	}
//	
//	//find the second highest number.
//	public Integer secondHighest(List<Integer>input){
//		
//		
//		List<Integer> result =  input.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).limit(2).collect(Collectors.toList());
//		//other way
//		
//		Optional<Integer> res = input.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
//		return res.get();
//
////		System.out.println(result);
////		return result.get(0);
//	}
//	//split the list into two parts odd/even
//	
//	public Map<Boolean,List<Integer>> splitList(List<Integer>input){
//		
//		return input.stream().collect(Collectors.partitioningBy(ele->ele %2!=0));
//	}
//	//find the longest string
//	
//	public String findTheLongestString(List<String>input) {
//		
//		Optional<String> result = input.stream().max(Comparator.comparing(ele->ele.length()));
//		if(result.isPresent())
//			return result.get();
//		return null;
//	}
//	
//	//find the first employee only wh's salary is greater then 5k
//	public Employee findTheFirstEmployeWhosSalaryIsGreaterThen5k(List<Employee>employee){
//
////		return employee.stream().max(Comparator.comparing(emp->emp.getSalary()>5000)).get();
//		Optional<Employee> result  = employee.stream().filter(ele->ele.getSalary()>5000).findFirst();
//		if(result.isPresent()) {
//			return result.get();
//		}
//		return null;
//		
//	}
//	
//	//find the all the employee who's salary is greater then 5k
//	
//	public List<Employee> getEmployeeSalaryGreaterthen5K(List<Employee>employee){
//		
//		
//		return employee.stream().filter(emp->emp.getSalary()>5000).collect(Collectors.toList());
//	}
//	
//
//	
//	
//	public static void main(String[]args) {
//		
//		Main main = new Main();
//		
//		List<Integer> input = Arrays.asList(5,3,1,3,2,4,5);
//		List<Integer> result = main.removeDuplicate(input);
//		System.out.println(result);
//		result = main.removeDuplicateAndSortReverse(input);
//		System.out.println(result);
//		result = main.findAllOddNumber(input);
//		System.out.println(result);
//		result = main.findAllTheOddNumberAndItSqaure(input);
//		System.out.println(result);
//		List<Integer>nums = new ArrayList<>(Arrays.asList(10,20,30,40,50));
//		result = main.GetTheSpecifyPositionElement(nums);
//		System.out.println(result);
//		nums = Arrays.asList(20,10,10,45,30,45,5,20,50);
//		Integer res  = main.secondHighest(nums);
//		System.out.println(res);
//		System.out.println(main.splitList(input));
//		
//		List<String>strings = Arrays.asList("Java","Spring","SpringBoot","MySql"); 
//		System.out.println(main.findTheLongestString(strings));
//		
//
//		List<Employee> employee = Arrays.asList(
//				new Employee(1l,"Hector",3500.00),
//				new Employee(2l,"Annie",4000.00),
//				new Employee(3l,"Bridget",5000.00),
//				new Employee(4l,"Nic",2500.00),
//				new Employee(5l,"Alex",5500.00),
//				new Employee(6l,"Elam",6500.00));
//
//		
//		System.out.println(main.findTheFirstEmployeWhosSalaryIsGreaterThen5k(employee));
//		
//		System.out.println(main.getEmployeeSalaryGreaterthen5K(employee));
//		
//	}
//	
//}
