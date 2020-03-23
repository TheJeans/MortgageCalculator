import java.util.Scanner;

/**
 *
 */

public class Main {

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
         * Call method and display answer
         */
        System.out.print("The mortgage is : $"+mortgage(principal,interest,years));

    }

    public static double mortgage (int principal, double interest, int years) {
        /**
         * Calculates the mortgage by finding out interest and number of payments a MONTH.
         * Rounds the mortgage up to 2 decimal places and returns.
        */
        int monthsInYear = 12;
        int percent = 100;
        double monthlyInterest = interest/percent/monthsInYear;
        int numberOfPayments = years*monthsInYear;

        double mortgage = principal
                *(monthlyInterest*Math.pow(1+monthlyInterest,numberOfPayments)
                /(Math.pow(1+monthlyInterest, numberOfPayments)-1));
        double mortgageRoundup = (double) Math.round(mortgage*100)/100;
        return mortgageRoundup;
    }

}
