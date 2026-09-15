package com.code.main.java_8;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


//custom functional interface
@FunctionalInterface
interface FunDemo{
	
	int sum(int a, int b);
	
}


interface Java8{
	
	//java 8 on ward we can write static method inside interface to make utility level requirement 
	//because of in interface does not need to create constructor,block,or object directly using interface users can access it.
	
	public static String sayHello() {
		return "Hello";
	}
}
class Demo implements Java8{
	
	
	static int add(int a, int b) {
		return a+b;
	}
}


public class Features {


	public static void main(String[]args) {
		
		
		//custom functional interface
		FunDemo sum = (a,b)->a+b;
		int result = sum.sum(10,20);
		System.out.print(result);
		
		Predicate<Integer> isPrime = (nums)->nums%2!=0;
		
		Boolean res = isPrime.test(11);
		System.out.println(res);
		
		//method reference  removed the lambda expression code, but it used only for static method
		FunDemo methodRef = Demo::add;
		System.out.println(methodRef.sum(1, 3));
		
		System.out.println(Java8.sayHello());
		
		Demo d = new Demo();
		//d.sayHello();//static method inheritance is not applicable 
		
		
		//predicate is a functional interface which accept only one argument and it return boolean value
		
		java.util.function.Predicate<String> string = (input)->input.length()>10;
		res = string.test("Hello word");
		System.out.println(res);
		
		
		//Function is predefined functional interface which accept 1 argument and it return any type of object.
		
		java.util.function.Function<Integer, Boolean> even_odd = (a)->a%2==0;
		
		System.out.println(even_odd.apply(10));
		System.out.println(even_odd.apply(11));
		
		//Consumer is functional interface which accept one argument and it will not return anything.
		
		java.util.function.Consumer<Integer> consumer = (nums)->{
			
			for(int n = nums; n<=10;n++)
			System.out.println(n+"*"+n+":"+n*n);
		};
		
		consumer.accept(2);
		
		
		//Supplier is a functional interface which return object does not take as input
		java.util.function.Supplier<List<Integer>> prices = ()->{
			
			List<Integer>mrp = new ArrayList<>();
			Random random = new Random();
			
			for(int index = 0; index<=10; index++) {
				
				mrp.add(random.nextInt(900)+100);
			}
			return mrp;
		};
		
		System.out.println(prices.get());
		
		
		java.util.function.BiPredicate<Integer,Integer> biPredicate = (a,b)->a==b;
		biPredicate.test(10, 10);
		
		
	}
}
