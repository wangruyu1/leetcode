package com.canal.client.demo.leetcode.linkedlist;

class 删除形成子序列 {
    public static void main(String[] aegs) {
        删除形成子序列 solution = new 删除形成子序列();
        System.out.println(solution.isSubsequence("abc", "ahbgdc"));
    }

    public boolean isSubsequence(String s, String t) {

        if (s.length() < 1) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }
        int j = 0;
        char[] tchars = t.toCharArray();
        char[] schars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (; j < tchars.length; ) {
                if (tchars[j++] == schars[i]) {
                    if (i == schars.length - 1) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }
}