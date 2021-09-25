
import java.util.List;

/*
*Author : Teman Beck
*CMSC 350 Project 2
*Date : September 17th, 2021
*This class is a utility class which contains two overloaded implementations of a method named checkSorted.
*
*
*/



public class OrderedList {

    static List<Polynomial> list;
    
    public static<T extends Comparable<? super T>>boolean checkSorted(List<Polynomial> list){ 
        boolean isSorted = false;

        Polynomial orderedListCheckList = new Polynomial(list);
        
        return isSorted;
    }

    //override method to extend Comparable
    private static <T extends Comparable<? super T>> boolean checkSorted(List<Polynomial> list, Polynomial current) {

        return true;
    }
    
}
