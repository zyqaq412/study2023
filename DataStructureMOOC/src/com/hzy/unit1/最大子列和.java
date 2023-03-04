package com.hzy.unit1;

/**
 * @title: 最大子列和
 * @Author zxwyhzy
 * @Date: 2023/3/3 22:40
 * @Version 1.0
 */
public class 最大子列和 {

    // 慕课数据结构最大子列和
    public static void main(String[] args) {
        System.out.println(maxSubseqSum1(new int[]{1, 2, -6}, 3)); //3
        System.out.println(maxSubseqSum2(new int[]{1, 2, -6}, 3)); //3
        System.out.println(maxSubseqSum4(new int[]{1, 2, -6}, 3)); //3
    }

    /**
     * 第一种算法
     * 时间复杂度：3层for循环嵌套 T(n)=O(n3)
     * @param a 数组
     * @param n 数组长度
     * @return
     */
    public static int maxSubseqSum1(int[] a, int n) {
        int max =0;

        for (int i = 0; i < n; i++) { // i是子列左端
            for (int j = i; j < n; j++) { // j是子类右端
                int sum = 0;
                for (int k = i; k <= j; k++) { // k遍历子列
                    sum += a[k];
                }
                max = max>sum ? max: sum;
            }
        }
        return max;
    }
    /**
     * 第二种算法
     * 时间复杂度：2层for循环嵌套 T(n)=O(n2)
     * @param a 数组
     * @param n 数组长度
     * @return
     */
    public static int maxSubseqSum2(int[] a, int n) {
        int max =0;

        for (int i = 0; i < n; i++) { // i是子列左端
            int sum = 0;
            for (int j = i; j < n; j++) { // j是子类右端
                sum += a[j];
                // int sum = 0;
                // j每次加1时不需要k从头加，只需要再加一个a[j]
                /*for (int k = i; k <= j; k++) { // k遍历子列
                    sum += a[k];
                }*/
                max = max>sum ? max: sum;
            }
        }
        return max;
    }

    /**
     *  在线算法
     *  时间复杂度：2层for循环嵌套 T(n)=O(n)
     *
     * @param a
     * @param n
     * @return
     */
    public static int maxSubseqSum4(int[] a, int n){
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum +=a[i];
            if (sum > max) max = sum;
            if (sum < 0) sum = 0;
        }
        return max;
    }

}
