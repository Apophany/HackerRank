package com.hacker_rank.algorithms.implementation;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheTimeInWords {
    private static final Map<Integer, String> NUM_TO_WORDS;

    public static void main(String[] args) {
        final Scanner s = new Scanner(System.in);
        int hour = s.nextInt();
        int minute = s.nextInt();

        final StringBuilder builder = new StringBuilder();

        final String conjunction;
        final String numeral;
        if (minute <= 30) {
            conjunction = "past";
            numeral = NUM_TO_WORDS.get(minute);
        } else {
            conjunction = "to";
            numeral = NUM_TO_WORDS.get(30 - minute % 30);
            hour += 1;
        }

        String hourString = NUM_TO_WORDS.get(hour);
        String toReplace = hour == 1 ? " minute" : " minutes";
        hourString = hourString.replace(toReplace, "");

        if (minute == 0) {
            builder.append(hourString);
            builder.append(" ");
            builder.append("o' clock");
        } else {
            builder.append(numeral);
            builder.append(" ");
            builder.append(conjunction);
            builder.append(" ");
            builder.append(hourString);
        }
        System.out.println(builder.toString());
    }

    static {
        final Map<Integer, String> temp = new HashMap<>();
        temp.put(1, "one minute");
        temp.put(2, "two minutes");
        temp.put(3, "three minutes");
        temp.put(4, "four minutes");
        temp.put(5, "five minutes");
        temp.put(6, "six minutes");
        temp.put(7, "seven minutes");
        temp.put(8, "eight minutes");
        temp.put(9, "nine minutes");
        temp.put(10, "ten minutes");
        temp.put(11, "eleven minutes");
        temp.put(12, "twelve minutes");
        temp.put(13, "thirteen minutes");
        temp.put(14, "fourteen minutes");
        temp.put(15, "quarter");
        temp.put(16, "sixteen minutes");
        temp.put(17, "seventeen minutes");
        temp.put(18, "eighteen minutes");
        temp.put(19, "nineteen minutes");
        temp.put(20, "twenty minutes");
        temp.put(21, "twenty one minutes");
        temp.put(22, "twenty two minutes");
        temp.put(23, "twenty three minutes");
        temp.put(24, "twenty four minutes");
        temp.put(25, "twenty five minutes");
        temp.put(26, "twenty six minutes");
        temp.put(27, "twenty seven minutes");
        temp.put(28, "twenty eight minutes");
        temp.put(29, "twenty nine minutes");
        temp.put(30, "half");
        NUM_TO_WORDS = ImmutableMap.copyOf(temp);
    }
}
