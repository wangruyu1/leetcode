package com.canal.client.demo.leetcode.array;

class 数组加一66 {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] += 1;
        int cur = 0;

        for (int i = digits.length - 1; i >= 0; i--) {
            int total = digits[i] + cur;
            digits[i] = total % 10;
            cur = total / 10;
        }
        if (cur == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        }

        return digits;
    }
}