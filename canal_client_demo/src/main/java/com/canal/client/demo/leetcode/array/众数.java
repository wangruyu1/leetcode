package com.canal.client.demo.leetcode.array;

import java.util.Arrays;

class 众数 {
    public static void main(String[] args) {
        众数 solution = new 众数();
        solution.majorityElement(new int[]{3, 2, 3});
    }

    public int majorityElement(int[] nums) {
        int group = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count <= 0) {
                group = nums[i];
                count = 1;
                continue;
            }
            if (nums[i] == group) {
                count++;
            } else {
                count--;
            }
        }

        return group;
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }
}