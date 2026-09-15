package com.code.main.java_8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee implements Comparable<Employee> {
	
	private int id;
	private String name;
	private Double salary;
	private String dept;
	

	
	
	public Employee(int id, String name, Double salary, String dept) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.dept = dept;
	}

	@Override
	public int compareTo(Employee other) { 
		return Integer.compare(this.getId(), other.getId());
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employes [id=" + id + ", name=" + name + ", salary=" + salary + ", dept="+dept+"]";
	}

}

class Product {
	
	private Long id;
	private String name;
	private Double price;
	

	
	public Product(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Double getPrice() {
		return price;
	}




	public void setPrice(Double price) {
		this.price = price;
	}



	

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}




}


public class Core {

	
	public static void main(String[]args) {
		
		Employee emp1 = new Employee(1,"annie",45000.00,"IT");
		Employee emp2 = new Employee(3,"hector",51000.00,"HR");
		Employee emp3 = new Employee(2,"bridget",55000.00,"IT");
		Employee emp4 = new Employee(4,"annie",63000.00,"Finance");
		Employee emp5 = new Employee(5,"alex",70000.00,"Finance");
		Employee emp6 = new Employee(6,"Gunja",9000.00,"IT");
		Employee emp7 = new Employee(7,"shahrukh",58000.00,"HR");
		Employee emp8 = new Employee(8,"ali",55000.00,"Finance");
		
		List<Employee>employee = Arrays.asList(emp1,emp2,emp3,emp4,emp5,emp6,emp7,emp8);
		System.out.println(employee);
		Collections.sort(employee);
		System.out.println("After applying Comparable");
		System.out.println(employee);
		
		Product p1 = new Product(1l,"Laptop",1500.00);
		Product p2 = new Product(3l,"Monitor",1700.00);
		Product p3 = new Product(4l,"HDD",1300.00);
		Product p4 = new Product(2l,"RAM",1400.00);
		Product p5 = new Product(5l,"SSD",1200.00);
		
		List<Product> product = Arrays.asList(p1,p2,p3,p4,p5);
		
		System.out.println("After applying the Comparator");
		product.sort(Comparator.comparing(Product::getId));
		System.out.println(product);
		
		//Highest to lowest salary sorting using stream
		
		List<Product> high_low = product.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
		System.out.println(high_low);
		
		//list the employee who's salary is greater then 1000;
		
		List<Employee>emp = employee.stream().filter(e->e.getSalary()>1000).collect(Collectors.toList());
		System.out.println(emp);
		
		
		//list the employee name who's salary is greater then 5k
		List<String>emp_names = employee.stream().filter(e->e.getSalary()>50000).map(name->name.getName()).collect(Collectors.toList());
		System.out.println(emp_names);
		
		//Group all the employee their deparment;
		
		employee.stream().collect(Collectors.groupingBy(e->e.getDept())).entrySet().forEach(System.out::println);
		
		//count the employee per department
		
		employee.stream().collect(Collectors.groupingBy(e->e.getDept(),Collectors.counting())).entrySet().forEach(System.out::println);
		
		//average salary of each department
		employee.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.averagingDouble(Employee::getSalary))).entrySet().forEach(System.out::println);
		
		//highest salary employee details
//		Note:
		/**
----------------------------------------------------------------------------------------------------
		 * 
		 * Highest employee overall
			→ max()

		  Employees grouped by department
			→ groupingBy()

		Highest employee in EACH department
		→ groupingBy() + maxBy()
----------------------------------------------------------------------------------------------------
		 */
				
		Employee e = employee.stream().max(Comparator.comparing(Employee::getSalary)).orElse(null);
		System.out.println(e);
		
		
		
		//second highest salary
		Employee secondHighest = employee.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst().orElse(null);
		System.out.println(secondHighest);
		
		//second highest distinct salary
		Double secondHighestSalary = employee.stream()
			    .map(Employee::getSalary)
			    .distinct()
			    .sorted(Comparator.reverseOrder())
			    .skip(1)
			    .findFirst()
			    .orElseThrow(() -> new RuntimeException("Salary not found"));

		System.out.println(secondHighestSalary);
		
		
		
		
		//Highest paid employee per department
		employee.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))).entrySet().forEach(System.out::println);
		
		
		//find the total salary of each department
		System.out.println(employee);
		
		employee.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.summingDouble(Employee::getSalary))).entrySet().forEach(System.out::println);
		
		/*
		 * 	groupingBy(
    		Employee::getDept,    ← GROUP BY
    		summingDouble(Employee::getSalary) ← WHAT TO DO
			)
		 * 
		 * 
		 */
		
		//department having highest total salary
		
		employee.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.summingDouble(Employee::getSalary))).entrySet().stream().max(Map.Entry.comparingByValue());
		
		
		
		List<Employee> emps = Arrays.asList(
			    // IT (5)
			    new Employee(1, "Annie", 1500.00, "IT"),
			    new Employee(2, "Bob", 1800.00, "IT"),
			    new Employee(3, "Charlie", 2100.00, "IT"),
			    new Employee(4, "David", 1600.00, "IT"),
			    new Employee(5, "Eva", 2000.00, "IT"),
			    new Employee(1, "Annie", 1500.00, "IT"),

			    // HR (2)
			    new Employee(6, "Frank", 1400.00, "HR"),
			    new Employee(7, "Grace", 1700.00, "HR"),
			    new Employee(6, "Frank", 1400.00, "HR"),

			    // Finance (4)
			    new Employee(8, "Hannah", 2200.00, "Finance"),
			    new Employee(9, "Ian", 1900.00, "Finance"),
			    new Employee(10, "Jack", 2500.00, "Finance"),
			    new Employee(11, "Karen", 2300.00, "Finance"),
			    new Employee(9, "Ian", 1900.00, "Finance"),

			    // Admin (1)
			    new Employee(12, "Leo", 1300.00, "Admin"),
			    new Employee(12, "Leo", 1300.00, "Admin")
			);
		
		
		//Employee count by department but only count>2
		
		emps.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.counting())).entrySet().stream().filter(em->em.getValue()>2).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
		.entrySet().forEach(System.out::println);
		/**
		 * after filter() you still have a Stream. If the question says “return a List/Map”, you’ll normally need a terminal operation such as collect().
		 */
		
		//find the duplicate employee name 
		List<String> names = emps.stream()
			    .collect(Collectors.groupingBy(
			        Employee::getName,
			        Collectors.counting()
			    ))
			    .entrySet()
			    .stream()
			    .filter(entry -> entry.getValue() > 1)
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList());
		System.out.println(names);

		
		//find the unique employee name.
		List<String>uniquenames = emps.stream().collect(Collectors.groupingBy(Employee::getName,Collectors.counting())).entrySet().stream().filter(ex->ex.getValue()==1).map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println(uniquenames);
		
		//find the employee who's name start with A
		
		List<String> namestartA = emps.stream().collect(Collectors.groupingBy(Employee::getName)).entrySet().stream().filter(ss->ss.getKey().startsWith("A")).map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println(namestartA);

		
	
	}
}
