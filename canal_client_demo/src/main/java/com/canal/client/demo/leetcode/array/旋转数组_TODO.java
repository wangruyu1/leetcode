package com.canal.client.demo.leetcode.array;

class 旋转数组_TODO {


    public void rotate(int[] nums, int k) {
        int mod = k % nums.length;
        if (mod == 0) {
            return;
        }

        if (nums.length % mod == 0) {
            for (int j = 0; j < mod; j++) {
                int preIndex = j;
                int preNum = nums[j];
                for (int i = 1; i <= nums.length / mod; i++) {
                    int target = (preIndex + mod) % nums.length;
                    int tmp = nums[target];
                    nums[target] = preNum;
                    preIndex = target;
                    preNum = tmp;
                }
            }
        } else {
            int preIndex = 0;
            int preNum = nums[preIndex];
            for (int i = 0; i < nums.length; i++) {
                int target = (preIndex + mod) % nums.length;
                int tmp = nums[target];
                nums[target] = preNum;
                preIndex = target;
                preNum = tmp;
            }
        }


    }


    public void rotate2(int[] nums, int k) {
        int mod = k % nums.length;
        for (int i = 0; i < mod; i++) {
            int x = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = x;
        }
    }
}