package com.healthcareapp.backend.Validations;

import com.healthcareapp.backend.Exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidationHelper {

    public void nameValidation(String name){
        if(name.length() > 250){
            throw new ValidationException("Too Long... Please enter another name");
        }
        for(int i=0; i<name.length(); i++){
            if( !( Character.isLetter(name.charAt(i)) || (name.charAt(i) == ' ') ) ){
                throw new ValidationException("Bad name with special characters... Please enter another name");
            }
        }
    }

    public void usernamePasswordValidation(String username){
        for(int i=0; i<username.length(); i++){
            char character = username.charAt(i);
            if(character == ' ' || character == '\'' || character == '-' || character == '<' || character == ',' || character == '+' || character == '>' || character == '(' || character == ')')
                throw new ValidationException("Entered username/password has invalid character of ', -, ,, +, >, <, (, )... Please enter another username/password");
        }
    }

    public void contactValidation(String contact){
        if(contact.length() != 10){
            throw new ValidationException("Contact number must be of length 10");
        }
        for(int i=0; i<contact.length(); i++){
            char character = contact.charAt(i);
            if(! (Character.isDigit(character)) ){
                throw new ValidationException("Contact number must contain only digits 0 to 9");
            }
        }
    }

    public void strValidation(String str){
        if(str.length() > 250){
            throw new ValidationException("Too Long... Please enter the field again");
        }
    }

    public void dateValidation(String date){
        if(date.length() != 10){
            throw new ValidationException("Date is always of length 10");
        }
        for(int i=0; i<4; i++){
            if( !Character.isDigit(date.charAt(i)) )
                throw new ValidationException("Please enter the date in the format YYYY-MM-DD");
        }

        if(date.charAt(4) != '-' || date.charAt(7) != '-')
            throw new ValidationException("Please enter the date in the format YYYY-MM-DD");

        if( !(Character.isDigit(date.charAt(5)) || Character.isDigit(date.charAt(6)) || Character.isDigit(date.charAt(8)) || Character.isDigit(date.charAt(9))) ){
            throw new ValidationException("Please enter the date in the format YYYY-MM-DD");
        }
    }

    public void pincodeValidation(int pincode){
        if(pincode >= 100000 && pincode <= 999999)
            return;
        throw new ValidationException("Pincode must be a 6 digit number with no leading 0's");
    }


    public void sexValidation(String sex){
        if(Objects.equals(sex, "Male") || Objects.equals(sex, "Female")){
            return;
        }
        throw new ValidationException("Sex can only be 'Male' or 'Female'");
    }
}
