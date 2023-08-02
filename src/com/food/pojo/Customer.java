package com.food.pojo;

/*
 * achiving encapsulation -> by creating private data members and generating accessor and mutator methods
 */

public class Customer {
    

    // data members for Customer
    private String ctr_Name;
    private String emailId;
    private String password;
    private String phoneno;
    private String address;
    
    
   
    public Customer() {
    }


    public Customer(String ctr_Name, String emailId, String password, String phoneno, String address) {
        this.ctr_Name = ctr_Name;
        this.emailId = emailId;
        this.password = password;
        this.phoneno = phoneno;
        this.address = address;
    }


    public String getCtr_Name() {
        return ctr_Name;
    }


    public void setCtr_Name(String ctr_Name) {
        this.ctr_Name = ctr_Name;
    }


    public String getEmailId() {
        return emailId;
    }


    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneno() {
        return phoneno;
    }


    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Customer [ctr_Name=" + ctr_Name + ", emailId=" + emailId + ", password=" + password + ", phoneno="
                + phoneno + ", address=" + address + "]";
    }
    
    
    
}
