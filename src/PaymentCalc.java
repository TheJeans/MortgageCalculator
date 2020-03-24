import java.text.NumberFormat;
import java.util.Scanner;

/**
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
        System.out.print("What is the principal?: $");
        Scanner principalInput = new Scanner(System.in);
        int principal = principalInput.nextInt();

        System.out.print("What is the annual interest rate?: ");
        Scanner interestInput = new Scanner(System.in);
        double interest = interestInput.nextDouble();

        System.out.print("What is the period (in years)?: ");
        Scanner yearsInput = new Scanner(System.in);
        int years = yearsInput.nextInt();

        /**
         * Call methods and display answers
         */
        printMortgage(principal, interest, years);
        printPaymentSchedule(principal, interest, years);


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