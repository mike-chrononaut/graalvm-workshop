package ru.workshop.calculator;

public class SequenceCalculator {

    public int[] findSequence(int count) {
        int num1 = 1, num2 = 1;
        int currentNum, index;

        int[] answerArray = new int[count];
        answerArray[0] = num1;
        answerArray[1] = num2;

        for (index = 3; index < count; index++) {
            currentNum = num2 - num1;
            num1 = num2;
            num2 = currentNum;
            answerArray[index - 1] = currentNum;
        }
        return answerArray;
    }

    public static void main(String[] args) {
        SequenceCalculator calculator = new SequenceCalculator();
        long startTime = System.currentTimeMillis();
        long now;
        long last = startTime;

        for (int i = 1_000_000_000; i < 1_000_000_020; i++) {
            int[] numbers = calculator.findSequence(i);

            int total = 0;
            for (int j = 0; j < numbers.length; j++) {
                total += numbers[j];
            }

            now = System.currentTimeMillis();
            System.out.println("Total = " + total);
            System.out.printf("%d (%d ms)%n", i, now - last);
            last = now;
        }

        System.out.printf("total: (%d ms)%n", System.currentTimeMillis() - startTime);
    }

}
