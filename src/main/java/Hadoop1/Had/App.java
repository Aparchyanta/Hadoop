package Hadoop1.Had;

import java.beans.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App
{
	// JDBC driver name and database URL
	  // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	  // static final String DB_URL = "jdbc:mysql://localhost:3306/my_database";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "Friendsforever12";
	   
	   public static void main(String[] args) {
		   Properties properties = new Properties();
		   File file = new File("C:\\My Drive\\Cloudwick\\Had\\target\\classes\\db.properties");
		   FileInputStream fileInput;
		 try {
		 fileInput = new FileInputStream(file);
		 properties.load(fileInput);
		 } catch (FileNotFoundException e1) {
		 // TODO Auto-generated catch block
		 e1.printStackTrace();
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		           
		   final String JDBC_DRIVER = properties.getProperty("jdbcDriver");  
		   final String DB_URL = properties.getProperty("jdbcUrl");
		   final String USER = properties.getProperty("jdbcUser");
		   final String PASS = properties.getProperty("jdbcPass");
		   System.out.println ("jdbcDrivr: " + JDBC_DRIVER);
		   Connection conn = null;
		   
	   java.sql.Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);
	     
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT Student_ID, fname, lname FROM student";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("Student_ID");
	         String first = rs.getString("fname");
	         String last = rs.getString("lname");

	         //Display values
	         System.out.print("Student_ID: " + id);
	        
	         System.out.print(", fname: " + first);
	         System.out.println(", lname: " + last);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
}
