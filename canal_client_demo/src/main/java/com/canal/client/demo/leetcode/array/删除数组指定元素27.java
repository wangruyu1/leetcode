package com.canal.client.demo.leetcode.array;

class 删除数组指定元素27 {


    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] == val) {
                j++;
                continue;
            }
            nums[i++] = nums[j++];
        }
        return i;
    }
}