package com.Interview;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		Animal ac = new Dog();
		Animal ct = new Cat();
		Toy car = new Toy();
		
		List<Animal> newList = new ArrayList<>();
		newList.add(ac);
		newList.add(ct);
		newList.add(car);
		
		playSounds(newList);
	}

	private static void playSounds(List<Animal> newList) {
		for(Animal c:newList) {
			c.makeSounds();
			//c.poop();
		}
			
	}
}
