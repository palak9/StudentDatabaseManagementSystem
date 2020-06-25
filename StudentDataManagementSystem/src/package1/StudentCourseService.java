package package1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



import package1.EnumClass.courseName;


public class StudentCourseService{

	    int id;
		//String courseName;
		int courseDuration;
		int fees;
	
		Scanner sc = new Scanner(System.in);
			
	void launchCourse(String cname)
	{
		int counter=0;
		EnumClass eobj = new EnumClass();
		try {
			
		       Class.forName("oracle.jdbc.driver.OracleDriver");
		       
		       String url="jdbc:oracle:thin:@localhost:1521:orcl";
		       String username="hr";
		       String password="hr";
		       Connection con=DriverManager.getConnection(url, username, password);
		       
		       System.out.println("Enter Course Details : ");
		       
		       System.out.println("Enter Course Duration : ");
		       int cd = sc.nextInt();
		       System.out.println("Enter Course Fees : ");
		       int cfee = sc.nextInt();
		       
		       String sql3 = "select max(courseId) from courseDetails";
				Statement st3 = con.createStatement();
				ResultSet rs2 = st3.executeQuery(sql3);
				
				if(rs2.next())
					counter=rs2.getInt(1);	
		       
		       
		       String insert = "insert into courseDetails values(?,?,?,?)";
		       
		       PreparedStatement ps  = con.prepareStatement(insert);
		       
		        ps.setString(1, cname);
				ps.setInt(2, ++counter);
				ps.setInt(3, cd);
				ps.setInt(4,cfee);
				
	            int rows = ps.executeUpdate();
	            
	            if(rows>0)
	            {
	            	System.out.println("Course inserted ! ");
	            }
		             	            
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Course> fetchAllCourseDetails()
	{
	     ArrayList<Course> al=new ArrayList<Course>();
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				String username="hr";
				String password="hr";
				Connection con=DriverManager.getConnection(url,username,password);
				
		        String sql="select * from courseDetails";
				Statement st=con.createStatement();
		        ResultSet rs=st.executeQuery(sql);
		        Course c=new Course();
		        
		        if(rs.next())
		        {
		        	c.setCourseId(rs.getInt(2));
		        	courseName[] course =EnumClass.courseName.values();
		    	    for(courseName c1:course)
		    	    {
		    		  String st1=course.toString();
		    		  if(st1.equalsIgnoreCase(rs.getString(1)))
		    		  {
		    		 	c.setCoursename(c1);
		    		  }
		    	   }
		    	    
		        	c.setDuration(rs.getInt(3));
		        	c.setFees(rs.getInt(4));
		        	al.add(c);
		           System.out.println("List of Course Details returned Successfully !!");       	
		        }      
		  
		 }
		 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return al;
	 }
	 
	 public Course fetchCourseDetails(int id)
	 {
	 	Course cou=new Course();
	 	try {
	 		Class.forName("oracle.jdbc.driver.OracleDriver");
	 		String url="jdbc:oracle:thin:@localhost:1521:orcl";
	 		String username="hr";
	 		String password="hr";
	 		Connection con=DriverManager.getConnection(url,username,password);
	   		
	 		String sql="select * from courseDetails where courseId="+id;
	 		Statement st=con.createStatement();
	 		ResultSet rs=st.executeQuery(sql);
	 		
	 		if(rs.next())
	 		{
	 			//in loop create obj for all detail store in obj of emp
	 			//cou.setCourseId(rs.getInt(2));
	 			//Course c =new Course();
	 				cou.setCourseId(rs.getInt(2));
	 				courseName[] course =EnumClass.courseName.values();
		    	    for(courseName c1:course)
		    	    {
		    		  String st1=course.toString();
		    		  if(st1.equalsIgnoreCase(rs.getString(1)))
		    		  {
		    		 	cou.setCoursename(c1);
		    		  }
		    	   }   
		    	    cou.setDuration(rs.getInt(3));
		 			cou.setFees(rs.getInt(4));
		        }
	 		}
	 		catch (ClassNotFoundException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	} catch (SQLException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}

	 	   	return cou;
	 }

	
	 public ArrayList<Student> FetchStudentDetails(int cid)
		{
			ArrayList<Student> al=new ArrayList<Student>();
	      
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				String username="hr";
				String password="hr";
				Connection con=DriverManager.getConnection(url,username,password);
				
				String sql="select * from studentDetails where courseId="+cid;
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
                System.out.println("........");
				while(rs.next())
				{
					
					Student stu=new Student();
					stu.setCourseId(rs.getInt(1));
					stu.setStudentName(rs.getString(2));
					stu.setAddress(rs.getString(3));
					stu.setContactNumber(rs.getLong(4));
					stu.setCourseId(rs.getInt(5));
				    stu.setFeesPaid(rs.getInt(6));
					stu.setDateOfAdmission(rs.getString(7));
		            stu.setStartDate(rs.getString(8));
					stu.setEndDate(rs.getString(9));
					al.add(stu);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}

			     return al;
		}

	 
	 
	public static void main(String[] args) {
		
		
		StudentCourseService obj = new StudentCourseService();
		StudentManager sm = new StudentManager();
	    
	    CourseIdValidator cobj = new CourseIdValidator();
		EnumClass eobj = new EnumClass();
		Scanner s = new Scanner(System.in);
		int choice =0;
		int counter=0;
		do
		{
			System.out.println("Enter your choice : ");
			System.out.println("1.Launch Course \n2.Enroll Student \n3.View Course Details \n4.View Student Details \n5.Exit");
			choice=s.nextInt();
			
			switch(choice)
			{
			case 1 : System.out.println("Enter Course Name : ");
		             String cname = s.next();
		             boolean valid  = eobj.enumFunction(cname);
				      if(valid)
				         obj.launchCourse(cname);
				      else
				      {
				    	  ++counter;
				    	  System.out.println("Courses Available are : Java, C, Hibernate, Spring, J2EE");
				      }
				    	  
			         
				     break;
			         
			case 2 :
				    System.out.println("Enter Course Id : [C ,Java , J2EE , SPRING , HIBERNATE ]");
                    int courseId=s.nextInt();
                    boolean valid2 = cobj.validateCourseId(courseId);
	                if(valid2)
	            	  sm.enrollStudent(courseId);     
	                else
	        	      System.out.println("Please enter valid course id !!");
			      break;
	        
				     
		    case 3 : System.out.println("1. View All Course Details \n2. View Course Details(Course ID)");
		    	     int choice2 = s.nextInt();
		    	    switch(choice2)
		    	    {
		    	      case 1 : ArrayList<Course> al = obj.fetchAllCourseDetails();
			                   for(int i=0;i<al.size();i++)
	                           {
	                              Object obj2=al.get(i);
	                              System.out.println("Course Details : ");
	        	                  System.out.println("Course ID : "+((Course)obj2).getCourseId());
	        	                  System.out.println("Course Name : "+((Course)obj2).getCoursename());
	        	                  System.out.println("Course Duration : "+((Course)obj2).getDuration());
	        	                  System.out.println("Course Fees : "+((Course)obj2).getFees());
	        	                  System.out.println("********************************************");
	                     
	                            }
			                   break;
			                   
		    	      case 2 :  System.out.println("Enter course ID to search Details : ");
         			            int id = s.nextInt();
         			            Course c2 = new Course(); 
		          	            c2 = obj.fetchCourseDetails(id);
         
		          	            System.out.println("Course Details : ");
        	                    System.out.println("Course ID : "+(c2.getCourseId()));
        	                    System.out.println("Course Name : "+(c2.getCoursename()));
        	                    System.out.println("Course Duration : "+(c2.getDuration()));
        	                    System.out.println("Course Fees : "+(c2.getFees()));
        	                    System.out.println("********************************************");
		          	            break;
		    	    }
		    	    break;
	                 
	        case 4 :	        	  
	        	    int choice4=0;
	        	    System.out.println("1. View Student Details (Course ID ) \n2. View Student Details (Reg ID)");
	        	    choice4 = s.nextInt();
	        	    switch(choice4)
	    	        {
	    	            case 1 :  System.out.println("Enter Course Id : ");
			                      int courseId2=s.nextInt();
			                      sm.viewStudentDetails(courseId2);
			                      break;
	         
	    	            case 2 : System.out.println("Enter Register Id : ");
		                         int regId = s.nextInt();
		                         sm.viewStudentDetail(regId); 
	    	    	             break;
	    	        }

	        	    break; 
	        	    
	        	    
	   	     
	       case 5 : break;     
			
		}
			
	  }while(choice!=5 && counter<=1);
		
	}	
	
}
