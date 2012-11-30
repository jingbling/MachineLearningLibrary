public class testLibJ48{ 
    public testLibJ48{ 
        //constructor - empty for now
    }
    public String RunTree(double sepallength, double sepalwidth, double petallength, double petalwidth) {
        String classifier;
        if (petalwidth <= 0.6) { 
            classifier = "Iris-setosa";
        } 
        if (petalwidth > 0.6) {
            if (petalwidth <= 1.7) {
                if (petallength <= 4.9) { 
                    classifier = "Iris-versicolor";
                } 
                if (petallength > 4.9) {
                    if (petalwidth <= 1.5) { 
                        classifier = "Iris-virginica";
                    } 
                    if (petalwidth > 1.5) { 
                        classifier = "Iris-versicolor";
                    } 
                }
            }
            if (petalwidth > 1.7) { 
                classifier = "Iris-virginica";
            } 
    }
}
