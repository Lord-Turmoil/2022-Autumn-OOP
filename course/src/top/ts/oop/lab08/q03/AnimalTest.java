package top.ts.oop.lab08.q03;

import java.util.List;
import java.util.ArrayList;

//Animal.java
class Animal {
	public Animal(){
		System.out.println("I am an animal");
	}
}

//Dog.java
class Dog extends Animal{
	public Dog(){
		System.out.println("I am a dog");
	}
}

//AnimalTest1.java
public class AnimalTest {
	public void testDemo(List<?> s){
		for(Object obj:s){
			System.out.println("My type is "+obj.getClass().getName());
		}
	}
	public static void main(String[] args){
		AnimalTest test=new AnimalTest();
		Dog dog=new Dog();
		Animal animal=new Animal();
		List<Animal> s=new ArrayList<Animal>();
		s.add(dog);
		s.add(animal);
		test.testDemo(s);
	}
}