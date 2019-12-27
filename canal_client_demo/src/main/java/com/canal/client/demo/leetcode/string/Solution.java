package com.canal.client.demo.leetcode.string;

import java.util.Stack;

class Solution {
    //给定一个只包括 '('，')'，'{'，'}'，'['，']'
    public boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        if ((s.length() & 0x00000001) == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            if ('(' == curChar || '{' == curChar || '[' == curChar) {
                stack.push(curChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character preChar = stack.pop();
                if ((preChar == '(' && curChar == ')') || (preChar == '{' && curChar == '}') || (preChar == '[' && curChar == ']')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}