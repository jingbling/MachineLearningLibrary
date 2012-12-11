import mlParser.parseTree;
import utilities.createClassFile;
import weka.classifiers.Classifier;
import weka.core.Instances;
import wekaInterface.createWekaStructure;
import wekaInterface.runWeka;
import wekaInterface.runWekaModelClassifier;


/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 8:37 PM
 * Test file to create J48 tree classifier
 *  Example input: C:\Users\Jing\VMWareShare\ActDataSubject1_train.arff ActDataSubj1.java -testargs -1 -2
 */
public class runClassifier {

    public static String main(String[] args) throws Exception {

//        String fileInput = "WekaOutLargeNum.txt";
//        String fileInput = "WekaOutputStatesRandomTree.txt";
//        String fileOutput = "TreeClassifier.java";
        String fileInput="null";
        String fileOutput="null";
        String classifierName="null";
//        List<String> classifierArgs = new ArrayList<String>();
        String[] classifierArgs = new String[args.length-3];
        Instances wekaData=null;
        String wekaOutput="null";
        StringBuffer parsedTree;
        Classifier wekaClassifier;
//        StringBuffer createdTree;
        Integer icount=3;
        createWekaStructure WekaStructure = new createWekaStructure();
        runWekaModelClassifier CreateWekaClassifier = new runWekaModelClassifier();
        parseTree CreateParsedTree = new parseTree();
        createClassFile NewOutputCodeFile = new createClassFile();
        runWeka wekaClassifierInterface = new runWeka();
        try {
            // arg[0] = input file name
            fileInput = args[0].toString();
            fileOutput = args[1].toString();
            classifierName = args[2].toString();
            // save remaining arguments to pass into classifier call

//            System.out.println("Input args: "+args[0].toString()+" "+args[1].toString()+" "+args[2].toString());

            for (icount = 3; icount<=args.length-1; icount++) {
//                System.out.println("running for loop: "+icount.toString() + " of "+(args.length-3));
                classifierArgs[icount-3] = args[icount];
//                System.out.println("classifierArgs: " + classifierArgs[icount - 3].toString());
            }
        } catch (Exception e) {
            System.err.println("Error with input arguments");
//            usage();
            System.exit(1);
        }

        wekaData = WekaStructure.CreateWekaDataStructure(fileInput);

        wekaClassifier = CreateWekaClassifier.J48(wekaData, classifierArgs);

        parsedTree = CreateParsedTree.J48(wekaClassifier.toString());

//        System.out.println("parsedTree = " + parsedTree.toString());
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

//        NewOutputCodeFile.writeJavaFile(parsedTree, attributeArgs, fileOutput);

        // Test running classifier on example inputs  - assume that the inputs would be passed in by a call
        // This section is just a proof of concept.
        String[] sampleInput = {"576.337988","544.907779"};
        classifierName = wekaClassifierInterface.runClassifyInstance(wekaClassifier, wekaData, sampleInput);
        System.out.println("input classified as: " + classifierName);
        return classifierName;
    }

}
