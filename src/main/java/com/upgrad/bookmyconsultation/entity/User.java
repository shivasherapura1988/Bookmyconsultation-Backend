package com.upgrad.bookmyconsultation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;




//Mark it with Data, Entity, Builder, NoArgsConstructor, AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//create a class named User
    public class User {


    //create firstName of type String
    private String firstName;
    //create lastName of type String
    private String lastName;
    //create dob of type String
    private String dob;
    //create mobile of type String
    private String mobile;
    @Id
    //create primary key 'emailId' of type String
    public  String emailId;
    //create password of type String
    private String password;
    //create createdDate of type String
    private String createdDate;
    //create salt of type String
    private String salt;
    //all the mentioned members must be private
}