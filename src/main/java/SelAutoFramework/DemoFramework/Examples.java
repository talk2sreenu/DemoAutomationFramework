package SelAutoFramework.DemoFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Examples {
	public static void main(String[] args) {
		//traverseArraList();
		//convertArraytoList();
		//testSets();
		sortArrays();
	}
	
	public static void traverseArraList() {
		List<Integer> testList = new ArrayList<>();
		testList.add(20);
		testList.add(40);
		testList.add(32);
		testList.add(12);
		
		Iterator<Integer> ir = testList.iterator();
		while(ir.hasNext())
			System.out.print(ir.next()+" ");
		System.out.println("");
		
		for(Integer a:testList)
			System.out.print(a + " ");
		System.out.println("");
		
		Collections.sort(testList);
		for(Integer a:testList)
			System.out.print(a + " ");
		System.out.println("");
		
		Collections.reverse(testList);
		//Collections.sort(testList, comp);
		for(Integer a:testList)
			System.out.print(a + " ");
		System.out.println("");
	}
	
	public static void convertArraytoList() {
		Integer arr[] = {3,5,6,7,8,45,67,34,23,19,40};
		
		List<Integer> arrList = Arrays.asList(arr);
		for(Integer a:arrList)
			System.out.print(a + " ");
		System.out.println("");
		Integer temp[] = arrList.toArray(new Integer[arrList.size()]);
		for(Integer a:temp)
			System.out.print(a + " ");
		
		if(arrList.contains(5))
			System.out.println("Exists");
		
		int maxVal = Collections.max(arrList);
		int minVal = Collections.min(arrList);
		System.out.println("Max Value : "+maxVal+" Min Value : "+ minVal);
		
		List<String> strList = new ArrayList<>();
		strList.add("Hello");
		Collections.addAll(strList,"how", "are", "you", "doing");
		for(String a:strList)
			System.out.print(a + " ");
		
		strList.remove(2);
		System.out.print(strList);		
	}
	
	public static void testSets() {
		
		Integer arr[] = {3,5,6,7,8,45,67,34,23,19,40};
		Set<Integer> mySet = new HashSet<>(Arrays.asList(arr));
		for(Integer a:mySet)
			System.out.print(a + " ");
		
		Collections.addAll(mySet, 32);

		System.out.println(mySet);
		List<Integer> newList = new ArrayList<>(mySet);
		Collections.sort(newList);
		System.out.println(newList);
		Collections.reverse(newList);
		System.out.println(newList);
		Collections.sort(newList, Collections.reverseOrder());
		System.out.println(newList);
	}
	
	public static void sortArrays() {
		Integer arr[] = {3,5,6,7,8,45,67,34,23,19,40};
		Arrays.sort(arr);
		for(Integer a:arr)
			System.out.print(a + " ");
		System.out.println("");
		Arrays.sort(arr, Collections.reverseOrder());
		for(Integer a:arr)
			System.out.print(a + " ");
		System.out.println("");
		Arrays.sort(arr, 3,7);
		for(Integer a:arr)
			System.out.print(a + " ");
	}
}
