package com.canal.client.demo.leetcode.array;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

class 找到数组中不存在的数 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -1 * Math.abs(nums[index]);
        }
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] > 0)) {
                result.add(i + 1);
            }
        }

        return result;

    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        BitSet bitSet = new BitSet(nums.length);
        for (int x : nums) {
            bitSet.set(x);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!bitSet.get(i)) {
                result.add(i);
            }
        }
        return result;
    }
}