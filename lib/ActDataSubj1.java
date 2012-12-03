public class ActDataSubj1{ 
    public String RunClassifier(double hipAccelSensor1, double hipAccelSensor2) {
        String classifier= null;
        if (hipAccelSensor2 <= 516.757877) {
            if (hipAccelSensor1 <= 526.807639) { 
                classifier = "running";
            } 
            if (hipAccelSensor1 > 526.807639) {
                if (hipAccelSensor1 <= 634.151833) {
                    if (hipAccelSensor2 <= 468.422971) { 
                        classifier = "running";
                    } 
                    if (hipAccelSensor2 > 468.422971) {
                        if (hipAccelSensor2 <= 510.235417) { 
                            classifier = "standing";
                        } 
                        if (hipAccelSensor2 > 510.235417) {
                            if (hipAccelSensor1 <= 577.939759) {
                                if (hipAccelSensor2 <= 516.028117) { 
                                    classifier = "running";
                                } 
                                if (hipAccelSensor2 > 516.028117) { 
                                    classifier = "standing";
                                } 
                            }
                            if (hipAccelSensor1 > 577.939759) { 
                                classifier = "standing";
                            } 
                        }
                    }
                }
                if (hipAccelSensor1 > 634.151833) {
                    if (hipAccelSensor1 <= 693.83712) {
                        if (hipAccelSensor1 <= 666.913705) { 
                            classifier = "running";
                        } 
                        if (hipAccelSensor1 > 666.913705) {
                            if (hipAccelSensor2 <= 491.700557) { 
                                classifier = "walking";
                            } 
                            if (hipAccelSensor2 > 491.700557) {
                                if (hipAccelSensor1 <= 672.307289) { 
                                    classifier = "walking";
                                } 
                                if (hipAccelSensor1 > 672.307289) { 
                                    classifier = "running";
                                } 
                            }
                        }
                    }
                    if (hipAccelSensor1 > 693.83712) { 
                        classifier = "running";
                    } 
                }
            }
        }
        if (hipAccelSensor2 > 516.757877) {
            if (hipAccelSensor1 <= 627.137439) {
                if (hipAccelSensor1 <= 539.089181) { 
                    classifier = "running";
                } 
                if (hipAccelSensor1 > 539.089181) { 
                    classifier = "walking";
                } 
            }
            if (hipAccelSensor1 > 627.137439) {
                if (hipAccelSensor1 <= 660.158022) {
                    if (hipAccelSensor2 <= 546.717342) {
                        if (hipAccelSensor2 <= 535.198704) {
                            if (hipAccelSensor2 <= 527.650225) {
                                if (hipAccelSensor2 <= 517.756423) { 
                                    classifier = "running";
                                } 
                                if (hipAccelSensor2 > 517.756423) { 
                                    classifier = "walking";
                                } 
                            }
                            if (hipAccelSensor2 > 527.650225) { 
                                classifier = "running";
                            } 
                        }
                        if (hipAccelSensor2 > 535.198704) { 
                            classifier = "walking";
                        } 
                    }
                    if (hipAccelSensor2 > 546.717342) { 
                        classifier = "running";
                    } 
                }
                if (hipAccelSensor1 > 660.158022) { 
                    classifier = "running";
                } 
            }
        }
        return classifier;
    }
}
