package com.canal.client.demo.leetcode.array;

class 搜索插入位置35 {


    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}