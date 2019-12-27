package com.canal.client.demo.leetcode.array;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class 第三大的数 {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(5);
        set.add(3);
        set.add(5);
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();
        it = set.descendingIterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }

    }

    public int thirdMax(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : nums) {
            if (set.size() < 3) {
                set.add(x);
            } else if (set.contains(x)) {
                continue;
            } else {
                Iterator<Integer> it = set.iterator();
                Integer min = it.next();
                if (x > min) {
                    it.remove();
                    set.add(x);
                }
            }
        }
        if (set.size() < 3) {
            return set.descendingIterator().next();
        }
        return set.iterator().next();
    }

    public int thirdMax2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        long max[] = new long[]{Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        for (int i = 0; i < nums.length; i++) {
            boolean exist = false;
            int num = nums[i];
            for (int j = 0; j < 2; j++) {
                if (max[j] == num) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                continue;
            }
            for (int j = 0; j < 3; j++) {
                if (num > max[j]) {
                    for (int k = 2; k > j; k--) {
                        max[k] = max[k - 1];
                    }
                    max[j] = num;
                    break;
                }
            }
        }
        for (int j = 0; j < 3; j++) {
            if (max[j] == Long.MIN_VALUE) {
                return (int) max[0];
            }
        }
        return (int) max[2];

    }
}