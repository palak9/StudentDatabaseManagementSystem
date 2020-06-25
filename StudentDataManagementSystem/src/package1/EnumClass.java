package package1;

import java.util.Scanner;

public class EnumClass {

	enum courseName
	{
		JAVA,C,J2EE,HIBERNATE,SPRING;
	}

	
	public boolean enumFunction(String str)
	{
		courseName[] c =courseName.values();
   	    boolean status=false;
	    for(courseName c1:c)
	   {
		  String st=c1.toString();
		  if(st.equalsIgnoreCase(str))
		 {
		 	System.out.println("Valid Course !");
			status = true;
			break;
		 }
	  }

	  if(!status)
	  {
		System.out.println("invalid course !");
	  }
	 
	   return status;
	}

	

}


	


	
	
	