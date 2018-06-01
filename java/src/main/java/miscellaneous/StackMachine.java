package miscellaneous;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class StackMachine {
    private static final Scanner sc = new Scanner(System.in);
    private static final Deque<Integer> programStack = new LinkedList<>();

    public static void process(String program) {
        for (Character instruction : program.toCharArray()) {
            if (isInteger(instruction)) {
                programStack.offerFirst(toInteger(instruction));
                continue;
            }

            switch (instruction) {
                case '+':
                    programStack.offerFirst(programStack.pollFirst() + programStack.pollFirst());
                    break;
                case '*':
                    programStack.offerFirst(programStack.pollFirst() * programStack.pollFirst());
                    break;
                case '/':
                    programStack.offerFirst(programStack.pollFirst() / programStack.pollFirst());
                    break;
                case '.':
                    System.out.println(programStack.pollFirst());
                    break;
                case '#':
                    String res = sc.nextLine();
                    process(res);
                default:
                    throw new IllegalStateException("Invalid operation: " + instruction);
            }
        }
    }

    private static int toInteger(Character c) {
        int res = Character.getNumericValue(c);
        if (res < 0) {
            throw new IllegalStateException("Invalid operation");
        }
        return res;
    }

    private static boolean isInteger(Character c) {
        return Character.getNumericValue(c) >= 0;
    }

    public static void main(String[] args) {
        StackMachine.process("#");
    }
}
