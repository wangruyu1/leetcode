package com.canal.client.demo.leetcode.array;

class 删除有序数组重复元素26 {

    public static void main(String[] args) {
        删除有序数组重复元素26 solution = new 删除有序数组重复元素26();
        System.out.println(solution.removeDuplicates(new int[]{1, 1, 2}));
    }

    public int removeDuplicates(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j++];
            } else {
                j++;
            }
        }
        return i + 1;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int pre = nums[0];
        int removeNum = 0;
        int i = 1;
        int j = 1;
        while (j++ < nums.length) {
            if (nums[i] == pre) {
                //移动后面元素到前面
                if (i < nums.length) {
                    System.arraycopy(nums, i, nums, i - 1, nums.length - i - removeNum);
                    removeNum++;
                }
            } else {
                pre = nums[i++];
            }
        }

        return nums.length - removeNum;
    }
}