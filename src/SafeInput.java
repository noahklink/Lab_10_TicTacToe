/**
 * @author Noah Klink klinkna@mail.uc.edu
 */

import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString;

        do {
            IO.print(prompt);
            retString = pipe.nextLine();
        } while (retString.isEmpty());

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        String trash;
        boolean done = false;
        do {
            IO.print(prompt);
            if(pipe.hasNextInt()) {
                done = true;
                retInt = pipe.nextInt();
            } else {
                trash = pipe.nextLine();
                IO.println("Expected int, got \"" + trash + "\"");
            }
        } while (!done);

        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0;
        String trash;
        boolean done = false;
        do {
            IO.print(prompt);
            if(pipe.hasNextDouble()) {
                done = true;
                retDouble = pipe.nextDouble();
            } else {
                trash = pipe.nextLine();
                IO.println("Expected double, got \"" + trash + "\"");
            }
        } while (!done);

        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retInt = 0;
        String trash;
        boolean done = false;
        do {
            IO.print(prompt);
            if(pipe.hasNextInt()) {
                int num =  pipe.nextInt();

                if (num >= low && num <= high) {
                    done = true;
                    retInt = num;
                    pipe.nextLine();
                } else {
                    System.out.printf("Expected [%d-%d], got %d\n", low, high, num);
                    pipe.nextLine();
                }
            } else {
                trash = pipe.nextLine();
                IO.println("Expected int, got \"" + trash + "\"");
            }
        } while (!done);

        return retInt;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retDouble = 0;
        String trash;
        boolean done = false;
        do {
            IO.print(prompt);
            if(pipe.hasNextDouble()) {
                double num =  pipe.nextDouble();

                if (num >= low && num <= high) {
                    done = true;
                    retDouble = num;
                    pipe.nextLine();
                } else {
                    System.out.printf("Expected [%.2f-%.2f], got %f\n", low, high, num);
                    pipe.nextLine();
                }
            } else {
                trash = pipe.nextLine();
                IO.println("Expected double, got \"" + trash + "\"");
            }
        } while (!done);

        return retDouble;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean done = false;
        boolean retBool = true;
        do {
            IO.print(prompt);
            if (pipe.hasNextLine()) {
                String str = pipe.nextLine();
                if (str.equalsIgnoreCase("y"))
                    retBool = done = true;
                else if (str.equalsIgnoreCase("n")) {
                    retBool = false;
                    done = true;
                } else {
                    IO.println("Expected y/n, got \"" + str + "\"");
                }
            }
        } while(!done);

        return retBool;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx, String format) {
        boolean done = false;
        String retString = "";

        do {
            IO.print(prompt);
            if(pipe.hasNextLine()) {
                String str = pipe.nextLine();
                if (str.matches(regEx)) {
                    done = true;
                    retString = str;
                } else {
                    IO.println("Expected format " + format + ", got \"" + str + "\"");
                }
            }
        } while (!done);
        
        return retString;
    }

    public static void prettyHeader(String msg) {
        for (int i = 1; i <= 60; i++) IO.print("*");

        int eachSideBlankSpace = (54 - msg.length()) / 2;
        IO.print("\n***");
        for (int i = 1; i <= eachSideBlankSpace; i++) IO.print(" ");
        IO.print(msg);
        for (int i = 1; i <= eachSideBlankSpace; i++) IO.print(" ");
        // account for uneven string len + add space
        if (msg.length() % 2 == 0) IO.print("***\n");
        else IO.print(" ***\n");

        for (int i = 1; i <= 60; i++) IO.print("*");
    }
}