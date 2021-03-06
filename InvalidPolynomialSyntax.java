/*
*Author : Teman Beck
*CMSC 350 Project 2
Date : September 17th, 2021
*This class defines an unchecked exception that contains a constructor that allows a message to be supplied
*Thrown by the constructor of the Polynomial class should the supplied string contain coefficients or exponents of an improper type or 
*should the exponents fail to be listed in strictly descending order
*/

public class InvalidPolynomialSyntax extends RuntimeException {
    InvalidPolynomialSyntax(String msg){
        super(msg);                                                 //message supplied by calling method
    }

    
}