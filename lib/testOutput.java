/**
 * Created with IntelliJ IDEA.
 * User: Jing
 * Date: 12/3/12
 * Time: 12:48 AM
 * Used to call and test output files
 */
public class testOutput {

    public static void main(String[] args) {
        // Call ActDataSubj1Cluster
        String output;
        ActDataSubj1Cluster testCluster = new ActDataSubj1Cluster();
        output = testCluster.RunClassifier(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        System.out.println("clusterer classify result: " + output);

        ActDataSubj1 testTree = new ActDataSubj1();
        output = testTree.RunClassifier(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        System.out.println("classifier classify result: " + output);

    }
}
