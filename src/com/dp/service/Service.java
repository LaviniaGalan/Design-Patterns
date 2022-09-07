package com.dp.service;

import com.dp.domain.DiabetesRecord;
import com.dp.domain.PredictiveReport;
import com.dp.domain.PredictiveReportExclusiveDecorator;
import com.dp.repository.DiabetesRecordRepository;
import com.dp.repository.ReportsRepository;
import com.dp.repository.iterator.Iterator;
import com.dp.service.strategy.DistanceCalculator;
import com.dp.service.strategy.EuclideanDistanceCalculator;
import com.dp.service.strategy.ManhattanDistanceCalculator;
import com.dp.utils.HTMLTableFormatter;
import com.dp.utils.ReportToDataTableAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    DiabetesRecordRepository recordRepository;
    ReportsRepository reportsRepository;

    static int K = 10;

    public Service(DiabetesRecordRepository recordRepository, ReportsRepository reportsRepository) {
        this.recordRepository = recordRepository;
        this.reportsRepository = reportsRepository;
    }

    public PredictiveReport getPredictedReport(String inputType, String gender, int age, float urea, int creatinine, float cholesterol, float BMI, String reportType){
        DiabetesRecord record = new DiabetesRecord.RecordBuilder()
                .setGender(gender)
                .setAge(age)
                .setUrea(urea)
                .setCreatinine(creatinine)
                .setCholesterol(cholesterol)
                .setBMI(BMI)
                .build();

        List<Map.Entry<DiabetesRecord, Float>> kNearestNeighbours = getKNearestNeighbours(inputType, record);

        PredictiveReport report = new PredictiveReport(record, kNearestNeighbours);
        if(reportType.equalsIgnoreCase("EXCLUSIVE")){
            report = new PredictiveReportExclusiveDecorator(report);
        }
        reportsRepository.save(report);
        return report;
    }

    public List<Map.Entry<DiabetesRecord, Float>> getKNearestNeighbours(String inputType, DiabetesRecord record){
        List<DiabetesRecord> allRecords = (List<DiabetesRecord>) recordRepository.getAll();
        Map<DiabetesRecord, Float> distancesMap = new HashMap<>();

        DistanceCalculator distanceCalculator;
        if(inputType.equalsIgnoreCase("EXTENDED")){
            distanceCalculator = new ManhattanDistanceCalculator();
        }
        else{
            distanceCalculator = new EuclideanDistanceCalculator();
        }

        for(DiabetesRecord r: allRecords){
            distancesMap.put(r, distanceCalculator.computeDistance(r, record));
        }

        LinkedHashMap<DiabetesRecord,Float> sortedMap = distancesMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        List<Map.Entry<DiabetesRecord, Float>> kNearestNeighbours = new ArrayList<>();
        int i = 0;
        for(Map.Entry<DiabetesRecord, Float> entry: sortedMap.entrySet()){
            kNearestNeighbours.add(entry);
            i++;
            if(i == K){
                break;
            }
        }

        return kNearestNeighbours;
    }

    public Iterator getReportsIterator(){
        return reportsRepository.getIterator();
    }

    public void saveReport(String fileName, PredictiveReport report){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.append("FOR " + report.getAnalysedRecord().toString() + "\n");

            writer.append(HTMLTableFormatter.format(new ReportToDataTableAdapter(report)));
            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
