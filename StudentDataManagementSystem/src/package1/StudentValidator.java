package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class StudentValidator {

	
	Scanner sc1 =new Scanner(System.in);
	
	public boolean registerIdValidator(int regId)
	{
	  try {
    	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver Loaded");
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String username="hr";
		String password="hr";
		Connection con=DriverManager.getConnection(url, username, password);
		String sql_courseId = "select * from studentDetails where regId = "+regId;
		Statement st= con.createStatement();
		ResultSet rs = st.executeQuery(sql_courseId);
		
		
	   try
	   {
		   while(rs.next())
		     {
			  System.out.println("Register ID : "+rs.getInt(1));
			  System.out.println("Name : "+rs.getString(2));
			  System.out.println("Address : "+rs.getString(3));
			  System.out.println("Contact No"+rs.getLong(4));
			  System.out.println("Course ID"+rs.getInt(5));
			  System.out.println("Fees Paid"+rs.getInt(6));
			  System.out.println("Date Of Admission"+rs.getString(7));
			  System.out.println("Start Date"+rs.getString(8));
			  System.out.println("End Date"+rs.getString(9));
			  System.out.println("----------------------------------------------");
		    }  
		
	   }
	   catch(Exception e)
	   {
		   System.out.println("Invalid Register Id !");
	   }
	   

    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
     return false;

   }
}
