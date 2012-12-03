package utilities;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Scanner;


/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 1:26 PM
 * This class is used to create java code given the input code, filename, and desired input parameters to resulting
 * java class file.
 */
public class createClassFile {

    public void writeJavaFile (StringBuffer inputCode, String[] parameters, String outputFile) {
        Integer icount, i;
        Scanner s = null;
        String line = null;
        StringBuffer finalOutput = new StringBuffer();

        // First need to add tabs to input code before writing it to the file.
        // For now, need to add 2 tabs worth of spaces. Make this hardcoded for now.
        // At end, must prepend each line of generated tree with desired additional tabs
        // Check numTabs to determine number of tabs to prepend
        Integer numTabs = 2;
        try {
            s = new Scanner(new BufferedReader(new StringReader(inputCode.toString())));
            s.useDelimiter(",\\n");
            while (s.hasNext()) {
                line = s.nextLine();
                for (i=numTabs; i>0; i--) {
                    finalOutput.append("    ");
                }
                finalOutput.append(line+String.format("%n"));
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }

        // Once finalOutput created, generate separate java class for running classifier
        try{
            FileWriter fstream = new FileWriter(outputFile);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("public class "+ FilenameUtils.removeExtension(outputFile)+ String.format("{ %n"));
//            out.write("    public "+ FilenameUtils.removeExtension(outputFile)+ String.format("{ %n"));
//            out.write("        //constructor - empty for now"+String.format("%n    }%n"));
            out.write("    public String RunClassifier(");
            // Write parameters as variable declarations, expected in [type name] pairs
            // e.g. double length
            for (icount=0;icount<parameters.length; icount=icount+2) {
                // if this is the last pair, then don't add comma at the end
                if (icount == parameters.length-2){
                    out.write(parameters[icount]+" "+parameters[icount+1]);
                } else
                    out.write(parameters[icount]+" "+parameters[icount+1]+", ");
            }
            out.write(") {" + String.format("%n"));

            out.write(finalOutput.toString());
            out.write("    }"+String.format("%n"));
            out.write("}"+String.format("%n"));
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }
}
