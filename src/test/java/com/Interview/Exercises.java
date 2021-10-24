package com.Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Exercises {
	public static void main(String[] args) {
		int a[]={1,2,5,6,3,2};
		List<Integer> evenNum = new ArrayList<>();
		for(int num:a) {
			if(num%2 == 0) evenNum.add(num);
		}

		System.out.println(evenNum);

		Set<Integer> setNum = new HashSet<>(evenNum);
		System.out.println(setNum);

		List<Integer> list = new ArrayList<>(setNum);
		System.out.println(list);

		Integer newArr[] = list.toArray(new Integer[list.size()]);

		int arrVal[] = new int[list.size()];
		for(int i=0;i<list.size();i++) arrVal[i] = list.get(i);

		Integer arr[] = {10,20,20,30,30,40,50,50};

		Set<Integer> dupVal = new LinkedHashSet<>(Arrays.asList(arr));
		System.out.println(dupVal);

		int arr2[] = {10,20,20,30,30,40,50,50};

		List<Integer> tempList = new ArrayList<>();
		Set<Integer> uniqueVals = new LinkedHashSet<>();
		
		for(int i=0;i<arr2.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr2[i] == arr2[j]) { 
					tempList.add(Integer.valueOf(arr2[i])); 
					break; 
				}
			}
		}

		System.out.println(tempList);
		
		for(int i=0;i<arr2.length;i++) {
			boolean found = false;
			for(int j=i+1;j<arr2.length;j++) {
				if(arr2[i]==arr2[j]) {
					found = true;
					i++;
					break;
				}
			}
			if(!found)
				uniqueVals.add(Integer.valueOf(arr2[i]));

		}	
		System.out.println(uniqueVals);
		
		//Set<Integer> tempSet = new LinkedHashSet<>();
		List<Integer> tempSet = new ArrayList<>();
		for(int temp:arr2) {
			if(!tempSet.contains(temp)) {
				tempSet.add(temp);
			}
		}
		
		System.out.println(tempSet);
	}
}
