package com.hacker_rank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by Apophany on 29/01/2017.
 */
public class DesignerPdfViewer {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] heights = new int[26];

        for (int i = 0; i < 26; i++) {
            heights[i] = scan.nextInt();
        }
        String word = scan.next();
        scan.close();

        int rect_height = 0;

        for (int j = 0; j < word.length(); j++) {
            rect_height = Math.max(
                    rect_height,
                    heights[(int) word.charAt(j) - (int) 'a']
            );
        }

        int ans = word.length() * rect_height;
        System.out.println(ans);
    }
}
