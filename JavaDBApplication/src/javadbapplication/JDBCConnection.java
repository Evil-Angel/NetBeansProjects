/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbapplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author Ankit.Saxena
 */


public class JDBCConnection {
    public Properties prop = new Properties();
    public String propfilename = "javadbapplication/configuration/Configuration.properties";
    
    JDBCConnection(String configfilename)
    {
        if (configfilename != " " && configfilename != "" || configfilename != null)
        {
            propfilename = configfilename;
        }
    }
    
    public Connection getConnection()
    {
        Connection conn = null;
        String dbUrl = "jdbc:mysql://localhost:3306/";
        String dbName = "world";
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUser = "tvc";
        String dbPassword = "tru777#";
        
        System.out.println("getConfigValue(dbUrl) = " + getConfigValue("dbUrl"));
        System.out.println("getConfigValue(dbName) = " + getConfigValue("dbName"));
        System.out.println("getConfigValue(dbDriver) = " + getConfigValue("dbDriver"));
        System.out.println("getConfigValue(dbUser) = " + getConfigValue("dbUser"));
        System.out.println("getConfigValue(dbPassword) = " + getConfigValue("dbPassword"));
        
        try{
            Class.forName(dbDriver).newInstance();
            conn = DriverManager.getConnection(dbUrl+dbName, dbUser, dbPassword);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            conn = null;
            System.out.println("Connection could not be created: \n" + ex.getMessage());
        }
        
        return conn;
    }
    
    public void closeConnection(Connection conn)
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
    
    public String getConfigValue(String configItem)
    {
        printClassPath();
        String configValue = "";
        InputStream configInputStream =  getClass().getClassLoader().getResourceAsStream(propfilename);
        
        try{            
            if (configInputStream != null)
            {
                prop.load(configInputStream);
                
                configValue = prop.getProperty(configItem);
                System.out.println("Config Item : " + configItem + "found; Value = " + configValue);
            }
            else
            {
                System.out.println(propfilename + " file missing...");
                throw new FileNotFoundException("Configuration property file \"" + propfilename + "\" not found in classpath..");
            }
        }
        catch (Exception ex)
        {
            configValue = "";
            ex.printStackTrace();
        }
        
        return configValue;
    }
    
    public void printClassPath()
    {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
    }
}
