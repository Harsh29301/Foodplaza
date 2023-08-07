package com.food.daoimplement;

import com.food.dao.LoginDAO;
import com.food.pojo.Customer;

public class LoginDAOImpl implements LoginDAO{

    @Override
    public boolean signUp(Customer customer) {

        if(customer != null){
            return true;
        }        
        return false;
    }

    @Override
    public boolean signIn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signIn'");
    }
    
}
