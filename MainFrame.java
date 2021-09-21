import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;


/*Author : Teman Beck
*Date : September 15th, 2021
*This class extends our GUI class and adds all relative Java Swing components to the GUI
*/

public class MainFrame extends JFrame {
    final static boolean shouldFill = true;
    JButton openFile;
    List<String> polynomialList = new ArrayList<>();

    
    

    public MainFrame(String title){
        super(title);


        openFile = new JButton("Select File");                      //creates an open file button



        openFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == openFile){                                      //checks to see if openFile button was clicked
                    //polynomialList.inputFromPolyFile();
                    
                    JFileChooser fileChooser = new JFileChooser();                  //creates new JFileChooser to allow file selection
                    ArrayList<String> expressionList = new ArrayList<>();

                    int response = fileChooser.showOpenDialog(null);               //checks response of click to see if the file open was clicked

                    if(response ==JFileChooser.APPROVE_OPTION){   
                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());      //assigns file path to file var when file is selected
                        
                        System.out.println("The program loaded : " + file);                         //prints file to console   

                        try {
                            Scanner polyFile = new Scanner(file);
                            String polyRow = polyFile.nextLine();

                            System.out.println("polyFile scanned successfully" + polyFile);
                            System.out.println("polyRow is " + polyRow);

                            polyFile.close();

                        } catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(null, "File Not Found ",                  //JOptionPane popup
                            "Test 1", JOptionPane.ERROR_MESSAGE); 
                            
                        }

                        // try{
                        //     Scanner scannedFile = new Scanner(file);
                        //     if(file.isFile()){
                        //         while(scannedFile.hasNextLine()){
                        //             String polyExpression = scannedFile.nextLine();
                        //             expressionList.add(polyExpression);
                        //         }
                        
                        //         scannedFile.close();
                                                        
                        //     }
                        // }catch (FileNotFoundException e){
                        //     JOptionPane.showMessageDialog(null, "File Not Found ",                  //JOptionPane popup
                        //     "Test 1", JOptionPane.ERROR_MESSAGE);
                        // }

                    }
                                        
                }
            }
        });

        this.add(openFile);

                            
        
    
    
    // public void processPolyList(){
    //     try{
    //         ArrayList<String> theList = inputFromPolyFile();

    //         for(String element: theList){
    //             Polynomial p = new Polynomial(element);
    //             System.out.println(p);
    //   //          polynomialList.add(p);
    //         }
    //     }catch (InvalidPolynomialSyntax ips){
    //         JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ips.getMessage());
    //     }

    // }


    // public ArrayList<String> inputFromPolyFile(){
    //     JFileChooser fileChooser = new JFileChooser();                  //creates new JFileChooser to allow file selection
    //     ArrayList<String> expressionList = new ArrayList<>();

    //                 int response = fileChooser.showOpenDialog(null);               //checks response of click to see if the file open was clicked

    //                 if(response ==JFileChooser.APPROVE_OPTION){


    //                     File file = new File(fileChooser.getSelectedFile().getAbsolutePath());      //assigns file path to file var when file is selected
                        
    //                     System.out.println("The program loaded : " + file);                                                   //prints file to console

    //                     try{
    //                         Scanner scannedFile = new Scanner(file);
    //                         if(file.isFile()){
    //                             while(scannedFile.hasNextLine()){
    //                                 String polyExpression = scannedFile.nextLine();
    //                                 expressionList.add(polyExpression);
    //                             }

    //                             scannedFile.close();
                                
    //                         }
    //                     } catch (FileNotFoundException e){
    //                         JOptionPane.showMessageDialog(null, "File Not Found ",                  //JOptionPane popup
    //                     "Test 1", JOptionPane.ERROR_MESSAGE);
    //                     } 
                        
    //                     return expressionList;
    //                 }               
    // }
    }
}

