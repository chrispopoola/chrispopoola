package introToProgramming;
/*  SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 10:  10
       Comment:
   2. Did I use easy-to-understand, meaningful CONSTANT names?
       Mark out of 5:  5
       Comment:
    3. Did I format the CONSTANT names properly (in UPPERCASE)?
       Mark out of 5:  5
       Comment:
   4. Did I use easy-to-understand meaningful variable names?
       Mark out of 10:  10
       Comment:
   5. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 10:  10
       Comment:
   6. Did I indent the code appropriately?
       Mark out of 10:  10
       Comment:
   7. Did I read the input correctly from the user using appropriate question(s)?
       Mark out of 10:  10
       Comment:
   8. Did I compute the disposable income correctly?
       Mark out of 10:  10
       Comment:
   9. Did I compute the disposable income percentage correctly?
       Mark out of 10:  10
       Comment:
   10. Did I output the correct answer in the correct format (as shown in the examples)?
       Mark out of 10:  10
       Comment:
   11. How well did I complete this self-assessment?
       Mark out of 10:  10
       Comment:
   Total Mark out of 100 (Add all the previous marks):  100
 */

import java.util.Scanner;
import java.lang.Math;

public class DisposableIncome {
	
	public static final double PERCENTAGE_TAX = 0.65;
	public static final double AVERAGE_MONTHLY_DISPOSABLE_INCOME = 500.00;
	public static final double FIFTY_PERCENT_GREATER_THAN_AVERAGE_MONTHLY_DISPOSABLE_INCOME = 1.5;
	public static final double FIFTY_PERCENT_LESS_THAN_AVERAGE_MONTHLY_DISPOSABLE_INCOME = .5;


	public static void main(String[] args) {

		Scanner Input = new Scanner (System.in);

		System.out.print("What is your monthly income before tax? €");
		double grossIncome = Input.nextDouble();

		System.out.print("What is the cost of your rent/mortgage per month? €");
		double rentMortgage = Input.nextDouble();

		System.out.print("How much does commuting cost per month? €");
		double commuteCost = Input.nextDouble();

		System.out.print("How much do you spend on food per month? €");
		double foodCost = Input.nextDouble();

		double netIncome = (grossIncome * PERCENTAGE_TAX);
		double monthlyDisposableIncome = (netIncome - rentMortgage - commuteCost - foodCost);
		double percentageDisposableIncome = (monthlyDisposableIncome/ grossIncome) * 100; 

		System.out.println("Your monthly disposable income is €" + monthlyDisposableIncome + " which is " + Math.round(percentageDisposableIncome) + "% of your salary.");


		if (monthlyDisposableIncome > AVERAGE_MONTHLY_DISPOSABLE_INCOME * FIFTY_PERCENT_GREATER_THAN_AVERAGE_MONTHLY_DISPOSABLE_INCOME)
			System.out.print("Your monthly disposable income is much more than the average. ");
		
		else if (monthlyDisposableIncome > AVERAGE_MONTHLY_DISPOSABLE_INCOME)
			System.out.print("Your monthly disposable income is more than the average. ");
		
		else if (monthlyDisposableIncome == AVERAGE_MONTHLY_DISPOSABLE_INCOME)
			System.out.print("Your monthly disposable income is equivalent to the average. ");

		else if (monthlyDisposableIncome < AVERAGE_MONTHLY_DISPOSABLE_INCOME)
			System.out.print("Your monthly disposable income is less than the average. ");
		
		else if (monthlyDisposableIncome < AVERAGE_MONTHLY_DISPOSABLE_INCOME * FIFTY_PERCENT_LESS_THAN_AVERAGE_MONTHLY_DISPOSABLE_INCOME)
			System.out.print("Your monthly disposable income is much lesser than the average. ");

		else if (monthlyDisposableIncome <= 0.00)
			System.out.print("You have no monthly disposable income. ");
		
		Input.close();
	}
}

