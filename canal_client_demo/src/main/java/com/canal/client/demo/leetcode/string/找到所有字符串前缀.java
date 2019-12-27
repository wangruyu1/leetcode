package com.canal.client.demo.leetcode.string;

class 找到所有字符串前缀 {

    public static void main(String[] args) {

        System.out.println(longestCommonPrefix3(new String[]{"12345", "12345"}));
    }

    public static String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        String preffix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String cur = strs[i];

            int minLen = Math.min(preffix.length(), cur.length());
            if (minLen < 1) {
                return "";
            }
            int j = 0;
            for (; j < minLen; j++) {
                if (preffix.charAt(j) != cur.charAt(j)) {
                    break;
                }
            }

        }
        return preffix;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        String preffix = strs[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < strs.length; i++) {
            sb.setLength(0);
            String cur = strs[i];
            int minLen = Math.min(preffix.length(), cur.length());
            if (minLen < 1) {
                return "";
            }
            for (int j = 0; j < minLen; j++) {
                char preChar = preffix.charAt(j);
                if (preChar == cur.charAt(j)) {
                    sb.append(preChar);
                    if (j == minLen - 1) {
                        preffix = sb.toString();
                        break;
                    }
                } else {
                    preffix = sb.toString();
                    break;
                }
            }
        }
        return preffix;
    }

}