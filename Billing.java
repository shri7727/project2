package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Scanner;

public class Billing {

	public void DisplayBillingDetails()
	{
		AirportQuarantineZone aqz=new AirportQuarantineZone();
		System.out.println();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			String q="select * from bill";
			Statement pstat=con.createStatement();
			ResultSet rs=pstat.executeQuery(q);
			System.out.println("*************DETAILS OF BILL************");
			System.out.println("The Bill Number\t\tNumber Of Days\t\tTotal Amount\t\tPassport Number\t\tFlight Number");
			while(rs.next())
			{
				int bnum=rs.getInt(1);
				String numday=rs.getString(2);
				String tamt=rs.getString(3);
				String passno=rs.getString(4);
				String fnum=rs.getString(5);
			System.out.println(bnum+"\t\t\t"+""+numday+"\t\t\t"+tamt+"\t\t\t"+passno+"\t\t\t"+fnum);
				}
		
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
public String noOfDays(String tid) {
	//String nd = null ;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
		PreparedStatement pstmt=con.prepareStatement("select timestampdiff(day,admitDate,dischargeDate) as noofdays ,(timestampdiff(day,admitDate,dischargeDate)*250) as total "
				+ "from airport_quar where airport_quar.testid=?;");
		pstmt.setString(1, tid);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return tid;
	
	
	
}
	void ad() {
		Scanner sc=new Scanner(System.in);
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			//System.out.println("class found");
			
				System.out.println(".....Welcome....");
				System.out.println("press 1 for Bill Details");
				System.out.println("press 2 for Updating Bill details");
				System.out.println("press 3 for Adding new Bill details");
				System.out.println("press 4 to return on main page");
				System.out.println("enter your choice");
				int ch=sc.nextInt();
				switch (ch) {
				case 1:DisplayBillingDetails();
					break; 
				case 2:
					
					System.out.println("press 1 for Updating Number of Day");
					System.out.println("press 2 for Updating Total Amount");
					System.out.println("press 3 for Updating Passport Number");
					System.out.println("press 4 for Updating Flight Number");
					
					int ch1=sc.nextInt();
					switch (ch1) {
					case 1:
						PreparedStatement pst1=con.prepareStatement("update bill set noofdays=? where billno=?  ");
						System.out.println("Enter the Bill Number");
						int bno=sc.nextInt();
						System.out.println("Enter new Number of Days");
						int nday=sc.nextInt();
						pst1.setInt(2, bno);
						pst1.setInt(1, nday);
						int rs1=pst1.executeUpdate();
						if(rs1==1) {
							System.out.println("Updated successfully.....");
						}
						else {
							System.out.println("Something went wrong!!!");
						}
						break;
					case 2:PreparedStatement pst2=con.prepareStatement("update bill set totalAmt=? where billno=?  ");
						System.out.println("Enter the Bill Number");
						int bno1=sc.nextInt();
						System.out.println("Enter new Total Amount");
						int tamt=sc.nextInt();
						pst2.setInt(2, bno1);
						pst2.setInt(1, tamt);
						int rs2=pst2.executeUpdate();
						if(rs2==1) {
							System.out.println("Updated successfully.....");
						}
						else {
							System.out.println("Something went wrong!!!");
						}
						break;
						
					case 3:PreparedStatement pst3=con.prepareStatement("update bill set passNo=? where billno=?  ");
					System.out.println("Enter the Bill Number");
					int bno2=sc.nextInt();
					System.out.println("Enter new Passport Number");
					String passnum=sc.next();
					pst3.setInt(2, bno2);
					pst3.setString(1, passnum);
					int rs3=pst3.executeUpdate();
					if(rs3>0) {
						System.out.println("Updated successfully.....");
					}
					else {
						System.out.println("Something went wrong!!!");
					}
					break;
					
					
					case 4:PreparedStatement pst4=con.prepareStatement("update bill set flightnum=? where billno=?  ");
					System.out.println("Enter the Bill Number");
					int bno3=sc.nextInt();
					System.out.println("Enter new Flight Number");
					String fnum=sc.next();
					pst4.setInt(2, bno3);
					pst4.setString(1, fnum);
					int rs4=pst4.executeUpdate();
					if(rs4==1) {
						System.out.println("Updated successfully.....");
					}
					else {
						System.out.println("Something went wrong!!!");
					}
					break;
						
					
						
							
					default:
						break;
					}
					break;
				
				case 3:
					PreparedStatement pst7=con.prepareStatement("insert into bill values(?,?,?,?,?)");
					
					
					System.out.println("Enter Bill Number");
					int bnum=sc.nextInt();
					
					System.out.println("Enter the Passport Number ");
					String pnum=sc.next();
					
					PreparedStatement pstmt1=con.prepareStatement("select testid from test where test.passportno=?;");
					pstmt1.setString(1,pnum);
					ResultSet rs1=pstmt1.executeQuery();
					rs1.next();
					String testid=rs1.getString("testid");
					//System.out.println(testid);
					
					PreparedStatement pstmt=con.prepareStatement("select timestampdiff(day,admitDate,dischargeDate) as noofdays "
																	+ ",(timestampdiff(day,admitDate,dischargeDate)*250) as total "
																	+ "from airport_quar where airport_quar.testid=?;");
					pstmt.setString(1,testid);
					ResultSet rs=pstmt.executeQuery();
					rs.next();
					String nd=rs.getString(1);
					
					String tamt=rs.getString(2);
					
					//System.out.println(tamt);
					System.out.println(nd);
					//System.out.println("Enter Total Amount");
					//int tamt=sc.nextInt();
					
					System.out.println("Enter the Flight Number");
					String fnum=sc.next();
					
					pst7.setInt(1,bnum);
					pst7.setString(2, nd);
					pst7.setString(3, tamt);
					pst7.setString(4, pnum);
					pst7.setString(5, fnum);
					
					
					int rs7=pst7.executeUpdate();
					if(rs7==1) {
						System.out.println("Record Added successfully.....");
					}
					else {
						System.out.println("Something went wrong!!!");
					}
					break;
				case 4:	Project p=new Project();
						p.adminPage();
						break;	
				default:
					System.out.println("Enter valid choice");
					break;
					//throw new IllegalArgumentException("Unexpected value: " + ch);
				}
			
		
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
}
	
	
	
	
	


