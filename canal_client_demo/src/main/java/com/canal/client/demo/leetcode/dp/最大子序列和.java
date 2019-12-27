package com.canal.client.demo.leetcode.dp;

class 最大子序列和 {

    public static void main(String[] args) {
        最大子序列和 最大子序列和 = new 最大子序列和();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(最大子序列和.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int result = nums[0];
        int curMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = curMax > 0 ? curMax + nums[i] : nums[i];
            result = Math.max(result, curMax);
        }
        return result;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int result = nums[0];
        int curMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(nums[i], nums[i] + curMax);
            result = Math.max(result, curMax);
        }
        return result;
    }
}