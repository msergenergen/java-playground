package com.playground.javafundamentals.streams;

import java.util.Arrays;

public class RangeWithStream {
    public static int calculateRange(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int min = Arrays.stream(arr).min().orElseThrow();
        int max = Arrays.stream(arr).max().orElseThrow();

        return max - min;
    }

    public static double calculateAverage(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        return Arrays.stream(arr).average().orElseThrow();
    }

    public static double findMedianNumber(int[] arr)
    {
        if (arr == null || arr.length == 0)
        {
            throw new IllegalArgumentException("Array must not be empty");
        }
        int[] sortedArray = Arrays.stream(arr).sorted().toArray();
        int length = sortedArray.length;

        if( length % 2 == 1)
        {
            return sortedArray[length/2];
        }
        else
        {
            return (sortedArray[length/2 - 1] + sortedArray[length/2]) / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {15, 16, 10, 9, 6, 7, 17};
        int range = calculateRange(numbers);
        double average = calculateAverage(numbers);
        double medianValue = findMedianNumber(numbers);
        String formatted = String.format("%.3f", average);
        System.out.println("Average: " + formatted);
        System.out.println("Range: " + range);
        System.out.println("Median Value: " + medianValue);
    }
}

