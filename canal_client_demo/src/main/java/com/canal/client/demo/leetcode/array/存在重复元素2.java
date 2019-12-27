package com.canal.client.demo.leetcode.array;

import java.util.HashMap;
import java.util.Map;

class 存在重复元素2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>(nums.length / 2);
        for (int i = 0; i < nums.length; i++) {
            Integer num = nums[i];
            Integer x = map.get(num);
            if (x == null) {
                map.put(num, i);
            } else {
                if (i - x <= k) {
                    return true;
                }
                map.put(num, i);
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j - i > k) {
                    break;
                }
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}