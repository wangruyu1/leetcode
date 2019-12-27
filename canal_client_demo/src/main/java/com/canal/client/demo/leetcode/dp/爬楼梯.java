package com.canal.client.demo.leetcode.dp;

class 爬楼梯 {

    public static void main(String[] args) {
        爬楼梯 爬楼梯 = new 爬楼梯();
        System.out.println(爬楼梯.climbStairs(2));
        System.out.println(爬楼梯.climbStairs(5));
    }

    public int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int dp[] = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public int climbStairs2(int n) {
        return climb(0, n);
    }

    public int climb(int cur, int n) {
        if (cur > n) {
            return 0;
        }
        if (cur == n) {
            return 1;
        }
        return climb(cur + 1, n) + climb(cur + 2, n);
    }
}