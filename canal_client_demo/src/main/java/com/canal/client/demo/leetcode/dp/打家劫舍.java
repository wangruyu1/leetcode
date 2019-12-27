package com.canal.client.demo.leetcode.dp;

class 打家劫舍 {

    public static void main(String[] args) {
        打家劫舍 solution = new 打家劫舍();
        System.out.println(solution.rob(new int[]{2, 7, 9, 3, 1}));
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] curMax = new int[nums.length];
        curMax[0] = nums[0];
        curMax[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            curMax[i] = Math.max(curMax[i - 1], curMax[i - 2] + nums[i]);
        }

        return curMax[nums.length - 1];
    }
}