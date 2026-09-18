package com.code.streamapi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Employees{
	
	
	private long id;
	private String name;
	private String departname;
	private Double salary;
	
	
	public Employees(long id, String name, String departname, Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.departname = departname;
		this.salary = salary;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(departname, id, name, salary);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employees other = (Employees) obj;
		return Objects.equals(departname, other.departname) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(salary, other.salary);
	}
	@Override
	public String toString() {
		return "Employees [id=" + id + ", name=" + name + ", departname=" + departname + ", salary=" + salary + "]";
	}
	
	
	
	
}
public class StreamAPI {

	/**
	 * 
	 * Stream API Rules
	 * 
	 *1: Source 	  ? 
	 *2: What I Want  ? 
	 *3: Final Result ? 
	 */
	public List<Employees> findEmployeeSalaryGreather2000(List<Employees>employees){
		
		return employees.stream().filter(e->e.getSalary()>2000).collect(Collectors.toList());

	}
	
	//Find the employees name who's salary is greater then 2000
	Function<List<Employees>,List<String>>names = (emp)->{
		
		return emp.stream().filter(e->e.getSalary()>2000).map(n->n.getName()).collect(Collectors.toList());
	};
	//Find all employee who's belong to IT department
	Function<List<Employees>,List<Employees>> empitdpt = (emp)->{
		
		return emp.stream().filter(e->e.getDepartname().equalsIgnoreCase("IT")).collect(Collectors.toList());
	};
	//Find all employee who's name start with A
	Function<List<Employees>,List<Employees>>prefix = (emp)->{
		
		return emp.stream().filter(p->p.getName().startsWith("A")).collect(Collectors.toList());
	};
	//Convert all employee into uppercase
	Function<List<Employees>,List<String>>uppercase = (emp)->{
		
		return emp.stream().map(e->e.getName().toUpperCase()).collect(Collectors.toList());
	};
	
	//Return the name of employee only
	Function<List<Employees>,List<String>>empname = (emp)->{
		
		return emp.stream().map(e->e.getName()).collect(Collectors.toList());
	};
	//find the distinct employee name
	Function<List<Employees>,List<String>>distinct = (emp)->{
		
		return emp.stream().map(Employees::getName).distinct().collect(Collectors.toList());
	};
	
	Function<List<Employees>,List<Double>>salaryList = (input)->{
		
		return input.stream().map(Employees::getSalary).collect(Collectors.toList());
	};
	
	Function<List<Employees>,Long> countemp = (input)->{
		
		return input.stream().count();
	};
	
	
	
	Predicate<List<Employees>> check = (input)->{
		
		return input.stream().anyMatch(e->e.getSalary()>1800);
	};
	
	Predicate<List<Employees>>allcheck = (input)->{
		
		return input.stream().allMatch(e->e.getSalary()>1000);
	};
	
	//Find the first employee belonging to the HR department.
	
	Function<List<Employees>,Employees> firstemp = (input)->{
		
		return input.stream().filter(e->e.getDepartname().equals("HR")).findFirst().orElse(null);
	};
	//Find the first employee whose name is "Annie".
	
	Function<List<Employees>,Employees>annie = (input)->{
		
		return input.stream().filter(s->s.getName().equals("Annie")).findFirst().orElse(null);
	};

	//Sort employees by salary in ascending order.
	Function<List<Employees>,List<Employees>>sortemp = (input)->{
		
		return input.stream().sorted(Comparator.comparing(Employees::getSalary)).collect(Collectors.toList());
	};
	
	//Sort employees by salary from highest to lowest.
	
	Function<List<Employees>,List<Employees>>descemp = (input)->{
		
		return input.stream().sorted(Comparator.comparing(Employees::getSalary,Comparator.reverseOrder())).collect(Collectors.toList());
	};
	
	Function<List<Employees>,List<Employees>>sortAlph = (input)->{
		
		return input.stream().sorted(Comparator.comparing(Employees::getName)).collect(Collectors.toList());
	};
	
	Function<List<Employees>,List<Employees>>sortId = (input)->{
		
		return input.stream().sorted(Comparator.comparing(Employees::getId)).collect(Collectors.toList());
	};
	
	//Find the employee receiving the highest salary.
	
	Function<List<Employees>,Employees>highest = (input)->{
		
		return input.stream().max(Comparator.comparing(Employees::getSalary)).orElse(null);
	};
	
	//Find the employee receiving the lowest salary.
	
	Function<List<Employees>,Employees>lowest = (input)->{
		
		return input.stream().min(Comparator.comparing(Employees::getSalary)).orElse(null);
	};
	
	//Find the second-highest-paid employee.
	
	Function<List<Employees>,Employees>secondHighest = (input)->{
		
		return input.stream().sorted(Comparator.comparing(Employees::getSalary,Comparator.reverseOrder())).skip(1).findFirst().orElse(null);
	};

	//Find the second-highest distinct salary from the employee list.
	Function<List<Employees>,Double> secondDistinct = (input)->{
		
		return input.stream().map(Employees::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null);
	};
	
	
	//Find the third-highest distinct salary.
	
	Function<List<Employees>,Double>thirdHighest = (input)->{
		
		return input.stream().map(Employees::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(2).limit(3).findFirst().orElse(null);
	};
	
	//Calculate the total salary of all employees.
	Function<List<Employees>,Double>sumemp = (input)->{
		
		return input.stream().mapToDouble(Employees::getSalary).sum();
	};
	
	//Calculate the average salary of all employees.
	
	Function<List<Employees>,Double> averageemp = (input)->{
		
		return input.stream().mapToDouble(Employees::getSalary).average().orElse(-1);
	};
	
	//Group all employees according to their department.
	Consumer<List<Employees>> consumer =  (input)->{
		
		Map<String, List<Employees>>groupmap = input.stream().collect(Collectors.groupingBy(Employees::getDepartname));
		for(Map.Entry entry: groupmap.entrySet()) {
			
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	};
	
	//Count how many employees work in each department.
	
	Consumer<List<Employees>>countdeptnoemp = (input)->{
		
		input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.counting())).entrySet().forEach(e->System.out.println(e.getKey()+":"+e.getValue()));
	};
	
	//Calculate the total salary paid by each department.
	Consumer<List<Employees>>totalsalbydept = (input)->{
		
		input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.summingDouble(Employees::getSalary))).entrySet().forEach(e->System.out.println(e.getKey()+":"+e.getValue()));
	};
	
	//Find the highest-paid employee from each department.
	
	Consumer<List<Employees>>highestPaidByDept = (input)->{
		
	
	input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.maxBy(Comparator.comparingDouble(Employees::getSalary)))).entrySet().forEach(System.out::println);
	};
	
	//Find the lowest-paid employee from each department.
	
	Consumer<List<Employees>>lowestDept = (input)->{
		
		input.stream().collect(Collectors.toMap(Employees::getDepartname, e->e,BinaryOperator.maxBy(Comparator.comparingDouble(Employees::getSalary)))).entrySet().forEach(e->System.out.println(e.getKey()+":"+e.getValue()));
	};
	//Find departments having more than two employees.
	public Map<String,Long> havingMoreThen2Emp(List<Employees>emp){
		
		return emp.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.counting())).entrySet().stream().filter(e->e.getValue()>2).collect(Collectors.toMap(Map.Entry:: getKey, Map.Entry::getValue));
	}
	
	//Find all duplicate employee names from the list.
	
	Function<List<Employees>,List<String>>duplicate = (input)->{
		
		return input.stream().collect(Collectors.groupingBy(Employees::getName,Collectors.counting())).entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList());
		/*
            // Step 1: Group by name and count occurrences
            .collect(Collectors.groupingBy(Employees::getName, Collectors.counting()))
            
            // Step 2: Turn the Map into a Stream of Entries
            .entrySet().stream()
            
            // Step 3: Filter to keep only duplicates (count > 1)
            .filter(entry -> entry.getValue() > 1)
            
            // Step 4: THE FIX! Extract just the Name (the Key) from the Entry
            .map(Map.Entry::getKey) 
            
            // Step 5: Collect the Strings into a List
            .collect(Collectors.toList());
}
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	};

	//Find employee names that occur exactly once.
	
	Function<List<Employees>,List<String>>unique = (input)->{
		
		return input.stream().collect(Collectors.groupingBy(Employees::getName,Collectors.counting())).entrySet().stream().filter(e->e.getValue()==1).map(Map.Entry::getKey).collect(Collectors.toList());
	};
	
	//Find the number of occurrences of every employee name.
	
	Function<List<Employees>,Long>occurences = (input)->{
		
		 input.stream().collect(Collectors.groupingBy(Employees::getName,Collectors.counting())).entrySet().forEach(e->System.out.println(e.getKey()+":"+e.getValue()));
		 return null;
	};
	//Partition employees into two groups: salary greater than 2000 and salary less than or equal to 2000.
	
	Consumer<List<Employees>>partitioning = (input)->{
		
		input.stream().collect(Collectors.partitioningBy(e->e.getSalary()>2000)).forEach((isHigher,emp)->{
			System.out.println(isHigher+":"+emp);
			
		});
	};
	
	//Create a comma-separated string containing all employee names.
	Function<List<Employees>,String> commaseperated = (input)->{
		
		return input.stream().map(Employees::getName).collect(Collectors.joining(","));
	};
	//Find the department having the highest total salary.
	
	Function<List<Employees>,String> highestDeptSal = (input)->{
		
		return input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.summingDouble(Employees::getSalary))).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No Department Found");
	};
	//Find the department having the lowest total salary.
	
	Function<List<Employees>,String>lowestDeptSal = (input)->{
		
		return input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.summingDouble(Employees::getSalary))).entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No Department Found");
	};

	//Find the department having the highest average salary.
	
	Function<List<Employees>,String> highestAvgSalDept = (input)->{
		
		return input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.averagingDouble(Employees::getSalary))).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("Department not found");
	};
	
	//Find the department containing the maximum number of employees.
	
	Function<List<Employees>,String>findDeptMaxNoEmp = (input)->{
		
		return input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No department found");
	};
	
	//Find the department containing the minimum number of employees.
	Function<List<Employees>,String> minnoofemp = (input)->{
		
		return input.stream().collect(Collectors.groupingBy(Employees::getDepartname,Collectors.counting())).entrySet().stream().min(Map.Entry.comparingByKey()).map(Map.Entry::getKey).orElse("Department not found");
	};
	
	//Find the second-highest-paid employee from each department.
	
	Function<List<Employees>,Employees> shighest = (input)->{
		
		input.stream().sorted(Employees::getSalary,Comparator.reverseOrder()).skip(1).limit(2);
	};
	
	public static void main(String[]args) {
		
		StreamAPI api = new StreamAPI();
		
		List<Employees>emp = Arrays.asList(new Employees(1l,"Jhon","IT",3000.00),new Employees(2,"Annie","HR",1800.00),new Employees(3l,"Bob","IT",2500.00));
		
		List<Employees> result = null;
		result = api.findEmployeeSalaryGreather2000(emp);
		System.out.println(result);
	    List<String>namesResult = api.names.apply(emp);
	    System.out.println(namesResult);
	    result = api.empitdpt.apply(emp);
	    System.out.println(result);
	    result = api.prefix.apply(emp);
	    System.out.println(result);
	    List<Employees> employees = Arrays.asList(new Employees(1l,"Annie","IT",3000.00),new Employees(5l,"Alex","HR",1800.00),new Employees(2l,"Annie","HR",1800.00),new Employees(3l,"Emma","IT",3500.00),new Employees(4l,"Annie","IT",3000.00));
	    namesResult = api.uppercase.apply(emp);
	    System.out.println(namesResult);
	    namesResult = api.empname.apply(emp);
	    System.out.println(namesResult);
	    namesResult = api.distinct.apply(employees);
	    System.out.println(namesResult);
	    System.out.println(api.salaryList.apply(employees));
	    System.out.println(api.countemp.apply(employees));
	    System.out.println(api.check.test(employees));
	    System.out.println(api.allcheck.test(employees));
	    System.out.println(api.firstemp.apply(employees));
	    System.out.println(api.annie.apply(employees));
	    System.out.println(api.sortemp.apply(employees));
	    System.out.println(api.descemp.apply(employees));
	    List<Employees> names = Arrays.asList(new Employees(1l,"John","IT",3000.00),new Employees(5l,"Annie","HR",100.00),new Employees(2l,"David","HR",9800.00),new Employees(3l,"Bob","IT",3500.00),new Employees(4l,"Annie","IT",3000.00));
	    System.out.println(api.sortAlph.apply(names));
	    System.out.println(api.sortId.apply(names));
	    List<Employees> sal = Arrays.asList(new Employees(1l,"John","IT",3000.00),new Employees(5l,"Annie","HR",1800.00),new Employees(2l,"David","HR",1800.00),new Employees(3l,"Bob","IT",3500.00),new Employees(4l,"Annie","IT",3000.00));
	    System.out.println(api.highest.apply(sal));
	    System.out.println(api.lowest.apply(sal));
	    System.out.println(api.secondHighest.apply(sal));
	    System.out.println(api.secondDistinct.apply(sal));
	    System.out.println(api.thirdHighest.apply(sal));
	    System.out.println(api.sumemp.apply(sal));
	    System.out.println(api.averageemp.apply(sal));
	    
	    api.consumer.accept(names);
	    api.countdeptnoemp.accept(names);
	    api.totalsalbydept.accept(names);
	    api.highestPaidByDept.accept(names);
	    api.lowestDept.accept(names);
	    
	    Map<String,Long>havingmorethen2 = api.havingMoreThen2Emp(names);
	    for(Map.Entry entry: havingmorethen2.entrySet()) {
	    	System.out.println(entry.getKey()+":"+entry.getValue());
	    }
//	    api.duplicate.apply(names).forEach(System.out::println);
	    api.unique.apply(names).forEach(System.out::println);
	    api.occurences.apply(names);
	    api.partitioning.accept(names);
	    System.out.println(api.commaseperated.apply(names));
//	    System.out.println(api.highestDeptSal.apply(names));
//	    System.out.println(api.lowestDeptSal.apply(names));
	    System.out.println(api.highestAvgSalDept.apply(names));
	    System.out.println(api.findDeptMaxNoEmp.apply(names));
	    System.out.println(api.minnoofemp.apply(names));

	}
}
