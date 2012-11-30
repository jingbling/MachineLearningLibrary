package wekaInterface;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class createWekaStructure {

    public Instances CreateWekaDataStructure(String inputFile) {
        // This function is used to run the weka algorithms to generate a classifier output
        // Open input file and feed to Weka, accepted file types are arff,csv,xrff:
//        System.out.println("File type detected: " + FilenameUtils.getExtension(inputFile));
//        System.out.println("File to load: " + inputFile);
        Instances wekaData = null;
        try {
            ConverterUtils.DataSource wekaDataSource = new ConverterUtils.DataSource(inputFile);
            wekaData = wekaDataSource.getDataSet();
            // setting class attribute if the data format does not provide this information
            // For example, the XRFF format saves the class attribute information as well
            if (wekaData.classIndex() == -1)
                wekaData.setClassIndex(wekaData.numAttributes() - 1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return wekaData;

    }

    public String[] GetAttributeArgs(Instances wekaInstance) {
        String[] returnAttrArgs = new String[(wekaInstance.numAttributes()-1)*2];
        Integer jcount = 0, argcount=0;
        String[] currentSplitLine = new String[3];
//        Scanner s = null;
//        String currentLine = "";
        // Parse assuming last attribute is the class attribute
        for (jcount=0; jcount<wekaInstance.numAttributes()-1; jcount++) {
            argcount = jcount*2;
            //parse each attribute type
//            s = new Scanner(new BufferedReader(new StringReader(wekaInstance.attribute(jcount).toString())));
//            currentLine = s.nextLine();
            currentSplitLine = wekaInstance.attribute(jcount).toString().split(" ");
            // check third split for attribute type
            if (currentSplitLine[2].equals("relational")) {
                System.err.println("Error, currently relational attributes are not handled, will return as string");
                returnAttrArgs[argcount] = "String";
            } else if (currentSplitLine[2].equals("numeric")) {
                returnAttrArgs[argcount] = "double";
            } else if (currentSplitLine[2].equals("date")) {
                returnAttrArgs[argcount] = "Date";
            } else {
                // treat all others as string
                returnAttrArgs[argcount] = "String";
            }
            // next save attribute name
            returnAttrArgs[argcount+1]= currentSplitLine[1];

//            System.out.println("Attribute " + jcount + ": " + currentSplitLine[1]);
//            System.out.println("Try parsing " + currentSplitLine[2]);
//            System.out.println("Saved args: " + returnAttrArgs[argcount] + " " + returnAttrArgs[argcount+1]);
//            System.out.println("size return: " + (wekaInstance.numAttributes()-1)*2);
        }

        return returnAttrArgs;
    }

}
