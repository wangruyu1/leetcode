package com.canal.client.demo.leetcode.array;

import java.util.Arrays;

class 找到数组对中min之后的最大值 {
    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length - 1; i += 2) {
            result += Math.min(nums[i], nums[i + 1]);
        }

        return result;
    }
}