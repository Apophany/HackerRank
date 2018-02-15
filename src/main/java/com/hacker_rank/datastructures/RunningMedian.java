package com.hacker_rank.datastructures;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class RunningMedian {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        runningMedian(a);
    }

    private static void runningMedian(int[] arr) {
        final Queue<Integer> minHeap = new PriorityQueue<>();
        final Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (final int curr : arr) {
            if (minHeap.size() == 0 || curr <= minHeap.peek()) {
                maxHeap.offer(curr);
            }
            else if (maxHeap.size() == 0 || curr >= maxHeap.peek()) {
                minHeap.offer(curr);
            }

            rebalance(minHeap, maxHeap);

            if (minHeap.size() == maxHeap.size()) {
                System.out.printf("%.1f\n", (minHeap.peek() + maxHeap.peek()) / 2.0);
            } else if (minHeap.size() > maxHeap.size()) {
                System.out.printf("%.1f\n", Double.valueOf(minHeap.peek()));
            } else if (maxHeap.size() > minHeap.size()) {
                System.out.printf("%.1f\n", Double.valueOf(maxHeap.peek()));
            }
        }
    }

    private static void rebalance(Queue<Integer> first, Queue<Integer> second) {
        if(Math.abs(first.size() - second.size()) <= 1){
            return;
        }
        if (first.size() - second.size() >= 2) {
            second.offer(first.poll());
        }
        if(second.size() - first.size() >= 2) {
            first.offer(second.poll());
        }
        rebalance(first, second);
    }
}
