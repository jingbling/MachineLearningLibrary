package wekaInterface;

import weka.clusterers.Clusterer;
import weka.clusterers.EM;
import weka.core.Instances;

/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 11/25/12
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class runWekaModelClusterer {

//    protected Clusterer clu = null;
    protected String cluOutput = "null";
    protected weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();


    public Clusterer EM(Instances wekaInputData, String[] classifierArgs) throws Exception {
        // Since wekaInputData still contains a class attribute, remove this before running clusterer
        filter.setAttributeIndices("" + (wekaInputData.classIndex() + 1));
        filter.setInputFormat(wekaInputData);
        Instances wekaInputClusterer = filter.useFilter(wekaInputData, filter);

        EM clu = new weka.clusterers.EM();
        for(int i=0;i<classifierArgs.length;i++) {
            System.out.println(classifierArgs[i]);
        }
        clu.setOptions(classifierArgs);

        System.out.println("completed set options, running clusterer");

        clu.buildClusterer(wekaInputClusterer);
//        System.out.println(cls);

        cluOutput = clu.toString();

        System.out.println("output of clusterer: " + cluOutput);

        return clu;

    }
}
