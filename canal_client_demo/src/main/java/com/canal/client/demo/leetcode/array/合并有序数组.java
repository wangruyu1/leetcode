package com.canal.client.demo.leetcode.array;

class 合并有序数组 {
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (nums2[j] >= nums1[k]) {
                k++;
                i++;
            } else {
                //移动k以及后面的数据到后面
                System.arraycopy(nums1, k, nums1, k + 1, m - i);
                nums1[k++] = nums2[j++];
            }
        }
        for (; j < n; j++) {
            nums1[k++] = nums2[j];
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums3 = new int[m];
        System.arraycopy(nums1, 0, nums3, 0, m);
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (nums2[j] >= nums3[i]) {
                nums1[k++] = nums3[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }
        if (j < n) {
            System.arraycopy(nums2, j, nums1, k, n - j);
        }
        if (i < m) {
            System.arraycopy(nums3, i, nums1, k, m - i);
        }

    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 5, 6, 0, 0, 0};
        int[] nums2 = new int[]{1, 2, 3};
        合并有序数组 solution = new 合并有序数组();
        solution.merge(nums1, 3, nums2, 3);
    }
}