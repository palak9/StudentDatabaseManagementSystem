package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentManager {

	int counter;
    StudentCourseService scs = new StudentCourseService();
	Scanner sc = new Scanner(System.in);
	CourseIdValidator obj = new CourseIdValidator();
	int days,fees1;

	public void viewStudentDetails(int courseId)
	{
		ArrayList<Student> al = new ArrayList<Student>();
		al = scs.FetchStudentDetails(courseId);
		for(Student s : al)
		{
			System.out.println("Reg ID : "+s.getRegId());
			System.out.println("Name : "+s.getStudentName());
			System.out.println("Address : "+s.getAddress());
			System.out.println("Contact No : "+s.getContactNumber());
			System.out.println("Course ID : "+s.getCourseId());
			System.out.println("Fees Paid : "+s.getFeesPaid());
			System.out.println("Date Of Admission : "+s.getDateOfAdmission());
			System.out.println("Start Date : "+s.getStartDate());
			System.out.println("End Date : "+s.getEndDate());
			System.out.println("*********************************************");
		}
	}
	
	void enrollStudent(int courseId)
	{
		int rows=0;
		try {
			
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		       String url="jdbc:oracle:thin:@localhost:1521:orcl";
		       String username="hr";
		       String password="hr";
		       Connection con=DriverManager.getConnection(url, username, password);
			   
				String newDate="";
				String start="";
				String s="";
	        		
				System.out.println("Enter Details to Enroll for new Course : ");
				System.out.println("Enter Name : ");
				String name = sc.next();
				System.out.println("Enter Address : ");
				String address = sc.next();
				System.out.println("Enter Contact Number : ");
				long contact = sc.nextLong();
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal=Calendar.getInstance();
				s=sdf.format(cal.getTime());
				
				System.out.println("Date Of Admission : "+s);
				System.out.println("When do you want to start your course ? (yyyy-MM-dd) : ");
				start=sc.next();
				
				if(start.compareTo(s)>0)
				{
					SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
	    			  Calendar c=Calendar.getInstance();
		    	      try {
			    		c.setTime(sd.parse(start));
				      } 
			          catch (ParseException e) {
					  // TODO Auto-generated catch block
					  e.printStackTrace();
				     }
		    	      
				   String sql2 = "select * from courseDetails where courseId="+courseId;
				   Statement st = con.createStatement();
				   ResultSet rs = st.executeQuery(sql2);
				
				   String sql3 = "select max(regId) from studentDetails";
				   Statement st3 = con.createStatement();
				   ResultSet rs2 = st3.executeQuery(sql3);
				
			    	if(rs2.next())
				    	counter=rs2.getInt(1);		
				 
				   if(rs.next())
				  {
				    days=rs.getInt(3);
				    fees1 = rs.getInt(4);
				  }   
				  System.out.println("Duration : "+days+" Days");
				  System.out.println("Fees are : "+fees1);
				  c.add(Calendar.DAY_OF_MONTH, days);
				  newDate=sdf.format(c.getTime());
				  System.out.println("End Date of Course :"+newDate);
				
	              String sql = "insert into studentDetails values(?,?,?,?,?,?,?,?,?)";
				  PreparedStatement st1 = con.prepareStatement(sql);
				
				  st1.setInt(1,++counter);
				  st1.setString(2,name);
				  st1.setString(3,address);
				  st1.setLong(4,contact);
				  st1.setInt(5, courseId);
				  st1.setInt(6, fees1);
				  st1.setString(7, s);
				  st1.setString(8,start);
				  st1.setString(9, newDate);
				  rows = st1.executeUpdate();
				  if(rows > 0)
				  {
					  System.out.println("Student enrolled Successfully !");
				  }
				  else
				  {
					  System.out.println("Enter Valid Information to enroll !");
				  }
			}
			else
			{
			   System.out.println("Please enter valid start date !");
			}
				
			}
			catch(Exception e)
			{
				                       
			}
			
	}
	
	
	void viewStudentDetail(int regId)
	{
		
		StudentValidator obj2  = new StudentValidator();

		Connection con = null;
		try 
		{
			boolean valid2 = obj2.registerIdValidator(regId);
			if(valid2==true)
			{
		       Class.forName("oracle.jdbc.driver.OracleDriver");
		      
		       con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
		       
		       String sql= "select * from studentDetails where regId="+regId;
		       Statement st = con.createStatement();
	
		       ResultSet rs = st.executeQuery(sql);
		 
		      while(rs.next())
		     {
			  System.out.println("Register ID : "+rs.getInt(1));
			  System.out.println("Name : "+rs.getString(2));
			  System.out.println("Address : "+rs.getString(3));
			  System.out.println("Contact No : "+rs.getLong(4));
			  System.out.println("Course ID : "+rs.getInt(5));
			  System.out.println("Fees Paid : "+rs.getInt(6));
			  System.out.println("Date Of Admission : "+rs.getString(7));
			  System.out.println("Start Date : "+rs.getString(8));
			  System.out.println("End Date : "+rs.getString(9));
			  System.out.println("***************************************************");
		    }
		
		
			}
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
	
			try {
				if(con != null)
				  con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	public static void main(String[] args) {
	
		StudentManager obj = new StudentManager();
		CourseIdValidator obj2 = new CourseIdValidator();		
		Scanner s = new Scanner(System.in);
		int choice =0;
		
		do
		{
			System.out.println("Enter your choice : ");
			System.out.println("1.Enroll Student \n2.View Student Details (Course ID) \n3.View Student Details (Register ID)\n4.Exit");
			choice=s.nextInt();
			
			switch(choice)
			{
			case 1 :System.out.println("Enter Course Id : [ 1.C , 2.Java , 3.J2EE , 4.SPRING , 5.HIBERNATE ]");
	                int courseId=s.nextInt();
	                boolean valid = obj2.validateCourseId(courseId);
		            if(valid)
		            	obj.enrollStudent(courseId);     
		            else
		        	   System.out.println("Please enter valid course id !!");
							
				
			         break;
			         
			case 2 : System.out.println("Enter Course Id : ");
			         int courseId2=s.nextInt();
			         obj.viewStudentDetails(courseId2);
			         break;
	        
		    case 3 : System.out.println("Enter Register Id : ");
		             int regId = s.nextInt();
		             obj.viewStudentDetail(regId);
	                 break;
	                 
	        case 4 : break;         
			}
			
		  }while(choice!=4);
		}
}

