public class ActDataSubj1Cluster{ 
    public String RunClassifier(double hipAccelSensor1, double hipAccelSensor2) {
        String classifier;
        Integer i = 0;
        Integer bestZMatchIndex = 0;
        int numClusters = 3;
        String[] clusterClassMap = new String[numClusters];
        double[] zValue = new double[numClusters];
        double[] currMean = new double[numClusters];
        double[] currStdDev = new double[numClusters];
        int[] clusterMatch= new int[numClusters];
        
        currMean[0]=568.7746;
        currStdDev[0]=36.8415;
        currMean[1]=614.0827;
        currStdDev[1]=102.9915;
        currMean[2]=597.9143;
        currStdDev[2]=16.9981;
        for (i=0;i<numClusters;i++) {
            zValue[i] = Math.abs((hipAccelSensor1-currMean[i])/(currStdDev[i]));
            //Check if current Z value is larger than best match so far 
            if (zValue[i]<zValue[bestZMatchIndex]) 
                bestZMatchIndex = i;
        }
        clusterMatch[bestZMatchIndex]++;
        bestZMatchIndex=0;
        
        currMean[0]=497.6253;
        currStdDev[0]=8.2496;
        currMean[1]=517.4901;
        currStdDev[1]=31.361;
        currMean[2]=520.4543;
        currStdDev[2]=24.7052;
        for (i=0;i<numClusters;i++) {
            zValue[i] = Math.abs((hipAccelSensor2-currMean[i])/(currStdDev[i]));
            //Check if current Z value is larger than best match so far 
            if (zValue[i]<zValue[bestZMatchIndex]) 
                bestZMatchIndex = i;
        }
        clusterMatch[bestZMatchIndex]++;
        bestZMatchIndex=0;
        
        clusterClassMap[0]="standing";
        clusterClassMap[1]="running";
        clusterClassMap[2]="walking";
        for (i=0;i<numClusters;i++) {
            if (clusterMatch[i]>clusterMatch[bestZMatchIndex])
                bestZMatchIndex = i;
        }
        classifier=clusterClassMap[bestZMatchIndex];
        return classifier;
    }
}
