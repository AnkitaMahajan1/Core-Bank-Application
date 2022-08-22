package org.amdocs.com;

import java.util.Scanner;

public class View {
	public void switches() {
		Scanner sc = new Scanner(System.in);                        
		Controller c = new Controller();
		int user_input;
		System.out.println("Welcome to ABC Bank: ");                           //menu driven bank interface
		System.out.println("1. Check Balance \n2. Withdraw money \n3. Deposit money"
				+ " \n4. Open New Account \n5.Exit");
		System.out.println("Enter your choice: ");	
		user_input = sc.nextInt();                                            //taking user input related to options given

		
		switch(user_input) {
		case 1:
			System.out.println("Enter Account number: ");                      //case1 for checking balance
			int ac = sc.nextInt();
			c.check_balance(ac);                                               //<---fuction code is in controller class
			break;
		case 2:                                                                //case2 for withdrawing money
			System.out.println("Enter Account number: ");
			int ac1 = sc.nextInt();
			System.out.println("Enter Amount to withdraw: ");
			int amount = sc.nextInt();
			c.withdraw(ac1, amount);
			break;
		case 3:                                                                //case3 for depositing money
			System.out.println("Enter Account number: ");
			int ac2 = sc.nextInt();
			System.out.println("Enter Amount to deposit: ");
			int amount1 = sc.nextInt();
			c.deposit(ac2, amount1);
			break;
		case 4:								                                   //case4 for creating new user account
			System.out.println("Enter Name: ");
			String name = sc.next();
			System.out.println("Enter mobile: ");
			String mobile = sc.next();
			c.new_account(name,mobile);
			break;
		case 5:                                                                  //case5 for exit
			System.out.println("Have a good day!!");
			break;
		}
//		
	}

}
