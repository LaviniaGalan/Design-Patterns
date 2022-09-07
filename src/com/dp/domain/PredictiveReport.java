package com.dp.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictiveReport {
    DiabetesRecord analysedRecord;
    List<Map.Entry<DiabetesRecord, Float>> nearestRecords;

    public PredictiveReport(DiabetesRecord analysedRecord, List<Map.Entry<DiabetesRecord, Float>> nearestRecords) {
        this.analysedRecord = analysedRecord;
        this.nearestRecords = nearestRecords;
    }

    public Map<String, Float> createSummary(){
        Map<String, Float> summary = new HashMap<>();
        summary.put("N", 0f);
        summary.put("Y", 0f);
        summary.put("P", 0f);

        System.out.println(nearestRecords);
        for(Map.Entry<DiabetesRecord, Float> record: nearestRecords){
            float newValue = summary.get(record.getKey().getDiabetesClass()) + 1;
            summary.put(record.getKey().getDiabetesClass(), newValue);
        }

        int total = nearestRecords.size();
        for(String diabetesClass: summary.keySet()){
            summary.put(diabetesClass, summary.get(diabetesClass) * 100 / total);
        }

        return summary;
    }

    public DiabetesRecord getAnalysedRecord() {
        return analysedRecord;
    }

    public List<Map.Entry<DiabetesRecord, Float>> getNearestRecords() {
        return nearestRecords;
    }

    public String getDisplayedSummary(){
        String result = "";

        Map<String, Float> summary = createSummary();
        for(Map.Entry<String, Float> entry: summary.entrySet()){
            result += entry.getKey() + " - " + entry.getValue() + "%\n";
        }
        result +="    (Y = Diabetic, N = Non-Diabetic, P = Prone-Diabetic)\n";

        return result;
    }

    @Override
    public String toString() {
        String result = "Given data: \n";
        result += analysedRecord + "\n";
        result += "\nResults: \n";

        for(Map.Entry<DiabetesRecord, Float> record: getNearestRecords()){
            result += record.getKey() + " | Distance = " + record.getValue() + "\n";
        }

        result += "\nSummary: \n";
        result += getDisplayedSummary();

        return result;
    }

    //System.out.println("   (Y = Diabetic, N = Non-Diabetic, P = Predict-Diabetic)");
}
