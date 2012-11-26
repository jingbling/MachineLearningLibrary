public class testLibJ48{ 
    public testLibJ48{ 
        //constructor - empty for now
    }
    public static String RunTree(string outlook, double temperature, double humidity, string windy) {
        if (outlook == "sunny") {
            if (humidity <= 75) { 
                classifier = "yes";
            } 
            if (humidity > 75) { 
                classifier = "no";
            } 
        }
        if (outlook == "overcast") { 
            classifier = "yes";
        } 
        if (outlook == "rainy") {
            if (windy == "TRUE") { 
                classifier = "no";
            } 
            if (windy == "FALSE") { 
                classifier = "yes";
            } 
        return classifier;
    }
}
