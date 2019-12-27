package com.canal.client.demo.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class 存在重复元素 {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Set<Integer> existNum = new HashSet<>(nums.length / 2);
        for (int x : nums) {
            if (existNum.contains(x)) {
                return true;
            }
            existNum.add(x);
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }
}