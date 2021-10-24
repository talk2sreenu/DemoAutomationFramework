package com.Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class VowelCount {
	public int countVowels(String s) {
		List<String> srcString = Arrays.asList(s.split(""));
		List<String> vowels = new ArrayList<>();
		String charVal ="";
		Iterator<String> ir = srcString.iterator();
		while(ir.hasNext()) {
			charVal = ir.next();
			switch(charVal.toLowerCase()) {
			case "a":
			case "e":
			case "i":
			case "o":
			case "u":
				vowels.add(charVal);
			}
		}
		return vowels.size();
	}

	public static void main(String[] args) {
		String str = "This is my testing string for the a um";
		VowelCount vc = new VowelCount();
		System.out.println(vc.countVowels(str));
	}
}
