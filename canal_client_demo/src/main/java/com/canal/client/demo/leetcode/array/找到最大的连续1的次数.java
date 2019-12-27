package com.canal.client.demo.leetcode.array;

class 找到最大的连续1的次数 {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int max = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                max = Math.max(max, cur);
                cur = 0;
            } else {
                cur++;
            }
        }
        max = Math.max(max, cur);
        return max;
    }
}