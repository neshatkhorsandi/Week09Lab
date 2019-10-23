package models;

import java.io.Serializable;

public class User implements Serializable
{
    boolean active;
    String email;
    String fname;
    String lname;
    String password;

    Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    
    
    public User() 
    {
        active=true;
    }
    
    public User(String email, String fname, String lname, String password, Role role) 
    {
      this.active= true;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.role = role;
    }

    public boolean isActive() 
    {
        return active;
    }

    public void setActive(boolean active) 
    {
        this.active = active;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getFname() 
    {
        return fname;
    }

    public void setFname(String fname) 
    {
        this.fname = fname;
    }

    public String getLname() 
    {
        return lname;
    }

    public void setLname(String lname) 
    {
        this.lname = lname;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

  @Override
  public String toString() {
    return "User{" + "active=" + active + ", email=" + email + ", fname=" + fname + ", lname=" + lname + ", password=" + password + '}';
  }
    
}
