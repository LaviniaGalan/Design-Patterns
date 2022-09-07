package com.dp.utils;

import com.dp.domain.DiabetesRecord;
import com.dp.domain.PredictiveReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReportToDataTableAdapter implements DataTable {

    PredictiveReport report;

    public ReportToDataTableAdapter(PredictiveReport report) {
        this.report = report;
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Gender", "Age", "Cholesterol", "Urea", "Creatinine", "BMI", "Class", "Distance");
    }

    @Override
    public List<List<String>> getRows() {
        List<Map.Entry<DiabetesRecord, Float>> records = report.getNearestRecords();

        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<DiabetesRecord, Float> record: records){
            List<String> row = new ArrayList<>();

            row.add(record.getKey().getGender());
            row.add(String.valueOf(record.getKey().getAge()));
            row.add(String.valueOf(record.getKey().getCholesterol()));
            row.add(String.valueOf(record.getKey().getUrea()));
            row.add(String.valueOf(record.getKey().getCreatinine()));
            row.add(String.valueOf(record.getKey().getBMI()));
            row.add(record.getKey().getDiabetesClass());
            row.add(String.valueOf(record.getValue()));
            result.add(row);
        }
        return result;
    }
}
