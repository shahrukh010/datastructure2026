package com.code.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;

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
	
	
	
	
	public static void main(String[]args) {
		
		StreamAPI api = new StreamAPI();
		
		List<Employees>emp = Arrays.asList(new Employees(1l,"Jhon","IT",3000.00),new Employees(2,"Annie","HR",1800.00),new Employees(3l,"Bob","IT",2500.00));
		
		List<Employees> result = null;
		result = api.findEmployeeSalaryGreather2000(emp);
		System.out.println(result);
	    List<String>namesResult = api.names.apply(emp);
	    System.out.println(namesResult);

	}
}
