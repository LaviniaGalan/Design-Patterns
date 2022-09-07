package com.dp.domain;

import java.util.*;

public class PredictiveReportExclusiveDecorator extends PredictiveReport{
    PredictiveReport report;
    Map.Entry<String, Float> exclusivePrediction;

    public PredictiveReportExclusiveDecorator(PredictiveReport report) {
        super(report.getAnalysedRecord(), report.getNearestRecords());
        this.report = report;
    }

    @Override
    public List<Map.Entry<DiabetesRecord, Float>> getNearestRecords() {
        List<Map.Entry<DiabetesRecord, Float>> exclusiveClassRecords = new ArrayList<>();

        Map<String, Float> summary = createSummary();
        float max = 0; String predictedClass = "";

        for(Map.Entry<String, Float> summaryEntry: summary.entrySet()){
            if(summaryEntry.getValue() > max){
                max = summaryEntry.getValue();
                predictedClass = summaryEntry.getKey();
            }
            //System.out.println(summaryEntry.getValue());
        }
        exclusivePrediction = new AbstractMap.SimpleEntry<>(predictedClass, max);

        for(Map.Entry<DiabetesRecord, Float> record: nearestRecords){
            if(record.getKey().getDiabetesClass().equals(predictedClass)){
                exclusiveClassRecords.add(record);
            }
        }
        return exclusiveClassRecords;
    }

    @Override
    public String getDisplayedSummary(){
        return exclusivePrediction.getKey() + " - " + exclusivePrediction.getValue() + "%" +
                "    (Y = Diabetic, N = Non-Diabetic, P = Prone-Diabetic)\n";
    }

//    @Override
//    public String toString() {
//        String result = "Given data: \n";
//        result += analysedRecord + "\n";
//        result += "\nResults: \n";
//
//        List<Map.Entry<DiabetesRecord, Float>> records =  getNearestRecords();
//        for(Map.Entry<DiabetesRecord, Float> record: records){
//            result += record.getKey() + " | Distance = " + record.getValue() + "\n";
//        }
//        result += "\nSummary: \n";
//        result += getDisplayedSummary();
//
//        return result;
//    }
}
