package com.testTurotials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecondMostChar {

	public void returnCharacter(String val) {
		Map<String, Integer> charMap = new HashMap<String, Integer>();
		for(char chars:val.toCharArray())
		{
			String ch = String.valueOf(chars).toLowerCase();
			if(charMap.containsKey(ch))
				charMap.put(ch, charMap.get(ch)+1);
			else
				charMap.put(ch, 1);
		}

		System.out.println(charMap);

		int max = 0;
		int secMax = 0;
		String reqChar="";
		List<String> charList = new ArrayList<>();
		for(Map.Entry<String, Integer> tempMap: charMap.entrySet()) {
			if(max <=tempMap.getValue()) {
				secMax = max;
				max = tempMap.getValue();
				reqChar = tempMap.getKey();
			}
		}

		System.out.println(secMax + ":" +reqChar);
	}
	public static void main(String[] args) {
		String val = "DevLabsAlliance";
		SecondMostChar smc = new SecondMostChar();
		smc.returnCharacter(val);
		smc.reverseString(val);

		String str = "Learn, Lead and Succeed in DevLabsAlliance";

		String toBeReplaced = "in";

		String toReplacedWith = "with";

		System.out.println(str.replace(toBeReplaced, toReplacedWith));

		int[] arr={28,3,15,9,17,4,23,2};
		Arrays.sort(arr);
		System.out.println(arr[arr.length-1]);

		String strVal = "Training Training course and certification course in Devlabs Alliance";
		String[] tempArray = strVal.split(" ");
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		for(String temp:tempArray) {
			if(wordMap.containsKey(temp))
				wordMap.put(temp, wordMap.get(temp)+1);
			else
				wordMap.put(temp, 1);
		}

		System.out.println(wordMap);

		smc.findDuplicates(val);
		smc.moveAllZeros();
		smc.printPrime();
		smc.reverse();
		smc.removeDupFromArrayList();
	}

	public void reverseString(String val) {
		StringBuilder sb = new StringBuilder();
		for(int i=val.length()-1;i>=0;i--) {
			sb.append(val.charAt(i));
		}

		System.out.println(sb.toString());
	}

	public void findDuplicates(String val) {
		Set<String> dupList = new HashSet<>();

		for(int i=0;i<val.length();i++) {
			String ch = String.valueOf(val.charAt(i)).toLowerCase();
			for(int j=i+1;j<val.length();j++) {
				String ch2 = String.valueOf(val.charAt(j)).toLowerCase();
				if(ch.equals(ch2)) {
					dupList.add(ch);
					break;
				}
			}
		}
		System.out.println(dupList);
		
		List<String> list = Arrays.asList(val.toLowerCase().split(""));
		List<String> newList = new ArrayList<>();
		for(String ch:list) {
			if(!newList.contains(ch))
				newList.add(ch);
		}
		
		System.out.println(newList);
	}

	public void moveAllZeros() {
		int arr[] = { 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9 };
		int len = arr.length;
		List<Integer> tempList = new ArrayList<>();
		for(int i=0;i<len;i++) {
			if(arr[i]!=0)
				tempList.add(Integer.valueOf(arr[i]));
		}
		while(tempList.size() < len) {
			tempList.add(0);
		}
		System.out.println(tempList);

	}

	public void printPrime() {
		int num = 25;
		for(int i=2;i<num;i++) {
			if(isPrime(i))
				System.out.println(i);
		}
	}

	public static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false; // number is divisible so its not prime
			}
		}
		return true; // number is prime now
	}
	
	public void reverse() {
		int num = 3689;
		int revNum = 0;
		while(num >0) {
			revNum = revNum *10 + num%10;
			num = num/10;
		}
		
		System.out.println(revNum);
	}
	
	public void removeDupFromArrayList() {
		List<String> list = Arrays.asList("Dev", "Labs", "Alliance", "Dev", "Labs", "Dev", "DevLabsAlliance");
		List<String> newList = new ArrayList<>();
		for(String str:list) {
			if(!newList.contains(str))
				newList.add(str);
		}
		
		System.out.println(newList);
	}
}
