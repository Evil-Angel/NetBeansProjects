/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbapplication;

/**
 *
 * @author Ankit.Saxena
 */
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.lang.*;
import java.util.*;

public class JavaDBApplication {

    /**
     * @param args the command line arguments
     */
    public static Connection conn = null;
    
    public static void main(String[] args) {        
        
        try{
            JDBCConnection objConn = new JDBCConnection("javadbapplication/configuration/Configuration.properties");
            
            conn = objConn.getConnection();
            if (conn != null)
            {
                Statement st = conn.createStatement();
                ResultSet res = st.executeQuery("select * from city");
                System.out.println("    ID | Name  |  Country Code  |  District    ");
                ResultSetMetaData rsmd = res.getMetaData();
                System.out.println(rsmd.getColumnName(1));
//                System.out.println("    Name  |  Country Code  |  District    ");
                while (res.next())
                {
                    String id = res.getString("ID");
                    String name = res.getString("Name");
                    String countryCode = res.getString("CountryCode");
                    String district = res.getString("District");

                    System.out.println("    " + id + " | " + name + "  |  " + countryCode + "  |  " + district + "  |  ");
//                    System.out.println("    " + name + "  |  " + countryCode + "  |  " + district + "  |  ");
                }
                
                objConn.closeConnection(conn);
                closeConnection();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    public static void closeConnection()
    {
        try 
        {
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    
    }
}
