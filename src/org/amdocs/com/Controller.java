package org.amdocs.com;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Controller {

//private static final int NULL = 0;
static Connection con = ConnectionBank.connect();                            //creating database connection to oracle
//database code implementation present in connection bank class
static String sql = "";
View v1 = new View();
public void check_balance(int ac_number) {                                          //function for displaying current balance
		try {
			sql = "select * from account where accountnumber =" + ac_number;                //storing query to execute in a variable
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("Your balance is: "+rs.getString(1));
				v1.switches();
			}
			else {
				System.out.println("Account does not exist");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("No");
		}
		
}
public void withdraw(int ac_number, int amount) {                          //function for withdrawing money
	try {                                                            //exception prone area in try
		con.setAutoCommit(false);                            //doesn't create new transaction
		sql = "select * from account where accountnumber =" + ac_number;
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			if(rs.getInt(1) < amount) {
				System.out.println("Insufficent Balance!");
				return;
			}
			
		}
		Statement stmt1 = con.createStatement();
		con.setSavepoint();                                    //creating a savepoint in case need rollback
		sql = "update account set balance = balance-" + amount +  "where accountnumber =" + ac_number;
		stmt1.executeUpdate(sql);
		con.commit();                                       //autocommit(false) so explicit commit
		check_balance(ac_number);                              //calling function to return balance
		
		
	}catch(Exception e) {                                   //for handling exception
		e.printStackTrace();
	}
}

public void deposit(int ac_number, int amount) {                   //function for depositing money
	try {
		con.setAutoCommit(false);
		sql = "select * from account where accountnumber =" + ac_number;
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			if(amount <= 0) {
				System.out.println("Incorrect amount!");
				return;
			}
			
		}
		Statement stmt1 = con.createStatement();
		con.setSavepoint();
		sql = "update account set balance = balance+" + amount +  "where accountnumber =" + ac_number;
		stmt1.executeUpdate(sql);
		con.commit();
		check_balance(ac_number);
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}

public boolean new_account(String name, String mobile) {                          //function for creating new account
int max = 0;
	try {
		sql = "select MAX(accountnumber)+1 from customer";                 //query for generating next account number
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			max = rs.getInt(1);
		}
		
		PreparedStatement st = con.prepareStatement("insert into customer values(?,?,?)");  //for inserting variable values in table
		st.setInt(1,max);
		st.setString(2,name);
		st.setString(3, mobile);
//		sql = "insert into customer values((select MAX(accountnumber)+1 from customer),"+ name+ ", '0606060')";
		
		if(st.executeUpdate() == 1) {
			System.out.println("Your account has been successfully created.");
			System.out.println("Your account number is: " + max);
			return true;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return false;
}
	
}
