package com.advisor.trip.entity.user;

import java.util.Arrays;

class bubblesort{

	public static void sort(int array[]){
		boolean flag = true;
		int lastExchangeIndex = 0;
		int temp=0;
		int sortedBoard = array.length-1;
		for(int i=0; i<array.length;i++){
			flag = true;
			for(int j=0;j<sortedBoard;j++){
				if(array[j]>array[j+1]){
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					lastExchangeIndex = j;
					flag = false;
				}
			}
			sortedBoard = lastExchangeIndex;
			if (flag) {
				break;
			}
		}
	}

	public static void main(){
		int[] array = new int[]{3,2,1,5,6,7,8};
		sort(array);
		System.out.println(Arrays.toString(array));
	}


}
