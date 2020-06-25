package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
		
	public class CourseIdValidator {
		
		Scanner sc1 =new Scanner(System.in);
		
			
			public boolean validateCourseId(int ID)
			{
			try 
			{
		    	int	newId=ID;
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				String username="hr";
				String password="hr";
				Connection con=DriverManager.getConnection(url, username, password);
				String sql_courseId = "select * from courseDetails where courseId = '"+newId+"'";
				Statement st= con.createStatement();
				ResultSet rs2 = st.executeQuery(sql_courseId);
				
			   try
		       {
				   if(rs2.next()){
					
					return true;
				  }
				
		       }
		       catch(Exception e)
		       {
		    	   System.out.println("Course ID is Invalid ");
		       }

		    } 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			return false;

			
	}
			
	
			
			
			
			
	public static void main(String[] args) {
				
				CourseIdValidator obj = new CourseIdValidator();
				obj.validateCourseId(1);
			}

		}






