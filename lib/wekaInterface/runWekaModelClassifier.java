package wekaInterface;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;

/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class runWekaModelClassifier {

    protected Classifier cls = null;
    protected String clsOutput = "null";
    public runWekaModelClassifier() {
    }

    public Classifier J48(Instances wekaInputData, String[] classifierArgs) throws Exception {
        // Depending on which classifier selected, run weka algorithms
        // classifierArgs contain additional options, such as incremental or batch
        cls = new weka.classifiers.trees.J48();

        cls.buildClassifier(wekaInputData);
//        System.out.println(cls);
        //System.out.println(classifierArgs);

        clsOutput = cls.toString();

        System.out.println("output of cls: " + clsOutput);

        return cls;

    }

    public Classifier RandomTree(Instances wekaInputData, String[] classifierArgs) throws Exception {
        // Depending on which classifier selected, run weka algorithms
        // classifierArgs contain additional options, such as incremental or batch
        cls = new RandomTree();

        cls.buildClassifier(wekaInputData);
//        System.out.println(cls);
        //System.out.println(classifierArgs);

        clsOutput = cls.toString();

        System.out.println("output of cls: " + clsOutput);

        return cls;

    }


}
