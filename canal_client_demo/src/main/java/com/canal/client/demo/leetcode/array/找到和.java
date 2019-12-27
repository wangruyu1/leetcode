package com.canal.client.demo.leetcode.array;

import java.util.HashMap;
import java.util.Map;

class 找到和 {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] numbers, int target) {

        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum < target) {
                low++;
            } else if (sum > target) {
                high--;
            } else {
                return new int[]{low + 1, high + 1};
            }
        }

        return null;
    }

    public int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> anotherNumMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            Integer index = anotherNumMap.get(numbers[i]);
            if (index != null) {
                return new int[]{index + 1, i + 1};
            }
            anotherNumMap.put(target - numbers[i], i);
        }
        return null;
    }
}