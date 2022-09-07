package com.dp.repository;

import com.dp.domain.PredictiveReport;
import com.dp.repository.iterator.Iterator;

import java.util.ArrayList;
import java.util.List;

public class ReportsRepository implements IRepository<PredictiveReport> {

    List<PredictiveReport> allReports = new ArrayList<>();

    public ReportsRepository() {
    }

    public void save(PredictiveReport report){
        allReports.add(report);
    }

    @Override
    public Iterable<PredictiveReport> getAll(){
        return allReports;
    }

    public Iterator getIterator(){
        return new ReportsIterator();
    }

    private class ReportsIterator implements Iterator{
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < allReports.size();
        }

        @Override
        public Object getNext() {
            if(hasNext()){
                PredictiveReport report = allReports.get(currentIndex);
                currentIndex++;
                return report;
            }
            return null;
        }
    }
}
