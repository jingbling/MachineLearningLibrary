package mlParser;

/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class parseEM {

    public StringBuffer GMM(String clustererToClass) {
        // Parse the EM output into classification code
        // This function expects a clusterer to class mapping at the end, and gaussian curve of each attribute for each cluster
        StringBuffer output = new StringBuffer();
        // Split input string to parse
        String[] inputSplit = clustererToClass.split("\n");
        String[] tempMeanLine, tempStdDevLine;
        int counter = 0, icounter=0,numClusters = 0, currPointer=0;

        // First define needed variables
        output.append("String classifier;"+String.format("%n"));
        output.append("Integer i = 0;"+String.format("%n"));
        output.append("Integer bestZMatchIndex = 0;"+String.format("%n"));

        // Step through output from clusterer
        for (counter=2;counter<inputSplit.length-1;counter++) {
//            System.out.println("Currently parsing "+counter+" of "+String.format("%d",inputSplit.length-1));
            if (inputSplit[counter].trim().isEmpty()) {
                // line is blank, do nothing
            } else {
                // First determine the number of clusters
                if (inputSplit[counter].contains("Number of clusters")) {
                    currPointer = inputSplit[counter].indexOf(":")+1;
                    numClusters = Integer.parseInt(inputSplit[counter].substring(currPointer).trim());
//                    System.out.println("Number of Clusters is "+numClusters);
                    // Setup initial parameters
                    output.append(String.format("int numClusters = %d;%n",numClusters));
                    output.append(String.format("String[] clusterClassMap = new String[numClusters];%n"));
                    output.append(String.format("double[] zValue = new double[numClusters];%n"));
                    output.append(String.format("double[] currMean = new double[numClusters];%n"));
                    output.append(String.format("double[] currStdDev = new double[numClusters];%n"));
                    output.append(String.format("int[] clusterMatch= new int[numClusters];%n%n"));
                }
                // Then build and evaluate the gaussian curves for each attribute
                if (inputSplit[counter+1].contains("mean")) {
//                    System.out.println("Mean found");
                    //if next line is mean, current line has attribute name, 3rd line has standard deviation
//                    System.out.println("Mean line is: "+inputSplit[counter+1]);
//                    System.out.println("StdDev line is: "+inputSplit[counter+2]);
                    tempMeanLine=inputSplit[counter+1].split("\\s+");
                    tempStdDevLine=inputSplit[counter+2].split("\\s+");
//                    System.out.println("size of mean line is:"+String.format("%d",tempMeanLine.length));
//                    System.out.println("size of StdDev line is:"+String.format("%d",tempStdDevLine.length));
                    for (icounter = 0;icounter<numClusters;icounter++) {
//                        System.out.println("Mean line split: "+tempMeanLine[icounter+2]);
                        output.append("currMean["+icounter+"]="+tempMeanLine[icounter+2]+String.format(";%n"));
                        output.append("currStdDev["+icounter+"]="+tempStdDevLine[icounter+3]+String.format(";%n"));
                    }
                    output.append("for (i=0;i<numClusters;i++) {"+String.format("%n"));
//                    System.out.println("attribute found: "+inputSplit[counter]);
                    output.append("    zValue[i] = Math.abs(("+inputSplit[counter].trim()+"-currMean[i])/(currStdDev[i]));" +String.format("%n"));
                    output.append(String.format("    //Check if current Z value is larger than best match so far %n"));
                    output.append(String.format("    if (zValue[i]<zValue[bestZMatchIndex]) %n"));
                    output.append(String.format("        bestZMatchIndex = i;%n"));
                    output.append(String.format("}%n"));
                    output.append(String.format("clusterMatch[bestZMatchIndex]++;%n"));
                    output.append(String.format("bestZMatchIndex=0;%n%n"));
                }
                // Save cluster map
                if (inputSplit[counter].contains("Cluster 0")) {
                    // save next few lines as mapping
                    for (icounter=0;icounter<numClusters;icounter++) {
                        // get index of <--
                        currPointer=inputSplit[counter+icounter].indexOf("<--")+3;
                        output.append(String.format("clusterClassMap[%d]=\"%s\";%n",icounter,inputSplit[counter+icounter].substring(currPointer).trim()));

                    }
                }


            }
        }

        // Return the best matched cluster for each attribute
        output.append(String.format("for (i=0;i<numClusters;i++) {%n"));
        output.append(String.format("    if (clusterMatch[i]>clusterMatch[bestZMatchIndex])%n"));
        output.append(String.format("        bestZMatchIndex = i;%n"));
        output.append(String.format("}%n"));
        output.append(String.format("classifier=clusterClassMap[bestZMatchIndex];%n"));
        output.append(String.format("return classifier;%n"));


        // Finally, return the class corresponding to that cluster

        return output;

    }
}
