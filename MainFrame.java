import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;


/*
*Author : Teman Beck
*CMSC 350 Project 2
*Date : September 15th, 2021
*This class extends our GUI class and adds all relative Java Swing components to the GUI
*This class adds action listeners and functionality to the open file button.
*/

public class MainFrame extends JFrame {
    final static boolean shouldFill = true;
    JButton openFile;                                                               //declares openFile Jbutton
    private static List<Polynomial> polynomialList = new ArrayList<Polynomial>();   //declares a list of Polynomials



    
    

    public MainFrame(String title){
        super(title);                                                               //title from super class GUI


        openFile = new JButton("Select File");                                      //creates an open file button



        openFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == openFile){                                      //checks to see if openFile button was clicked
                    processPolynomialFile();                                        //calls method to process the file selected
                }
            }
        });

        this.add(openFile);                                                         //adds openFile action listener
    }

    public static boolean checkStrongOrder(List<Polynomial> polynomialList, Comparator<Polynomial> polynomialComparator){
        //boolean isStrongOrder = true;                                                //sets isStrongOrder default to true
        //System.out.println(polynomialList);
        Iterator<Polynomial> polyIter = polynomialList.iterator();
        Polynomial previous = polyIter.next();
        Polynomial current = polyIter.next();

        //System.out.println(previous);
        //System.out.println(current);

        while(polyIter.hasNext()){
            current = polyIter.next();
            
            
            if(previous.compareTo(current) > 0){
                return false;
            }
            previous = current;
        }
        return true;
       
        //use compareTo method
        
        
        //Polynomial previous = (Polynomial) polynomialList.iterator().;         //sets previous to the previous polynomial
        //System.out.println("value of previous : " + previous.toString());

        // for(int i = 1 ; polynomialList.size() > i; i++ ){                         //
            
        //     if(previous.compareTo(polynomialList.get(i)) < 0){              //calls previous polynomial and checks exponent
        //         isStrongOrder = false;                                               //sets isStrongOrder to false 
        //     }
        // }
        //return isStrongOrder;                                                        //returns result of isStrongOrder
    }   

    public static void processPolynomialFile(){
        try{
            ArrayList<String> theList = inputFromPolyFile();
            System.out.println("The contents currently of theList are  " + theList);

            for(String element: theList){                                                                   //iterates through the input theList
                Polynomial localPolynomial = new Polynomial(element);                                       //declares new polynomial element
                //System.out.println(localPolynomial);                                                      //prints to console the localPolynomial
                polynomialList.add(localPolynomial);                                                        //adds localPolynomial to theList
                //polynomialList.forEach(System.out::println);


            }
        }catch (InvalidPolynomialSyntax invalidPolySyntax){                                                 //catchs invalid polynomial syntax error
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), invalidPolySyntax.getMessage());      //messages comes from caller method
        }

        
        System.out.println("Strong ordered : " + checkStrongOrder(polynomialList, null));                   //returns boolean for checking strong order
        System.out.println("Weak ordered : " + OrderedList.checkSorted(polynomialList));                  //returns boolean for checking weak order
       

    }

    private static ArrayList<String> inputFromPolyFile() {                                                  //method to process the actionlistener being called from processPolynomialFile

        JFileChooser fileChooser = new JFileChooser();                                                      //creates new JFileChooser to allow file selection
        ArrayList<String> expressionList = new ArrayList<>();                                               //declares expressionList to an ArrayList

        int response = fileChooser.showOpenDialog(null);                                                    //checks response of click to see if the file open was clicked

        if(response ==JFileChooser.APPROVE_OPTION){   
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());                          //assigns file path to file var when file is selected
                        
                        //System.out.println("The program loaded : " + file);                               //prints file to console   

                try {
                    Scanner polyFile = new Scanner(file);                                                   //scanner to process input file
                    while(polyFile.hasNextLine()){                                          
                        String polyRow = polyFile.nextLine();                                               //assigns a row from polynomial file to polyRow


                                //pass in polyRow to polynomial to create it

                                //System.out.println("polyRow is " + polyRow);

                        expressionList.add(polyRow);                                                        //adds polyRow to expressionList

                                //System.out.println("This is the input from expressionList" + expressionList);
                    }
                    polyFile.close();                                                       //closes file to ensure no memory leaks

                    } catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(null, "File Not Found ",                  //JOptionPane popup
                            "Test 1", JOptionPane.ERROR_MESSAGE); 
                            
                    }
                        

                    }

        return expressionList;
    }
}

