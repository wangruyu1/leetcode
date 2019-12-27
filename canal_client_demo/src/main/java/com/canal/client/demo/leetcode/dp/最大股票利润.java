package com.canal.client.demo.leetcode.dp;

class 最大股票利润 {

    public static void main(String[] args) {
        最大股票利润 最大股票利润 = new 最大股票利润();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(最大股票利润.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] min = new int[prices.length];
        min[0] = prices[0];
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            min[i] = Math.min(min[i - 1], prices[i]);
            result = Math.max(result, prices[i] - min[i]);
        }
        return result;
    }
}