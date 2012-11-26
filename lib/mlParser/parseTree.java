package mlParser;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 2:02 PM
 * Class for parsing various types of trees
 */
public class parseTree {

    public StringBuffer J48(String input) {
        // this function expects a string with the classfier output and is for parsing
        // J48 or random tree outputs
        String[] inputSplit = input.split("\n");
        StringBuffer modLine = new StringBuffer();
        Integer prevIfs=0, numIfs=0, isString=0;
        Integer i, counter=0,icount=0,lineStart=0;
        Integer endIndex=-1, stateIndexStart = -1;

        ArrayList BayesArrayList = new ArrayList();

//        System.out.println("classifier chosen: "+ classifier + " found = "+ TreeArrayList.contains(classifier));
//        System.out.println("inputSplit.length: "+ inputSplit.length );

        for (counter=2;counter<inputSplit.length;counter++) {
            //parse through classifier lines

//                System.out.println("inputSplit currently being checked: "+ inputSplit[counter] );

            if (inputSplit[counter].trim().isEmpty()) {
                // line is blank, do nothing
            }
            else {
                lineStart=0; //reset lineStart after end of each line read
                prevIfs = numIfs; // save previous number of tabs to determine how many open ifs to close
                numIfs=0; // reset numIfs after previous line read
                for (i=0; inputSplit[counter].substring(i,i+1).equals("|"); i=i+4) {
                    //                        System.out.println("found | at"+i);
                    numIfs=i/4+1;
                    lineStart=numIfs*4;
                }
                //                    System.out.println("numIfs: "+numIfs+"prevIfs: "+prevIfs+"lineStart:"+lineStart);

                // check for end of pruned tree, marked by "Number of Leaves  :"
                // Must be placed here after prevIfs and numIfs refreshed so cleanup will be correct
                if (inputSplit[counter].contains("Number of Leaves") || inputSplit[counter].contains("Size of the tree")) break;

                // Need to check to see if logic in the current line is checking a state value
                stateIndexStart = inputSplit[counter].indexOf(" = ");
                isString = 0;
//                    System.out.println("line to parse:"+line + "stateIndexStart: "+stateIndexStart);
                if (stateIndexStart > 0) {
                    // found = sign by itself, so insert another for boolean compare
                    inputSplit[counter] = new StringBuffer(inputSplit[counter]).insert(stateIndexStart + 1, "=").toString();
                    // Use first character after equals sign to determine if there is a number
                    if (inputSplit[counter].substring(stateIndexStart+3,stateIndexStart+5).contains("[0-9.]")){
                        //if number, leave as is, otherwise add quotes
                    } else {
                        isString = 1;
                        inputSplit[counter] = new StringBuffer(inputSplit[counter]).insert(stateIndexStart + 4, "\"").toString();
                    }

                }
                if (numIfs < prevIfs) {
                    // need to close out open ifs
                    for (icount=prevIfs;icount>numIfs;icount--) {
                        for (i=icount-1;i>0;i--){
                            modLine.append("    ");
                        }
                        modLine.append(String.format("}%n"));
                    }
                }
                if (numIfs > 0) {
                    for (i=numIfs;i>0;i--) {
                        //                            System.out.println("prepending line");
                        modLine.append("    ");
                    }
                }
                //                    System.out.println("modLine: "+modLine+"numIfs="+numIfs);
                // After check for tabs, add if
                modLine.append("if (");
                // search for either where : appears, if it does not, create open "if" statement
                endIndex = inputSplit[counter].indexOf(":");
                if (endIndex > 0) {
                    if (isString == 1) {
                        modLine.append(inputSplit[counter].substring(lineStart,endIndex).trim()+String.format("\") { %n"));
                    } else {
                        modLine.append(inputSplit[counter].substring(lineStart,endIndex).trim()+String.format(") { %n"));
                    }
                    i = endIndex+1;
                    endIndex = inputSplit[counter].indexOf('(');

                    // add tabs based on how many if statements in we are
                    for (icount = numIfs+1; icount > 0; icount--) {
                        modLine.append("    ");
                    }
//                        System.out.println("line to write:"+inputSplit[counter].substring(i,endIndex));

                    modLine.append("classifier = \""+inputSplit[counter].substring(i,endIndex).trim()+String.format("\";%n"));
                    // add more tabs for end
                    for (icount = numIfs; icount > 0; icount--) {
                        modLine.append("    ");
                    }
                    modLine.append(String.format("} %n"));
                }
                else {
                    if (isString == 1) {
                        modLine.append(inputSplit[counter].substring(lineStart).trim()+String.format("\") {%n"));
                    } else {
//                            System.out.println("trimmed: "+inputSplit[counter].substring(lineStart).trim());
                        modLine.append(inputSplit[counter].substring(lineStart).trim()+String.format(") {%n"));
                    }
                }

            }

        }
        return modLine;
    }
}
