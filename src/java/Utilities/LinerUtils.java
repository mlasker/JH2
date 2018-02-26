package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletContext;


public class LinerUtils {
    
    ArrayList<String> oneLiners = new ArrayList<String>();

    
    // Constructor given a path name that then reads in text file line by line
    // into instance variable arraylist
    
    public LinerUtils(String filePath){
        
            File file = new File(filePath);
            try { 
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    oneLiners.add(scan.nextLine());
                }
                
            }   catch (FileNotFoundException e) {
                
                }
    }
    
    public ArrayList<String> getLines() { 
        
        return this.oneLiners;
    }

}
