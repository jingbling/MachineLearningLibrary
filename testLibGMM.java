public class testLibGMM{ 
    public testLibGMM{ 
        //constructor - empty for now
    }
    public String RunTree(double sepallength, double sepalwidth, double petallength, double petalwidth) {
        String classifier;
        Integer i = 0;
        Integer bestZMatchIndex = 0;
        Integer numClusters =3
        String[] clusterClassMap = new String[numClusters];
        Double[] zValue = new Double [numClusters];
        Double[] currMean = new Double [numClusters];
        Double[] currStdDev = new Double [numClusters];
        Integer[] clusterMatch= new Integer [numClusters];
        
        currMean[0]=5.9275;
        currStdDev[0]=0.4817;
        currMean[1]=5.006;
        currStdDev[1]=0.3489;
        currMean[2]=6.8085;
        currStdDev[2]=0.5339;
        for (i=0;i<numClusters;i++) {
            zValue[i] = Math.abs((sepallength-currMean[i])/(currStdDev[i]));
            //Check if current Z value is larger than best match so far 
            if (zValue[i]<zValue[bestZMatchIndex]) 
                bestZMatchIndex = i;
        }
        clusterMatch[bestZMatchIndex]++;
        bestZMatchIndex=0;
        
        currMean[0]=2.7503;
        currStdDev[0]=0.2956;
        currMean[1]=3.418;
        currStdDev[1]=0.3772;
        currMean[2]=3.0709;
        currStdDev[2]=0.2867;
        for (i=0;i<numClusters;i++) {
            zValue[i] = Math.abs((sepalwidth-currMean[i])/(currStdDev[i]));
            //Check if current Z value is larger than best match so far 
            if (zValue[i]<zValue[bestZMatchIndex]) 
                bestZMatchIndex = i;
        }
        clusterMatch[bestZMatchIndex]++;
        bestZMatchIndex=0;
        
        currMean[0]=4.4057;
        currStdDev[0]=0.5254;
        currMean[1]=1.464;
        currStdDev[1]=0.1718;
        currMean[2]=5.7233;
        currStdDev[2]=0.4991;
        for (i=0;i<numClusters;i++) {
            zValue[i] = Math.abs((petallength-currMean[i])/(currStdDev[i]));
            //Check if current Z value is larger than best match so far 
            if (zValue[i]<zValue[bestZMatchIndex]) 
                bestZMatchIndex = i;
        }
        clusterMatch[bestZMatchIndex]++;
        bestZMatchIndex=0;
        
        currMean[0]=1.4131;
        currStdDev[0]=0.2627;
        currMean[1]=0.244;
        currStdDev[1]=0.1061;
        currMean[2]=2.1055;
        currStdDev[2]=0.2456;
        for (i=0;i<numClusters;i++) {
            zValue[i] = Math.abs((petalwidth-currMean[i])/(currStdDev[i]));
            //Check if current Z value is larger than best match so far 
            if (zValue[i]<zValue[bestZMatchIndex]) 
                bestZMatchIndex = i;
        }
        clusterMatch[bestZMatchIndex]++;
        bestZMatchIndex=0;
        
        clusterClassMap[0]="Iris-versicolor";
        clusterClassMap[1]="Iris-setosa";
        clusterClassMap[2]="Iris-virginica";
        for (i=0;i<numClusters;i++) {
            if (clusterMatch[i]>clusterMatch[bestZMatchIndex])
                bestZMatchIndex = i;
        }
        classifier=clusterMatch[bestZMatchIndex];
        return classifier;
    }
}
