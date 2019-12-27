package com.canal.client.demo.leetcode.dp;

class 阶梯最小花费 {
    public static void main(String[] args) {
        阶梯最小花费 solution = new 阶梯最小花费();
        System.out.println(solution.minCostClimbingStairs(new int[]{0, 1, 1, 0}));
    }

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length < 1) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[1], cost[0]);
        }
        int[] min = new int[cost.length + 1];
        min[0] = cost[0];
        min[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            min[i] = Math.min(min[i - 1], min[i - 2]) + cost[i];
        }
        min[min.length - 1] = Math.min(min[min.length - 2], min[min.length - 3]);
//        System.out.println(Arrays.toString(min));
        return min[min.length - 1];
    }
}