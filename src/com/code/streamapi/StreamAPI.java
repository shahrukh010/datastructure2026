package com.code.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
	    List<Employees> employees = Arrays.asList(new Employees(1l,"Annie","IT",3000.00),new Employees(2l,"Sara","HR",1800.00),new Employees(3l,"Emma","IT",3500.00),new Employees(4l,"Annie","IT",3000.00),new Employees(5l,"Sara","HR",1800.00));
	    namesResult = api.uppercase.apply(emp);
	    System.out.println(namesResult);
	    namesResult = api.empname.apply(emp);
	    System.out.println(namesResult);
	    namesResult = api.distinct.apply(employees);
	    System.out.println(namesResult);

	}
}
