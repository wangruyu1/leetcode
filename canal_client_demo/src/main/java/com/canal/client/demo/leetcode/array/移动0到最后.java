package com.canal.client.demo.leetcode.array;

class 移动0到最后 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }
        for (int j = nums.length - 1; j >= i; j--) {
            nums[j] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int removed = 0;
        int size = nums.length;
        int i = 0;
        int k = 0;
        int last = size - 1;
        while (i < size - 1) {
            i++;
            if (nums[k] == 0) {
                System.arraycopy(nums, k + 1, nums, k, size - 1 - k - removed);
                nums[last--] = 0;
            } else {
                k++;
            }
        }
    }
}