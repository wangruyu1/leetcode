package com.canal.client.demo.leetcode.string;

import java.util.HashMap;
import java.util.Map;

class 罗马字符转数字 {

    private static final Map<Character, Integer> numMap = new HashMap<>();
    private static final int[] CHAR_NUM = new int[500];

    static {
        numMap.put('I', 1);
        numMap.put('V', 5);
        numMap.put('X', 10);
        numMap.put('L', 50);
        numMap.put('C', 100);
        numMap.put('D', 500);
        numMap.put('M', 1000);

        CHAR_NUM[(int) ('I')] = 1;
        CHAR_NUM[(int) ('V')] = 5;
        CHAR_NUM[(int) ('X')] = 10;
        CHAR_NUM[(int) ('L')] = 50;
        CHAR_NUM[(int) ('C')] = 100;
        CHAR_NUM[(int) ('D')] = 500;
        CHAR_NUM[(int) ('M')] = 1000;
    }

    //    I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
//    X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
//    C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
    public int romanToInt(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int sum = 0;
        int i = s.length() - 1;
        for (; i > 0; i--) {
            char cur = s.charAt(i);
            char pre = s.charAt(i - 1);
            sum += CHAR_NUM[(int) (cur)];
            if ((cur == 'V' || cur == 'X') && pre == 'I') {
                sum -= CHAR_NUM[(int) ('I')];
                i--;
            } else if ((cur == 'L' || cur == 'C') && pre == 'X') {
                sum -= CHAR_NUM[(int) ('X')];
                i--;
            } else if ((cur == 'D' || cur == 'M') && pre == 'C') {
                sum -= CHAR_NUM[(int) ('C')];
                i--;
            }
        }
        if (i == 0) {
            sum += numMap.get(s.charAt(0));
        }

        return sum;
    }

    public int romanToInt2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int sum = 0;
        int i = s.length() - 1;
        for (; i > 0; i--) {
            char cur = s.charAt(i);
            char pre = s.charAt(i - 1);
            if ((cur == 'V' || cur == 'X') && pre == 'I') {
                sum += numMap.get(cur) - numMap.get('I');
                i--;
            } else if ((cur == 'L' || cur == 'C') && pre == 'X') {
                sum += numMap.get(cur) - numMap.get('X');
                i--;
            } else if ((cur == 'D' || cur == 'M') && pre == 'C') {
                sum += numMap.get(cur) - numMap.get('C');
                i--;
            } else {
                sum += numMap.get(cur);
            }
        }
        if (i == 0) {
            sum += numMap.get(s.charAt(0));
        }

        return sum;
    }
}