package introToProgramming;
import java.util.Scanner;

public class BankCustomer {

	Scanner Input = new Scanner(System.in);

	private String customerName;
	private int customerAccountNumber;
	private String customerAddress;
	private String customerDateOfBirth;

	public  String getName () 
	{
		String name = Input.next();
		return name;
	}
	public int getAccountNumber () 
	{
		int accountNumber = Input.nextInt();
		return accountNumber;
	}
	public String getAddress () 
	{
		String address = Input.next();
		return address;
	}
	public String getDateOfBirth () 
	{
		customerDateOfBirth = Input.next();
		return customerDateOfBirth;
	}

	public void setName (String name)
	{
		customerName = name;
	}
	public void setAccountNumber (int accountNumber)
	{
		customerAccountNumber = accountNumber;
	}
	public void setCustomerAddress (String address)
	{
		customerAddress = address;
	}
	public void setDateOfBirth (String dateOfBirth)
	{
		customerDateOfBirth = dateOfBirth;
	}


	public void main () 
	{

	//	BankCustomer a = new BankCustomer ();

		System.out.print("Enter your name. ");
		getName();
		setName(customerName);

		System.out.print("Enter your account number. ");
		getAccountNumber();
		setAccountNumber(customerAccountNumber);

		System.out.print("Enter your address. ");
		getAddress();
		setCustomerAddress(customerAddress);

		System.out.print("Enter your date of birth. ");
		getDateOfBirth();
		setDateOfBirth(customerDateOfBirth);

		Input.close();
	}
}
