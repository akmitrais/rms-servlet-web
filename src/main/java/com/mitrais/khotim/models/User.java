package com.mitrais.khotim.models;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class User
{
    public static final int ROLE_EMPLOYEE = 1;
    public static final int ROLE_ADMIN = 11;

    public int id;
    public String name;
    public String email;
    public String password;
    public int role_id;
    public long created_at;
    public int created_by;
    public long updated_at;
    public int updated_by;
    
    public User() {
        
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the role_id
     */
    public Integer getRole_id() {
        return role_id;
    }
    
    /**
     * @return the formatted role
     */
    public String getRole() {
        switch (this.role_id) {
            case ROLE_ADMIN:
                return "Admin";
            case ROLE_EMPLOYEE:
                return "Employee";
            default:
                return "Undefined role";
        }
    }

    /**
     * @return the formatted created_at
     */
    public String getCreatedAt() {
        return Instant.ofEpochSecond(this.created_at).atZone(ZoneId.of("GMT+7")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * @return the formatted created_by
     */
    public String getCreatedBy() {
        return String.valueOf(created_by);
    }
    
    /**
     * @return the formatted updated_at
     */
    public String getUpdatedAt() {
        return Instant.ofEpochSecond(this.updated_at).atZone(ZoneId.of("GMT+7")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * @return the formatted updated_by
     */
    public String getUpdatedBy() {
        return String.valueOf(updated_by);
    }
}
