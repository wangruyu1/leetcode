package com.canal.client.demo.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class 杨辉三角形2 {
    public static void main(String[] args) {
        杨辉三角形2 solution = new 杨辉三角形2();
        System.out.println(solution.getRow(3));
    }

    public List<Integer> getRow(int rowIndex) {

        List<Integer> result = new ArrayList<>(rowIndex + 1);
        if (rowIndex < 0) {
            return Collections.emptyList();
        }
        result.add(1);
        if (rowIndex == 0) {
            return result;
        }
        result.add(1);
        if (rowIndex == 1) {
            return result;
        }
        for (int i = 2; i <= rowIndex; i++) {
            result.add(1);
            for (int j = i - 1; j > 0; j--) {
                result.set(j, result.get(j - 1) + result.get(j));
            }
        }

        return result;
    }

    public List<Integer> getRow2(int rowIndex) {

        List<Integer> result = new ArrayList<>(rowIndex + 1);
        if (rowIndex < 0) {
            return Collections.emptyList();
        }
        result.add(1);
        if (rowIndex == 0) {
            return result;
        }
        result.add(1);
        if (rowIndex == 1) {
            return result;
        }
        for (int i = 2; i <= rowIndex; i++) {
            int pre = 0;
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    result.set(j, 1);
                    pre = 1;
                } else if (j == i) {
                    result.add(1);
                } else {
                    int tmp = result.get(j);
                    result.set(j, pre + result.get(j));
                    pre = tmp;
                }
            }
        }

        return result;
    }
}