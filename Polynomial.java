import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
*Author : Teman Beck
*CMSC 350 Project 2
*Date : September 15th, 2021
*This class defines our Polynomial class.
*This class contains a static term class used to build our polynomials
*
*
*/


public class Polynomial implements Iterable<Polynomial.Term>, Comparable<Polynomial> {
    Comparator<Polynomial> compare;
    private Term head = null;                                                                           //declares head variable to hold inner class Term 
    private Term previous;                                                                              //declares variable to hold previous term
    private String string;                                                                              //defines a piece of a polynomial
    private LinkedList<Polynomial.Term> polyBuilder = new LinkedList<>();                               //will hold pieces of polynomial or terms
    private String fromFile;
    private List<Polynomial> list;
    

    public Polynomial(List<Polynomial> list){
        this.list = list;




    }

    public Polynomial(String fromFile){
        this.fromFile = fromFile;                                                                       //assigns fromFile to local fromFile
        //System.out.println("fromFile is currently :" + fromFile );
        //thePolynomials.add(fromFile);
        // System.out.println("The poly list from constructor is :  " + thePolynomials);

        head = null;                                                                                    //sets head to null
        Scanner termScanner = new Scanner(fromFile);
        try{
            while(termScanner.hasNext()){
                String first = termScanner.next();
                String second = termScanner.next();
                //System.out.println("polybuilder test add : " + polyBuilder);
                addTerm(Double.valueOf(first), Integer.valueOf(second));

            } 
            termScanner.close();                                                                        //closes termScanner to prevent memory leaks
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            throw new InvalidPolynomialSyntax("Incorrect Syntax, check file input again.");             //calls InvalidPolynomialSyntax method with supplied message
        }  
    }

    public void addTerm(double coefficient, int exponent){
        if(exponent < 0){                                                                               //checks to see if exponent is less than 0
            throw new InvalidPolynomialSyntax("You have entered a negative exponent. Check inputs for valid syntax.");      //calls InvalidPolynomialSyntax method with supplied message
        }
        Term current = head;                                                                            //assigns current the present value of head - null
        //System.out.println(" current is equal to : " + current);
        //System.out.println(" head is equal to : " + head);
        


        if(current == null){
            current = new Term(coefficient, exponent);                                                  //sets the current var to new Term
            //System.out.println("current equals : " + current);
            if(previous != null && current.getExponent() > previous.getExponent()){
                throw new InvalidPolynomialSyntax("Incorrect Syntax, polynomials are not in the proper descending order.");
            }
             polyBuilder.add(current);                                                                  //adds head to the polyBuilder LinkedList
                                                                                 
            System.out.println("polybuilder to console in required format : " + polyBuilder);           //test to see current polyBuilder LinkedList to console
        }

        previous = current;
        //System.out.println("previous equals : " + previous);
        
    }

    
   

    public int compareExponents(Polynomial comparingPolynomial){
        Term thisPolyTerm = this.head;
        Term otherPolyTerm = comparingPolynomial.head;
        while(thisPolyTerm != null && otherPolyTerm != null){
            if(thisPolyTerm.getExponent() != otherPolyTerm.getExponent()){
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();    
            }else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext(); 
            }
        }
        if(thisPolyTerm ==null && otherPolyTerm ==null){
            return 0;
        }
        if(otherPolyTerm == null){
            return +1;
        }else {
            return -1;
        }
    }


    


    @Override
    public Iterator<Polynomial.Term> iterator() {
        return new IteratorImplementation();   
    
    }  


    @Override
    public String toString() {
        StringBuilder expressionBuilder = new StringBuilder();
        
        if (head.coefficient > 0){
            expressionBuilder.append(head.toString());
        }else{
            expressionBuilder.append(" - ").append(head.toString());
        
        for(Term temporaryTerm = head.next; temporaryTerm != null; temporaryTerm = temporaryTerm.next) {
            if (temporaryTerm.coefficient < 0) {
                expressionBuilder.append(" - ").append(temporaryTerm.toString());
            } else {
                expressionBuilder.append(" + ").append(temporaryTerm.toString());
            }
        }
        string = expressionBuilder.toString();
        //return string;
        }
        
        return string;       
    }


     private final class IteratorImplementation implements Iterator<Polynomial.Term> {
        private Term current = getHead();

        @Override
        public Term next(){
        Term data = current;
        current = current.next;
      
        return data;
        }

        @Override
        public boolean hasNext(){
            return current != null && current.getNext() != null;
        }
    }

    class Term{
        private double coefficient;
        private int exponent;
        private Term next;
                                
        private Term(double c, int e) {
            coefficient = c;
            exponent = e;
            next = null;                                                //sets next to null
        }

        private int getExponent(){
            return this.exponent;
        }
        private double getCoefficient(){
            return this.coefficient;
        }
        private Term getNext(){
            return next;
        }

        @Override
        public String toString(){
        String termToString = String.format("%.1f", Math.abs(coefficient));
            if (exponent == 0) {                                                //no variable
                return termToString;
            } else if(exponent == 1){                                           // do not display exponent
                return termToString + "x";
            } else {                                                             // display exponent after variable
                return termToString + "x^" + exponent;
            }
        }
    }
        
        private Term getHead() {
            return head;
        }

        

        @Override
        public int compareTo(Polynomial one) {
            Term firstPolyToCompare = this.head;
            Term otherPolyToCompare = one.head;

            while(firstPolyToCompare != null && otherPolyToCompare != null){
                if(firstPolyToCompare.getExponent() != otherPolyToCompare.getExponent()){
                    return firstPolyToCompare.getExponent() - otherPolyToCompare.getExponent();
                } else if(firstPolyToCompare.getCoefficient() != otherPolyToCompare.getCoefficient()){
                    if(firstPolyToCompare.getCoefficient() > otherPolyToCompare.getCoefficient()){
                        return -1;
                    }else if(firstPolyToCompare.getCoefficient() < otherPolyToCompare.getCoefficient()){
                        return +1;
                    }
                }
                firstPolyToCompare = firstPolyToCompare.getNext();
                otherPolyToCompare = otherPolyToCompare.getNext();

                if(firstPolyToCompare == null && otherPolyToCompare == null){
                    return 0;
                }

                if(firstPolyToCompare == null){
                    return -1;
                }else{
                    return +1;
                }
            }
            return 0;
            
        }
}
