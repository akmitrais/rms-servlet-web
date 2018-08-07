package com.mitrais.khotim.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.khotim.models.User;

public class DBUtils
{
    public static User findUser(Connection conn, String email, String password) throws SQLException {

        String sql = "SELECT u.id, u.email, u.password FROM user u WHERE u.email = ? AND u.password= ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            User user = new User();
            user.id = rs.getInt("id");
            user.email = email;
            user.setPassword(password);
            
            return user;
        }
        
        return null;
    }
    
    public static User findUser(Connection conn, String email) throws SQLException {
        
        String sql = "SELECT u.email, u.password FROM user u WHERE u.email = ? ";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            String password = rs.getString("password");
            User user = new User();
            user.email = email;
            user.setPassword(password);
            
            return user;
        }
        return null;
    }
    
    public static User findUser(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM user u WHERE u.id=?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
            User user = prepareUserData(rs);
            
            return user;
        }
        
        return null;
    }
    
    public static List<User> queryUser(Connection conn) throws SQLException {
        String sql = "SELECT * FROM user u";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        ResultSet rs = pstm.executeQuery();
        List<User> list = new ArrayList<User>();
        
        while (rs.next()) {
            User user = prepareUserData(rs);
            list.add(user);
        }
        
        return list;
    }
    
    public static void updateUser(Connection conn, User user) throws SQLException {
        String sql = "UPDATE user SET name=?, email =?, password=?, role_id=?, updated_at=?, updated_by=? WHERE id=? ";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        long unixTimestamp = Instant.now().getEpochSecond();
        
        pstm.setString(1, user.name);
        pstm.setString(2, user.email);
        pstm.setString(3, user.password);
        pstm.setInt(4, user.role_id);
        pstm.setLong(5, unixTimestamp);
        pstm.setInt(6, user.updated_by);
        pstm.setInt(7, user.id);
        
        pstm.executeUpdate();
    }
    
    public static void insertUser(Connection conn, User user) throws SQLException {
        String sql = "INSERT into user(name, email, password, role_id, created_at, created_by, updated_at, updated_by) VALUES (?,?,?,?,?,?,?,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        long unixTimestamp = Instant.now().getEpochSecond();

        pstm.setString(1, user.name);
        pstm.setString(2, user.email);
        pstm.setString(3, user.password);
        pstm.setInt(4, user.role_id);
        pstm.setLong(5, unixTimestamp);
        pstm.setInt(6, user.created_by);
        pstm.setLong(7, unixTimestamp);
        pstm.setInt(8, user.updated_by);
        
        pstm.executeUpdate();
    }
    
    public static void deleteUser(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id= ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        pstm.executeUpdate();
    }
    
    private static User prepareUserData(ResultSet rs) throws SQLException {
        User user = new User();
        user.id = rs.getInt("id");
        user.email = rs.getString("email");
        user.name = rs.getString("name");
        user.role_id = rs.getInt("role_id");
        user.created_at = rs.getInt("created_at");
        user.created_by = rs.getInt("created_by");
        user.updated_at = rs.getInt("updated_at");
        user.updated_by = rs.getInt("updated_by");
        
        return user;
    }
}
