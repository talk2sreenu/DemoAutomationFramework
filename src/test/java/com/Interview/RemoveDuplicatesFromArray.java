package com.Interview;

import java.util.Arrays;

public class RemoveDuplicatesFromArray {
	
	public int removeDuplicates(int[] nums) {
		int k=0;
		for(int i=0;i<nums.length;i++) {
			int found = 0;
			for(int j=i+1;j<nums.length;j++) {
				if(nums[i]==nums[j]) {
					found = 1;
					break;
				}
			}
			if(found != 1) {
				k++;
			}
		}	
		return k;
    }
	
	public int missingNumber(int[] nums) {
		Arrays.sort(nums);
		for(int i=0;i<nums.length;i++) {
			if(nums[i]!=i)
				return i;
		}	
		return 0;
    }
	
	
	public static void main(String[] args) {
		RemoveDuplicatesFromArray rdc = new RemoveDuplicatesFromArray();
		int val[] = {0,0,1,1,1,2,2,3,3,4};
		int nums[] = {3,0,1};
		System.out.println(rdc.removeDuplicates(val));
		System.out.println(rdc.missingNumber(nums));
	}
}
