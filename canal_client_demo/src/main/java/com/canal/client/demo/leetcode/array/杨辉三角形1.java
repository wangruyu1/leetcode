package com.canal.client.demo.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class 杨辉三角形1 {

    public static void main(String[] args) {
        杨辉三角形1 solution = new 杨辉三角形1();
        solution.generate(5);
    }

    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>(numRows);
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        if (numRows == 1) {
            return result;
        }
        List<Integer> second = new ArrayList<>();
        second.add(1);
        second.add(1);
        result.add(second);
        if (numRows == 2) {
            return result;
        }
        for (int i = 2; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(result.get(i - 1).size() + 1);
            for (int j = 0; j < result.get(i - 1).size() + 1; j++) {
                if (j == 0 || j == result.get(i - 1).size()) {
                    list.add(1);
                } else {
                    list.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(list);
        }

        return result;
    }
}