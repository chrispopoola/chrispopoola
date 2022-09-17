package introToProgramming;

import java.util.Scanner;

public class MortgageCalculatorCorrect 
{
	static Scanner Input = new Scanner (System.in);
	static final int MONTHS_OF_THE_YEAR = 12;
	static final int DURATION_OF_MORTGAGE = 20;
	
	public static void main(String[] args) 
	{
		String userAnswer;
		do 
		{

			System.out.print("Welcome to the Mortgage Calculator.");

			double userMortgage = readDoubleFromUser(" \nEnter the Mortgage amount: ");

			double userAPR = readDoubleFromUser("Enter the annual interest rate (APR): ");

			System.out.println("Assuming a 20 year term, the monthly repayments would be " + calculateMonthlyRepayment(userAPR, userMortgage) + ".");

			double userAffordableAmount = readDoubleFromUser("Enter how much you can afford to pay each month. ");

			int months = calculateMonthsToRepayMortgage(userMortgage, userAffordableAmount, userAPR);
			int monthsLeft = months % MONTHS_OF_THE_YEAR;
			int years = (months - monthsLeft) / MONTHS_OF_THE_YEAR;

			System.out.println("If you pay " + userAffordableAmount + " per month your mortgage would be paid off in " + years + " years and " + monthsLeft + " months. ");

			System.out.print("Would you like to use the mortgage calculator again (yes/no)? ");
			userAnswer = Input.next();
		}
		while (userAnswer.contains("yes")); {}
		
		Input.close();
	}
	public static double readDoubleFromUser (String userPrompt) 
	{
		System.out.print(userPrompt);
		String userStringInput = Input.next();

		double userDouble = Double.parseDouble(userStringInput);

		return userDouble;
	}
	public static double calculateMonthlyRepayment (double userAPR, double userMortgage)
	{

		double monthlyRepayment = (userMortgage) * (userAPR/ 12 /100) / (1 - Math.pow((1 + userAPR/ 12 /100), (-DURATION_OF_MORTGAGE * 12))); 
		double monthlyRepaymentRoundedOff = Math.round(monthlyRepayment*100.0)/ 100.0;

		return monthlyRepaymentRoundedOff;		
	}
	private static int calculateMonthsToRepayMortgage(double userMortgage, double userAffordableAmount, double userAPR) 
	{

		int count = 0;
		double userMonthlyInterestRate = (userAPR/12)/100;

		while (userMortgage > 0) 
		{
			userMortgage = userMortgage * (1 + userMonthlyInterestRate);
			userMortgage = userMortgage - userAffordableAmount;
			count = count + 1;
		}

		int	monthsToRepay = count;
		return monthsToRepay; 
	}
}
