import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Polynomial {
    String bReader;                     //declare String variable to intake bufferedreader
                            //create a Linked List of polynomials from file
    


    public Polynomial(String polynomialFile){
        //accepts and stores one polynomial in the same format as provided by input file
        //this.


    }

    public String readAllLinesWithStream(BufferedReader reader){
        return reader.lines()
            .collect(Collectors.joining(System.lineSeparator()));
    }



    // public static String toString(InputStream inputStream) {
    //     BufferedReader reader = new BufferedReader(
    //         new InputStreamReader(inputStream));
    //     return reader.lines().collect(Collectors.joining(
    //         System.getProperty("line.separator")));
    // }
    //     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    //         return reader.lines().collect(Collectors.joining(System.getProperty("line.separator")));
}
