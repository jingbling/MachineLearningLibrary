package wekaInterface;

import weka.classifiers.Classifier;
import weka.core.Instances;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 12/10/12
 * Time: 10:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class runWeka {
    public String runClassifyInstance (Classifier wekaClassifier, Instances wekaInstance, String[] values) throws IOException {
        String classResult;
        StringBuffer inputBuffer = new StringBuffer();
        int i = 0;

        // First create a string buffer that is readable by the classifier
        inputBuffer.append(String.format("@RELATION tempInput %n"));
        for (i=0; i<wekaInstance.numAttributes(); i++) {
            //save each attribute
            inputBuffer.append(String.format("%s %n",wekaInstance.attribute(i).toString()));
        }

        // Finally, append data
        inputBuffer.append(String.format("@DATA %n"));
        for (i=0; i<values.length; i++){
            inputBuffer.append(String.format("%s,", values[i]));
        }
        // for final classifier, append a blank without comma
        inputBuffer.append(String.format("? %n"));


        // run classifier based on stringbuffer

        System.out.println(" inputBuffer: " + inputBuffer.toString());

        InputStream tempStream = new ByteArrayInputStream(inputBuffer.toString().getBytes());
        // load unlabeled data
        Instances unlabeled = new Instances(new BufferedReader( new InputStreamReader(tempStream)));
        // set class attribute
        unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

        // create copy
        Instances labeled = new Instances(unlabeled);

        /* label instances - sample code for more than one instance, for our purposes assume only
         one instance needs labeling at a time */
//        for (i = 0; i < unlabeled.numInstances(); i++) {
//            double clsLabel = 0;
//            try {
//                clsLabel = wekaClassifier.classifyInstance(unlabeled.instance(i));
//            } catch (Exception e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//            labeled.instance(i).setClassValue(clsLabel);
//        }
        double clsLabel = 0;
        try {
            clsLabel = wekaClassifier.classifyInstance(unlabeled.instance(0));
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        labeled.instance(0).setClassValue(clsLabel);
        // output labeled data
        classResult = labeled.classAttribute().value((int) clsLabel);
        return classResult;
    }

}
