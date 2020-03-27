import java.text.NumberFormat;
import java.util.Scanner;

/**
 * PT 2
 * Further continuation of "Mortgage Calculator"
 * It takes the inputs from the user and calculates the mortgage at the end of the questions.
 * Then it calculates and displays the monthly payments as well as a payment schedule that shows
 * the balance after every months payment.
 */

public class PaymentCalc {

    final static int monthsInYear = 12;
    final static int percent = 100;

    public static void main(String[] args) {

        /**
         * Collect inputs from user
         */
        int principal = (int) readNumber("What is the principal?: $", 1000, 1_000_000);
        double interest = readNumber("What is the annual interest rate?: ", 1, 30);
        int years = (int) readNumber("What is the period (in years)?: ", 1, 30);


        /**
         * Call methods and display answers
         */
        printMortgage(principal, interest, years);
        printPaymentSchedule(principal, interest, years);


    }

    public static double readNumber(String prompt, double min, double max){
        /**
         * Takes the desired prompt, min, and max.
         * Creates the scanner and proceeds into the loop.
         * Prints the prompt and takes in the input value
         * if the input(value) is within the min and max range
         * it breaks out of the loop and returns the value.
         * Otherwise, it will tell the user to input a value
         * between min and max until they do so.
         */
        Scanner input = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = input.nextDouble();
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static void printMortgage(int principal, double interest, int years){
        double mortgage = mortgage(principal, interest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public static void printPaymentSchedule(int principal, double interest, int years){
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (int month = 1; month <= years * monthsInYear; month++) {
            double balance = balance(principal, interest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }


    public static double mortgage (int principal, double interest, int years) {
        /**
         * Calculates interest and number of payments a MONTH.
         * Finds the mortgage through the equation: M = P[(r(1+r)^n)/((1+r)^n-1)]
         * Where M is monthly payment, P is principal, r is your monthly interest rate, & n is your number of payments.
         * Returns mortgage calculation.
         */
        double monthlyInterest = interest/percent/monthsInYear;
        int numberOfPayments = years*monthsInYear;

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public static double balance(int principal, double interest, int years, int numberOfPaymentsMade){
        /**
         * Calculates interest and number of payments a MONTH.
         * Finds the mortgage through the equation: B = P[(1+r)^n-(1+r)p]/[(1+r)^n-1]
         * Where B is balance, P is principal, r is your monthly interest rate, n is your number of payments, & p is the number of payments made.
         * Returns mortgage calculation.
         */
        double monthlyInterest = interest/percent/monthsInYear;
        int numberOfPayments = years*monthsInYear;


        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }




}
