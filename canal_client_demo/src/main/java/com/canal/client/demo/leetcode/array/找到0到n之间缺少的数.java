package com.canal.client.demo.leetcode.array;

import java.util.Arrays;
import java.util.BitSet;

class 找到0到n之间缺少的数 {


    public int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i;
            result ^= nums[i];
        }

        return result;
    }

    public int missingNumber3(int[] nums) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < nums.length; i++) {
            bitSet.set(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!bitSet.get(i)) {
                return i;
            }
        }
        return nums.length;
    }

    public int missingNumber2(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return i;
    }
}