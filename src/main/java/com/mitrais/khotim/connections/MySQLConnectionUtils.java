package com.mitrais.khotim.connections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectionUtils
{
    private String dbDriver;
    private String connectionUrl;
    private String dbUser;
    private String dbPassword;
    
    private MySQLConnectionUtils() {
        Properties prop;
        try {
            prop = loadConfig();
            this.dbDriver = prop.getProperty("jdbc.driver");
            this.connectionUrl = prop.getProperty("jdbc.url");
            this.dbUser = prop.getProperty("jdbc.user");
            this.dbPassword = prop.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Properties loadConfig() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        
        return prop;
    }

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        MySQLConnectionUtils mysqlConn = new MySQLConnectionUtils();

        return getMySQLConnection(mysqlConn);
    }
    
    public static Connection getMySQLConnection(MySQLConnectionUtils mysqlConn) throws SQLException, ClassNotFoundException {
        Class.forName(mysqlConn.dbDriver);
        
        Connection conn = DriverManager.getConnection(mysqlConn.connectionUrl, mysqlConn.dbUser, mysqlConn.dbPassword);
        
        return conn;
    }
}
