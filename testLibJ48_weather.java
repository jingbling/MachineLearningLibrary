public class testLibJ48_weather{ 
    public testLibJ48_weather{ 
        //constructor - empty for now
    }
    public String RunTree(String outlook, double temperature, double humidity, String windy) {
        String classifier;
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
    }
}
