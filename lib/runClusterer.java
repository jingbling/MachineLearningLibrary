import mlParser.parseEM;
import utilities.createClassFile;
import weka.clusterers.Clusterer;
import weka.core.Instances;
import wekaInterface.createWekaStructure;
import wekaInterface.runWekaModelClusterer;

/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 9:09 PM
 * Test file to run cluster code generator
 *  Example input: C:\Users\Jing\VMWareShare\ActDataSubject1_train.arff ActDataSubj1Cluster.java -I 100 -N 3 -M 1.0E-6 -S 100
 *  or C:\Users\Jing\Documents\MSENGROL\_2012Fall_E299\WekaSource\data\iris.arff testLibGMM.java -I 100 -N 3 -M 1.0E-6 -S 100
        */
public class runClusterer {

    public static void main(String[] args) throws Exception {

//        String fileInput = "WekaOutLargeNum.txt";
//        String fileInput = "WekaOutputStatesRandomTree.txt";
//        String fileOutput = "TreeClassifier.java";
        String fileInput="null";
        String fileOutput="null";
        String classifierName="null";
//        List<String> classifierArgs = new ArrayList<String>();
        String[] classifierArgs = new String[args.length-2];
        Instances wekaData=null;
        String wekaOutput="null", wekaClustererToClassifier="null";
        StringBuffer parsedCode;
        Clusterer wekaClusterer;
//        StringBuffer createdTree;
        Integer icount=3;
        createWekaStructure WekaStructure = new createWekaStructure();
        runWekaModelClusterer CreateWekaClusterer = new runWekaModelClusterer();
        parseEM CreateParsedGMM = new parseEM();
        createClassFile NewOutputCodeFile = new createClassFile();

        try {
            // arg[0] = input file name
            fileInput = args[0].toString();
            fileOutput = args[1].toString();
            // save remaining arguments to pass into classifier call

//            System.out.println("Input args: "+args[0].toString()+" "+args[1].toString()+" "+args[2].toString());

            for (icount = 2; icount<=args.length-1; icount++) {
//                System.out.println("running for loop: "+icount.toString() + " of "+(args.length-3));
                classifierArgs[icount-2] = args[icount];
//                System.out.println("classifierArgs: " + classifierArgs[icount - 3].toString());
            }
        } catch (Exception e) {
            System.err.println("Error with input arguments");
//            usage();
            System.exit(1);
        }

        wekaData = WekaStructure.CreateWekaDataStructure(fileInput);

        wekaClusterer = CreateWekaClusterer.EM(wekaData, classifierArgs);

        wekaClustererToClassifier = CreateWekaClusterer.outputClassesToClustererEval(wekaClusterer,wekaData);

//        System.out.println("parsed Clusterer to Class = " + wekaClustererToClassifier);

        parsedCode = CreateParsedGMM.GMM(wekaClustererToClassifier);

//        System.out.println("parsedCode = " + parsedTree.toString());
//
//        // put some random args for now
        //String[] tempArgs = {"int","numLeaves","String","blah","double","somenumber"};
        String[] attributeArgs = WekaStructure.GetAttributeArgs(wekaData);
//
        // print attributes <debug>
//        System.out.println("Num attributes: " + wekaData.numAttributes());
//        for (icount = 0;icount < wekaData.numAttributes(); icount++) {
//            System.out.println("Attribute " + icount + ": " + wekaData.attribute(icount));
//        }
//        System.out.println("
//        System.out.println(", actual: " + wekaData.classAttribute().value((int) wekaData.instance(0).classValue()));

        NewOutputCodeFile.writeJavaFile(parsedCode, attributeArgs, fileOutput);

//
//        // Call subroutine to parse input file
//        createdTree = ParseTree(fileInput, 2);
//        System.out.println(createdTree);

        // Call subroutine to write output file
//        writeJavaFile(createdTree, args, fileOutput);

    }
}
